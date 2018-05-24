package com.ipplus360.service;

import com.ipplus360.entity.UserLog;

/**
 * 定位日志
 * Created by 辉太郎 on 2017/6/16.
 */
public interface LocateLogService {
    int add(UserLog userLog);
    void record();

    String getLogFile(String path, String prefix);
}
