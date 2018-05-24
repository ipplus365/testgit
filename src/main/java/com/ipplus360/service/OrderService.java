package com.ipplus360.service;

import java.util.List;

import com.ipplus360.entity.Order;
import com.ipplus360.entity.User;

/**
 * 订单服务接口</br>
 * Created by 辉太郎 on 2017/3/06.</br>
 * @author wangguoyan
 */
public interface OrderService {
	
	/**
	 * 根据ID查询订单
	 * @param id 订单
	 * @return 订单
	 */
	Order getById(Long id);

	Order getById1(Long id);

	/**
	 * 根据查询用户订单
	 * @param userId 用户ID
	 * @param orderSerial 订单序列号
	 * @return 订单
	 */
	Order getOrder(Long userId,String orderSerial);

	/**
	 * 根据订单序列号查询订单
	 * @param orderSerial 订单序列号
	 * @return 订单
	 */
	Order getOrderByOrderSerial(String orderSerial);

	/**
	 * 根据ID查询订单
	 * @param userId 用户ID
	 * @param orderSerial 订单序列号
	 * @return 订单
	 */
	Order getByUserIdAndOrderId(Long userId,String orderSerial);
	
	/**
	 * 获取所有订单
	 * @return 订单列表
	 */
	List<Order> getAll();
	
	/**
	 * 根据用户ID获取用户所有订单
	 * @return 订单列表
	 */
	List<Order> getAll(Long userId,int currPage,int pageSize);
	
	/**
	 * 根据订单条件进行筛选查询
	 * @param entity 订单
	 * @return 订单
	 */
	List<Order> getAll(Order entity);
	
	/**
	 * 更新订单
	 * @param entity 订单
	 * @return int
	 */
	int update(Order entity);
	
	/**
	 * 添加订单
	 * @param user 用户
	 * @param orderSerial 订单序列号(可为空)
	 * @param isInvoice  是否需要发票
	 * @param payType 支付类型(0:在线支付,1:线下支付)
	 * @return 订单
	 */
	Order insert(User user, String orderSerial, Integer isInvoice,Integer payType);
	
	/**
	 * 根据订单号删除订单
	 * @param orderSerial 订单序列号
	 * @return int
	 */
	int delete(String orderSerial);

	/**
	 * 检查订单是否存在已下线商品,存在返回true，不存在返回false
	 * @param orderId 订单ID
	 * @return
	 */
	boolean isExitsExpiredItem(Long orderId);
	
	/**
	 * 某个用户的订单总数量
	 * @param id
	 * @return
	 */
	int getAllCountByUserId(Long id);

    Order getByUserAndOrder(Long id, String orderSerial);

    List<Order> getAllOrdersByUserid(Long id, Integer currPage, Integer pageSize);
}
