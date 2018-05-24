package com.ipplus360.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.OrderUser;

/**
 * 订单项查询接口</br>
 * Created by 辉太郎 on 2017/3/04.</br>
 * @author wangguoyan
 */
public interface OrderItemDao {
	
	/**
	 * 根据订单orderId查询订单
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getByOrderId(Long orderId);
	
	/**
	 * 根据ID查询订单项
	 * @param id
	 * @return
	 */
	OrderItem getById(Long id);
	
	/**
	 * 获取所有订单项
	 * @return
	 */
	List<OrderItem> getAll();
	
	/**
	 * 根据产品ID查询订单
	 * @param productId
	 * @return
	 */
	List<OrderItem> getByProductId(Long productId);

    /**
     * 获取已付款订单（不包含虚拟订单）
     * @param date
     * @return
     */
	List<OrderUser> getOrdersByDay(Date date);

	/**
	 * 根据订单ID查询订单项
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getAllByOrderId(Long orderId);
	
	/**
	 * 新增订单项
	 * @param orderItem
	 * @return
	 */
	@Transactional
	int insert(OrderItem orderItem);
	
	@Transactional
	int insertList(List<OrderItem> orderItemList);
	
	/**
	 * 更新订单项
	 * @param orderItem
	 * @return
	 */
	@Transactional
	int update(OrderItem orderItem);
	
	/**
	 * 根据ID删除订单项
	 * @param id
	 * @return
	 */
	@Transactional
	int delete(Long id);
	
	/**
	 * 清空订单项
	 * @param orderId
	 * @return
	 */
	@Transactional
	int deleteByOrderId(Long orderId);
	
	/**
	 * 根据商品id查询订单
	 * @param productId
	 * @return
	 */
	List<Map> getAllByProductId(Long productId);

	List<OrderItem> getAllByOrderItem(OrderItem entity);

	/**
	 * 查询是否存在已下架商品的订单项
	 * @param orderId 订单ID
	 * @return
	 */
	List<OrderItem> checkOrderItem(Long orderId);

	List<OrderItem> getOrderItemByOrderId(Long orderId);

    List<OrderItem> getByOrderIdWithoutPricePackage(Long orderId);
}