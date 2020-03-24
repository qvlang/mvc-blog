package com.lang.blog.service;

import com.lang.blog.model.BlogUser;

public interface IUserService {
    BlogUser login(String username);

    boolean register(BlogUser user);

    boolean updateUser(BlogUser user);

    boolean updateUserPassword(Long id, String password);

    boolean publish(Long uid);
    //获取用户对应角色
    String getUserRole(Long uid);
}
