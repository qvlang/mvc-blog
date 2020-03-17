package com.lang.blog.model;

import com.lang.blog.centers.IObserve;
import com.lang.blog.service.IUserArticleSbService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserConcern implements Serializable, IObserve {

    private static final Long serialVersionUID = 1569988888888l;
    private Long id;
    private Long uid;
    private Long fid;
    private Date concernTime;

    @Override
    public void subscript(Long aid, IUserArticleSbService sbService) {
        UserArticleSb userArticleSb = new UserArticleSb();
        userArticleSb.setUid(fid);
        userArticleSb.setAid(aid);
        sbService.createArticleSb(userArticleSb);
    }

}
