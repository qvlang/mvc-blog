package com.lang.blog.service;

import com.lang.blog.model.Article;
import com.lang.blog.model.result.CommonPageInfo;

import java.util.List;

public interface IArticleService {

    Article getArticleByid(Long id);

    boolean createArticle(Article article);

    boolean updateArticle(Article article);

    boolean deleteArticle(Long id);

    CommonPageInfo<Article> getPageList(Integer pageNum, Integer pageSize);

    List<Article> getArticleBatchIds(List<Long> ids);
}
