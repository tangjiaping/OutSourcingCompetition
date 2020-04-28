package hhuc.cn.senddatautil

import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

/**
 * kafkaProducerUtil工具类
 */
object KafkaProducerUtil {

  // 编写kafka配置文件
  val prop = new Properties()
  prop.put("bootstrap.servers", "122.51.19.184:9092")
  prop.put("acks", "1")
  prop.put("retries", "3")
  prop.put("key.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  prop.put("value.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")

  // 构造kafkaproducer
  val kafkaProducer = new KafkaProducer[String, String](prop)

  // 该工具类对send方法进行封装
  def send(key:String,value:String,topic:String = "TJPtopic") :Unit = {

    // 调用生成者的发送方法进行消息发送
    kafkaProducer.send(new ProducerRecord[String,String](topic,key,value),new Callback {
      override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
        if (recordMetadata != null){
          println(value + "-----发送成功")
        }else if (e != null){
          println(value + "-----发送失败")
        }
      }
    })
  }

  // 发送消息方法重载
  def send(value:String,topic:String) :Unit = {
    send(null,value,topic)
  }
}
