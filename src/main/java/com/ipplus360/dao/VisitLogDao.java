package com.ipplus360.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ipplus360.entity.VisitLog;

/**
 * Created by 辉太郎 on 2017/7/4.
 */
public interface VisitLogDao {

    int add(VisitLog visitLog);

    int addBatch(List<VisitLog> visitLogList);

    Long getIPCounts(Date date);

    List<Map<String, Object>> getSources(Date date);

    /**
     * 从官网进入商城的IP数量
     * @param date
     * @return
     */
    Long getCountsFromGw(Date date);

}
