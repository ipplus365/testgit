package com.ipplus360.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ipplus360.dao.PricePackageDao;
import com.ipplus360.entity.PricePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipplus360.dao.ProductDao;
import com.ipplus360.entity.Product;
import com.ipplus360.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired private ProductDao productDao;

	@Autowired private PricePackageDao pricePackageDao;

	/**
	 * 获取单个商品
	 * @param productId id
	 * @return 返回商品
	 */
	@Override
	public Product getOne(Long productId) {
        Product product = productDao.getOne(productId);
        List<PricePackage> pricePackages = pricePackageDao.getPackagesByProduct(productId);
        product.setPricePackage(pricePackages);
        return product;
	}

	/***********************以下为以前遗留不规范方法 by ll**************************/
	@Override
	public List<Product> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getById(Long id) {
		return productDao.getProductById(id);
	}
	
	@Override
	public Product getForToken(Long id) {
		// TODO Auto-generated method stub
		return productDao.getById(id);
	}
	/***********************以上为以前遗留不规范方法 by ll**************************/

}
