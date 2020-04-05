package cn.hhuc.bean;

public class StayVolume {
    private String area;
    private int value;

    public StayVolume(){}

    public StayVolume(String area, int value) {
        this.area = area;
        this.value = value;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getvalue() {
        return value;
    }

    public void setvalue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StayVolume{" + "area='" + area + '\'' + ", value=" + value + '}';
    }
}
