package cn.hhuc.service.travelVolume;

import cn.hhuc.bean.TravelAreaVolume;
import cn.hhuc.bean.TravelTimeVolume;

import java.util.List;

public interface ITravelVolume {
    public List<TravelTimeVolume> getTravelTimeVolumes();
    public TravelTimeVolume getTravelTimeVolumeByTime(String time);
    public List<TravelAreaVolume> getTravelAreaVolumes();
    public TravelAreaVolume getTravelAreaVolumeByArea(String area);
}
