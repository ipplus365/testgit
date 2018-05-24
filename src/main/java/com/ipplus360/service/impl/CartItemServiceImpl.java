package com.ipplus360.service.impl;

import java.util.List;

import com.ipplus360.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.dao.CartItemDao;
import com.ipplus360.service.CartItemService;
import com.ipplus360.service.PricePackageService;

@Service
public class CartItemServiceImpl implements CartItemService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private PricePackageService productAndPriceService;

	@Override
	public CartItem getById(Long id, Long cartId) {

		return cartItemDao.getById(id, cartId);
	}

	@Override
	public List<CartItem> getAllByCartId(Long cartId) {

		List<CartItem> cartItemList = cartItemDao.getAllByCartId(cartId);
		for (CartItem cartItem : cartItemList) {
			String itemType = cartItem.getItemType();

			if (StringUtils.isBlank(itemType) || itemType.equals("IPSceneAPI") || itemType.equals("IPDistrictAPI")) {
				if (null != cartItem.getPricePackage().getId()) {
					PricePackage pricePackage = productAndPriceService
							.getPricePackageById(cartItem.getPricePackage().getId());
					logger.info("pricePackage--{}", pricePackage);
					if (null != pricePackage)
						cartItem.setPricePackage(pricePackage);
				}
			}
		}
		return cartItemList;
	}

	@Override
	@Transactional
	public int insert(CartItem entity) {

		return cartItemDao.insert(entity);
	}

	@Override
	@Transactional
	public int update(CartItem entity) {

		return cartItemDao.update(entity);
	}

	@Override
	public int delete(Long id) {

		return cartItemDao.delete(id);
	}

	@Override
	@Transactional
	public int deleteByCartId(Long cartId) {

		return cartItemDao.deleteByCartId(cartId);
	}

	@Override
	public CartItem getByCartItem(CartItem entity) {
		return cartItemDao.getByCartItem(entity);
	}

	@Override
	public List<CartItem> addDistrict(ShoppingCart cart, CartItem item) {
		List<CartItem> items = getAllByCartId(cart.getId());
		if (hasItem(item, items)) {
			return items;
		} else {
			insert(item);
		}
		return items;
	}

	/**
	 * 是否已存在此购物项
	 * 
	 * @param item
	 *            购物项
	 * @return boolean
	 */
	private boolean hasItem(CartItem item, List<CartItem> items) {
		String attrIds = item.getAttrIds();
		for (CartItem item1 : items) {
			String attrIds1 = item1.getAttrIds();
			if (StringUtils.isNotBlank(attrIds1) && attrIds1.equals(attrIds)) {
				return true;
			}
		}
		return false;
	}

}
