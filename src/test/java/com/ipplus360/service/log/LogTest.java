package com.ipplus360.service.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 辉太郎 on 2017/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class LogTest {
    private static final Logger LOGGER = LoggerFactory.getLogger("logMysql");

    @Test
    public void testAppend() {

        LOGGER.info("{} {} {} {} {} {} {} {} {} {} {}", new Object[]{
                "WEB", true, UUID.randomUUID().toString().replace("-", ""), 1, "192.168.1.250", "abcdefghijklmnopqrstuvwxyz",
                11111, 11234, 1, "1-1-1", new Date()
        });

    }
}
