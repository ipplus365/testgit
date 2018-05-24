package com.ipplus360.dao;

import com.ipplus360.entity.Invoice;

/**
 * 发票信息数据操作接口
 * Created by 辉太郎 on 2017/3/17.</br>
 * @author wangguoyan
 *
 */

public interface InvoiceDao {
	
	Invoice getByOrderId(Long userId, String orderId);
	
	Invoice getById(Long id);
	
	int insert(Invoice invoice);
	
	int update(Invoice invoice);

	Invoice getByUserId(Long userId);
	
}
