package com.ipplus360.service.impl;

import com.ipplus360.dao.SalesActivityDao;
import com.ipplus360.entity.SalesActivity;
import com.ipplus360.service.SalesActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 辉太郎 on 2017/10/11.
 */
@Service
public class SalesActivityServiceImpl implements SalesActivityService {

    @Autowired private SalesActivityDao activityDao;

    @Override
    public int add(SalesActivity salesActivity) {
        return activityDao.add(salesActivity);
    }

    @Override
    public int update(SalesActivity salesActivity) {
        return activityDao.update(salesActivity);
    }

    @Override
    public int delete(Integer id) {
        return activityDao.delete(id);
    }

    @Override
    public SalesActivity getById(Integer id) {
        return activityDao.getById(id);
    }

    @Override
    public List<SalesActivity> getByUserId(Long userId) {
        return activityDao.getByUserId(userId);
    }
}
