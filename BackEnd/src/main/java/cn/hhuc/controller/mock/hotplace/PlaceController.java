package cn.hhuc.controller.mock.hotplace;


import cn.hhuc.bean.mock.hotplace.Place;
import cn.hhuc.service.mock.hotplace.HotPlaceServer;
import cn.hhuc.service.mock.hotplace.PlaceService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 热点位置控制类
 */
@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private HotPlaceServer hotPlaceServer;

    @RequestMapping("/findPlaceByName")
    public Place findPlaceByName(@RequestParam String name){
        Place place = placeService.findPlaceByName(name);
        System.out.println(place);
        return place;
    }

    /**
     * 接收前端发送的监控位置
     * 前端发送的格式{places : [....]}
     * @param jsonObject
     */
    @RequestMapping(value = "/putPlaces",produces = "application/json;charset=UTF-8")
    public void putPlaces(@RequestBody JSONObject jsonObject){
        // 将前端发送来的地名转化为对象数组
        JSONArray places = jsonObject.getJSONArray("places");
        Object[] objects = places.toArray();
        System.out.println(objects.toString());
        // 调用service层的方法
        placeService.addPlaces(objects);
    }

    /**
     * 定时任务，每秒发送一次热点位置信息
     */
    @Scheduled(fixedRate = 1000)
    public void sendHotPlace(){
        hotPlaceServer.sendHotPlace("place","/hotplace");
    }
}
