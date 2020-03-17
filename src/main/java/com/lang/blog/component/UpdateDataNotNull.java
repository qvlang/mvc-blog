package com.lang.blog.component;

import com.lang.blog.model.Article;
import com.lang.blog.model.BlogUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

public class UpdateDataNotNull extends SQL {
    //更新文章非空字段
    public String updateData(final Article article) {
        return new SQL() {{
            UPDATE("tb_article");
            if (!StringUtils.isEmpty(article.getTitle())) {
                SET("title = #{title}");
            }
            if (!StringUtils.isEmpty(article.getCover())) {
                SET("cover = #{cover}");
            }
            if (!StringUtils.isEmpty(article.getAuthorId())) {
                SET("author_id = #{authorId}");
            }
            if (!StringUtils.isEmpty(article.getContentMd())) {
                SET("content_md = #{contentMd}");
            }
            if (!StringUtils.isEmpty(article.getCategory())) {
                SET("category = #{category}");
            }
            if (!StringUtils.isEmpty(article.getOrigin())) {
                SET("origin = #{origin}");
            }
            if (!StringUtils.isEmpty(article.getState())) {
                SET("state = #{state}");
            }
            if (!StringUtils.isEmpty(article.getPublishTime())) {
                SET("publish_time = #{publishTime}");
            }
            if (!StringUtils.isEmpty(article.getEditTime())) {
                SET("edit_time = #{editTime}");
            }
            if (!StringUtils.isEmpty(article.getCreateTime())) {
                SET("create_time = #{createTime}");
            }
            if (!StringUtils.isEmpty(article.getType())) {
                SET("type = #{type}");
            }
            if (!StringUtils.isEmpty(article.getContent())) {
                SET("content = #{content}");
            }
            WHERE("id = #{id}");

        }}.toString();
    }

    public String updateUserNotNull(final BlogUser user) {
        return new SQL() {{
            UPDATE("tb_user");
            if (!StringUtils.isEmpty(user.getUsername())) {
                SET("username = #{username}");
            }
            if (!StringUtils.isEmpty(user.getSalt())) {
                SET("salt = #{salt}");
            }
            if (!StringUtils.isEmpty(user.getAvatar())) {
                SET("avatar = #{avatar}");
            }
            if (!StringUtils.isEmpty(user.getIntroduce())) {
                SET("introduce = #{introduce}");
            }
            if (!StringUtils.isEmpty(user.getRemark())) {
                SET("remark = #{remark}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
