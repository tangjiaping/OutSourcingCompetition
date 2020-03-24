package hhuc.cn.mocktime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class MockTime {
    public static String mockTime(){
        // 设置时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取当前时间，并按照设定格式输出
        String time = dateFormat.format(System.currentTimeMillis());
        return time;
    }

    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println(mockTime());
            Thread.sleep(1000);
        }
    }
}
