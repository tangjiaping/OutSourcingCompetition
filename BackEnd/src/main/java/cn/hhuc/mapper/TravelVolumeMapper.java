package cn.hhuc.mapper;

import cn.hhuc.bean.TravelAreaVolume;
import cn.hhuc.bean.TravelTimeVolume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TravelVolumeMapper {
    public List<TravelTimeVolume> getTravelTimeVolumes();

    public TravelTimeVolume getTravelTimeVolumeByTime(String time);

    public List<TravelAreaVolume> getTravelAreaVolumes();

    public TravelAreaVolume getTravelAreaVolumeByTime(String area);

}
