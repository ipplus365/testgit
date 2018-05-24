package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.Product;

/**
 * Created by 辉太郎 on 2017/2/23.
 */
public interface ProductDao {

    Product getOne(Long productId);

    /***********************以下为以前遗留不规范方法 by ll**************************/
	//得到商品详情
    Product getProductById(Long id);

    //得到商品列表
    List<Product> listAll();
    
    //增加浏览量
    int updatePageviews(Long id);

    int add(Product product);

    int update(Product product);

	Product getById(Long id);

	//增加评论量
	int updateEvaluate(Long id);

    List<Product> getByIds(List<Long> productIds);
    /***********************以上为以前遗留不规范方法 by ll**************************/
}
