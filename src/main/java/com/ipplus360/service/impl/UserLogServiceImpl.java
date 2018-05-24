package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.UserLogDao;
import com.ipplus360.entity.UserLog;
import com.ipplus360.service.UserLogService;

/**
 * Created by 辉太郎 on 2017/5/31.
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogDao userLogDao;

    @Override
    public int add(UserLog userLog) {
        return userLogDao.add(userLog);
    }
}
