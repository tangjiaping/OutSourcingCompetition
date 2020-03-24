package hhuc.cn.etl.initialdata.mapreduce;

import hhuc.cn.etl.initialdata.bean.DataBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer阶段
 */
public class ETLReducer extends Reducer<DataBean, NullWritable, DataBean,NullWritable> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("enter reducer setup");
    }

    /**
     * 直接写出就行，这里主要利用shuffle阶段对Bean对象排序，不然可以去掉Reducer阶段
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(DataBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}
