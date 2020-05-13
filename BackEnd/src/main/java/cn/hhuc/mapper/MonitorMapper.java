package cn.hhuc.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MonitorMapper {
    public Map<String,String> getMonitorNumberAndIp(String placeName);
}
