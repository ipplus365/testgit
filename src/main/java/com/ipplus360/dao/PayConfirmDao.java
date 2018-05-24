package com.ipplus360.dao;

import com.ipplus360.entity.PayConfirm;

/**
 * Created by 辉太郎 on 2017/6/7.
 */
public interface PayConfirmDao {
    /**
     * 添加支付确认
     * @param payConfirm
     * @return
     */
    int insert(PayConfirm payConfirm);

    /**
     * 更新支付确认信息
     * @param payConfirm
     * @return
     */
    int update(PayConfirm payConfirm);
}
