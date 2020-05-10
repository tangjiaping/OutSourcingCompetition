package cn.hhuc.service.mock.hotplace;

import cn.hhuc.bean.LngLat;
import cn.hhuc.bean.mock.hotplace.Place;
import cn.hhuc.service.heatmap.DataFromCluster;
import cn.hhuc.service.heatmap.KafkaConsumerUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Iterator;

@Service
public class HotPlaceServer {

    // 创建kafka消费者对象 server "122.51.19.184:9092", topic："mockdata", group："hotplace"
    private final KafkaConsumer kafkaConsumer = KafkaConsumerUtil.getKafkaConsumer("122.51.19.184:9092","mockdata","hotplace");

    private DataFromCluster cluster = new DataFromCluster();

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SimpMessagingTemplate template;

    private void monitorHotPlace(String user, String destination){
        // 获得数据
        Iterator iterator = cluster.getFromKafka(kafkaConsumer);

        // 遍历数据
        while (iterator != null && iterator.hasNext()){
            ConsumerRecord record = (ConsumerRecord) iterator.next();
            String[] strings = ((String) record.value()).split(" ");
            // 判断该热点数据是否被监控
            Place place = placeService.getPlace(strings[0], strings[1]);

            // 如果被监控，则将该热点数据推送给前端
            if (place != null){
                place.setNowNum(Integer.parseInt(strings[2]));
                template.convertAndSendToUser(user,destination,place);
            }
        }
    }

    public void sendHotPlace(String user, String destination){
        monitorHotPlace(user,destination);
    }
    
}
