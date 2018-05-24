package com.ipplus360.dao;

import com.ipplus360.entity.ProductDesc;

public interface ProductDescDao {
	/**
	 * 根据ID获取产品描述
	 * @param productId
	 * @return
	 */
	ProductDesc getByProductId(Long productId);
}
