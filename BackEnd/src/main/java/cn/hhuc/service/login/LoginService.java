package cn.hhuc.service.login;

import cn.hhuc.bean.login.State;
import cn.hhuc.bean.login.VerifyCode;
import cn.hhuc.bean.user.User;
import cn.hhuc.service.aliyun.sms.RandomVerifyCode;
import cn.hhuc.service.aliyun.sms.ShortMessageServer;
import cn.hhuc.service.user.IUserService;
import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class LoginService {

    @Autowired
    private IUserService userService;

    Map<String, VerifyCode> map = new HashMap<>();

    ShortMessageServer messageServer = new ShortMessageServer();

    public State login(HttpServletRequest request,String username,String password){
        State checkRes = checkUser(username, password);
        if (checkRes.getStatus().equalsIgnoreCase("success")){
            request.getSession().setAttribute("isLogin","true");
        }
        return checkRes;
    }

    public State login(HttpServletRequest request,String phone,int toBeVerifiedCode){
        VerifyCode verifyCode = map.get(phone);
        if (verifyCode == null)
            return new State("error","验证码过期");
        if (verifyCode.getCode() == toBeVerifiedCode){
            request.getSession().setAttribute("isLogin","true");
            return new State("success","验证成功");
        }else {
            return new State("error","验证失败");
        }
    }

    /**
     * 发送手机验证码服务
     * @param phone
     * @return
     */
    public State sendVerifyCode(String phone){
        // 首先判断该用户是否具有访问权限
        if (!isAccess(phone)){
            return new State("error","该用户没有访问权限");
        }
        // 调用随机数工具函数随机生成6位验证码
        int code = RandomVerifyCode.sixBitVerifyCode();
        // 调用阿里短信服务，向phone手机发送code验证码
        String status = messageServer.sendVerifyCode(phone, code);
        // 对状态进行判断
        if (status.equalsIgnoreCase("Ok")){
            System.out.println("-------------------------");
            System.out.println("验证码：" + code + " 已发送至 " + phone + ",请注意查收");
            System.out.println("-------------------------");
            // 将验证码放入map中，供后继验证
            map.put(phone,new VerifyCode(code,System.currentTimeMillis()));
        }else {
            System.out.println("-------------------------");
            System.out.println("验证码：" + code + " 发送至 " + phone + "失败");
            System.out.println("-------------------------");
        }
        return new State("success","发送成功");
    }

    // 判断该手机号是否有权限访问
    public boolean isAccess(String phone){
        boolean isAccess = userService.isAccess(phone);
        return isAccess;
    }

    // 对用户进行身份核实
    public State checkUser(String username,String password){
        User user = userService.queryUserByName(username, password);
        if (null != user && user.getPassword().equalsIgnoreCase(password)){
            return new State("success","登录成功");
        }else if (null != user){
            return new State("error","密码错误");
        }else
            return new State("error","用户没有访问权限");
    }

    // 定时清除过期验证码
    @Scheduled(fixedRate = 10 * 1000)
    public void clearExpireVerifyCode(){
        if (map.isEmpty())
            return;
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            if (System.currentTimeMillis() - map.get(key).getSendTime() > 60 * 1000)
                map.remove(key);
        }
    }
}
