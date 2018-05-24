package com.ipplus360.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.ipplus360.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml", "classpath:spring/acaptcha.xml"})
public class UserControllerTest {
	
	
	private  MockHttpServletRequest request ;
    private  MockHttpServletResponse response ;
    
    //省略set get函数
    @Before
    public void before() throws Exception {
    	
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testSelectUserById(){
    	
    	

		User singup= new User();
		String email="754345556@qq.com";
		singup.setUsername("jack1");
		singup.setRealname("jack");
		singup.setEmail(email);
		singup.setMobile("1234567891");
		singup.setPassword("12345678911");
		singup.setPasswordSalt("12345678911");
		singup.setOrganizationId(1L);
		singup.setDateCreated(null);
		singup.setToken("1");
		//singup.setToken_exptime(email);
		//singup.setToken_status(0);
		
    	//MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        
    	/*request.setRequestURI("/reg/reg");
    	request.setCharacterEncoding("UTF-8");   
    	request.setMethod("post");
    	request.addHeader("user", singup);*/
    	
    	//String result = userSignUpController.login(request,);
		Model model = null;
    	//ModelAndView result = userSignUpController.userSignUp(request, singup ,  model);
    	//System.out.println("result"+result);
    	//userSignUpController.login(singup);
    	
    	/*MockHttpServletRequest req = new MockHttpServletRequest();  
        ModelAndView mv = userController.view(1L, req);  
  */
        //ModelAndViewAssert.assertViewName(result, "reg/userSignUp");  
        //ModelAndViewAssert.assertModelAttributeAvailable(result, "user");  
    	
        
        
    }
	
}
