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

    private static Set<String> areas = new HashSet<>();
    static {
        areas.add( "和平区");
        areas.add( "沈河区");
        areas.add( "大东区");
        areas.add( "皇姑区");
        areas.add( "铁西区");
        areas.add( "辽中区");
        areas.add( "康平县");
        areas.add( "法库县");
        areas.add( "新民市");
        areas.add( "苏家屯区");
        areas.add( "沈北新区");
        areas.add( "浑南区");
    }

    @Override
    public List<StayVolume> getAreaStayVolumes() {
        List<StayVolume> stayVolumes = deleteAreaByAreas(stayVolume.getAreaStayVolumes());
        return stayVolumes;
    }

    @Override
    public List<StayVolume> getTopNAreaStayVolumes(int n) {
        List<StayVolume> areaStayVolumes = getAreaStayVolumes();
        areaStayVolumes.sort(new Comparator<StayVolume>() {
            @Override
            public int compare(StayVolume o1, StayVolume o2) {
                return o1.getValue() >= o2.getValue() ? -1 : 1;
            }
        });
        return areaStayVolumes.subList(0,n);
    }

    @Override
    public StayVolume getAreaStayVolumeByArea(String area) {
        return stayVolume.getAreaStayVolumeByArea(area);
    }

    public List<StayVolume> deleteAreaByAreas(List<StayVolume> stayVolumes){
        Iterator<StayVolume> iterator = stayVolumes.iterator();
        while (iterator.hasNext()){
            StayVolume next = iterator.next();
            System.out.println(next.getName());
            if (!areas.contains(next.getName())){
                iterator.remove();
            }
        }
        return stayVolumes;
    }

}
