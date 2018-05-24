package com.ipplus360.service.impl;

import com.ipplus360.entity.UserToken;
import com.ipplus360.service.TokenService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class TokenServiceImplTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void testAdd() {
        for (int i=0; i<10; i++) {
            String token = RandomStringUtils.randomAlphanumeric(64);
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken.setAvailable(true);
            userToken.setCreatedDate(new Date());
            userToken.setEffectiveDate(new Date());
            //userToken.setProductIdsStr("1,2,3,");
            userToken.setProductId(1L);
            userToken.setUserId(i);

            tokenService.add(userToken);
        }
    }

    @Test
    public void testGetById() {
/*        UserToken token = tokenService.getById(12l);
        token.setTest(true);
        token.setTestCount(5000L);
        tokenService.update(token);
        System.out.println(token);*/

        String token = RandomStringUtils.randomAlphanumeric(64);
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setAvailable(true);
        userToken.setCreatedDate(new Date());
        userToken.setEffectiveDate(new Date());
        //userToken.setProductIdsStr("1,2,3,");
        userToken.setProductId(1L);
        userToken.setUserId(12);
        tokenService.add(userToken);
    }
}