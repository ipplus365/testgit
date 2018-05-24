package com.ipplus360.service.impl;

import com.ipplus360.entity.GeoIP;
import com.ipplus360.service.IPService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class IPServiceImlTest {
    @Test
    public void getLocation() throws Exception {
    }

    @Autowired
    private IPService ipService;

    @Test
    public void locate() throws Exception {
        String ip = "1.36.176.198";
        String token = "53zffaS6sUFHm52MFIH4vZnX6xRGowgceaUh3l7wWsrtrgzTxPg625gpYw32ncqT";

        GeoIP geoIP = ipService.locate(ip, token, null, null, "", true);

        Assert.assertNull(geoIP);
    }

    @Test
    public void testGetLocation() throws Exception {
        String location = ipService.getLocation("1.36.176.198");
        assertEquals("中国香港", location);
        String location1 = ipService.getLocation("1.0.37.0");
        assertEquals("广东省", location1);
        String location2 = ipService.getLocation("1.14.50.27");
        assertEquals("大连市", location2);
        String location3 = ipService.getLocation("1.0.0.0");
        assertEquals("澳大利亚", location3);
        String location4 = ipService.getLocation("0.255.255.255");
        assertNull(location4);
    }

}