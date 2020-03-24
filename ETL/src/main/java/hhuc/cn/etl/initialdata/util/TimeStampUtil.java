package hhuc.cn.etl.initialdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳工具类
 */
public class TimeStampUtil {

    /**
     * 该方法将字符串时间戳转化为字符串时间输出 yyyyMMddHHmmss 格式
     * @param timestamp 输入的字符串时间戳
     * @return String 转化为时间的字符串
     */
    public static String toTime(String timestamp){
        // 将字符串转化为整形
        Long aLong = Long.parseLong(timestamp);
        // 将整形转换为时间
        Date date = new Date(aLong);
        // 定义时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 功能测试代码
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(toTime("1538481509242"));
    }
}
