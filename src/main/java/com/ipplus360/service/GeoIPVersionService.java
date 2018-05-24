package com.ipplus360.service;

import com.ipplus360.entity.GeoIPVersion;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
public interface GeoIPVersionService {

    GeoIPVersion getByAvailable(boolean isAvailable);

    GeoIPVersion getCurrentVersion();
}
