package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.ApiInfoDao;
import com.ipplus360.entity.ApiInfo;
import com.ipplus360.service.ApiInfoDaoService;

@Service
public class ApiInfoDaoServiceImpl implements ApiInfoDaoService {

	@Autowired
	private ApiInfoDao apiInfoDao;
	
	@Override
	public ApiInfo getApiInfo(Long productId) {
		return apiInfoDao.getApiInfo(productId);
	}

}
