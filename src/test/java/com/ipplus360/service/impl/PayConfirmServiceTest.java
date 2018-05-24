package com.ipplus360.service.impl;

import com.ipplus360.entity.Delivery;
import com.ipplus360.entity.PayConfirm;
import com.ipplus360.service.DeliveryService;
import com.ipplus360.service.PayConfirmService;
import javafx.animation.PauseTransition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 辉太郎 on 2017/6/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class PayConfirmServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PayConfirmService payConfirmService;

    @Autowired
    private DeliveryService deliveryService;

    @Test
    public void test(){
        PayConfirm payConfirm = new PayConfirm(2L,"ip-1231245",new Date(),new BigDecimal(100),"在线支付","772171@qwe.com");
        payConfirmService.insert(payConfirm);
        logger.info("TEST:{}",payConfirm.getId());
        payConfirm.setTokenIdsStr("1,2,3,");
        payConfirm.setCounts(1000L);
        payConfirm.setDateUpdated(new Date());
        payConfirmService.update(payConfirm);
        Delivery delivery = new Delivery(null,"ip-test","系统");
        delivery.setCounts(100000L);
        delivery.setDeliveryDate(new Date());
        delivery.setToken("testtaADASADGASFAGASDASD");
        deliveryService.insert(delivery);
        logger.info("DeliverId:{}",delivery.getId());
    }
}
