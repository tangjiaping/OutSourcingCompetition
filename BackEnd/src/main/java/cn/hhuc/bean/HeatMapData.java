package cn.hhuc.bean;


import java.util.Objects;

public class HeatMapData {
    private float lng;
    private float lat;
    private int count;

    public HeatMapData(){}

    public HeatMapData(float lng, float lat, int count) {
        this.lng = lng;
        this.lat = lat;
        this.count = count;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeatMapData)) return false;
        HeatMapData that = (HeatMapData) o;
        return Float.compare(that.lng, lng) == 0 && Float.compare(that.lat, lat) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat);
    }

    @Override
    public String toString() {
        return "lng:" + lng + ",lat:" + lat + ",count:" + count;
    }

    public static void main(String[] args) {
        System.out.println(new HeatMapData(116.456456f,42.3456625f,20));
    }
}
