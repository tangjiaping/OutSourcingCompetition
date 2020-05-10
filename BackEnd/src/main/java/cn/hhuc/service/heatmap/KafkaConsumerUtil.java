package cn.hhuc.service.heatmap;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;


/**
 * 该类是一个工具类，用来提供kafka消费者的配置信息
 */
public class KafkaConsumerUtil {

    public static KafkaConsumer getKafkaConsumer(String server,String topic,String group){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",server);
//        properties.put("group.id","tencent");
        properties.put("group.id",group);
        properties.put("auto.offset.reset","latest");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("session.timeout.ms","30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        return kafkaConsumer;
    }
}
