package com.ipplus360.service;

import java.util.List;

import com.ipplus360.entity.VisitLog;

/**
 * Created by 辉太郎 on 2017/7/4.
 */
public interface VisitLogService {

    int add(VisitLog visitLog);

    int addBatch(List<VisitLog> visitLogList);

    void recordAndAnalysis();
}
