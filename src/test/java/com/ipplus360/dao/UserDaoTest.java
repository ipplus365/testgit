package com.ipplus360.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class UserDaoTest {
	
	
	
	@Autowired
    private UserDao userDao;
	//private User singup;
	
	
	
	@Test
    public void findByName() throws Exception {
		String email="1111";
		String password="0000";
		System.out.println(11);
		
		
		User user=new User();
		user.setEmail(email);
		user.setPassword(password);
		
		User singup=userDao.userSignIn(user);
		//System.out.println(test.getPassword());
		System.err.println(singup.getPassword());
		//System.out.println(test);
		
		//singup = null;
		/*singup.setUsername("jack");
		singup.setRealname("jack");
		singup.setEmail("1@qq.com");
		singup.setMobile("12345678911");
		singup.setPassword("12345678911");
		singup.setPasswordSalt("12345678911");
		singup.setOrganizationId(0L);
		singup.setDateCreated(new Date());
		singup.setToken("0");
		singup.setToken_exptime(email);
		singup.setToken_status(0);
		System.out.println(singup);
		
		int usersingup=userDao.userSignUp(singup);
		System.out.println(usersingup);
		*/
	}
	
}
