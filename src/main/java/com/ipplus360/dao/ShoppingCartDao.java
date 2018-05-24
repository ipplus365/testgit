package com.ipplus360.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.ShoppingCart;

/**
 * 购物车查询接口</br>
 * Created by 辉太郎 on 2017/3/04.</br>
 * @author wangguoyan
 */
public interface ShoppingCartDao {
	
	/**
	 * 根据ID查询购物车
	 * @param id
	 * @return
	 */
	ShoppingCart getById(Long id);
	
	/**
	 * 根据用户ID查询购物车
	 * @param userId
	 * @return
	 */
	ShoppingCart getAllByUserId(Long userId);
	
	/**
	 * 根据用户ID查询购物项数量
	 * @param userId
	 * @return
	 */
	ShoppingCart getCountByUserId(Long userId);
	
	/**
	 * 获取所有购物车
	 * @return
	 */
	List<ShoppingCart> getAll();
	
	/**
	 * 根据购物车条件进行筛选
	 * @param entity
	 * @return
	 */
	List<ShoppingCart> getAllByCart(ShoppingCart entity);
	
	/**
	 * 新增购物车
	 * @param entity
	 * @return
	 */
	@Transactional
	int insert(ShoppingCart entity);
	
	/**
	 * 更新购物车
	 * @param entity
	 * @return
	 */
	@Transactional
	int update(ShoppingCart entity);
	
//	int delete(Long id);
	
//	int deleteByUserid(Long userId);
	
}
