package com.lang.blog.mapper;

import com.lang.blog.component.UpdateDataNotNull;
import com.lang.blog.model.BlogUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

public interface UserMapper {
    /**
     * @Description: 根据用户名查询密码登录
     * @Param: 用户名
     * @return: 密码
     */
    @Select("SELECT * FROM tb_user where username = #{username}")
    BlogUser findOne(String username);

    //根据id查询用户
    @Select("select password from tb_user where id = #{id}")
    String findPasswordByid(Long id);

    /**
     * @Description: 插入用户
     * @Param: bloguser
     * @return: 是否成功
     */
    @Insert({"insert into tb_user (username,password,salt,avatar,introduce,remark) values(#{username},#{password},#{salt},#{avatar},#{introduce},#{remark})"})
    int insertOne(BlogUser user);

    //修改用户非空信息
    @UpdateProvider(type = UpdateDataNotNull.class, method = "updateUserNotNull")
    int updateById(BlogUser user);

    //修改密码
    @Update("update tb_user set password = #{password} where id = #{id}")
    int updatePassword(@Param("password") String password, @Param("id") Long id);

}
