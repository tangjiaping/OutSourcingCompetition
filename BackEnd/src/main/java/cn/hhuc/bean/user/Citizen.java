package cn.hhuc.bean.user;

import java.util.Objects;

public class Citizen {
    private String imsi;
    private String lng;
    private String lat;

    public Citizen() {
    }

    public Citizen(String imsi, String lng, String lat) {
        this.imsi = imsi;
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizen)) return false;
        Citizen citizen = (Citizen) o;
        return Objects.equals(imsi, citizen.imsi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imsi);
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
