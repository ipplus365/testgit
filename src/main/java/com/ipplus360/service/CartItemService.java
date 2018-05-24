package com.ipplus360.service;

import java.util.List;

import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.ShoppingCart;

/**
 * 购物项服务接口</br>
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
public interface CartItemService {
	
	/**
	 * 根据ID获取购物项
	 * @param id
	 * @return
	 */
	CartItem getById(Long id,Long cartId);
	
	/**
	 * 根据购物车ID获取所有购物项
	 * @param cartId
	 * @return
	 */
	List<CartItem> getAllByCartId(Long cartId);
	
	/**
	 * 根据购物项筛选
	 * @param entity
	 * @return
	 */
	CartItem getByCartItem(CartItem entity);
	
	/**
	 * 新增购物项
	 * @param entity
	 * @return
	 */
	int insert(CartItem entity);
	
	/**
	 * 更新购物项
	 * @param entity
	 * @return
	 */
	int update(CartItem entity);
	
	/**
	 * 根据ID删除购物项
	 * @param id
	 * @return
	 */
	int delete(Long id);
	
	/**
	 * 清空购物车中的购物项
	 * @param cartId
	 * @return
	 */
	int deleteByCartId(Long cartId);

	/**
	 * 添加购物项
	 * @param item 购物项
	 * @Author LL(lijian@ipplus360.com)
	 */
	List<CartItem> addDistrict(ShoppingCart cart, CartItem item);

}
