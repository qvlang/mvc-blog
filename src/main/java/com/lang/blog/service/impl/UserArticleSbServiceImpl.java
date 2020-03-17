package com.lang.blog.service.impl;

import com.lang.blog.mapper.IUserArticleSbMapper;
import com.lang.blog.model.Article;
import com.lang.blog.model.UserArticleSb;
import com.lang.blog.service.IArticleService;
import com.lang.blog.service.IUserArticleSbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArticleSbServiceImpl implements IUserArticleSbService {

    @Autowired
    private IUserArticleSbMapper mapper;
    @Autowired
    IArticleService articleService;

    @Override
    public int createArticleSb(UserArticleSb userArticleSb) {
        return mapper.insert(userArticleSb);
    }

    @Override
    public int removeArticleSbById(Long id) {
        return mapper.deleteByid(id);
    }

    @Override
    public List<Article> getAllByUid(Long uid) {
        //拿到所有的文章id
        List<Long> articleIds = mapper.findAll(uid);
        //遍历文章id拿到对应的文章

        return articleService.getArticleBatchIds(articleIds);
    }
}
