package cn.hhuc.service.heatmap;

import cn.hhuc.bean.HeatMapData;

import java.util.List;

public interface IHeadMap {
    public List<HeatMapData> getHeatMapDatas();
    public void monitorDensity();
}
