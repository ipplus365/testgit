package com.ipplus360.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class PricePackageDaoTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PricePackageDao pricePackageDao;
	
	@Test
	public void test() {
		for(Long i = 1l;i < 10l;i++)
		logger.info("TestPricPackage: {}",pricePackageDao.getPricePackageById(1l));
	}

}
