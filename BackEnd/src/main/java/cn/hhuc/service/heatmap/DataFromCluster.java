package cn.hhuc.service.heatmap;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Iterator;

/**
 * 从集群中获得数据
 */
public class DataFromCluster {

    /**
     * 从kafka集群获取
     * @param kafkaConsumer
     * @return
     */
    public Iterator getFromKafka(KafkaConsumer kafkaConsumer){
        // 每个一秒从kafka集群拉取一次信息
        ConsumerRecords records = kafkaConsumer.poll(Duration.ofSeconds(1));
        if (records.isEmpty()){
            return null;
        }else {
            // 返回信息迭代器
            return records.iterator();
        }
    }
}
