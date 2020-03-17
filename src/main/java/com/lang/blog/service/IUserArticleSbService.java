package com.lang.blog.service;

import com.lang.blog.model.Article;
import com.lang.blog.model.UserArticleSb;

import java.util.List;

public interface IUserArticleSbService {
    //创建订阅文章记录
    int createArticleSb(UserArticleSb userArticleSb);
    //删除
    int removeArticleSbById(Long id);
    //查询所有
    List<Article> getAllByUid(Long uid);
}
