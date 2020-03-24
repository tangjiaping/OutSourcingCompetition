package cn.hhuc.service.heatmap;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Iterator;

public class DataFromCluster {

    public Iterator getFromKafka(KafkaConsumer kafkaConsumer){
        ConsumerRecords records = kafkaConsumer.poll(Duration.ofSeconds(1));
        if (records.isEmpty()){
            return null;
        }else {
            return records.iterator();
        }
    }
}
