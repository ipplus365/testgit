package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.UserLog;

/**
 * Created by 辉太郎 on 2017/5/31.
 */
public interface UserLogDao {
    int add(UserLog userLog);

    int addBatch(List<UserLog> userLogList);
}