package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.LogFile;

/**
 * Created by 辉太郎 on 2017/6/22.
 */
public interface LogFileDao {
    int add(LogFile logFile);

    int update(LogFile logFile);

    LogFile getByDesc();

    List<LogFile> getUnRecord();
}
