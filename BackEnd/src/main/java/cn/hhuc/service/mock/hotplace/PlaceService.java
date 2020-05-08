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
    private Set places = new HashSet();

    public Place findPlaceByName(String name){
        Place place = placeMapper.findPlaceDataByPlaceName(name);
        return place;
    }
    public List getAllPlaces(){
        ArrayList<Object> list = new ArrayList<>();
        for (Object placeName : places){
            Place place = findPlaceByName((String) placeName);
            list.add(place);
        }
        return list;
    }
    public void addPlaces(Object[] objects){
        places.clear();
        for (Object o : objects){
            places.add(o);
        }
    }
}
