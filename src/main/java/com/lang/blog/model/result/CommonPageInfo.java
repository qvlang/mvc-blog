package com.lang.blog.model.result;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页对象 对分页对象的再封装只读取要的属性
 */
@Data
public class CommonPageInfo<T> implements Serializable {
    private static final Long serialVersionUID = 65645656454545l;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;
    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;

    //私有化构造器 外边不能创建该对象
    private CommonPageInfo(int pageNum, int pageSize, long total, int size, int pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.size = size;
        this.pages = pages;
        this.list = list;
    }

    public static <T> CommonPageInfo getCommonPage(PageInfo<T> pageInfo) {
        return new CommonPageInfo(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getPages(), pageInfo.getList());
    }
}
