package cn.hhuc.bean;

import java.util.Objects;

public class LngLat {
    private Float lng;
    private Float lat;

    public LngLat() {
    }

    public LngLat(Float lng, Float lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public Float getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LngLat)) return false;
        LngLat lngLat = (LngLat) o;
        return Objects.equals(lng, lngLat.lng) && Objects.equals(lat, lngLat.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat);
    }
}
