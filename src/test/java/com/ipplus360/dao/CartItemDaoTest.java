package com.ipplus360.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class CartItemDaoTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CartItem ci;
	
	@Autowired
    private CartItemDao cartItemDao;
    
	@Test
	public void getByIdTest(){
		ci = cartItemDao.getById(397L,28L);
//		ci.setPrice(new BigDecimal(21));
//		List<CartItem> l = cartItemDao.getAllByCartId(ci.getId());
//		ci = l.get(0);
		logger.info("getByIdTest:{}",ci);
	}
	
//	@Test
//	@Transactional
	public void updateTest(){
		ci =new CartItem();
		Product p1 = new Product();
		PricePackage p2 = new PricePackage();
		p1.setId(1l);
		p2.setId(1l);
		ci.setId(1l);
		ci.setPricePackage(p2);
		ci.setProduct(p1);
		ci.setPrice(new BigDecimal(10000));
		logger.info("test--{}",ci.getId());
		cartItemDao.update(ci);
	}

}
