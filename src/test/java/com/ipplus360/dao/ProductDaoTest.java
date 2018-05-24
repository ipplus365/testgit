package com.ipplus360.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.Product;
import com.mysql.fabric.xmlrpc.base.Data;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class ProductDaoTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private ProductDao productDao;
	
	@Autowired
    private PricePackageDao pricePackageDao;
	
//	@Test
    public void findByName() throws Exception {
		List<Product> productTest= productDao.listAll();
		/*System.out.println(productTest.get(0).getSkuId());
		System.out.println(productTest.get(0).getProductName());
		System.out.println(productTest.get(0).getEvaluate());
		System.out.println(productTest.get(0).getPageviews());*/
		
		/*PricePackage pricePackage = pricePackageDao.getById(1L);
			System.out.println("Price="+pricePackage.getPrice());
			System.out.println("Type="+pricePackage.getType());
			System.out.println("Id="+pricePackage.getId());*/
			
		
		
		/*String[] Amount = pricePackageDao.getAmountById(1L);
		System.out.println("Amount"+Amount[0]);*/
		
		
		/*Map getType = new HashMap();
		getType.put("nowtime",new Date());
		getType.put("id",1);
		System.out.println(getType.get("nowtime").toString());
		List<String> Type = pricePackageDao.getPackageTypeById(getType);
		System.out.println(Type.get(0));*/
		//System.out.println(Type.get(0));
		
		
		Map getPrice = new HashMap();
		getPrice.put("amount",5000000L);
		getPrice.put("id",1L);
		getPrice.put("accuracy", 1);
		PricePackage pricePackagetest = pricePackageDao.getProductPrice(getPrice);
		System.out.println(pricePackagetest.getPrice());
		System.out.println(pricePackagetest.getUnit());
		/*System.out.println(pricePackage.getPrice());*/
	}
    @Test
    public void getAllTest(){
    	Product product = productDao.getProductById(1l);
    	logger.info("getProduct:{}",product);
    }
	
}
