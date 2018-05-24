package com.ipplus360.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.PricePackageDao;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.service.PricePackageService;
@Service
public class PricePackageServiceImpl implements PricePackageService {

	@Autowired
	private PricePackageDao pricePackagDao;
	
	@Override
	public PricePackage getById(Long id) {
		
		return pricePackagDao.getById(id);
	}
	
	@Override
	public PricePackage getPricePackageById(Long id) {

		return pricePackagDao.getPricePackageById(id);
	}

	@Override
	public List<PricePackage> getAll() {
		return null;
	}

	@Override
	public List<PricePackage> getAll(Long productId) {
		// TODO Auto-generated method stub
		return pricePackagDao.getByProductId(productId);
	}

	@Override
	public List<PricePackage> getAll(Long productId, Long accuracyId) {
		Map<String,Long> parm = new HashMap<>();
		parm.put("productId",productId);
		parm.put("accuracyId",accuracyId);
		return pricePackagDao.getByProductIdAndAccuracyId(parm);
	}

	@Override
	public PricePackage getByPricePackage(PricePackage entity) {
		return pricePackagDao.getByPricePackage(entity);
	}

	@Override
	public List<PricePackage> getPackagesByProduct(Long productId) {
		return pricePackagDao.getPackagesByProduct(productId);
	}

	@Override
	public PricePackage getByPriceId(Long id) {
		return pricePackagDao.getByPriceId(id);
	}

}
