package cn.hhuc.service.tripVolume;

import cn.hhuc.bean.TripVolume;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITripVolumeService {
    public List<TripVolume> getAreaTripVolumes();
    public List<TripVolume> getTopNAreaTripVolumes(int n);
    public TripVolume getAreaTripVolumeByArea(String area);
}
