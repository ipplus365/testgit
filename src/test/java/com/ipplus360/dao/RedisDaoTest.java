package com.ipplus360.dao;

import com.ipplus360.entity.GeoIP;
import com.ipplus360.entity.UserToken;
import com.ipplus360.util.IPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDaoTest.class);

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private GeoIPDao geoIPDao;

    @Test
    public void getGeoIP() throws Exception {
        String ip = "58.131.162.223";
        GeoIP geoIP = redisDao.getGeoIP(IPUtil.ipToLong(ip));
        System.err.println(geoIP);
    }

    @Test
    public void putGeoIP11() throws Exception {
        long start = System.currentTimeMillis();
        /*for (long i=0; i<800000; i++) {
            LOGGER.info("The {}", i);
            GeoIP geoIP = geoIPDao.getById(i);
            if (geoIP != null) {
                redisDao.putGeoIP(geoIP);
            }
        }*/

        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void putGeoIP() throws Exception {
        String ip = "198.10.23.1";
        long start = System.currentTimeMillis();
        /*for (long i=800000; i<3800000; i++) {
            LOGGER.info("The {}", i);
            GeoIP geoIP = geoIPDao.getById(i);
            if (geoIP != null) {
                redisDao.putGeoIP(geoIP);
            }
        }*/

        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void putGeoIP1() throws Exception {
        String ip = "198.10.23.1";
        long start = System.currentTimeMillis();
        /*for (long i=3800001; i<5800000; i++) {
            LOGGER.info("2 The {}", i);
            GeoIP geoIP = geoIPDao.getById(i);
            if (geoIP != null) {
                redisDao.putGeoIP(geoIP);
            }
        }*/

        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void testToken() {
        UserToken userToken = redisDao.getUserToken("tokens:Fi71xJ0Jocd8DGJNdxTFpyOAaGvp4ityvz0NRRO7mfYQX6QmRVn7115PD4mATOSI");
        System.err.println(userToken);
    }

    @Test
    public void testLimit() throws InterruptedException {
        boolean b = redisDao.isUnLimited("abcde");
        System.out.println(b);
    }
}