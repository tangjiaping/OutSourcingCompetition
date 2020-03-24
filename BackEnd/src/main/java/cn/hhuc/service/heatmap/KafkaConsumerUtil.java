package cn.hhuc.service.heatmap;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerUtil {

    public static KafkaConsumer getKafkaConsumer(String server,String topic){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",server);
        properties.put("group.id","headmap");
        properties.put("auto.offset.reset","latest");
        properties.put("auto.commit.interval.ms","10000");
        properties.put("session.timeout.ms","30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        return kafkaConsumer;
    }
}
