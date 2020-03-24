package hhuc.cn.realtimecalculation

import java.util.Properties

import hhuc.cn.senddatautil.KafkaProducerUtil
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object StatisticalDensity{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("statisticalDensity").setMaster("local[*]")
    val context = new SparkContext(conf)
    context.setLogLevel("ERROR")


    // 创建StatisticalDensity对象
    val statisticalDensity = new StatisticalDensity(context, 5)

    // 获取StatisticalDensity对象的StreamingContext
    val streamingContext: StreamingContext = statisticalDensity.streamingContext

    // 调用StatisticalDensity对象的创建输出流方法
    val stream: ReceiverInputDStream[(String, String)] = statisticalDensity.createStream("192.168.2.121:2181", "TJP", "initdata", 1)
//    val stream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(streamingContext, "192.168.2.121:2181", "TJPgroup", Map("initdata" -> 2))

    // 实时计算
    statisticalDensity.calculationDensity(stream)

    // 开启
    streamingContext.start()

    // 阻塞，一直运行
    streamingContext.awaitTermination()
  }
}

/**
 * 人群密度计算类
 * @param context  sparkContext上下文
 * @param interviewTime  实时计算间隔时间
 */
class StatisticalDensity(context: SparkContext,interviewTime:Int) {
  private val sparkContext: SparkContext = context

  // 通过context，指定间隔时间，创建StreamingContext环境
  val streamingContext = new StreamingContext(sparkContext, Seconds(interviewTime))

  /**
   * 创建receiverInputDStream输出流，用来读取kafka集群中的数据
   * @param zkQuorum 指定zookeeper路径
   * @param groupId 指定消费者组
   * @param topics 指定消费者主题
   * @param partition 指定消费主题分区
   * @return 返回receiverInputDStream
   */
  def createStream(zkQuorum:String,groupId:String,topics:String,partition:Int) :ReceiverInputDStream[(String,String)] = {
    println("start")

    // 调用KafkaUtils的createStream方法创建输出流
    val kafkaStream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(streamingContext, zkQuorum, groupId, Map(topics -> partition))

    println("end")

    return kafkaStream
  }

  /**
   * 用来实时计算人群密度并将计算结果发送到kafka消息队列
   * @param kafkaStream kafka输出流
   */
  def calculationDensity(kafkaStream:ReceiverInputDStream[(String,String)]) : Unit = {
    println("enter calculation")

    /**
     * 调用flatMap扁平化函数，将一行一行的信息切割成单个单词
     * 由于kafka消息是以key-value形式存储，而value才是我们要的信息
     * （key,"time IMSI phone lng lat"）
     * 扁平化后,返回字符数组
     * time imsi phone lng lat
     * 取出lng lat，将其封装成tuple(lng,lat)
     * 由于要计数，故使用map将其结构变成((lng,lat),1)
     * 使用reduceByKey进行聚合
     */

    val lnglatTuple: DStream[Tuple1[(String, String)]] = kafkaStream.flatMap(line => {
      val words: Array[String] = line._2.split(" ")
      val tuple: Tuple1[(String, String)] = Tuple1(words(3), words(4))
      val array: Array[Tuple1[(String, String)]] = Array(tuple)
      array
    })

    val lnglatMap: DStream[(Tuple1[(String, String)], Int)] = lnglatTuple.map((_, 1))

    val resDStream: DStream[(Tuple1[(String, String)], Int)] = lnglatMap.reduceByKey(_+_)

    /**
     * 取出lng,lat,num将其组成一个字符串，然后通过kafka生成者发送
     */

    var res : String = ""

    resDStream.foreachRDD(tupleRDD => {
      tupleRDD.foreach(tuple => {
        res = tuple._1._1._1 + " " + tuple._1._1._2 + " " + tuple._2
    //  println(res)

        // 通过自定义kafkaProducerUtil工具类将统计结果发送到kafka消息队列中
        KafkaProducerUtil.send(res,"tjp")
      })
    })
  }
}
