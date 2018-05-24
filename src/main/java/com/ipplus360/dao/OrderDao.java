package com.ipplus360.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ipplus360.entity.Order;

/**
 * 订单查询接口</br>
 * Created by 辉太郎 on 2017/2/23.
 */
public interface OrderDao {
	/**
	 * 根据ID查询订单
	 * @param id
	 * @return
	 */
    Order getById(Long id);
    
    /**
	 * 根据ID查询订单
	 * @param userId
     * @param orderSerial
	 * @return
	 */
    Order getByUserIdAndOrderId(Long userId,String orderSerial);
    
    /**
     * 根据用户ID查询订单 
     * @param userId
     * @return
     */
    List<Order> getAllByUserId(Map<String,Object> map);
    
    /**
     * 获取所有订单
     * @return
     */
    List<Order> getAll();
    
    /**
     * 根据条件筛选订单
     * @param order
     * @return
     */
    List<Order> getAllByOrder(Order order);
    
    /**
     * 更新订单
     * @param order
     * @return
     */
    @Transactional
    int update(Order order);

    @Transactional
    int delete(String orderSerial);
    
    /**
     * 新增订单
     * @param order
     * @return
     */
    @Transactional
    int insert(Order order);

    /**
     * 获取订单
     * @param userId
     * @param orderSerial
     * @return
     */
	Order getOrder(Long userId, String orderSerial);

    /**
     * 根据订单序列号获取订单
     * @param orderSerial
     * @return
     */
	Order getOrderByOrderSerial(String orderSerial);

    /**
     * 一周订单统计，订单数，订单价格
     * @param date
     * @return
     */
    Map<String, Object> orderStatisticsWeekly(LocalDate date);

    /**
     * 每月订单统计，订单数，订单价格
     * @param date
     * @return
     */
    Map<String,Object> orderStatisticsMonthly(LocalDate date);

	int getAllCountByUserId(Long id);

    Order getByUserAndOrder(Long userId, String orderSerial);

    List<Order> getAllOrdersByUserid(Map<String,Object> map);

    Order getById1(Long id);
}
