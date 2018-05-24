package com.ipplus360.dao;

import com.ipplus360.entity.UserProductRecord;

/**
 * 用户产品查询次数dao
 * Created by 辉太郎 on 2017/2/23.
 */
public interface UserProductRecordDao {
    /**
     * userId和productId能保证唯一
     * @param userId
     * @param productId
     * @return
     */
    UserProductRecord getByUserIdAndProductId(Long userId, Long productId);
    
    /**
     * 用户付款成功之后增加一条记录
     * 与update方法不同之处为，update方法为用户之前购买过同样的产品，在次数上累加
     * @param record
     * @return
     */
    int add(UserProductRecord record);

    /**
     * 用户付款成功之后更新记录
     * @param record
     * @return
     */
    int update(UserProductRecord record);

}
