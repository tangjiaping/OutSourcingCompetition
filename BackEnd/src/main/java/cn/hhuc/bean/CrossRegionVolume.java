package cn.hhuc.bean;

public class CrossRegionVolume {
    private String startArea;
    private String endArea;
    private int volume;

    public CrossRegionVolume() {
    }

    public CrossRegionVolume(String startArea, String endArea, int volume) {
        this.startArea = startArea;
        this.endArea = endArea;
        this.volume = volume;
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "CrossRegionVolume{" + "startArea='" + startArea + '\'' + ", endArea='" + endArea + '\'' + ", volume=" + volume + '}';
    }
}
