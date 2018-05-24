package com.ipplus360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.ipplus360.entity.User;
import com.ipplus360.web.OrderController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml", "classpath:spring/acaptcha.xml"})
public class OrderControllerTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private MockHttpServletRequest request;   
	
    private MockHttpSession session;
    
    private Model model;
	
	private User user;
    @Autowired
	private OrderController orderController;
    
    
    @Test
	public void test(){
		 try {
			 String orderSerial = "IP-170318241863849";
			 request = new MockHttpServletRequest("POST","/order/addInvoice");
			 request.addParameter("orderSerial", orderSerial);
//			 request.addParameter()
			 session = new MockHttpSession();
			 user = new User();
			 user.setId(2l);
			 
			 
			 logger.info("user--{}", "test");
			 user.setEmail("75434556@qq.com");
			 user.setId(1l);
			 
			 session.setAttribute("user", user);
			 request.setCharacterEncoding("UTF-8");   
			
			 
//			 String test = cartController.getCart(request, session);
//			 assertEquals("/list",test) ;
//			 @SuppressWarnings("unchecked")
//			 ShoppingCart cart =  (ShoppingCart) session.getAttribute("cart");
//			 logger.info("cartItemId:{}", cart.toString());
				 
		 }catch (Exception e) {
			 e.printStackTrace();
			 logger.info("error--{}", e.getMessage());
		}
		
	}
    
	
	

}
