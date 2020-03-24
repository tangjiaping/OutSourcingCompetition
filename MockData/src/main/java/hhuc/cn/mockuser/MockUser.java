package hhuc.cn.mockuser;

public class MockUser {
    // 模拟用户
    public static String mockUser(){
        String first = "4600";
        String second = "00" + (int)(Math.random() * 10 + 100);
        String third = "5063";
        String four = "85" + (int)(Math.random() * 100 + 100);
        String five = (int)(50 + Math.random() * 50) + "";
        String user = first + second + third + four + five;
        return user;
    }
    public static void main(String[] args) {
        while (true){
            System.out.println(mockUser());
        }
    }
}
