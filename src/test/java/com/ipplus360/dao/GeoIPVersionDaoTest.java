package com.ipplus360.dao;

import com.ipplus360.entity.GeoIPVersion;
import com.ipplus360.entity.IPDownload;
import com.ipplus360.service.GeoIPVersionService;
import com.ipplus360.service.IPDownloadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 辉太郎 on 2017/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class GeoIPVersionDaoTest {
    @Autowired
    private GeoIPVersionDao geoIPVersionDao;

    private GeoIPVersion geoIPVersion;

    private IPDownload ipDownload;

    @Autowired
    private GeoIPVersionService geoIPVersionService;

    @Autowired
    private IPDownloadService ipDownloadService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void Test(){
        geoIPVersion = geoIPVersionService.getByAvailable(true);
        ipDownload = ipDownloadService.getByVersionId(geoIPVersion.getId());
        logger.info("GeoIPVersion:{}",geoIPVersion);
        logger.info("ipDownload:{}",ipDownload);
    }
}
