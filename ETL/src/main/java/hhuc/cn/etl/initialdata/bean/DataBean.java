package hhuc.cn.etl.initialdata.bean;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataBean implements WritableComparable<DataBean> {
    private String time;
    private String imsi;
    private String lacId;
    private String cellId;

    public DataBean(){}

    public DataBean(String time, String imsi, String lacId, String cellId) {
        this.time = time;
        this.imsi = imsi;
        this.lacId = lacId;
        this.cellId = cellId;
    }

    /**
     * 对象序列化方法
     * @param dataOutput
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(time);
        dataOutput.writeUTF(imsi);
        dataOutput.writeUTF(lacId);
        dataOutput.writeUTF(cellId);

    }

    public void readFields(DataInput dataInput) throws IOException {
        time = dataInput.readUTF();
        imsi = dataInput.readUTF();
        lacId = dataInput.readUTF();
        cellId = dataInput.readUTF();

    }

    /**
     * 由于要对该对象进行排序，故要重写该方法
     * @param o
     * @return
     */
    public int compareTo(DataBean o) {
        System.out.println("enter sort");
        if (Long.parseLong(this.imsi) > Long.parseLong(o.getImsi())){
            return 1;
        }else if (Long.parseLong(this.imsi) < Long.parseLong(o.getImsi())){
            return -1;
        }else {
            if ((Long.parseLong(this.time) > Long.parseLong(o.gettime()))){
                return 1;
            }else if (Long.parseLong(this.time) < Long.parseLong(o.gettime())){
                return -1;
            }else {
                return 0;
            }
        }
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getLacId() {
        return lacId;
    }

    public void setLacId(String lacId) {
        this.lacId = lacId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    @Override
    public String toString() {
        return time + '\t' +
               imsi + '\t' +
               lacId + '\t' +
               cellId;
    }


}
