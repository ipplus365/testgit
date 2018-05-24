package com.ipplus360.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.OrderLogDao;
import com.ipplus360.entity.OrderLog;
import com.ipplus360.service.OrderLogService;

/**
 * Created by 辉太郎 on 2017/4/26.
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Autowired
    OrderLogDao orderLogDao;

    @Override
    public List<OrderLog> getByOrderSerial(String orderSerial) {

        return orderLogDao.getByOrderSerial(orderSerial);
    }

    @Override
    public int insert(OrderLog orderLog) {

        return orderLogDao.insert(orderLog);
    }
}
