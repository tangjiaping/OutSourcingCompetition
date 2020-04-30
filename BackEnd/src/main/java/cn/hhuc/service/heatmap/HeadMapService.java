package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;
import cn.hhuc.bean.LngLat;
import cn.hhuc.bean.user.Citizen;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class HeadMapService implements IHeadMap {

    private final static Logger log = Logger.getLogger(HeadMapService.class);

    private ArrayList heatMapDatas = new ArrayList<HeatMapData>();

    // 该map用来维护用户所在的基站经纬度  通过用户可以找到其所在的基站经纬度
    private Map<String,LngLat> mapper = new HashMap<>();
    // 该map用来维护基站经纬度对应的value
    private Map<LngLat,Integer> datas = new HashMap<>();


    // 提供对外的接口，外界只需要访问该接口就可以得到基站的拥挤程度
    @Override
    public Map getHeatMapDatas() {
        return datas;
    }

    /**
     * 该方法用来监测来自集群的数据
     */
    @Override
    @Test
    public void monitorDensity() {
        log.info("enter monitorDensity");
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
                    System.out.println("接受到：" + res);
                    System.out.println("================================");
                    String[] strings = res.split(" ");
                try {
                    LngLat lngLat = new LngLat(Float.parseFloat(strings[0]), Float.parseFloat(strings[1]));
                    // 如果数据存在，则将其值加1即可，否则加入map
                    if (datas.get(lngLat) != null){
                        datas.put(lngLat,datas.get(lngLat) + Integer.parseInt(strings[2]));
                    }else {
                        datas.put(lngLat,Integer.parseInt(strings[2]));
                    }
                }catch (Exception e){
                    log.error("data format error：" + e.getMessage(),e.getCause());
                }
                }
            }
        }

    }

    /**
     * 该方法主要用来清除用户过期数据
     */
    @Override
    public void deleteExpireData() {
        System.out.println("======================start clear expire data===========================");
        KafkaConsumer kafkaConsumerExpire = KafkaConsumerUtil.getKafkaConsumer("122.51.19.184:9092", "expire");
        DataFromCluster cluster = new DataFromCluster();

        Iterator iterator = cluster.getFromKafka(kafkaConsumerExpire);
        if (iterator != null){
            while (iterator.hasNext()){
                ConsumerRecord record = (ConsumerRecord) iterator.next();
                String imsi = (String) record.value();
                if (mapper.get(imsi) != null){
                    Integer integer = datas.get(mapper.get(imsi));
                    integer -= 1;
                    datas.put(mapper.get(imsi),integer);
                }
            }
        }
    }

    /**
     * 该函数用来清除所有数据
     */
    @Override
    public void clearDatas() {
        mapper.clear();
        datas.clear();
    }
}
