package cn.hhuc.service.areaTravelWatVolume;

import cn.hhuc.bean.AreaTravelWayVolume;
import cn.hhuc.mapper.IAreaTravelWayVolumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaTravelWayVolumeService implements IAreaTravelWayVolumeService {

    @Autowired
    private IAreaTravelWayVolumeMapper travelWayVolumeMapper;
    @Override
    public List<AreaTravelWayVolume> getTravelWayVolumeByArea(String area) {
        return travelWayVolumeMapper.getTravelWayVolumeByArea(area);
    }
}
