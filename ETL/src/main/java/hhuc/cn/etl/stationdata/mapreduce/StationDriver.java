package hhuc.cn.etl.stationdata.mapreduce;

import hhuc.cn.etl.stationdata.bean.StationDataBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class StationDriver implements Tool {
    private Configuration conf;
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(conf);

        job.setJarByClass(StationDriver.class);
        job.setMapperClass(StatonMapper.class);
        job.setReducerClass(StationReducer.class);

        job.setMapOutputKeyClass(StationDataBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(StationDataBean.class);
        job.setOutputValueClass(NullWritable.class);

        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);

        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 1 : 0;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Configuration getConf() {
        return conf;
    }

    public static void main(String[] args) {
        try {
            int run = ToolRunner.run(new Configuration(), new StationDriver(), args);
            if (run == 1){
                System.out.println("basic staton data are ETL successfully");
            }else {
                System.out.println("basic staton data are ETL failfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
