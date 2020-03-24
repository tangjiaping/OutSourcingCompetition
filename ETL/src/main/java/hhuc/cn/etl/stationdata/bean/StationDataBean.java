package hhuc.cn.etl.stationdata.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StationDataBean implements WritableComparable<StationDataBean> {
    private String longitude;
    private String latitude;
    private String laci;

    public StationDataBean(){}

    public StationDataBean(String longitude, String latitude, String laci) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.laci = laci;
    }


    public int compareTo(StationDataBean o) {
        String[] strings = laci.split("-");
        int lac = Integer.parseInt(strings[0]);
        int cell = Integer.parseInt(strings[1]);
        String[] ostrings = o.laci.split("-");
        int olac = Integer.parseInt(ostrings[0]);
        int ocell = Integer.parseInt(ostrings[1]);
        if (lac > olac){
            return 1;
        }else if (lac < olac){
            return -1;
        }else {
            if (cell > ocell){
                return 1;
            }else if (cell < ocell){
                return -1;
            }else {
                return 0;
            }
        }
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(longitude);
        out.writeUTF(latitude);
        out.writeUTF(laci);
    }

    public void readFields(DataInput in) throws IOException {
        longitude = in.readUTF();
        latitude = in.readUTF();
        laci = in.readUTF();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLaci() {
        return laci;
    }

    public void setLaci(String laci) {
        this.laci = laci;
    }

    @Override
    public String toString() {
        return longitude + '\t' +
               latitude + '\t' +
               laci;
    }
}
