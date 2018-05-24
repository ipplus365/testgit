package com.ipplus360.service;

import com.ipplus360.dto.LocateProps;
import com.ipplus360.entity.GeoIP;

/**
 * IP信息相关service
 * Created by 辉太郎 on 2017/3/13.
 */
public interface IPService {

    GeoIP locate(String ip, String key, String user_ip, String source, String coordSuffix, Boolean hasScene);

    String getLocation(String ip);

    GeoIP districtLocate(LocateProps props);

    GeoIP getScene(LocateProps props);
}
