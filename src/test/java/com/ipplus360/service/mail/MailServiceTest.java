package com.ipplus360.service.mail;

import com.ipplus360.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 辉太郎 on 2017/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {
        String toEmail = "754345556@qq.com";

        User user = new User();
        user.setEmail(toEmail);
        user.setToken("1234112341234");
        mailService.sendCheckMail("754345556@qq.com", "用户激活邮件", 144444);
        
    }

}