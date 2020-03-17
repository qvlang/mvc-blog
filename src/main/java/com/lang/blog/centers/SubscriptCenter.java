package com.lang.blog.centers;

import com.lang.blog.model.UserConcern;
import com.lang.blog.service.IUserArticleSbService;
import com.lang.blog.service.IUserConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章发布订阅中心
 * 用户发布文章后将文章id和用户id发到这里
 * 订阅中心查询是否有其他用户订阅了发布者
 * 再将文章推送给订阅用户
 */
@Component
public class SubscriptCenter {
    @Autowired
    private IUserConcernService userConcernService;
    @Autowired
    private IUserArticleSbService sbService;

    //关注用户
    public void concern(UserConcern userConcern) {
        //设置关注时间
        userConcern.setConcernTime(new Date());
        userConcernService.createUserConcern(userConcern);
    }

    //取消关注
    public void cancalConcern(Long uid, Long fid) {
        userConcernService.CancleUserConcern(uid, fid);
    }

    //发布文章
    public void publish(Long uid, Long aid, PublishArticle publiser) {
        //订阅者列表
        List<IObserve> list = new ArrayList<>();
        //通过用户id查询他的所有粉丝
        List<Long> flowers = userConcernService.getAllFlowers(uid);

        if (flowers.size() <= 0) return;

        for (Long flowerId : flowers) {
            UserConcern userConcern = new UserConcern();
            userConcern.setFid(flowerId);
            //注册订阅者
            publiser.registerObserve(userConcern);
        }
        //通知各订阅者修改的文章
        publiser.publish(aid, sbService);
    }
}
