package cn.hhuc.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Component
public class CorlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getRequestURL());
        // 解决ajax访问跨域问题
        response.setHeader("Access-Control-Allow-Headers", "*");

        // 解决ajax和websocket跨域冲突问题
        if (!StringUtils.contains(request.getRequestURL().toString(),"hhuc")){
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        filterChain.doFilter(servletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
