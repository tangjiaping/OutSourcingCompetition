package cn.hhuc.mapper;

import cn.hhuc.bean.AreaTravelWayVolume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAreaTravelWayVolumeMapper {
    public List<AreaTravelWayVolume> getTravelWayVolumeByArea(String area);
}
