package cn.hhuc.service.crossRegion;

import cn.hhuc.bean.CrossRegionVolume;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICrossRegion {
    public List<CrossRegionVolume> getCrossRegionVolumes();

    public List<CrossRegionVolume> getCrossRegionVolumesByArea(String area);
}
