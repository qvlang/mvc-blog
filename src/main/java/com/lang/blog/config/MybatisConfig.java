package com.lang.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
public class MybatisConfig {
    //数据库连接的属性
    private String username;
    private String password;
    private String url;
    private String driver;
    //配置数据源
    public DataSource getDataSource() {
        return new DruidDataSource();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
