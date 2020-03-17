package com.lang.blog.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleLog implements Serializable {
    static final Long serialVersionUID = 156488888886l;
    private Long id;
    private String username;
    private String operation;
    //执行时间
    private Long time;
    //调用方法名
    private String method;
    private String params;
    private String ip;
    private Date createTime;
    private String location;
}
