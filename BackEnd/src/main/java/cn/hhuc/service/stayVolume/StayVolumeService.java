package cn.hhuc.service.stayVolume;

import cn.hhuc.bean.StayVolume;
import cn.hhuc.mapper.IStayVolume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StayVolumeService implements IStayVolumeService {

    @Autowired
    private IStayVolume stayVolume;

    @Override
    public List<StayVolume> getAreaStayVolumes() {
        return stayVolume.getAreaStayVolumes();
    }

    @Override
    public List<StayVolume> getTopNAreaStayVolumes(int n) {
        return stayVolume.getTopNAreaStayVolumes(n);
    }

    @Override
    public StayVolume getAreaStayVolumeByArea(String area) {
        return stayVolume.getAreaStayVolumeByArea(area);
    }
}
