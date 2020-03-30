package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;
import cn.hhuc.controller.HeadMapController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class WebSocketService {

    private static final Logger logger = Logger.getLogger(WebSocketService.class);
    @Autowired
    private HeadMapService headMapService;

    @Autowired
    private SimpMessagingTemplate template;

    public <T> void sendHeadMapData(String user, String destination){

        template.convertAndSendToUser(user,destination,headMapService.getHeatMapDatasMap().keySet());

        // 模拟
        float lng = (float) Math.random();
        float lat = (float) Math.random();
        int counts = (int) (Math.random()*10 + 3);
        HeatMapData mapData = new HeatMapData(lng, lat, counts);
        headMapService.getHeatMapDatas().add(mapData);
        headMapService.getHeatMapDatasMap().put(mapData,1);
        //结束


        /**
         * 以下是用一个Map来维护每个数据的有效时长，每隔5s，map中的value加1，
         * 当大于设定的阈值，则将该key移除。
         */
        Set<HeatMapData> keySet = headMapService.getHeatMapDatasMap().keySet();
        Iterator<HeatMapData> iterator = keySet.iterator();
        int count;
        while (iterator.hasNext()){
            HeatMapData heatMapData = (HeatMapData)iterator.next();
            count = headMapService.getHeatMapDatasMap().get(heatMapData);
//            System.out.println(count);
            if (count > 3) {
                 iterator.remove();
                logger.info(heatMapData.toString() + " 被清出；还剩 " + headMapService.getHeatMapDatasMap().size());
            } else {
                synchronized (heatMapData){
                    headMapService.getHeatMapDatasMap().put(heatMapData,count+1);
                }
            }
        }
    }
}
