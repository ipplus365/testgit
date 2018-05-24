package com.ipplus360.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.PricePackageDao;
import com.ipplus360.dao.ProductDao;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.Product;
import com.ipplus360.service.ProductAndPriceService;

@Service
public class ProductAndPriceServiceImpl implements ProductAndPriceService {

	@Autowired
    private ProductDao productDao;
	
	@Autowired
    private PricePackageDao pricePackageDao;
	
	@Override
	public List<Product> listAll() {
		return productDao.listAll();
	}

	@Override
	public PricePackage getById(Long id) {
		return pricePackageDao.getById(id);
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<String> getPackageTypeList(Map getType) {
		return pricePackageDao.getPackageTypeById(getType);
	}

	@Override
	public List<PricePackage> getAmountList(Map getAmount) {
		return pricePackageDao.getAmountById(getAmount);
	}

	@Override
	public PricePackage getProductPrice(Map getPrice) {
		return pricePackageDao.getProductPrice(getPrice);
	}
	
	@Override
	public int updatePageviews(Long id) {
		return productDao.updatePageviews(id);
	}
	
	@Override
	public int updateEvaluate(Long id) {
		return productDao.updateEvaluate(id);
	}

	
}
