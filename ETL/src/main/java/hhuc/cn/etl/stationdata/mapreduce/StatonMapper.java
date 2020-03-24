package hhuc.cn.etl.stationdata.mapreduce;

import hhuc.cn.etl.stationdata.bean.StationDataBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class StatonMapper extends Mapper<LongWritable, Text, StationDataBean, NullWritable> {
    StationDataBean stationDataBean = new StationDataBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] strings = line.split(",");
        if (strings.length == 3){
            stationDataBean.setLongitude(strings[0]);
            stationDataBean.setLatitude(strings[1]);
            stationDataBean.setLaci(strings[2]);
            context.write(stationDataBean,NullWritable.get());
        }
    }
}
