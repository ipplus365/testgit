package com.ipplus360.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.CartItem;

/**
 * 购物项</br>
 * Created by 辉太郎 on 2017/3/04.</br>
 * @author wangguoyan
 */
public interface CartItemDao {
	/**
	 * 根据ID获取购物项
	 * @param itemId 购物项ID
	 * @param cartId 购物车ID
	 * @return
	 */
	CartItem getById(Long itemId,Long cartId);
	
	/**
	 * 获取所有购物项
	 * @return
	 */
	List<CartItem> getAll();
	
	/**
	 * 根据购物项进行筛选
	 * @param cartItem
	 * @return
	 */
	CartItem getByCartItem(CartItem cartItem);
	
	/**
	 * 根据购物车ID获取购物项
	 * @param cartId
	 * @return
	 */
	List<CartItem> getAllByCartId(Long cartId);
	
	/**
	 * 添加购物项
	 * @param entity
	 * @return
	 */
	@Transactional
	int insert(CartItem entity);
	
	/**
	 * 更新购物项
	 * @param entity
	 * @return
	 */
	@Transactional
	int update(CartItem entity);
	
	/**
	 *  根据ID删除购物项
	 * @param id
	 * @return
	 */
	@Transactional
	int delete(Long id);
	
	/**
	 * 清空购物项
	 * @param cartId
	 * @return
	 */
	@Transactional
	int deleteByCartId(Long cartId);

	CartItem get();

	CartItem get1();
}
