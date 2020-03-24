package com.lang.blog.controller;

import com.lang.blog.model.ArticleCategory;
import com.lang.blog.model.result.CommonResult;
import com.lang.blog.service.IArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articleCategory")
@Api(tags = "article")
@CacheConfig(cacheNames = "common") //cacheNames表明使用哪个cache组件来缓存数据

public class ArticleCategoryController {
    @Autowired
    private IArticleCategoryService categoryService;

    @GetMapping("")
    @ApiOperation("获取文章目录及所属目录下的文章")
    @Cacheable(key = "'category' + #p0")
    @Secured({"ROLE_admin", "ROLE_user"}) //必须以role开头 基于角色守卫
    public CommonResult<ArticleCategory> getArticleCategoryByName(String classifyName) {
        ArticleCategory articleCategory = categoryService.getArticleCategoryByName(classifyName);
        return CommonResult.SUCCESS(articleCategory);
    }

    //增加文章分类
    @PostMapping("")
    @ApiOperation("增加文章目录")
    @Secured("ROLE_admin")
    public CommonResult addArticleCategory(@Validated @RequestBody ArticleCategory category, BindingResult result) {
        boolean isSuccess = categoryService.createArticleCategory(category);
        return isSuccess ? CommonResult.SUCCESS("创建成功") : CommonResult.FAILED("创建失败");
    }

    //删除文章分类
    @DeleteMapping("/{id}")
    @CacheEvict(key = "'category' + #p0")
    @Secured("ROLE_admin")
    public CommonResult<String> deleteArticleCategoryById(@PathVariable Long id) {
        boolean isSuccess = categoryService.deleteArticleCategory(id);
        return isSuccess ? CommonResult.SUCCESS("删除成功") : CommonResult.FAILED("删除失败");
    }

    //修改文章分类
    @PutMapping("")
    @CachePut(key = "'category'+ #p0.id")
    @Secured("ROLE_admin")
    public CommonResult<String> updateArticleCategoryById(@Validated @RequestBody ArticleCategory category, BindingResult result) {
        boolean isSuccess = categoryService.updateArticleCategory(category);
        return isSuccess ? CommonResult.SUCCESS("修改成功") : CommonResult.FAILED("修改失败");
    }
}
