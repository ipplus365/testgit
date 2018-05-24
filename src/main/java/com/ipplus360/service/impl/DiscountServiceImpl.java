package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.DiscountDao;
import com.ipplus360.entity.Discount;
import com.ipplus360.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
    private DiscountDao discountDao;

	@Override
	public Discount getDiscounts(Discount discount) {
		return discountDao.getDiscounts(discount);
	}

}
