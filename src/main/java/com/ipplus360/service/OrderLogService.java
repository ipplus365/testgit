package com.ipplus360.service;

import java.util.List;

import com.ipplus360.entity.OrderLog;

/**
 * Created by 辉太郎 on 2017/4/26.
 */
public interface OrderLogService {

    List<OrderLog> getByOrderSerial(String orderSerial);

    int insert(OrderLog orderLog);
}
