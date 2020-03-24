package com.lang.blog.config;

import com.lang.blog.component.MyAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true, prePostEnabled = true) //开启注解方法保卫配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/",
                "*/html",
                "/favicon.ico",
                "*.tff",
                "*.woff",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-resources/**",
                "/v2/api-docs/**");
        /*允许静态资源*/

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭跨站请求伪造
        http.csrf().disable();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/api/login")
                .defaultSuccessUrl("/success", true)
                .failureHandler(myAuthenticationFailureHandler);
        //放行静态资源文件
        http.authorizeRequests()
                .antMatchers("/login")  //允许登录请求
                .permitAll()
//                .antMatchers("/api/article/**")
//                .hasRole("admin")
//                .antMatchers("/**") //测试运行 不拦截请求
//                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
