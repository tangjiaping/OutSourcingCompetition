package cn.hhuc.bean;

public class AreaTravelWayVolume {
    private String area;
    private String way;
    private int data;

    public AreaTravelWayVolume() {
    }

    public AreaTravelWayVolume(String area, String way, int data) {
        this.area = area;
        this.way = way;
        this.data = data;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AreaTravelWayVolume{" + "area='" + area + '\'' + ", way='" + way + '\'' + ", data=" + data + '}';
    }
}
