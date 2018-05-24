package com.ipplus360.service;

import com.ipplus360.entity.UserProductRecord;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月9日   
 */

public interface UserProductRecordService {

	UserProductRecord getByUserIdAndProductId(Long userId, Long productId);
	
}
