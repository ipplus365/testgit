package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.ProductDescDao;
import com.ipplus360.entity.ProductDesc;
import com.ipplus360.service.ProductDescService;

@Service
public class ProductDescServiceImpl implements ProductDescService {

	@Autowired
	ProductDescDao productDescDao;
	@Override
	public ProductDesc getByProductId(Long productId) {
		
		return productDescDao.getByProductId(productId);
	}

}
