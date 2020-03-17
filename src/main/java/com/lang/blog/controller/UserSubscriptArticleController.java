package com.lang.blog.controller;

import com.lang.blog.model.Article;
import com.lang.blog.model.UserArticleSb;
import com.lang.blog.model.result.CommonResult;
import com.lang.blog.service.IUserArticleSbService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("用户订阅")
@RequestMapping("/api/sub")
public class UserSubscriptArticleController {
    @Autowired
    private IUserArticleSbService sbService;

    /**
     * @Description: 用户获取所有订阅的文章
     * @Param: 用户id
     * @return:
     */
    @GetMapping("/{uid}")
    public CommonResult<List<Article>> getAllByUserUId(@PathVariable Long uid) {
        List<Article> articles = sbService.getAllByUid(uid);
        return CommonResult.SUCCESS(articles);
    }
}
