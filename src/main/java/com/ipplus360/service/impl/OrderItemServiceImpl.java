package com.ipplus360.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.dao.OrderItemDao;
import com.ipplus360.entity.OrderItem;
import com.ipplus360.service.OrderItemService;

/**
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public OrderItem getById(Long id) {
		
		return orderItemDao.getById(id);
	}

	@Override
	public List<OrderItem> getAll() {

		return orderItemDao.getAll();
	}

	@Override
	public List<OrderItem> getAll(Long orderId) {
		
		return orderItemDao.getAllByOrderId(orderId);
	}

	@Override
	public List<OrderItem> getAll(OrderItem entity) {
		
		return orderItemDao.getAllByOrderItem(entity);
	}

	@Override
	@Transactional
	public int insert(OrderItem entity) {
	
		return orderItemDao.insert(entity);
	}

	@Override
	@Transactional
	public int update(OrderItem entity) {
		
		return orderItemDao.update(entity);
	}

	@Override
	@Transactional
	public int delete(Long id) {

		return orderItemDao.delete(id);
	}

	@Override
	@Transactional
	public int deleteByOrderId(Long orderId) {

		return orderItemDao.deleteByOrderId(orderId);
	}

	@Override
	@Transactional
	public int insert(List<OrderItem> list) {
		
		return orderItemDao.insertList(list);
	}

	@Override
	public List<Map> getAllByProductId(Long productId) {
		return orderItemDao.getAllByProductId(productId);
	}

	@Override
	public List<OrderItem> getByOrderIdWithoutPricePackage(Long orderId) {
		return orderItemDao.getByOrderIdWithoutPricePackage(orderId);
	}

	@Override
	public List<OrderItem> getByOrderId(Long orderId) {
		return orderItemDao.getByOrderId(orderId);
	}

	@Override
	public List<OrderItem> getOrderItemByOrderId(Long orderId) {
		
		return orderItemDao.getOrderItemByOrderId(orderId);
	}

}
