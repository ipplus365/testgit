package com.ipplus360.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.InvoiceDao;
import com.ipplus360.entity.Invoice;
import com.ipplus360.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;
	@Override
	public Invoice getById(Long id) {
		
		return invoiceDao.getById(id);
	}

	@Override
	public Invoice getByOrderId(Long userId, String orderId) {
		
		return invoiceDao.getByOrderId(userId, orderId);
	}

	@Override
	public int insert(Invoice entity) {
		
		return invoiceDao.insert(entity);
	}

	@Override
	public int update(Invoice entity) {

		return invoiceDao.update(entity);
	}

	@Override
	public Invoice getByUserId(Long userId) {
		return invoiceDao.getByUserId(userId);
	}

}
