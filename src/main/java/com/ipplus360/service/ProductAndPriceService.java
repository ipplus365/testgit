package com.ipplus360.service;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.Product;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月4日   
 */

public interface ProductAndPriceService {
	//获得所有商品列表
	List<Product> listAll();
	
	//获得商品详情
	Product getProductById(Long id);
	
	//获得商品套餐详情
	PricePackage getById(Long id);
	
	//获得套餐类型列表
	List<String> getPackageTypeList(Map getType);
	
	//获得套餐数量列表
	List<PricePackage> getAmountList(Map getAmount);
	
	//根据选择套餐规格得到价格
	PricePackage getProductPrice(Map getPrice);
	
	//增加浏览量
	int updatePageviews(Long id);
	
	//增加评论量
	int updateEvaluate(Long id);

}
