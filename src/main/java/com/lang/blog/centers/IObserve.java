package com.lang.blog.centers;

import com.lang.blog.service.IUserArticleSbService;

public interface IObserve {
    /**
     * @Description: 用来订阅文章
     * @Param: 文章id
     * @return:
     */
    void subscript(Long aid, IUserArticleSbService sbService);

}
