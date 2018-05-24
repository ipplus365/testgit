package com.ipplus360.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class ShoppinCartServiceTest {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ShoppingCart shoppingCart;
	
//	@Test
	public void getByIdTest() {
		logger.info("1--getById--{}",shoppingCartService.getById(1l).getDescription());
	}
	
	//@Test
	public void getByUserId(){
		logger.info("2--getById--{}",shoppingCartService.getByUserId(1l).getDescription());
	}
	
	@Test
	public void getAll(){
//		List<ShoppingCart> lsc = shoppingCartService.getAll();
//		for(ShoppingCart sc : lsc){
//			logger.info("3--getAll--{}",sc.getDescription());
//		}

		ShoppingCart cart = shoppingCartService.getCountByUserId(2L);
		logger.info("CartINFO-----{}",cart);
	}
	
//	@Test
	public void getAllByEntity(){
		shoppingCart = new ShoppingCart();
		shoppingCart.setStatus(0);
		shoppingCart.setUserId(1l);
		List<ShoppingCart> lsc = shoppingCartService.getAll(shoppingCart);
		
		for(ShoppingCart sc : lsc){
			logger.info("4--getAll--{}",sc.getDescription());
		}
	}
	
//	@Test
	public void updateTest() {
		shoppingCart = shoppingCartService.getById(1l);
		shoppingCart.setPrice(new BigDecimal(12));
		shoppingCart.setDescription("购物车测试1" + new Date());
		shoppingCartService.update(shoppingCart);

	} 

}
