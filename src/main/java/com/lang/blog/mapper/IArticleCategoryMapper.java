package com.lang.blog.mapper;

import com.lang.blog.model.ArticleCategory;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

/**
 * 文章分类
 */
public interface IArticleCategoryMapper {
    /**
     * @Description: 通过文章分类名查询该分类下的所有文章
     * @Param: name
     * @return: 文章分类及其下所有文章
     */
    @Select("select * from tb_category where name = #{name}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "articles", many = @Many(select = "com.lang.blog.mapper.IArticleMapper.findArticlesByCategoryid", fetchType = FetchType.EAGER))
    })
    ArticleCategory findArticleCategoryByName(String name);

    //通过文章目录id查询目录
    @Select("select * from tb_category where id = #{id}")
    ArticleCategory findArticleCategoryById(Long id);

    //创建文章目录
    @Insert("insert into tb_category (name) values (#{name})")
    int insertArticleCategory(ArticleCategory category);
    //删除文章目录
    @Delete("delete from tb_category where id = #{id}")
    int deleteArticleCategory(Long id);
    //修改文章目录
    @Update("update tb_category set name = #{name} where id = #{id}")
    int updateArticleCategory(ArticleCategory category);
}
