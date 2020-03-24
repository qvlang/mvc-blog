package com.lang.blog.component;

import com.lang.blog.model.BlogUser;
import com.lang.blog.service.IUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser loginUser = userService.login(username);
        String password = "";
        String userRole = "";
        if (StringUtils.isEmpty(loginUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        {
            //获取用户对应的角色
            password = loginUser.getPassword();
            Long uid = loginUser.getId();
            userRole = userService.getUserRole(uid);
            log.info(username + "的角色为" + userRole);
        }

        return new User(username, password, AuthorityUtils.createAuthorityList("ROLE_" + userRole));
    }
}
