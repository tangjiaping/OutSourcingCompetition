package cn.hhuc.controller;

import cn.hhuc.bean.TripVolume;
import cn.hhuc.service.tripVolume.TripVolumeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TripVolumeController {

    private static final Logger logger = Logger.getLogger(TripVolumeController.class);

    @Autowired
    private TripVolumeService volumeService;

    /**
     * 获得出现量的所有数据
     * @return
     */
    @RequestMapping("/getAreaTripVolumes")
    public List<TripVolume> getAreaTripVolumes(){
        logger.info("请求getAreaTripVolumes获得出现量数据");
        return volumeService.getAreaTripVolumes();
    }

    /**
     * 获得Topn的出行量数据
     * @param n
     * @return
     */
    @RequestMapping("/getTopNAreaTripVolumes")
    public List<TripVolume> getTopNAreaTripVolumes(@RequestParam int n) {
        logger.info("请求getTopNAreaTripVolumes获得top " + n +" 出现量数据");
        return volumeService.getTopNAreaTripVolumes(n);
    }

    /**
     * 通过区获得区的出行量数据
     * @param area
     * @return
     */
    @RequestMapping("/getAreaTripVolumeByArea")
    public TripVolume getAreaTripVolumeByArea(@RequestParam String area) {
        logger.info("请求getAreaTripVolumeByArea获 " + area + " 得出现量数据");
        return volumeService.getAreaTripVolumeByArea(area);
    }

}
