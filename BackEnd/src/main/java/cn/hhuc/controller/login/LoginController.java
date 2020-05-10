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

    /**
     * 用户名、密码登录
     * @param username  用户名
     * @param password  密码
     * @param response  响应
     * @param request   请求
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/loginByup")
    private State login(@RequestParam String username, @RequestParam String password, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        State state = loginService.login(request, username, password);
        // 对状态进行判断
        String result = state.getStatus();
        if (result.equals("success")){
//            response.sendRedirect("/success.html");
        }else {
//            response.sendRedirect("/index.html");
        }
        return state;
    }

    /**
     * 手机验证码登录
     * @param phone     手机号码
     * @param code      待验证的验证码
     * @param request   响应
     * @return
     */
    @RequestMapping("/loginBypc")
    public State login(@RequestParam String phone,@RequestParam int code,HttpServletRequest request){
        State state = loginService.login(request,phone, code);
        return state;
    }

    /**
     * 退出登录
     * @param username  用户名
     * @param request
     * @throws IOException
     */
    @RequestMapping("/quitLogin")
    private void quitLogin(@RequestParam String username, HttpServletRequest request) throws IOException {
        System.out.println("=====================退出");
        request.getSession().removeAttribute("isLogin");
    }

    /**
     * 发送验证码
     * @param phone 手机号码
     * @return
     */
    @RequestMapping("/sendVerifyCode")
    public State sendVerifyCode(@RequestParam String phone){
        return loginService.sendVerifyCode(phone);
    }
}
