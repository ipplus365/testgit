package com.ipplus360.dao;

import static org.junit.Assert.*;

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

import com.ipplus360.entity.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml" })
public class CommentDapTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void test() {
//		List<Comment> comment = commentDao.getCommentByUserId(3l);
		Map getcomment = new HashMap();
		getcomment.put("productid", 1L);
		List<Comment> comment = commentDao.getCommentByProduct(getcomment);
		logger.info("CommentTest:{}",comment);
	}

}
