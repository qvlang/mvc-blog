package com.lang.blog.model;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Data
@ApiModel(value = "BlogUser", description = "博客用户")
public class BlogUser implements Serializable {
    private static final Long serialVersionUID = 1564646546456l;

    @ApiModelProperty(name = "id")
    private Long id;
    @Length(min = 3, max = 6, message = "用户名长度必须在3-6位")
    @ApiModelProperty(name = "username")
    private String username;
    @Length(min = 6, max = 12, message = "密码长度必须在6-12位")
    @ApiModelProperty(name = "password")
    private String password;
    @ApiModelProperty(name = "salt")
    private String salt;
    @URL(message = "必须符合url格式")
    @ApiModelProperty(name = "avatar")
    private String avatar;
    @ApiModelProperty(name = "introduce")
    private String introduce;
    @ApiModelProperty(name = "remark")
    private String remark;

}
