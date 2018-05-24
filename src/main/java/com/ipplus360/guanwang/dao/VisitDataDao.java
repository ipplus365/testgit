package com.ipplus360.guanwang.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 辉太郎 on 2017/7/31.
 */
public interface VisitDataDao {

    Long getIPCounts(Date visitTime);

    List<Map<String, Object>> getSources(Date visitTime);

    List<Map<String, Object>> getGwSourceUrls(Date visitTime);
}
