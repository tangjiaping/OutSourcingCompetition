package cn.hhuc.mapper;

import cn.hhuc.bean.StayVolume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IStayVolume {
    public List<StayVolume> getAreaStayVolumes();
    public List<StayVolume> getTopNAreaStayVolumes(int n);
    public StayVolume getAreaStayVolumeByArea(String area);
}
