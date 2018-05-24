package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.DeliveryDao;
import com.ipplus360.entity.Delivery;
import com.ipplus360.service.DeliveryService;

/**
 * Created by 辉太郎 on 2017/6/7.
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;

    @Override
    public int insert(Delivery delivery) {
        return deliveryDao.insert(delivery);
    }
}
