package com.lang.blog.service;

import com.lang.blog.model.ArticleCategory;

public interface IArticleCategoryService {
    ArticleCategory getArticleCategoryByName(String classifyName);

    boolean createArticleCategory(ArticleCategory category);

    boolean deleteArticleCategory(Long id);

    boolean updateArticleCategory(ArticleCategory category);
}
