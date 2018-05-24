package com.ipplus360.service;


import java.math.BigDecimal;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ipplus360.dao.CartItemDao;
import com.ipplus360.entity.Product;
import com.ipplus360.entity.ProductAttr;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.CartItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class CartItemServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CartItem ci ;
	
	@Autowired
	private CartItemService carItemSerice;
	@Autowired private ProductAttrService productAttrService;
	@Autowired private ProductService productService;
	@Autowired
	CartItemDao cartItemDao;
	
	//@Test
	public void getByIdTest() {
		logger.info("cartItemSrviceTest--{}",carItemSerice.getById(28L,397L).getPrice());
	}
	
	//@Test
	public void getByCartIdTest(){
		logger.info("cartItemSrviceTest--{}",carItemSerice.getAllByCartId(1l));
	}
	
	//@Test
	public void getByCartItemTest(){
		ci = carItemSerice.getById(28L,397L);
		ci.setId(2l);
		logger.info("cartItemSrviceTest.getByCartItemTest--{}",carItemSerice.getAllByCartId(ci.getId()));
	}
	
	//@Test
	public void insertTest(){
		ci = carItemSerice.getById(28L,397L);
		ci.setId(2l);
		ci.setPrice(new BigDecimal(250));
		logger.info("cartItemSrviceTest.getByCartItemTest--{}",carItemSerice.insert(ci));
	}
	
	//@Test
	//@Transactional
	public void deleteTest(){
		logger.info("cartItemSrviceTest.getByCartItemTest--{}",carItemSerice.delete(2l));
	}
	
//	@Test
//	@Transactional
	public void deleteByCartIdTest(){
		logger.info("cartItemSrviceTest.getByCartItemTest--{}",carItemSerice.deleteByCartId(1l));
	}
	
	@Test
	@Transactional
	public void update(){
		ci = carItemSerice.getById(28L,397L);
		ci.setPrice(new BigDecimal(11));
		logger.info("cartItemSrviceTest.getByCartItemTest--{}",carItemSerice.update(ci));
	}

	@Test
	public void testGet() {

		CartItem item = cartItemDao.get();
		String attrIds = item.getAttrIds();
		System.err.println("attrIds:" + attrIds);
		String[] strings = StringUtils.split(attrIds, ",");
		Integer[] ids = new Integer[]{Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])};

		List<ProductAttr> attrs = productAttrService.getAttrsByIds(ids);
		System.err.println(JSON.toJSONString(attrs));
	}

	@Test
	public void testGet1() {

		CartItem item = cartItemDao.get1();
		String attrIds = item.getAttrIds();
		System.err.println("attrIds:" + attrIds);
		String[] strings = StringUtils.split(attrIds, ",");
		Integer[] ids = new Integer[]{Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])};

		List<ProductAttr> attrs = productAttrService.getAttrsByIds(ids);
		System.err.println(JSON.toJSONString(attrs));
	}
}
