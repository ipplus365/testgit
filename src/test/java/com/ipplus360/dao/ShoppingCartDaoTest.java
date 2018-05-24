package com.ipplus360.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.ShoppingCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
//@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml" })
public class ShoppingCartDaoTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
	@Autowired
    private ShoppingCartDao shoppingCartDao;
	
//	@Test
	public void getByIdTest(){

		logger.info("getById1: {}",shoppingCartDao.getById(1l).getDescription());
	}
	
//	@Test
	public void getAllTest(){
		logger.info("getAll2: {}",shoppingCartDao.getAll().get(1).getDescription());
	}
	
//	@Test
	public void getAllByCartTest(){
		ShoppingCart sc = new ShoppingCart();
		//sc.setUserId(1l);
		sc.setStatus(1);
		List<ShoppingCart> ls = shoppingCartDao.getAllByCart(sc);
		for(ShoppingCart l : ls){
			logger.info("getAllByCart3: {}",l.getDescription());
		}
		//logger.info("getAllByCart3: {}",shoppingCartDao.getAllByCart(sc).get(0).getDescription());
	}
	
	//@Test
	public void getByUseridTest(){
		logger.info("getByUserid4: {}",shoppingCartDao.getAllByUserId(60l));
	}
	
//	@Test
//	@Transactional
	public void insertTest(){
		ShoppingCart cart = new ShoppingCart();
		cart.setDateCreated(new Date());
		//cart.setDateUpdated(new Date());
		cart.setUserId(1l);
		cart.setStatus(0);
		cart.setPrice(new BigDecimal(10001));
		cart.setDescription("购物车测试3");
		shoppingCartDao.insert(cart);
	}
	
//	@Test
	public void updateTest(){
		ShoppingCart cart = shoppingCartDao.getAllByUserId(1l);
		cart.setDescription("购物车更新测试");
		cart.setPrice(new BigDecimal(1112));
		cart.setDateUpdated(new Date());
		shoppingCartDao.update(cart);
	}

}
