package com.lang.blog.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@ApiModel(value = "ArticleCategory", description = "文章目录")
public class ArticleCategory implements Serializable {
    private static final Long serialVersionUID = 156464677788888l;
    @ApiModelProperty(name = "id")
    private Long id;
    @NotBlank
    @ApiModelProperty(name = "name")
    private String name;
    //组合该分类下的所有文章
    @ApiModelProperty(name = "articles")
    private List<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
