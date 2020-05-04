package cn.hhuc.service.login;

import cn.hhuc.bean.user.User;
import cn.hhuc.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {

    @Autowired
    private IUserService userService;

    public String login(HttpServletRequest request,String username,String password){
        String checkRes = checkUser(username, password);
        if (checkRes.equals("success")){
            request.getSession().setAttribute("isLogin","true");
        }
        return checkRes;
    }


    public String checkUser(String username,String password){
        User user = userService.queryUserByName(username, password);
        if (null != user){
            return "success";
        }else {
            return "error";
        }
    }


}
