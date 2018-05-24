package com.ipplus360.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.GeoIPDao;
import com.ipplus360.entity.GeoIP;
import com.ipplus360.service.GeoIPService;

/**
 * Created by 辉太郎 on 2017/2/15.
 */
@Service
public class GeoIPServiceImpl implements GeoIPService {

    @Autowired
    private GeoIPDao geoIPDao;

    @Override
    public GeoIP getById(Long id) {
        return geoIPDao.getById(id);
    }

    @Override
    public GeoIP getByMinIP(Map<String, Object> params) {
        return geoIPDao.getByMinIP(params);
    }
}
