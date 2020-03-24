package hhuc.cn.etl.initialdata.mapreduce;

import hhuc.cn.etl.initialdata.bean.DataBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

/**
 * ETLRMTDriver实现Tool
 * 实现其中的run(),setConf(),getConf()方法
 */
public class ETLRMTDriver implements Tool {
    private Configuration conf = null;

    /**
     * 核心方法
     * @param args 参数
     * @return
     * @throws Exception
     */
    public int run(String[] args) throws Exception {
        // 获取Job实例对象
        Job job = Job.getInstance(conf);
        // 将文件加入缓存
        job.addCacheFile(new URI(args[0]));
        // 设置Jar类
        job.setJarByClass(ETLRMTDriver.class);

        // 设置Mapper和Reducer类
        job.setMapperClass(ETLMapper.class);
        job.setReducerClass(ETLReducer.class);

        // 设置Mapper阶段和Reducer阶段输出的key,value类
        job.setMapOutputKeyClass(DataBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(DataBean.class);
        job.setOutputValueClass(NullWritable.class);

        // 设置输入输出文件路径
        Path inputPath = new Path(args[1]);
        Path outputPath = new Path(args[2]);
        FileInputFormat.setInputPaths(job,inputPath);
        FileOutputFormat.setOutputPath(job,outputPath);

        // 运行
        boolean isSuccess = job.waitForCompletion(true);
        return isSuccess ? 1 : 0;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Configuration getConf() {
        return conf;
    }

    /**
     * 命名行入口函数
     * @param args
     */
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        ETLRMTDriver etlrmtDriver = new ETLRMTDriver();
        try {
            int resultCode = ToolRunner.run(conf, etlrmtDriver, args);
            if (resultCode == 1){
                System.out.println("success");
            }else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
