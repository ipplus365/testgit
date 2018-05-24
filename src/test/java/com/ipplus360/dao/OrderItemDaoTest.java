package com.ipplus360.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class OrderItemDaoTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private OrderItem oi;
	
	@Autowired
    private OrderItemDao orderItemDao;
    
	@Test
	public void test(){
		
//		for(int i = 0; i <= 5;i++) insetTest();
//		getByIdTest(51l);
//		updateTest();
//		getAllByOrderItemTest();
//		getAllByOrderIdTest(149315L);
//		getAll();
		insetListTest();
//		deleteTest(1l);
//		deleteByOrderIdTest(149316l);
	}
	
	public void insetTest(){
		Product pd = new Product();
		PricePackage pp = new PricePackage();
		pp.setId(1l);
		pd.setId(1l);
		oi = new OrderItem();
		oi.setItemNum(2);
		oi.setOrderId(1l);
		oi.setPrice(new BigDecimal(100));
		oi.setProduct(pd);
		oi.setPricePackage(pp);
		orderItemDao.insert(oi);
	}
	
	public void insetListTest(){
		Product pd = new Product();
		PricePackage pp = new PricePackage();
		pp.setId(1l);
		pd.setId(1l);
		oi = new OrderItem();
		oi.setItemNum(10);
		oi.setOrderId(1l);
		oi.setPrice(new BigDecimal(100));
		oi.setProduct(pd);
		oi.setPricePackage(pp);
		
		List<OrderItem> list = new ArrayList<OrderItem>();
	
		for(int i =0;i<10;i++){
			list.add(oi);
		}
			
		
		orderItemDao.insertList(list);
	}
	
	public void getByIdTest(Long id){
		oi = orderItemDao.getById(id);
		logger.info("getById:{}",oi);
	}
	
	public void getAllByOrderItemTest(){
		oi =new OrderItem(new Product(1l));
//		Product p1 = new Product();
//		PricePackage p2 = new PricePackage();
//		p1.setId(2l);
//		p2.setId(1l);
//		oi.setId(4l);
//		oi.setPricePackage(p2);
//		oi.setProduct(p1);
//		oi.setItemNum(2);
//		oi.setPrice(new BigDecimal(100));
		logger.info("test--{}",oi);
		
//		List<OrderItem> oil = orderItemDao.getAllByOrderItem(oi);
//		logger.info("getById--{}",oil);
	}
	
	public void getAllByOrderIdTest(Long id){
		List<OrderItem> oil = orderItemDao.getAllByOrderId(id);
		logger.info("getAllByOrderId--{}",oil);
	}
	
	public void getAll(){
		List<OrderItem> oil = orderItemDao.getAll();
		logger.info("getAllByOrderId--{}",oil);
	}
	public void deleteTest(Long id){
		orderItemDao.delete(id);
	}
	
	public void deleteByOrderIdTest(Long id){
		orderItemDao.deleteByOrderId(id);
	}
	
	@Transactional
	public void updateTest(){
		oi =new OrderItem();
		Product p1 = new Product();
		PricePackage p2 = new PricePackage();
		p1.setId(2l);
		p2.setId(3l);
		oi.setId(4l);
		oi.setPricePackage(p2);
		oi.setProduct(p1);
		oi.setPrice(new BigDecimal(0.1125));
		logger.info("test--{}",oi.getId());
		orderItemDao.update(oi);
	}

}
