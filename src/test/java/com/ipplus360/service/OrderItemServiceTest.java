package com.ipplus360.service;


import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ipplus360.entity.OrderItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class OrderItemServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderItem oi ;
	
	@Autowired
	private OrderItemService orderItemSerice;
	
	@Test
	public void getByIdTest() {
		logger.info("orderItemSrviceTest--{}",orderItemSerice.getById(6l));
	}
	
//	@Test
	public void getByOrderIdTest(){
		logger.info("orderItemSrviceTest--{}",orderItemSerice.getAll(1l));
	}
	
	//@Test
/*	public void getByOrderItemTest(){
		oi = orderItemSerice.getById(1l);
		oi.setId(2l);
		logger.info("orderItemSrviceTest.getByOrderItemTest--{}",orderItemSerice.getAll(oi));
	}*/
	
	//@Test
	public void insertTest(){
		oi = orderItemSerice.getById(1l);
		oi.setId(2l);
		oi.setPrice(new BigDecimal(250));
		logger.info("orderItemSrviceTest.getByOrderItemTest--{}",orderItemSerice.insert(oi));
	}
	
	//@Test
	//@Transactional
	public void deleteTest(){
		logger.info("orderItemSrviceTest.getByOrderItemTest--{}",orderItemSerice.delete(2l));
	}
	
//	@Test
//	@Transactional
	public void deleteByOrderIdTest(){
		logger.info("orderItemSrviceTest.getByOrderItemTest--{}",orderItemSerice.deleteByOrderId(1l));
	}
	
//	@Test
//	@Transactional
	public void update(){
		oi = orderItemSerice.getById(1l);
		oi.setPrice(new BigDecimal(11));
		logger.info("orderItemSrviceTest.getByOrderItemTest--{}",orderItemSerice.update(oi));
	}

}
