package com.ipplus360.dao;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.PricePackage;

/**
 * Created by 辉太郎 on 2017/2/23.
 */
public interface PricePackageDao {
	
	//根据id得到商品详情
    PricePackage getById(Long id);
    
    //得到套餐类型列表
    List<String> getPackageTypeById(Map getType);
    PricePackage getPricePackageById(Long id);
    //得到套餐数量列表
    List<PricePackage> getAmountById(Map getAmount);

    List<PricePackage> getByProductId(Long productId);

    List<PricePackage> getByProductIdAndAccuracyId(Map<String,Long> parm);
    //根据选择套餐规格得到价格
  	PricePackage getProductPrice(Map getPrice);

    int add(PricePackage pricePackage);
    
    int insert(PricePackage pricePackage);

    int update(PricePackage pricePackage);

	PricePackage getByPricePackage(PricePackage pricePackage);

    List<PricePackage> getPackagesByProduct(Long productId);

    PricePackage getByPriceId(Long id);
}
