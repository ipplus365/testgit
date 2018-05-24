package com.ipplus360.dao;

import com.ipplus360.entity.SalesActivity;

import java.util.List;

/**
 *
 * Created by 辉太郎 on 2017/10/11.
 */
public interface SalesActivityDao {

    int add(SalesActivity salesActivity);

    int update(SalesActivity salesActivity);

    int delete(Integer id);

    SalesActivity getById(Integer id);

    List<SalesActivity> getByUserId(Long userId);
}
