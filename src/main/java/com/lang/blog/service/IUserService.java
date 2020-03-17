package com.lang.blog.service;

import com.lang.blog.model.BlogUser;

public interface IUserService {
    //根据传入的用户名查找出对应的密码
    String login(String username);

    boolean register(BlogUser user);

    boolean updateUser(BlogUser user);

    boolean updateUserPassword(Long id, String password);

    boolean publish(Long uid);
}
