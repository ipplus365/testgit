package com.ipplus360.dao;

import com.ipplus360.entity.UserLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by 辉太郎 on 2017/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class UserLogDaoTest {

    @Autowired
    private UserLogDao userLogDao;

    @Test
    public void testAdd() throws ParseException {
        String logS2 = "2017-05-31 10:09:41,798 [qtp126288666-44] INFO  record - |WEB|test:|613fff49160a42028ff910d87798ae4e|12|192.168.1.100" +
                "|20H2hpgJJVUZ3n5Ju1bH4uFkcD3V0P9fuu6DOWLVcBcfQ6KpYefcc2gIXVhUQw21|127246|19181766|1|3_3_3|Wed May 31 10:09:41 CST 2017";

        String[] split = logS2.split("\\|");

        String ss1 = split[0].split(",")[0];
        //System.err.println("ss1:" + ss1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        UserLog userLog = new UserLog();

        userLog.setLogDate(sdf.parse(ss1));
        userLog.setSource(split[1]);
        userLog.setTest(split[2]);
        userLog.setLogId(split[3]);
        userLog.setUserId(Long.parseLong(split[4]));
        userLog.setUserIp(split[5]);
        userLog.setToken(split[6]);
        userLog.setIpId(split[7]);
        userLog.setLocateIp(split[8]);
        userLog.setProductId(split[9]);
        userLog.setVersion(split[10]);

        userLogDao.add(userLog);
        System.out.println(userLog);
    }
}
