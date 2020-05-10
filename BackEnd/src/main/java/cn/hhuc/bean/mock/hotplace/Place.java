package cn.hhuc.bean.mock.hotplace;

import java.io.Serializable;

public class Place {
    private String name;    // 热点位置名字
    private double lng;     // 热点位置经度
    private double lat;     // 热点位置纬度
    private Integer nowNum;     // 热点位置目前的人流量
    private Integer maxnum;     // 热点位置最大的人流量
    private boolean overflow;   // 是否超出阈值

    public Place() {
    }

    public Place(String name, double lng, double lat) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

    public Place(String name, double lng, double lat, Integer maxnum) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.maxnum = maxnum;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
        this.overflow = nowNum >= maxnum;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public void setOverflow(Integer nowNum) {
        this.setOverflow(nowNum >= maxnum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Integer getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    @Override
    public String toString() {
        return "Place{" + "name='" + name + '\'' + ", lng=" + lng + ", lat=" + lat + ", maxnum=" + maxnum + '}';
    }
}
