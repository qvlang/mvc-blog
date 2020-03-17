package com.lang.blog.service.impl;

import com.lang.blog.mapper.IArticleCategoryMapper;
import com.lang.blog.model.ArticleCategory;
import com.lang.blog.service.IArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryServiceImpl implements IArticleCategoryService {
    @Autowired
    private IArticleCategoryMapper mapper;

    public ArticleCategory getArticleCategoryByName(String classifyName) {
        return mapper.findArticleCategoryByName(classifyName);

    }

    //创建
    public boolean createArticleCategory(ArticleCategory category) {
        int affectRow = mapper.insertArticleCategory(category);
        System.out.println(affectRow);
        return affectRow > 0;
    }

    //删除
    public boolean deleteArticleCategory(Long id) {
        int affectRow = mapper.deleteArticleCategory(id);
        return affectRow > 0;
    }

    //修改
    public boolean updateArticleCategory(ArticleCategory category) {
        int affectRow = mapper.updateArticleCategory(category);
        return affectRow > 0;
    }
}
