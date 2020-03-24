package cn.hhuc.bean;


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
    public String toString() {
        return "lng:" + lng + ",\nlat:" + lat + ",\ncount:" + count;
    }

    public static void main(String[] args) {
        System.out.println(new HeatMapData(116.456456f,42.3456625f,20));
    }
}
