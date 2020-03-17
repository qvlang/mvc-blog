package com.lang.blog.mapper;

import com.lang.blog.component.UpdateDataNotNull;
import com.lang.blog.model.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 文章
 */
public interface IArticleMapper {

    //根据文章目录id查询该目录下所有文章
    @Select("select art.* from tb_article art join tb_article_category cat on art.id=cat.article_id where cat.category_id=#{id}")
    List<Article> findArticlesByCategoryid(@Param("id") Long categoryid);

    //通过文章id查询文章
    @Select("select * from tb_article where id = #{id}")
    @Results(id = "findCategory", value = {
            @Result(column = "category", property = "category"),
            @Result(column = "category", property = "kind", one = @One(select = "com.lang.blog.mapper.IArticleCategoryMapper.findArticleCategoryById"))

    })
    Article findArticleByid(Long id);

    //插入文章
    //同时在表中建立触发器 在插入删除修改文章所属目录的操作过程中
    //会自动向文章目录关联表中插入对应关系
    @Insert("insert into tb_article(title,cover,author_id,content,content_md,category,origin,state,publish_time,edit_time,create_time,type) values" +
            "(#{title},#{cover},#{authorId},#{content},#{contentMd},#{category},#{origin},#{state},#{publishTime},#{editTime},#{createTime},#{type})")
    //拿到自增的主键id
    //@SelectKey(statement = "SELECT LAST_INSERT_ID()",keyColumn = "id",keyProperty = "id",before = false,resultType = Long.class)
    //一种方式
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertArticle(Article article);

    @UpdateProvider(type = UpdateDataNotNull.class, method = "updateData")
    int updateByid(Article article);

    @Delete("delete from tb_article where id = #{id}")
    int deleteByid(Long id);

    @Select("select * from tb_article")
    @ResultMap("findCategory")
    List<Article> findAll();

    //批量id获取批量文章
    @Select({"<script>",
            "select * from tb_article",
            "where id in",
            "<foreach item = 'item' collection ='ids' separator=',' open='(' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    @ResultMap("findCategory")
    List<Article> findAllBatchByids(@Param("ids") List<Long> ids);
}
