package com.ipplus360.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.Order;
import com.ipplus360.util.OrderUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class OrderDaoTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private volatile boolean finished = false;  
	private Order order;
	@Autowired
	private OrderDao orderDao;
	
//	@Before
	public void init(){
		order = new Order();
		order.setUserId(1l);
		order.setOrderSerial(OrderUtil.getOrderId());
		order.setDateCreated(new Date());
		order.setDescription("测试自动生成订单ID");
		order.setStatus(0);
		order.setPrice(new BigDecimal(1231));
	}
	
//	@Test
	public void test() {
		Order order =orderDao.getByUserIdAndOrderId(4l,"IP-1703171710339536");
		logger.info("test :{}",order);
//		logger.info("test :{}",orderDao.getAll());
//		insertTest();
	}
	
	@Test
	public void getOrderIdTest(){
		logger.info("OrderId={}",orderDao.getOrder(2l, "IP-170320626362128"));
	}
	
	@SuppressWarnings("static-access")
	public void insertTest(){
		
		Runnable task2 = () -> { 
			while(!finished){
				init();
				int i = orderDao.insert(order);
				logger.info("插入条数:{}, lastId :{}",i,order.getId());
				finished = true;
				break;
			}
			finished = false;
		};
		for (int i =0;i<2;){
			Thread t = new Thread(task2);
			t.start();
			try {
				t.sleep(1000l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.interrupt();
			 
		        
		}
		
		
	}

}
