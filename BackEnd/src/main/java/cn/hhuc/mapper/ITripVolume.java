package cn.hhuc.mapper;

import cn.hhuc.bean.TripVolume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ITripVolume {
    public List<TripVolume> getAreaTripVolumes();
    public TripVolume getAreaTripVolumeByArea(String area);
}
