package com.lang.blog.mapper;

import com.lang.blog.model.UserArticleSb;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserArticleSbMapper {
    //新增订阅列表
    @Insert("insert into tb_user_article_sb(uid,aid)values (#{uid},#{aid})")
    int insert(UserArticleSb userArticleSb);

    //删除
    @Delete("delete from tb_user_article_sb where id=#{id}")
    int deleteByid(Long id);

    @Select("select aid from tb_user_article_sb where uid = #{uid}")
    List<Long> findAll(Long uid);
}
