package cn.hhuc.controller;

import cn.hhuc.bean.StayVolume;
import cn.hhuc.config.CorlFilter;
import cn.hhuc.service.stayVolume.StayVolumeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StayVolumeController {

    private static final Logger logger = Logger.getLogger(StayVolumeController.class);

    @Autowired
    private StayVolumeService volumeService;


    /**
     * 获得出现量的所有数据
     * @return
     */
    @RequestMapping("/getAreaStayVolumes")
    public List<StayVolume> getAreaStayVolumes(){
        logger.info("请求getAreaStayVolumes获得出现量数据");
        return volumeService.getAreaStayVolumes();
    }

    /**
     * 获得Topn的驻留量数据
     * @param n
     * @return
     */
    @RequestMapping("/getTopNAreaStayVolumes")
    public List<StayVolume> getTopNAreaStayVolumes(@RequestParam int n) {
        logger.info("请求getTopNAreaStayVolumes获得top " + n +" 出现量数据");
        return volumeService.getTopNAreaStayVolumes(n);
    }

    /**
     * 通过区获得区的驻留量数据
     * @param area
     * @return
     */
    @RequestMapping("/getAreaStayVolumeByArea")
    public StayVolume getAreaStayVolumeByArea(@RequestParam String area) {
        logger.info("请求getAreaStayVolumeByArea获 " + area + " 得出现量数据");
        return volumeService.getAreaStayVolumeByArea(area);
    }

}
