package cn.hhuc.controller;

import cn.hhuc.bean.CrossRegionVolume;
import cn.hhuc.service.crossRegion.ICrossRegion;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrossRegionController {

    private static final Logger logger = Logger.getLogger(CrossRegionController.class);
    @Autowired
    private ICrossRegion crossRegion;

    @RequestMapping("/getCrossRegionVolumes")
    public List<CrossRegionVolume> getCrossRegionVolumes(){
        logger.info("请求getCrossRegionVolumes获得区域迁徙数据");
        List<CrossRegionVolume> volumes = crossRegion.getCrossRegionVolumes();
        return volumes;
    }

    @RequestMapping("/getCrossRegionVolumesByArea")
    public List<CrossRegionVolume> getCrossRegionVolumesByArea(@RequestParam String area){
        logger.info("请求getCrossRegionVolumesByArea获得 " + area +" 区迁徙数据");
        List<CrossRegionVolume> areaVolumes = crossRegion.getCrossRegionVolumesByArea(area);
        return areaVolumes;
    }

}
