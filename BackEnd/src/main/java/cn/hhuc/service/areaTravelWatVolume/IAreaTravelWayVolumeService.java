package cn.hhuc.service.areaTravelWatVolume;

import cn.hhuc.bean.AreaTravelWayVolume;

import java.util.List;

public interface IAreaTravelWayVolumeService {
    public List<AreaTravelWayVolume> getTravelWayVolumeByArea(String area);
}
