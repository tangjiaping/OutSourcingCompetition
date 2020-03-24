package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeadMap implements IHeadMap {

    private final static Logger log = Logger.getLogger(HeadMap.class);
    private List<HeatMapData> heatMapDatas = new ArrayList<HeatMapData>();

    @Override
    public List<HeatMapData> getHeatMapDatas() {
        return heatMapDatas;
    }

    @Override
    @Test
    public void monitorDensity() {
        log.info("enter");
        KafkaConsumer kafkaConsumer = KafkaConsumerUtil.getKafkaConsumer("192.168.2.120:9092", "tjp");
        DataFromCluster cluster = new DataFromCluster();
        while (true){
            Iterator dataIterator = cluster.getFromKafka(kafkaConsumer);
            if (dataIterator != null){
                while (dataIterator.hasNext()){
                    ConsumerRecord record = (ConsumerRecord)dataIterator.next();
                    String res = (String) record.value();
                    log.info("lng lat count: " + res);
                    String[] strings = res.split(" ");
                try {
                    HeatMapData heatMapData = new HeatMapData(Float.parseFloat(strings[0]), Float.parseFloat(strings[1]), Integer.parseInt(strings[2]));
                    heatMapDatas.add(heatMapData);
                    log.info("heatMapDatas size: " + heatMapDatas.size());
                }catch (Exception e){
                    log.error("data format error：" + e.getMessage(),e.getCause());
                }
                }
            }
        }

    }
}
