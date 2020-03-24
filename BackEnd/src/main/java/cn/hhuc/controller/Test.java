package cn.hhuc.controller;

import cn.hhuc.bean.TripVolume;
import cn.hhuc.mapper.ITripVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {
    @Autowired
    private ITripVolume tripVolume;

    @RequestMapping("/tripVolume")
    public List<TripVolume> testTripVolume(){
        return tripVolume.getAreaTripVolumes();
    }
}
