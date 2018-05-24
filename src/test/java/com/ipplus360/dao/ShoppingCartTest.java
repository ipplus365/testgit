package com.ipplus360.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class ShoppingCartTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private ShoppingCartDao shoppingCartDao;
	
	@Test
	public void getByIdTest(){

		logger.info("getById: {}",shoppingCartDao.getById(1l).getDescription());
	}
	
	//@Test
	public void getAllTest(){
		logger.info("getAll: {}",shoppingCartDao.getAll().get(1).getDescription());
	}
	
	//@Test
	public void getAllByCartTest(){
		logger.info("getAllByCart: {}",shoppingCartDao.getAllByCart(new ShoppingCart(){
			@Override
			public void setId(Long id) {
				super.setId(1l);
			}
		}).get(0).getDescription());
	}
	
	//@Test 
	public void getByUseridTest(){
		logger.info("getByUserid: {}",shoppingCartDao.getAllByUserId(1l).getDescription());
	}

}
