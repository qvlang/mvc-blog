package com.lang.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"javassistProxyFactory", "handler"})
@ApiModel(value = "Article",description = "文章对象")
public class Article implements Serializable {
    private static final Long serialVersionUID = 156464456l;
    @ApiModelProperty(name = "id")
    private Long id;
    @ApiModelProperty(name = "标题")
    @NotBlank
    private String title;
    @ApiModelProperty(name = "cover")
    private String cover;
    @ApiModelProperty(name = "authorid")
    private Long authorId;
    @NotBlank
    @ApiModelProperty(name = "content")
    private String content;
    @ApiModelProperty(name = "contentMd")
    private String contentMd;
    @NotBlank
    @ApiModelProperty(name = "category")
    private String category;
    @Range(min = 0,max = 1,message = "状态只有0标记为删除，1标记为激活")
    @ApiModelProperty(name = "state")
    private String state;
    @ApiModelProperty(name = "origin")
    private String origin;
    @ApiModelProperty(name = "type")
    private Integer type;
    @PastOrPresent(message = "创建时间只能是过去或现在")
    @ApiModelProperty(name = "publishTime")
    private Date publishTime;
    @PastOrPresent(message = "创建时间只能是过去或现在")
    @ApiModelProperty(name = "editTime")
    private Date editTime;
    @PastOrPresent(message = "创建时间只能是过去或现在")
    @ApiModelProperty(name = "createTime")
    private Date createTime;
    //文章所属分类名
    @ApiModelProperty(name = "kind")
    private ArticleCategory kind;
}
