package com.lang.blog.config;

import com.lang.blog.component.StringToDateFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        //成功失败进入的页面
        registry.addViewController("/success").setViewName("success");
    }
}
