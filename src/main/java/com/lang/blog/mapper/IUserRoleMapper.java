package com.lang.blog.mapper;

import org.apache.ibatis.annotations.Select;

public interface IUserRoleMapper {
    //通过用户id查询出对应
    @Select("SELECT r.role_name from tb_role r WHERE r.id = (SELECT ur.rid FROM tb_user_role ur WHERE ur.uid = #{uid});")
    String findRoleName(Long uid);
}
