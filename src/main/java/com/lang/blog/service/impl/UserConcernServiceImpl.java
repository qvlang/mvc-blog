package com.lang.blog.service.impl;

import com.lang.blog.mapper.IUserConcernMapper;
import com.lang.blog.model.UserConcern;
import com.lang.blog.service.IUserConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConcernServiceImpl implements IUserConcernService {
    @Autowired
    private IUserConcernMapper mapper;

    @Override
    public int createUserConcern(UserConcern concern) {
        return mapper.insertConcern(concern);
    }

    @Override
    public int CancleUserConcern(Long uid, Long fid) {

        return mapper.deleteConcern(uid, fid);
    }

    @Override
    public List<Long> getAllFlowers(Long uid) {
        return mapper.findAllFlowers(uid);
    }
}
