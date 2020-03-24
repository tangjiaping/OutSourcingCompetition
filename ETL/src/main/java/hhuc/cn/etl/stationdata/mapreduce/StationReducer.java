package hhuc.cn.etl.stationdata.mapreduce;

import hhuc.cn.etl.stationdata.bean.StationDataBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class StationReducer extends Reducer<StationDataBean, NullWritable,StationDataBean, NullWritable> {
    @Override
    protected void reduce(StationDataBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}
