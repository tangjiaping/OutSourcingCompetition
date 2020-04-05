package cn.hhuc.service.stayVolume;

import cn.hhuc.bean.StayVolume;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStayVolumeService {
    public List<StayVolume> getAreaStayVolumes();
    public List<StayVolume> getTopNAreaStayVolumes(int n);
    public StayVolume getAreaStayVolumeByArea(String area);
}
