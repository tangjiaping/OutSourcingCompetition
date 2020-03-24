package hhuc.cn.etl.initialdata.mapreduce;

import hhuc.cn.etl.initialdata.bean.DataBean;
import hhuc.cn.etl.initialdata.util.ETLUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 清洗数据Mapper阶段
 */
public class ETLMapper extends Mapper<LongWritable, Text, hhuc.cn.etl.initialdata.bean.DataBean, NullWritable> {
    // 定义一个ArrayList来存储 基站经纬度数据中的laci
    List list = new ArrayList<String>();

    /**
     * Mapper阶段的初始化函数
     * @param context MapReduce上下文
     * @throws IOException
     */
    @Override
    protected void setup(Context context) throws IOException {
        // 获取缓存文件路径，此缓存文件为 基站经纬度数据
        String path = context.getCacheFiles()[0].getPath();
        // 通过上下文获取配置文件，然后构建文件系统类
        Configuration conf = context.getConfiguration();
        FileSystem fileSystem = FileSystem.get(conf);
//        FSDataInputStream inputStream = fileSystem.open(new Path("/user/cache.txt"));
        // 文件流操作：打开文件流-->字符流包装-->缓存流加快读写
        FSDataInputStream inputStream = fileSystem.open(new Path(path));
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);

        /**
         * 逐行读取基站经纬度数据，切割获取需要的字段
         */
        String line;
        while ((line = reader.readLine()) != null){
            list.add(line.split(",")[2]);
        }

        // 关流操作
        reader.close();
        streamReader.close();
        inputStream.close();
//        list.add("16789-67200820");
        System.out.println("enter map setup");
    }

    /**
     * mapper阶段的核心函数
     * @param key map输入键值
     * @param value map输入value值
     * @param context MapReduce上下文
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /**
         * 逐行获取原始数据，并切割
         */
        String line = value.toString();
        String[] fields = line.split(",");
        String flag = fields[2] + "-" + fields[3];

        /**
         * 判断原始数据的基站信息是否在基站经纬度数据中，
         * 不存在则删除；
         * 存在则进行下一步处理
         */
        if (list.contains(flag)){
            System.out.println(flag);
            DataBean etlDataBean = ETLUtil.getETLData(line);
            /**
             * 判断Bean对象是否为空
             * 为空：不符合要求
             * 不为空：符合要求，写入Reducer
             */
            if (etlDataBean != null){
                System.out.println("map context write");
                System.out.println(etlDataBean + "=============" + NullWritable.get());
                context.write(etlDataBean,NullWritable.get());
            }
        }
    }
}
