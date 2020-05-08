package cn.hhuc.controller.mock.hotplace;


import cn.hhuc.bean.mock.hotplace.Place;
import cn.hhuc.service.mock.hotplace.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/findPlaceByName")
    public Place findPlaceByName(@RequestParam String name){
        Place place = placeService.findPlaceByName(name);
        System.out.println(place);
        return place;
    }
}
