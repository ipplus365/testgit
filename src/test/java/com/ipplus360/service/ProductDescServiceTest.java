package com.ipplus360.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class ProductDescServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductDescService productDescService;

	@Autowired
	private OrganizationService organizationService;

	@Test
	public void test() {

		logger.info("test----{}",organizationService.getByToken("0ce394ad65e94ef4ad4791c847079629"));
		//logger.info("test----{}",productDescService.getByProductId(1l).getApplicationAreas());
		
	}

}
