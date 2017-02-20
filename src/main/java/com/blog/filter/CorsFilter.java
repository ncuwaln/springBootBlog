package com.blog.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2017/2/19.
 */

@Component
public class CorsFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!check_cors()){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
    private boolean check_cors() throws IOException {
        Properties pros = new Properties();
        pros.load(new FileReader("target/classes/application.properties"));
        System.out.println(pros.getProperty("cors"));
        if ("true".equals(pros.getProperty("cors"))){
            return true;
        }
        return false;
    }
}
