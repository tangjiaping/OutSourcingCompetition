package cn.hhuc.service.crossRegion;

import cn.hhuc.bean.CrossRegionVolume;
import cn.hhuc.mapper.ICrossRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossRegion implements ICrossRegion {

    @Autowired
    private ICrossRegionMapper crossRegionMapper;
    @Override
    public List<CrossRegionVolume> getCrossRegionVolumes() {
        List<CrossRegionVolume> volumes = crossRegionMapper.getCrossRegionVolumes();
        return volumes;
    }

    @Override
    public List<CrossRegionVolume> getCrossRegionVolumesByArea(String area) {
        List<CrossRegionVolume> areaVolume = crossRegionMapper.getCrossRegionVolumesByArea(area);
        return areaVolume;
    }
}
