package com.lang.blog.component;

import com.lang.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = userService.login(username);
        System.out.println("我是这里" + username);
        if (StringUtils.isEmpty(password)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new User(username, password, AuthorityUtils.createAuthorityList("user"));
    }
}
