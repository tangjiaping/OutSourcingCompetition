package cn.hhuc.bean;

public class TravelAreaVolume {
    private String area;
    private String data;

    public TravelAreaVolume() {
    }

    public TravelAreaVolume(String area, String data) {
        this.area = area;
        this.data = data;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TravelareaVolume{" + "area='" + area + '\'' + ", data='" + data + '\'' + '}';
    }
}
