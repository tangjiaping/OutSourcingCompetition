package cn.hhuc.controller.user;

import cn.hhuc.bean.user.User;
import cn.hhuc.service.login.LoginService;
import cn.hhuc.service.user.IUserService;
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

    @RequestMapping("/login")
    private String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        String result = loginService.login(request,username, password);
        if (result.equals("success")){
            System.out.println("登入success");
            System.out.println(request.getSession().getAttribute("isLogin"));
            String contextPath = request.getContextPath();
            System.out.println(request.getRequestURL());
            System.out.println(contextPath);
            response.sendRedirect("/success.html");
        }else {
            response.sendRedirect("/index.html");
        }
        return result;
    }

    @RequestMapping("/quitLogin")
    private void quitLogin(@RequestParam String username, HttpServletRequest request) throws IOException {
        System.out.println("=====================退出");
        request.getSession().removeAttribute("isLogin");
    }
}
