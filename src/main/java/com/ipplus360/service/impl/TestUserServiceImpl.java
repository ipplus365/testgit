package com.ipplus360.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.TestUserDao;
import com.ipplus360.entity.TestUser;
import com.ipplus360.service.TestUserService;

@Service
public class TestUserServiceImpl implements TestUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	TestUserDao testUserDao;
	

	@Override
	public int add(TestUser testUser) {
		return testUserDao.add(testUser);
	}

}
