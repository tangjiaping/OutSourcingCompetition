package hhuc.cn.mocklngandlat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MockLngLat {
    // 用来存储区中心城市的经纬度
    private static List centerCityLngLat = new ArrayList<String>();
    // 定义保留小数格式
    private static final DecimalFormat df = new DecimalFormat("#.000000");
    static {
        // 沈阳经纬度
        centerCityLngLat.add("123.43" + " " + "41.80");
        // 新民市经纬度
        centerCityLngLat.add("122.83" + " " + "42.0025");
        // 辽中区经纬度
        centerCityLngLat.add("122.70" + " " + "41.52");
        // 和平区经纬度
        centerCityLngLat.add("123.40" + " " + "41.78");
        // 沈河区经纬度
        centerCityLngLat.add("123.45" + " " + "41.80");
        // 大东区经纬度
        centerCityLngLat.add("123.47" + " " + "41.80");
        // 皇姑区经纬度
        centerCityLngLat.add("123.42" + " " + "41.82");
        // 铁西区经纬度
        centerCityLngLat.add("122.95" + " " + "41.12");
        // 黄家屯经纬度
        centerCityLngLat.add("123.33" + " " + "41.67");
        // 于洪区经纬度
        centerCityLngLat.add("123.30" + " " + "41.78");
        // 康平县经纬度
        centerCityLngLat.add("123.35" + " " + "42.75");
        // 法库县经纬度
        centerCityLngLat.add("123.40" + " " + "42.50");
        // 沈北新区经纬度
        centerCityLngLat.add("123.235072" + " " + "41.545220");

        /**
         * 以下是东北、东南、西北、西南四个角的经纬度
         */
        centerCityLngLat.add("122.5507919" + " " + "42.80167847");
        centerCityLngLat.add("122.5507919" + " " + "41.38805847");
        centerCityLngLat.add("123.655555" + " " + "42.80167847");
        centerCityLngLat.add("123.655555" + " " + "41.38805847");
    }

    /**
     * 模拟经纬度数据函数
     * @return
     */
    public static String mockLngLat(){
        // 随机数
        int randomNum = (int)(Math.random() * 10);
        // 随机获取经纬度的偏移量
        double lngOffset = Math.random()*0.01 - 0.005;
        double latOffset = Math.random() * 0.05 - 0.025;

        /**
         * 如果随机数为偶数，则认为位置在沈阳附近
         * 否则按随机数
         */
        if (randomNum % 2 == 0){
            randomNum = 0;
        }else {
            randomNum = (int)(1 + Math.random() * (centerCityLngLat.size()-2));
        }
        // 获取中心位置
        String centerLngLat = (String) centerCityLngLat.get(randomNum);
        String centerLng = centerLngLat.split(" ")[0];
        String centerLat = centerLngLat.split(" ")[1];
        // 中心位置加上偏移量为当前模拟的经纬度
        String newLngLat = df.format((Double.parseDouble(centerLng) + lngOffset)) + " " + df.format((Double.parseDouble(centerLat) + latOffset));
        return newLngLat;
    }

    public static void main(String[] args) {
        while (true){
            System.out.println(mockLngLat());
        }
    }
}
