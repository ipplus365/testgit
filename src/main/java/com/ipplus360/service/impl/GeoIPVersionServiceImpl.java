package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.GeoIPVersionDao;
import com.ipplus360.entity.GeoIPVersion;
import com.ipplus360.service.GeoIPVersionService;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
@Service
public class GeoIPVersionServiceImpl implements GeoIPVersionService {
    @Autowired
    private GeoIPVersionDao geoIPVersionDao;

    public GeoIPVersion getByAvailable(boolean isAvailable){

        return geoIPVersionDao.getByAvailable(isAvailable);
    }

    @Override
    public GeoIPVersion getCurrentVersion() {
        return geoIPVersionDao.getCurrentVersion();
    }
}
