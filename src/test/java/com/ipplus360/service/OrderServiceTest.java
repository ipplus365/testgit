package com.ipplus360.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class OrderServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Order order;
	
	@Autowired
	private OrderService orderService;
	
	@Test
	@Transactional
	public void test() {
//		init();
//		for(int i = 0; i <= 5;i++) insetTest(order);
//		for(Long i = 12l; i <= 17l;i++) getByIdTest(i);
//		getAllTest();
//		getAllTest(order);
//		getByUserIdTest(1l);
//		updateTest(order);
		getAll(60L);
	}
	
	public void init(){
		order = new Order();
		order.setId(1l);
//		order.setDateCreated(new Date());
		//order.setDateUpdated(new Date());
//		order.setUserId( 1l);
		order.setStatus(1);
//		order.setOrderSerial("xxx112");
//		order.setPrice(new BigDecimal(10001));
		order.setDescription("OrderService测试5");
	}
	
	public void getAll(Long UserId){
		List<Order> order = orderService.getAll(null);
		logger.info("getAll:{}",order);
	}
	
	public void getAllTest(){
		List<Order> ol = orderService.getAll();
		for(Order o : ol){
			logger.info("getById:id--{}, Price--{}, Desc--{}",o.getId(),o.getPrice(),o.getDescription());
		}
	}
	
	public void getAllTest(Order order){
		List<Order> ol = orderService.getAll(order);
		for(Order o : ol){
			logger.info("getById:id--{}, Price--{}, Desc--{}",o.getId(),o.getPrice(),o.getDescription());
		}
	}
	
	/*public void getByUserIdTest(Long orderId){
		order = orderService.getAll(orderId);
		logger.info("getById:id--{}, Price--{}, Desc--{}",order.getId(),order.getPrice(),order.getDescription());
	}*/
	

	public void updateTest(Order order){
		orderService.update(order);
	}
	
/*	public void insetTest(Order order){
		orderService.insert(order);
	}*/

}
