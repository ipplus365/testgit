package com.ipplus360.service;

import java.util.Map;

import com.ipplus360.entity.GeoIP;

/**
 * Created by 辉太郎 on 2017/2/15.
 */
public interface GeoIPService {
    GeoIP getById(Long id);

    GeoIP getByMinIP(Map<String, Object> params);
    }
