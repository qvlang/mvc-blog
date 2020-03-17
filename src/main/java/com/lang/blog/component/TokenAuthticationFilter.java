package com.lang.blog.component;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class TokenAuthticationFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //查看当前请求是否有session 有的话提取session中的数据进行验证
        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");
        if (!StringUtils.isEmpty(username)) {

        }
    }
}
