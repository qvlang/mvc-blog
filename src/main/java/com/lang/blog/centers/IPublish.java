package com.lang.blog.centers;

import com.lang.blog.service.IUserArticleSbService;

public interface IPublish {

    /**
     * @Description: 注册观察者
     * @Param: 实现观察者接口的对象
     * @return:
     */
    void registerObserve(IObserve observe);

    /**
     * @Description: 取消订阅
     * @Param:
     * @return:
     */
    void removeObserve(IObserve observe);

    /**
     * @Description: 通知推送文章
     * @Param: 文章id
     * @return:
     */
    void notifyObserve(IUserArticleSbService sbService);
}
