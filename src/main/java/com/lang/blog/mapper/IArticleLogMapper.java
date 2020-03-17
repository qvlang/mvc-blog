package com.lang.blog.mapper;

import com.lang.blog.model.ArticleLog;
import org.apache.ibatis.annotations.Insert;

public interface IArticleLogMapper {
    //新增日志记录
    @Insert("insert into tb_log (username,operation,time,method,params,ip,create_time,location)" +
            "values(#{username},#{operation},#{time},#{method},#{params},#{ip},#{createTime},#{location})")
    int insertLog(ArticleLog log);
}
