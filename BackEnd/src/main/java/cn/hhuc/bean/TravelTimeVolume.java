package cn.hhuc.bean;

public class TravelTimeVolume {
    private String time;
    private String data;

    public TravelTimeVolume() {
    }

    public TravelTimeVolume(String time, String data) {
        this.time = time;
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TravelTimeVolume{" + "time='" + time + '\'' + ", data='" + data + '\'' + '}';
    }
}
