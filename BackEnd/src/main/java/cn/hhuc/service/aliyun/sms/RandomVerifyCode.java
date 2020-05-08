package cn.hhuc.service.aliyun.sms;

import org.junit.Test;

import java.util.Random;

/**
 * 随机验证码生成类
 */
public class RandomVerifyCode {

    private static final Random random = new Random();

    /**
     * 六位验证码生成
     * @return  返回生成的验证码
     */
    public static int sixBitVerifyCode(){
        return verifyCode(6);
    }

    /**
     *
     * @param n 表示要生成多少位的验证码
     * @return  返回生成的验证码
     */
    public static int verifyCode(int n){
        StringBuffer str = new StringBuffer();
        for (int i=0;i<n;i++){
            str.append(random.nextInt(10));
        }
        return Integer.parseInt(String.valueOf(str));
    }

    public static void main(String[] args) {
        System.out.println(sixBitVerifyCode());
    }
}
