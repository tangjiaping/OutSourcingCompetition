package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeadMapService implements IHeadMap {

    private final static Logger log = Logger.getLogger(HeadMapService.class);

    private List<HeatMapData> heatMapDatas = new ArrayList<HeatMapData>();
    private Map<HeatMapData,Integer> heatMapDatasMap = new HashMap<>();

    public Map<HeatMapData, Integer> getHeatMapDatasMap() {
        return heatMapDatasMap;
    }

    @Override
    public List<HeatMapData> getHeatMapDatas() {
        return heatMapDatas;
    }

    /**
     * 该方法用来监测来自集群的数据
     */
    @Override
    @Test
    public void monitorDensity() {
        log.info("enter");
        KafkaConsumer kafkaConsumer = KafkaConsumerUtil.getKafkaConsumer("122.51.19.184:9092", "tjp");
        DataFromCluster cluster = new DataFromCluster();
        /**
         * 数据切分和封装
         */
        while (true){
            Iterator dataIterator = cluster.getFromKafka(kafkaConsumer);
            if (dataIterator != null){
                while (dataIterator.hasNext()){
                    ConsumerRecord record = (ConsumerRecord)dataIterator.next();
                    String res = (String) record.value();
                    log.info("lng lat count: " + res);
                    System.out.println("==============================");
                    System.out.println(res);
                    System.out.println("================================");
                    String[] strings = res.split(" ");
                try {
                    HeatMapData heatMapData = new HeatMapData(Float.parseFloat(strings[0]), Float.parseFloat(strings[1]), Integer.parseInt(strings[2]));
                    heatMapDatas.add(heatMapData);
                    heatMapDatasMap.put(heatMapData,1);
                    log.info("heatMapDatas size: " + heatMapDatas.size());
                }catch (Exception e){
                    log.error("data format error：" + e.getMessage(),e.getCause());
                }
                }
            }
        }

    }
}
