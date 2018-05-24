package com.ipplus360.service;

import com.ipplus360.entity.Discount;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月8日   
 */

public interface DiscountService {
	//获得打折信息UserProductRecord
	Discount getDiscounts(Discount discount);
}
