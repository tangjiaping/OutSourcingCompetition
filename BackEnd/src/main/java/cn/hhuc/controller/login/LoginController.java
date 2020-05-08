package cn.hhuc.controller.login;

import cn.hhuc.bean.login.State;
import cn.hhuc.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/loginByup")
    private State login(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        State state = loginService.login(request, username, password);
        String result = state.getStatus();
        if (result.equals("success")){
            System.out.println("登入success");
            System.out.println(request.getSession().getAttribute("isLogin"));
            String contextPath = request.getContextPath();
            System.out.println(request.getRequestURL());
            System.out.println(contextPath);
//            response.sendRedirect("/success.html");
        }else {
//            response.sendRedirect("/index.html");
        }
        return state;
    }

    @RequestMapping("/loginBypc")
    public State login(@RequestParam String phone,@RequestParam int code){
        State state = loginService.login(phone, code);
        return state;
    }

    @RequestMapping("/quitLogin")
    private void quitLogin(@RequestParam String username, HttpServletRequest request) throws IOException {
        System.out.println("=====================退出");
        request.getSession().removeAttribute("isLogin");
    }


    @RequestMapping("/sendVerifyCode")
    public void sendVerifyCode(@RequestParam String phone){
        loginService.sendVerifyCode(phone);
    }
}
