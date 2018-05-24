package com.ipplus360.dao;

import com.ipplus360.domain.LoginLocation;
import com.ipplus360.dto.LocateProps;
import com.ipplus360.entity.GeoIP;

import java.util.Map;

/**
 * Created by 辉太郎 on 2017/2/12.
 */
public interface GeoIPDao {
    GeoIP getById(Long id);

    GeoIP getByMinIP(Map<String, Object> params);

    GeoIP getByMinIPTest(Long minip);

    GeoIP getByMinIPTest(Map<String, Object> params);

    LoginLocation getLocationByIp(long ip);

    GeoIP getDistrictInfo(LocateProps props);

    GeoIP getSceneInfo(LocateProps props);

    GeoIP getByMinIPWithScene(Map<String, Object> params);

    GeoIP getByMinIPTestWithScene(Map<String, Object> params);

    GeoIP getDistrictInfoNormal(LocateProps props);
}