package cn.hhuc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DownLoadMapper {
    // 注意，动态指定表名需要 @param(参数)
    public List<LinkedHashMap<String,String>> download(@Param("table") String table);
}
