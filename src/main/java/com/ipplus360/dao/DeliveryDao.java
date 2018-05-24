package com.ipplus360.dao;

import com.ipplus360.entity.Delivery;

/**
 * Created by 辉太郎 on 2017/6/7.
 */
public interface DeliveryDao {
    /**
     * 添加发货详情
     * @param delivery
     * @return
     */
    int insert(Delivery delivery);
}
