package com.ipplus360.service;

import com.ipplus360.entity.Invoice;

public interface InvoiceService {
	
	Invoice getById(Long id);
	
	Invoice getByOrderId(Long userId,String orderId);
	
	Invoice getByUserId(Long userId);
	
	int insert(Invoice entity);
	
	int update(Invoice entity);
	
}
