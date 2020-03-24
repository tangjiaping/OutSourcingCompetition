package hhuc.cn.sentdata

import java.text.SimpleDateFormat
import java.util
import java.util.Properties

import hhuc.cn.loadData.MappingData
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}


object SentDataToKafka {
  def main(args: Array[String]): Unit = {
    // 构造kafka配置文件信息
    val prop = new Properties()
    prop.put("bootstrap.servers", "192.168.2.121:9092")
    prop.put("acks", "1")
    prop.put("retries", "3")
    prop.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    prop.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")


    val dataToKafkaProducer = new SentDataToKafka(prop)
    val mappingData = new MappingData("cleardata")
    val dateFormat = new SimpleDateFormat("HHmmss")

    /**
     * 死循环，每个一秒查询一次信息
     */
    while (true){
      // 获取当前时间戳
      val currentTime: Long = System.currentTimeMillis()
      // 截取时间戳的时分秒
      val time: String = dateFormat.format(currentTime)
      // 调用MappingData中的loadDatasToIterator()方法，传入要操作的表和当前时间
      val iterator: util.Iterator[String] = mappingData.loadDatasToIterator("initdata", currentTime)
      // 遍历结果
      while (iterator != null && iterator.hasNext){
        // 获取要发送的message
        val message: String = iterator.next()
        // 调用生产者的方法发送信息
        dataToKafkaProducer.sendData("initdata",message)
      }
      // 发送信息后判断当前时间是否和信息发送前得到的时间相等，如果相等则为同一时刻，进入循环阻塞。否则为下一秒，继续执行查询语句
      while (dateFormat.format(System.currentTimeMillis()) == time){}
    }
  }

}

/**
 * 该类的作用是将查询的信息发送到kafka集群
 * @param kafkaProp kafka配置文件
 */
class SentDataToKafka(kafkaProp:Properties) {
  private val prop = kafkaProp
  private val producer:KafkaProducer[String, String] = new KafkaProducer[String, String](prop)

  /**
   * 发送信息方法
   * @param topic 要发送到的主题
   * @param value 要发送的信息
   */
  def sendData(topic: String,value: String): Unit = {
    sendData(topic,null,value)
  }

  /**
   * 发送信息方法
   * @param topic 要发送到的主题
   * @param key 要发送的key
   * @param value 要发送的value
   */
  def sendData(topic: String, key: String, value: String): Unit = {
    // 构造ProducerRecord
    var record:ProducerRecord[String,String] = null;

    // 判断是否有key
    if (key == null) {
      record = new ProducerRecord[String, String](topic, value)
    }else{
      record = new ProducerRecord[String, String](topic,key, value)
    }

    /**
     * 调用producer的send()方法发送信息，回调函数用来判断信息是否成功发送
     */
    producer.send(record,new Callback {
      override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
        if (metadata != null){
          println(value + "-----发送成功")
        }else if (exception != null){
          println(value + "-----发送失败")
        }
      }
    })
  }
}
