package com.ipplus360.service;

import com.ipplus360.entity.Product;

import java.util.List;

/**
 * 商品信息服务接口</br>
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
public interface ProductService {
	//获得所有商品列表
	List<Product> listAll();
	
	//获得商品详情
	Product getById(Long id);

	Product getForToken(Long id);

    Product getOne(Long productId);
}
