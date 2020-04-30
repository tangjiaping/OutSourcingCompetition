package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;
import cn.hhuc.bean.LngLat;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IHeadMap {
    public Map<LngLat,Integer> getHeatMapDatas();
    public void monitorDensity();

    public void deleteExpireData();

    public void clearDatas();
}
