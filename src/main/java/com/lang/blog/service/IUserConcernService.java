package com.lang.blog.service;

import com.lang.blog.model.UserConcern;

import java.util.List;

public interface IUserConcernService {
    //创建用户关注
    int createUserConcern(UserConcern concern);

    //取消用户关注
    int CancleUserConcern(Long uid, Long fid);

    //获取所有粉丝的id
    List<Long> getAllFlowers(Long uid);
}
