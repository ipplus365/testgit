package com.ipplus360.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.DownloadOrderDao;
import com.ipplus360.entity.DownloadOrder;
import com.ipplus360.service.DownloadOrderService;

@Service
public class DownloadOrderServiceImpl implements DownloadOrderService {

	@Autowired
    private DownloadOrderDao downloadOrderDao;

	@Override
	public List<DownloadOrder> getAll(Map getDownloadOrder) {
		return downloadOrderDao.getAll(getDownloadOrder);
	}

	@Override
	public int add(DownloadOrder downloadOrder) {
		return downloadOrderDao.add(downloadOrder);
	}
	
	
	

}
