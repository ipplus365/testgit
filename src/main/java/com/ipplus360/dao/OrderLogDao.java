package com.ipplus360.dao;

import java.util.List;

import com.ipplus360.entity.OrderLog;

/**
 * Created by 辉太郎 on 2017/4/26.
 */
public interface OrderLogDao {

    /**
     * 获取订单操作记录
     * @param orderSerial 订单序列号
     * @return 订单操作记录
     */
    List<OrderLog> getByOrderSerial(String orderSerial);

    /**
     * 保存订单操作记录
     * @param orderLog 订单操作记录
     * @return
     */
    int insert(OrderLog orderLog);
}
