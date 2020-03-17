package com.lang.blog.controller;

import com.lang.blog.centers.SubscriptCenter;
import com.lang.blog.model.BlogUser;
import com.lang.blog.model.UserConcern;
import com.lang.blog.model.result.CommonResult;
import com.lang.blog.service.IUserConcernService;
import com.lang.blog.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 引入jsr303数据校验步骤
 * 1 导入坐标
 * 2 对需要验证的javabean进行注解
 * 3 使用 @Validated 开启
 * 4 在传入参数中紧跟resultbind接收验证消息
 */
@RestController
@Slf4j
@RequestMapping("/api")
@Api("UserController")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private SubscriptCenter subscriptCenter;

    //登录接口交由springsurity处理了
    //注册接口
    @PostMapping(path = "/register")
    @ApiOperation("用户注册")
    public CommonResult<String> register(@Validated @RequestBody BlogUser user, BindingResult result) {
        log.info("info注册的用户名是{},注册的密码是{},头像地址是{},个人介绍是{}", user.getUsername(), user.getPassword(), user.getAvatar(), user.getIntroduce());
        boolean isSuccess = userService.register(user);
        return isSuccess ? CommonResult.SUCCESS("注册成功") : CommonResult.SUCCESS("注册失败");
    }

    //修改用户信息不包括密码
    @PutMapping("/update")
    @ApiOperation("修改用户信息")
    public CommonResult<String> update(@Validated @RequestBody BlogUser user, BindingResult result) {
        boolean isSuccess = userService.updateUser(user);
        return isSuccess ? CommonResult.SUCCESS("修改成功") : CommonResult.SUCCESS("修改失败");
    }

    //修改密码
    @PutMapping("/update/{id}")
    @ApiOperation("修改密码")
    public CommonResult<String> updatePassword(@PathVariable Long id, String password) {
        boolean isSuccess = userService.updateUserPassword(id, password);
        return isSuccess ? CommonResult.SUCCESS("修改密码成功") : CommonResult.SUCCESS("修改密码失败");
    }

    //用户之间互相关注
    @PostMapping("/concern")
    public CommonResult<String> userConcern(@RequestBody UserConcern userConcern) {
        subscriptCenter.concern(userConcern);
        return CommonResult.SUCCESS("关注成功");
    }
}
