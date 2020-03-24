package com.lang.blog.service.impl;

import com.lang.blog.centers.PublishArticle;
import com.lang.blog.centers.SubscriptCenter;
import com.lang.blog.mapper.IUserRoleMapper;
import com.lang.blog.mapper.UserMapper;
import com.lang.blog.model.BlogUser;
import com.lang.blog.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SubscriptCenter subscriptCenter;
    @Autowired
    private IUserRoleMapper roleMapper;

    public BlogUser login(String username) {
        BlogUser user = mapper.findOne(username);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    public boolean register(BlogUser user) {
        BlogUser insertUser = new BlogUser();
        //需要对密码进行加密
        //赋值属性不修改传入参数
        BeanUtils.copyProperties(user, insertUser);
        String password = insertUser.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        insertUser.setPassword(encodePassword);
        //插入修改后的用户
        int affectRow = mapper.insertOne(insertUser);
        return affectRow > 0;
    }

    public boolean updateUser(BlogUser user) {
        int affectRow = mapper.updateById(user);
        return affectRow > 0;
    }

    public boolean updateUserPassword(Long id, String password) {
        //通过id查询到原来的密码
        String dbPassword = mapper.findPasswordByid(id);
        //验证前端传来的密码是否和原密码相同 相同则不修改
        boolean ismatches = passwordEncoder.matches(password, dbPassword);
        if (ismatches) return false;
        String encodePassword = passwordEncoder.encode(password);
        int affectRow = mapper.updatePassword(encodePassword, id);
        return affectRow > 0;
    }

    @Override
    public boolean publish(Long uid) {
        //创建发布者
        PublishArticle publishArticle = new PublishArticle();

        subscriptCenter.publish(uid, 9L, publishArticle);
        return false;
    }

    @Override
    public String getUserRole(Long uid) {
        String roleName = roleMapper.findRoleName(uid);
        return roleName;
    }
}
