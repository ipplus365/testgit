package com.ipplus360.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.Discount;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class DiscountServiceTest {
	
	private Discount discount;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private DiscountService discountService;

	@Test
    public void DiscountTest() throws Exception {
		Discount ds  = new Discount();
		ds.setAccuracyId(1);
		ds.setPackageAmount(36);
		ds.setPricePackageId(3l);
		
		discount= discountService.getDiscounts(ds);
		logger.info("折扣信息:{}",discount);
	}
	
}
