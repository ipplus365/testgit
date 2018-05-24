package com.ipplus360.service;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.DownloadOrder;

public interface DownloadOrderService {
	
	
	List<DownloadOrder> getAll(Map getDownloadOrder);

	int add(DownloadOrder downloadOrder);
	

}
