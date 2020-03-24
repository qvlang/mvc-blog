package com.lang.blog.controller;

import com.lang.blog.model.Article;
import com.lang.blog.model.result.CommonPageInfo;
import com.lang.blog.model.result.CommonResult;
import com.lang.blog.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/article")
@Api(tags = "article")
public class ArticleController {
    @Autowired
    private IArticleService articleService;


    //根据文章id查询查出文章和文章所属分类
    @GetMapping("/{id}")
    @ApiOperation("通过文章id查询文章")
    @Secured("ROLE_user")
    public CommonResult<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.getArticleByid(id);
        return CommonResult.SUCCESS(article);
    }

    //创建文章
    @PostMapping("")
    @ApiOperation("创建文章")
    @Secured("ROLE_user")
    public CommonResult<String> addArticle(@Validated @RequestBody Article article, BindingResult result) {
        boolean isSuccess = articleService.createArticle(article);
        return isSuccess ? CommonResult.SUCCESS("创建成功") : CommonResult.FAILED("创建失败");
    }

    //修改文章
    @PutMapping("")
    @ApiOperation("修改文章")
    @Secured("ROLE_user")
    public CommonResult<String> updateArticle(@Validated @RequestBody Article article) {
        boolean isSuccess = articleService.updateArticle(article);
        return isSuccess ? CommonResult.SUCCESS("修改成功") : CommonResult.FAILED("修改失败");
    }

    //修改文章
    @DeleteMapping("/{id}")
    @ApiOperation("删除文章")
    @Secured("ROLE_user")
    public CommonResult<String> deleteArticle(@PathVariable Long id) {
        boolean isSuccess = articleService.deleteArticle(id);
        return isSuccess ? CommonResult.SUCCESS("删除成功") : CommonResult.FAILED("删除失败");
    }

    //分类查询所有文章
    @GetMapping("/pageList")
    @ApiOperation("分页查询文章")
    @Secured("ROLE_user")
    public CommonResult<CommonPageInfo<Article>> getPageList(Integer pageNum, Integer pageSize) {
        CommonPageInfo<Article> commonPageInfo = articleService.getPageList(pageNum, pageSize);
        return CommonResult.SUCCESS(commonPageInfo);
    }
}
