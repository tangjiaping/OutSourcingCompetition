package cn.hhuc.bean;

public class CrossRegionVolume {
    private String startArea;
    private String endArea;
    private int value;

    public CrossRegionVolume() {
    }

    public CrossRegionVolume(String startArea, String endArea, int value) {
        this.startArea = startArea;
        this.endArea = endArea;
        this.value = value;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CrossRegionvalue{" + "startArea='" + startArea + '\'' + ", endArea='" + endArea + '\'' + ", value=" + value + '}';
    }
}
