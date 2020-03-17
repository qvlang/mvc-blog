package com.lang.blog.centers;

import com.lang.blog.model.UserConcern;
import com.lang.blog.service.IUserArticleSbService;

import java.util.ArrayList;
import java.util.List;

public class PublishArticle implements IPublish {


    //要发布的内容 文章id
    //发布文章的用户id
    private Long aid;
    //保存所有的订阅者
    private List<IObserve> observes = new ArrayList<>();

    @Override
    public void registerObserve(IObserve observe) {
        observes.add(observe);
    }

    @Override
    public void removeObserve(IObserve observe) {
        if (observes.contains(observe)) observes.remove(observe);
    }

    @Override
    public void notifyObserve(IUserArticleSbService sbService) {
        for (IObserve observe : observes) {
            if (observe instanceof UserConcern) {
                UserConcern concern = (UserConcern) observe;
                //concern
                concern.subscript(aid, sbService);
            }
        }
    }

    public void publish(Long aid, IUserArticleSbService sbService) {
        this.aid = aid;
        //推送订阅者
        notifyObserve(sbService);
    }
}
