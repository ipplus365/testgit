package com.ipplus360.service;

import java.util.List;
import java.util.Map;

import com.ipplus360.entity.OrderItem;

/**
 * 订单项服务接口</br>
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
public interface OrderItemService {
	
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
	 * 根据订单ID获取所有订单项
	 * @param orderId
	 * @return
	 */
	List<OrderItem> getAll(Long orderId);
	
	List<OrderItem> getOrderItemByOrderId(Long orderId);
	
	/**
	 * 根据订单项进行筛选查询
	 * @param entity
	 * @return
	 */
	List<OrderItem> getAll(OrderItem entity);
	
	/**
	 * 插入订单项
	 * @param entity
	 * @return
	 */
	int insert(OrderItem entity);
	
	/**
	 * 批量插入订单项
	 * @param entity
	 * @return
	 */
	int insert(List<OrderItem> list);
	
	/**
	 * 更新订单项
	 * @param entity
	 * @return
	 */
	int update(OrderItem entity);
	
	
	/**
	 *  根据ID删除订单项
	 * @param id
	 * @return
	 */
	int delete(Long id);
	
	/**
	 * 清空订单中订单项
	 * @param orderId
	 * @return
	 */
	int deleteByOrderId(Long orderId);
	
	/**
	 * 根据商品id查询订单
	 * @param productId
	 * @return
	 */
	List<Map> getAllByProductId(Long productId);


    List<OrderItem> getByOrderIdWithoutPricePackage(Long orderId);
}
