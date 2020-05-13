package cn.hhuc.service.mock.hotplace;

import cn.hhuc.bean.mock.hotplace.Place;
import cn.hhuc.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlaceService {

    @Autowired
    private PlaceMapper placeMapper;
    private Set<Object> placeNames = new HashSet();
    ArrayList<Place> list = new ArrayList<>();

    // m默认有一个热点位置
    public PlaceService(){
    }

    // 根据热点位置名字查找存储在数据库中的信息
    public Place findPlaceByName(String name){
        Place place = placeMapper.findPlaceDataByPlaceName(name);
        return place;
    }

    /**
     * 根据前端传过来的数据，逐个加入到set和list中
     * @param objects
     */
    public void addPlaces(Object[] objects){
        // 原先的数据清除
        placeNames.clear();
        list.clear();
        for (Object o : objects){
            // 判断是否存在，存在的无需再次添加
            if (!placeNames.contains(o)){
                placeNames.add(o);
                // 从数据库中查找该热点位置的信息
                list.add(findPlaceByName((String) o));
            }
        }
    }

    // 通过经纬度判断热点位置是否在list中
    public Place getPlace(String lng,String lat){
        for (Place place : list){
            if (Double.parseDouble(lng) == place.getLng() && Double.parseDouble(lat) == place.getLat()){
                return place;
            }
        }
        return null;
    }
}
