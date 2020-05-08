package cn.hhuc.bean.mock.hotplace;

import java.io.Serializable;

public class Place {
    private String name;
    private double lng;
    private double lat;
    private Integer maxnum;

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
