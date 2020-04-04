package cn.hhuc.mapper;

import cn.hhuc.bean.CrossRegionVolume;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICrossRegionMapper {
    public List<CrossRegionVolume> getCrossRegionVolumes();

    public List<CrossRegionVolume> getCrossRegionVolumesByArea(String area);
}
