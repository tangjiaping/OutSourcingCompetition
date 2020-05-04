package cn.hhuc.controller.offline;

import cn.hhuc.bean.TravelAreaVolume;
import cn.hhuc.bean.TravelTimeVolume;
import cn.hhuc.service.travelVolume.ITravelVolume;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TravelVolumeController {

    private static final Logger logger = Logger.getLogger(TravelVolumeController.class);

    @Autowired
    private ITravelVolume travelVolume;

    @RequestMapping("/getTravelTimeVolumes")
    public List<TravelTimeVolume> getTravelTimeVolumes(){
        logger.info("通过getTravelTimeVolumes请求获取出现时间信息");
        return travelVolume.getTravelTimeVolumes();
    }

    @RequestMapping("/getTravelTimeVolumeByTime")
    public TravelTimeVolume getTravelTimeVolumeByTime(@RequestParam String time){
        logger.info("通过getTravelTimeVolumeByTime请求获取出现时间 " + time +" 信息");
        return travelVolume.getTravelTimeVolumeByTime(time);
    }

    @RequestMapping("/getTravelAreaVolumes")
    public List<TravelAreaVolume> getTravelAreaVolumes(){
        logger.info("通过getTravelAreaVolumes请求获取出现区域信息");
        return travelVolume.getTravelAreaVolumes();
    }

    @RequestMapping("/getTravelAreaVolumeByArea")
    public TravelAreaVolume getTravelAreaVolumeByArea(@RequestParam String area){
        logger.info("通过getTravelAreaVolumeByArea请求获取出行区域 " + area +" 信息");
        return travelVolume.getTravelAreaVolumeByArea(area);
    }
}
