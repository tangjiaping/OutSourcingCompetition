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
        String checkRes = checkUser(username, password);
        if (checkRes.equals("success")){
            request.getSession().setAttribute("isLogin","true");
        }
        String data = checkRes.equalsIgnoreCase("error") ? "验证失败" : "验证成功";
        return new State(checkRes,data);
    }

    public State login(String phone,int toBeVerifiedCode){
        VerifyCode verifyCode = map.get(phone);
        if (verifyCode == null)
            return new State("error","验证码过期");
        if (verifyCode.getCode() == toBeVerifiedCode){
            return new State("success","验证成功");
        }else {
            return new State("error","验证失败");
        }
    }

    public String sendVerifyCode(String phone){
        int code = RandomVerifyCode.sixBitVerifyCode();
        String status = messageServer.sendVerifyCode(phone, code);
        if (status.equalsIgnoreCase("Ok")){
            System.out.println("-------------------------");
            System.out.println("验证码：" + code + " 已发送至 " + phone + ",请注意查收");
            System.out.println("-------------------------");
            map.put(phone,new VerifyCode(code,System.currentTimeMillis()));
        }else {
            System.out.println("-------------------------");
            System.out.println("验证码：" + code + " 发送至 " + phone + "失败");
            System.out.println("-------------------------");
        }
        return status;
    }

    public String checkUser(String username,String password){
        User user = userService.queryUserByName(username, password);
        if (null != user){
            return "success";
        }else {
            return "error";
        }
    }

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
