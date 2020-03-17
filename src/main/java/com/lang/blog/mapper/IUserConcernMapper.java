package com.lang.blog.mapper;

import com.lang.blog.model.UserConcern;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserConcernMapper {
    //新增用户关注
    @Insert("insert into tb_user_concern(uid,fid,concern_time)values (#{uid},#{fid},#{concernTime})")
    int insertConcern(UserConcern concern);

    //删除关注
    @Delete("delete from tb_user_concern where uid = #{uid} and fid = #{fid}")
    int deleteConcern(@Param("uid") Long uid, @Param("fid") Long fid);

    //根据用户id查询所有粉丝id
    @Select("select fid from tb_user_concern where uid = #{uid}")
    List<Long> findAllFlowers(Long uid);
}
