package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.PayConfirmDao;
import com.ipplus360.entity.PayConfirm;
import com.ipplus360.service.PayConfirmService;

/**
 * Created by 辉太郎 on 2017/6/8.
 */
@Service
public class PayConfirmServiceImpl implements PayConfirmService {

    @Autowired
    private PayConfirmDao payConfirmDao;

    @Override
    public int insert(PayConfirm payConfirm) {
        return payConfirmDao.insert(payConfirm);
    }

    @Override
    public int update(PayConfirm payConfirm) {
        return payConfirmDao.update(payConfirm);
    }
}
