package cn.hhuc.mapper;

import cn.hhuc.bean.mock.hotplace.Place;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlaceMapper {
    public Place findPlaceDataByPlaceName(String placeName);
}
