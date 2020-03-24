package hhuc.cn.etl.initialdata.util;

import hhuc.cn.etl.initialdata.bean.DataBean;
import org.apache.commons.lang.StringUtils;

/**
 * 数据清洗工具类
 */
public class ETLUtil {

    /**
     * 将传过来的一行字符串进行处理，然后封装成一个Bean对象返回
     * @param originalData 待清洗字符串
     * @return DataBean 清洗好并封装成Bean对象的类
     */
    public static DataBean getETLData(String originalData){
        /**
         * 对传过来的一行字符串切割，然后提取需要的字段
         */
        String[] fields = originalData.split(",");
        String timestamp = fields[0];
        String imsi = fields[1];
        String lacId = fields[2];
        String cellId = fields[3];
//        System.out.println(timestamp + " " + imsi + " " + lacId + " " + cellId);

        /**
         * 将字段为空的、IMSI字段存在#，*，^字符的删除
         */
        if (!(StringUtils.isNotBlank(imsi)&& StringUtils.isNotBlank(lacId) && StringUtils.isNotBlank(cellId)) ||
            StringUtils.contains(imsi,"#")||
            StringUtils.contains(imsi,"*")||
            StringUtils.contains(imsi,"^")){
            return null;
        }

        /**
         * 调用时间戳工具函数，将string类型的时间戳转化为时间字符串
         */
        String time = TimeStampUtil.toTime(timestamp);
//        System.out.println(time);

        /**
         * 去除不在2018-10-03这天的数据
         */
        if (!StringUtils.contains(time,"20181003")){
            return null;
        }

        /**
         * 将清理好的数据封装成一个Bean对象
         */
        DataBean dataBean = new DataBean(time, imsi, lacId, cellId);
        return dataBean;
    }

    /**
     * 功能测试代码
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getETLData("1538500115198,460000095074476402,16789,67567667,86139666665799,1538500115189,, ,20,#*#9975"));
        System.out.println(getETLData("1538570430177,,16392,67169036,86135666676222,1538570436152,, ,2,#*#2226"));
    }
}
