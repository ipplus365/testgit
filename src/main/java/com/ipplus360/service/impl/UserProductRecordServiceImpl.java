package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.UserProductRecordDao;
import com.ipplus360.entity.UserProductRecord;
import com.ipplus360.service.UserProductRecordService;

@Service
public class UserProductRecordServiceImpl implements UserProductRecordService {

	@Autowired
    private UserProductRecordDao userProductRecordDao;
	
	@Override
	public UserProductRecord getByUserIdAndProductId(Long userId, Long productId) {
		// TODO Auto-generated method stub
		return userProductRecordDao.getByUserIdAndProductId(userId, productId);
	}

}
