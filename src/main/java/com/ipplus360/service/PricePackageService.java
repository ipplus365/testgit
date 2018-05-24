package com.ipplus360.service;

import com.ipplus360.entity.PricePackage;

import java.util.List;

/**
 * 价格套餐包服务接口</br>
 * Created by 辉太郎 on 2017/3/07.</br>
 * @author wangguoyan
 */
public interface PricePackageService {

    /**
     * 根据ID获取价格套餐包
     * @param id
     * @return
     */
    PricePackage getById(Long id);

    /**
     * 根据ID获取价格套餐包
     * @param id
     * @return
     */
    PricePackage getPricePackageById(Long id);

    PricePackage getByPricePackage(PricePackage entity);

    /**
     * 获取所有价格套餐包
     * @return
     */
    List<PricePackage> getAll();

    /**
     * 根据产品ID获取所有价格套餐包
     * @return
     */
    List<PricePackage> getAll(Long productId);


    List<PricePackage> getAll(Long productId,Long accuracyId);


    /**
     * 根据产品id获取对应价格包
     * @param productId id
     * @return list
     * @Author ll(lijian@ipplus360.com)
     * @since 2017年9月18日
     */
    List<PricePackage> getPackagesByProduct(Long productId);

    PricePackage getByPriceId(Long id);
}