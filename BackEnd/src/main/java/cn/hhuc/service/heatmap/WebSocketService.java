package cn.hhuc.service.heatmap;

import cn.hhuc.bean.LngLat;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class WebSocketService {

    private static final Logger logger = Logger.getLogger(WebSocketService.class);
    @Autowired
    private HeadMapService headMapService;

    private final Object object= new Object();
    // 标志实时计算线程是否开启，只允许开启一次
    private static volatile Boolean START = false;

    @Autowired
    private SimpMessagingTemplate template;

    public <T> void sendHeadMapData(String user, String destination){
        // 判断实时计算线程是否开启,如果没有开启，则需要开启，并将标志START设置为true
        // 为了保证线程只开启一次，需要通过同步保证。
        // 这里使用doublecheck进行同步
        if (!WebSocketService.START){
            synchronized (object){
                if (!WebSocketService.START){
                    new Thread(()->{
                        headMapService.monitorDensity();
                    }).start();
                    START = true;
                }
            }
        }
        // 获得数据map,map结构为：lnglat对象 -> value值
        Map heatMapDatas = headMapService.getHeatMapDatas();

        // 用来存放JSONObject对象
        ArrayList<JSONObject> objects = new ArrayList<>();

        for (Object lngLat : heatMapDatas.keySet()) {
            LngLat data = (LngLat)lngLat;
            // 字符串拼接
            String res = "{" + "lng" + ":" + data.getLng() + "," +
                         "lat" + ":" + data.getLat() + "," +
                         "count" + ":" + ((int)heatMapDatas.get(data) * 10) + "}";
            // 调用FastJson的方法将字符串转化为Json对象，其中Feature.OrderedField用来设置顺序不变
            objects.add(JSONObject.parseObject(res, Feature.OrderedField));
        }
        logger.info("发送热力图数据,当前有数据: " + objects.size());
        // 向前端推送数据
        template.convertAndSendToUser(user,destination,objects);
    }


    /**
     * 该定时任务是用来处理用户的过期数据
     * 比如：用户在前段时间的经纬度 123.4 43.1
     *  现在变为 123.6 43.5
     *  则需要将123.4 43.1的value减1
     */
    @Scheduled(fixedRate = 60000)
    public void clearExpireData(){
        headMapService.deleteExpireData();
    }

    /**
     * 该定时任务表示 每天凌晨1点执行
     * 主要将map中的数据清除。
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void cleardatas(){
        headMapService.clearDatas();
    }


    // 测试fastjson
    public static void main(String[] args) {
        String res = "{" + "lng" + ":" + 1 + "," +
                "lat" + ":" + 2 + "," +
                "value" + ":" + 3 + "}";
        JSONObject jsonObject = JSONObject.parseObject(res, Feature.OrderedField);
    }
}
