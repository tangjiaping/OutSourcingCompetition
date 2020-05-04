package cn.hhuc.controller.offline;

import cn.hhuc.bean.AreaTravelWayVolume;
import cn.hhuc.mapper.IAreaTravelWayVolumeMapper;
import cn.hhuc.service.areaTravelWatVolume.IAreaTravelWayVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaTravelWayVolumeController {

    @Autowired
    private IAreaTravelWayVolumeService travelWayVolumeService;

    @RequestMapping("/getTravelWayVolumeByArea")
    public List<AreaTravelWayVolume> getTravelWayVolumeByArea(@RequestParam String area){
        return travelWayVolumeService.getTravelWayVolumeByArea(area);
    }
}
