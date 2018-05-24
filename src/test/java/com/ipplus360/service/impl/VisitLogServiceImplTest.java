package com.ipplus360.service.impl;

import com.ipplus360.service.VisitLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class VisitLogServiceImplTest {

    @Autowired private VisitLogService visitLogService;

    @Test
    public void recordAndAnalysis() throws Exception {
        visitLogService.recordAndAnalysis();
    }

    @Test
    public void testSendMail() {
        //visitLogService.analysisAndSendMail();
    }

}