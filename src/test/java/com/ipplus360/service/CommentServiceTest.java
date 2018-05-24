package com.ipplus360.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipplus360.entity.Comment;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class CommentServiceTest {
	
	@Autowired
    private CommentService commentService;

	
	
	@Test
    public void userTest() throws Exception {
		//String email="74345556@qq.com";
		System.out.println(11);
		
		//User usertest=userService.userSignIn(email,);
		//System.out.println(usertest.getPassword());
		//System.out.println(test);
		
		/*Map getcomment = new HashMap();
		getcomment.put("productid", 2);
		getcomment.put("userid", 2);
		getcomment.put("comment", "sssss");
		
		commentService.add(getcomment);*/
		
		Map getcommentbyuser = new HashMap();
		getcommentbyuser.put("productid", 1L);
		List<Comment> comment=commentService.getCommentByProduct(getcommentbyuser);
		System.out.println(comment.get(0));
		
	}
	
}
