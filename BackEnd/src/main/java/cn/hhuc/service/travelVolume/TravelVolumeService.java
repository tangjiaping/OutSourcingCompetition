package cn.hhuc.service.travelVolume;

import cn.hhuc.bean.TravelAreaVolume;
import cn.hhuc.bean.TravelTimeVolume;
import cn.hhuc.mapper.TravelVolumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelVolumeService implements ITravelVolume {

    @Autowired
    private TravelVolumeMapper travelVolumeMapper;

    @Override
    public List<TravelTimeVolume> getTravelTimeVolumes() {
        return travelVolumeMapper.getTravelTimeVolumes();
    }

    @Override
    public TravelTimeVolume getTravelTimeVolumeByTime(String time) {
        return travelVolumeMapper.getTravelTimeVolumeByTime(time);
    }

    @Override
    public List<TravelAreaVolume> getTravelAreaVolumes() {
        return travelVolumeMapper.getTravelAreaVolumes();
    }

    @Override
    public TravelAreaVolume getTravelAreaVolumeByArea(String area) {
        return travelVolumeMapper.getTravelAreaVolumeByTime(area);
    }
}
