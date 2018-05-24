package com.ipplus360.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.User;
import com.ipplus360.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class UserServiceTest {
	
	@Autowired
    private UserService userService;

	
	
	@Test
    public void userTest() throws Exception {
		//String email="74345556@qq.com";
		System.out.println(11);

		//User usertest=userService.userSignIn(email,);
		//System.out.println(usertest.getPassword());
		//System.out.println(test);
		
		/*User singup= new User();
		String email="74345556@qq.com";
		singup.setPassword("zafeifei");
		singup.setPasswordSalt("1e3a8dbbd47f188bebacf9fef423a9b6783023de60964b50");
		
		
		
		String newPwd = "zafeiji";
		userService.changepwd(singup, newPwd);*/
		userService.sendEmailCheck("754345556@qq.com");
		
		/*singup.setToken_exptime(email);
		singup.setToken_status(0);*/
		
		/*int usersingup=userService.userSignUp(singup);
		System.out.println(usersingup);*/
		
	}
	
}
