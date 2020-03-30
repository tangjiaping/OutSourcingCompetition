package cn.hhuc.service.tripVolume;

import cn.hhuc.bean.TripVolume;
import cn.hhuc.mapper.ITripVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TripVolumeService implements ITripVolumeService {

    @Autowired
    private ITripVolume tripVolume;

    @Override
    public List<TripVolume> getAreaTripVolumes() {
        return tripVolume.getAreaTripVolumes();
    }

    @Override
    public List<TripVolume> getTopNAreaTripVolumes(int n) {
        return tripVolume.getTopNAreaTripVolumes(n);
    }

    @Override
    public TripVolume getAreaTripVolumeByArea(String area) {
        return tripVolume.getAreaTripVolumeByArea(area);
    }
}
