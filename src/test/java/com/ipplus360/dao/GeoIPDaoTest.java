package com.ipplus360.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ipplus360.entity.GeoIP;
import com.ipplus360.util.IPUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sylar on 2017/2/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class GeoIPDaoTest {

    @Autowired
    private GeoIPDao geoIPDao;

    @Test
    public void findById() throws Exception {
        long id = 34563;
        GeoIP geoIP = geoIPDao.getById(id);
        System.out.println(geoIP);
    }

    /*@Test
    public void findByMinIP() throws Exception {
        long ip = IPUtil.ipToLong("1.36.176.198");
        GeoIP geoIP = geoIPDao.getByMinIP(ip);
        System.err.println(geoIP);
        System.out.println();
    }*/

    @Test
    public void testGetIP() {
         String multiarea = "[{\"w\":\"34.8235\",\"j\":\"-118.9441\",\"r\":\"\",\"p\":\"California,\"c\":\"Frazier Park\",\"d\":\"\"}]";
        String multiarea1 = "[{\"w\":\"34.8235\",\"j\":\"-113.986181\",\"r\":\"\",\"p\":\"中国香港\",\"c\":\"Frazier Park\",\"d\":\"\"}]";
        JSONArray jsonArray = JSON.parseArray(multiarea1);

    }

}