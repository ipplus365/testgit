package com.ipplus360.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ipplus360.dao.OrderDao;
import com.ipplus360.dao.OrderItemDao;
import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.Invoice;
import com.ipplus360.entity.Order;
import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.entity.ShoppingCart;
import com.ipplus360.entity.User;
import com.ipplus360.exception.OrderException;
import com.ipplus360.service.InvoiceService;
import com.ipplus360.service.OrderItemService;
import com.ipplus360.service.OrderService;
import com.ipplus360.service.ProductAttrService;
import com.ipplus360.service.ShoppingCartService;
import com.ipplus360.service.UserService;
import com.ipplus360.util.OrderUtil;

/**
 * Created by 辉太郎 on 2017/3/06.</br>
 * 
 * @author wangguoyan
 */

@Service
public class OrderServiceImpl implements OrderService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductAttrService productAttrService;

	@Override
	public Order getById(Long id) {

		return orderDao.getById(id);
	}

	@Override
	public Order getById1(Long id) {
		return orderDao.getById1(id);
	}

	@Override
	public List<Order> getAll() {

		return orderDao.getAll();
	}

	@Override
	public List<Order> getAll(Long userId, int currPage, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("currPage", (currPage - 1) * pageSize);
		map.put("pageSize", pageSize);
		return orderDao.getAllByUserId(map);
	}

	@Override
	public List<Order> getAll(Order entity) {

		return orderDao.getAllByOrder(entity);
	}

	@Override
	@Transactional
	public int update(Order entity) {

		return orderDao.update(entity);
	}

	@Override
	@Transactional
	public Order insert(User user, String orderSerial, Integer isInvoice, Integer payType) {
		try {
			/* 获取用户购物车信息 */
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());

			if (null == cart) {
				throw new OrderException("购物车为空");
			}

			for (CartItem cartItem : cart.getCartItems()) {
				String cartItemType = cartItem.getItemType();
				if (StringUtils.isBlank(cartItemType)) {
					PricePackage pricePackage = cartItem.getPricePackage();
					if (null != pricePackage) {
						if (!pricePackage.isAvailable()) {
							throw new OrderException("存在已失效的商品，请重新购买");
						}
					}
				} else {
					if(cartItemType.equals("IPSceneAPI")){
						PricePackage pricePackage = cartItem.getPricePackage();
						if (null != pricePackage) {
							if (!pricePackage.isAvailable()) {
								throw new OrderException("存在已失效的商品，请重新购买");
							}
						}
						
					}
					if(cartItemType.equals("IPDistrictAPI")){
						PricePackage pricePackage = cartItem.getPricePackage();
						if (null != pricePackage) {
							if (!pricePackage.isAvailable()) {
								throw new OrderException("存在已失效的商品，请重新购买");
							}
						}
						
					}
					
					if (cartItemType.equals("IPDistrictDownload")) {
						// List<ProductAttr> attrs = cartItem.getAttrs();
						String attrIds = cartItem.getAttrIds();
						String[] arr = attrIds.split(",");
						Integer[] arrInt = new Integer[arr.length];
						for (int i = 0; i < arr.length; i++) {
							arrInt[i] = Integer.valueOf(arr[i]);
						}
						List<ProductAttr> attrs = productAttrService.getAttrsByIds(arrInt);

						for (ProductAttr attr : attrs) {
							if (attr.getStatus() != 1) {
								throw new OrderException("存在无效商品，请重新购买");
							}
						}
					}
				}
			}
			/*
			 * 获取购物项信息 getCartItemList(user,cart);
			 */

			Order order = new Order();// 新建订单
			List<OrderItem> orderItemList = new ArrayList<>();// 新建订单项
			/* 初始化订单,在数据库中生成订单 */
			initOrder(order, orderItemList, cart, user, orderSerial, isInvoice, payType);

			orderItemService.insert(orderItemList);
			/* 保存订单项到数据库中 */
			logger.info("Email:{},ip:{},OrderService插入订单列表:{}", user.getEmail(), user.getLastLoginFrom(),
					orderItemList);
			order.setOrderItems(orderItemList);

			return order;

		} catch (Exception e) {
			logger.error("Email:{},ip:{},添加订单异常:{}", user.getEmail(), user.getLastLoginFrom(), e);
			if (e instanceof OrderException) {
				throw new OrderException(e.getMessage());
			}
			throw new OrderException("添加订单异常");

		}

	}

	@Override
	public int delete(String orderSerial) {

		return orderDao.delete(orderSerial);
	}

	@Override
	public Order getByUserIdAndOrderId(Long userId, String orderSerial) {

		return orderDao.getByUserIdAndOrderId(userId, orderSerial);
	}

	@Override
	public Order getOrderByOrderSerial(String orderSerial) {
		return orderDao.getOrderByOrderSerial(orderSerial);
	}

	@Override
	public Order getOrder(Long userId, String orderSerial) {

		return orderDao.getOrder(userId, orderSerial);
	}

	/**
	 * 检查订单是否存在已下线商品,存在返回true，不存在返回false
	 * 
	 * @param orderId
	 *            订单ID
	 * @return
	 */
	@Override
	public boolean isExitsExpiredItem(Long orderId) {
		List<OrderItem> orderItems = orderItemDao.checkOrderItem(orderId);
		if (null != orderItems && !orderItems.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 初始化订单,生成订单列表</br>
	 * 获取购物车列表信息,如果列表为空,抛出异常</br>
	 *
	 * @param order
	 *            订单
	 * @param orderItemList
	 *            订单项
	 * @param cart
	 *            购物车
	 * @param orderSerial
	 *            订单序列号(可为空)
	 * @param isInvoice
	 *            是否需要发票
	 */
	private void initOrder(Order order, List<OrderItem> orderItemList, ShoppingCart cart, User user, String orderSerial,
			Integer isInvoice, Integer payType) {
		String message;
		try {

			List<CartItem> cartItemList = cart.getCartItems();

			logger.info("Email:{},ip:{},购物车:{}", user.getEmail(), user.getLastLoginFrom(), cartItemList);

			if (null == cartItemList || cartItemList.isEmpty()) {
				message = "购物车为空";
				logger.error("OrderService初始化订单错误:{},用户为:{}", message, user.getEmail());
				throw new OrderException(message);
			}

			if (1 == isInvoice) {// 需要发票
				order.setIsInvoice(isInvoice);

			} else {
				order.setIsInvoice(0);
			}

			// 获取订单号,如果用户已选择开发票信息则从发票信息里获取订单号,如果发票信息为则使用新生成的订单号
			if (StringUtils.isEmpty(orderSerial)) {
				order.setOrderSerial(OrderUtil.getOrderId());
			} else {
				Invoice invoice = invoiceService.getByOrderId(user.getId(), orderSerial);
				logger.info("OrderService初始化订单项,获取发票信息:{}", invoice);

				order.setOrderSerial(orderSerial);
				/*
				 * if(null != invoice){ order.setOrderSerial(orderSerial);
				 * }else{ order.setOrderSerial(OrderUtil.getOrderId());
				 * order.setIsInvoice(0);
				 * logger.error("用户{}发票信息错误:未找到发票信息,传入订单号为:{}",user.getEmail(),
				 * orderSerial); }
				 */
			}

			order.setDateCreated(new Date());
			order.setDateUpdated(new Date());

			order.setStatus(1);
			order.setUserId(user.getId());

			if (0 == payType) {// 支付方式:在线支付
				order.setPaymentMethod(payType);
			} else {// 线下支付
				order.setPaymentMethod(1);
			}

			order.setShippingStatus(0);// 发货状态

			order.setPrice(cart.getPrice());

			/* 添加订单到数据库,并获取订单所对应的ID */
			orderDao.insert(order);
			logger.info("Email:{},ip:{},OrderService生成新的订单:", user.getEmail(), user.getLastLoginFrom(), order);
			int testCount = user.getTestCount();

			/* 将订单项信息添加到订单项列表 */
			for (CartItem cartItem : cartItemList) {
				/*
				 * if(!cartItem.getPricePackage().isAvailable()){ throw new
				 * OrderException("存在已失效的商品，请重新购买"); }
				 */

				OrderItem oim = new OrderItem();// 新建一个订单项
				oim.setOrderId(order.getId());
				oim.setItemNum(cartItem.getItemNum());
				oim.setPrice(cartItem.getPrice());
				oim.setProduct(cartItem.getProduct());
				oim.setOriginalPrice(cartItem.getOriginalPrice());
				oim.setDiscount(OrderUtil.doubleTrans((Double.parseDouble(cartItem.getDiscount()) * 10)));
				String ids = cartItem.getAttrIds();
				if (StringUtils.isNotBlank(ids)) {
					oim.setAttrIds(ids);
				}

				String itemType = cartItem.getItemType();
				if (StringUtils.isEmpty(itemType)) {

					oim.setAmount(cartItem.getPricePackage().getAmount());
					oim.setPricePackage(cartItem.getPricePackage());
					oim.setPricePackageId(cartItem.getPricePackage().getId());
					oim.setAmountStr(cartItem.getPricePackage().getAmountStr());
					if (-1 == cartItem.getPricePackage().getType()) {
						user.setTestCount(cartItem.getItemNum() + testCount);
						userService.updateTestCount(user);
					}
					logger.info("Email:{},ip:{},OrderService初始化订单,订单项:{}", user.getEmail(), user.getLastLoginFrom(),
							oim);
				} else {
					if (itemType.equals("IPDistrictAPI")) {
						oim.setItemType(itemType);
						oim.setPricePackage(cartItem.getPricePackage());
						oim.setAmount(cartItem.getPricePackage().getAmount());
						oim.setAmountStr(cartItem.getPricePackage().getAmountStr());
						oim.setPricePackageId(cartItem.getPricePackage().getId());
					} else if (itemType.equals("IPDistrictDownload")) {

						// oim.setAttrs(JSON.toJSON(cartItem.getAttrs()).toString());
						String attrIds = cartItem.getAttrIds();
						// 得到区县库下载规格
						List<ProductAttr> productAttrList = shoppingCartService.getAttrsByIds(attrIds);
						/*
						 * String pp = ""; for (ProductAttr p : productAttrList)
						 * { pp = pp + p.getAttrValue() + ","; }
						 */
						oim.setAttrs(JSON.toJSONString(productAttrList));

						// oim.setAttrs(JSON.toJSONString(cartItem.getAttrs()));

						oim.setItemType(itemType);
					} else if (itemType.equals("IPSceneAPI")) {
						oim.setItemType(itemType);
						oim.setPricePackage(cartItem.getPricePackage());
						oim.setPricePackageId(cartItem.getPricePackage().getId());
						oim.setAmount(cartItem.getPricePackage().getAmount());
						oim.setAmountStr(cartItem.getPricePackage().getAmountStr());
					} else if (itemType.equals("IPSceneDownload")) {
						oim.setItemType(itemType);
						oim.setAttrs(JSON.toJSONString(cartItem.getAttrs()));
					}
				}

				addOrderItemToList(user, orderItemList, oim);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化订单出错:{}", e.getMessage());
			throw new OrderException("初始化订单出错", e);
		}

	}

	/**
	 * 将订单项添加到订单列表中
	 * 
	 * @param orderItemList
	 *            订单项列表
	 * @param orderItem
	 *            订单项
	 */
	private void addOrderItemToList(User user, List<OrderItem> orderItemList, OrderItem orderItem) {
		try {
			/*
			 * if(null == orderItemList || orderItemList.isEmpty() ) {
			 * orderItemList = new ArrayList<OrderItem>(); }
			 */
			orderItemList.add(orderItem);
			logger.info("Email:{},ip:{},OrderService将订单项添加到订单列表:{}", user.getEmail(), user.getLastLoginFrom(),
					orderItem);
		} catch (Exception e) {
			logger.error("Email:{},ip:{},添加订单项到订单列表出错,{}", user.getEmail(), user.getLastLoginFrom(), e.getMessage());
			throw new OrderException("添加订单项到订单列表出错");
		}

	}

	@Override
	public int getAllCountByUserId(Long id) {
		
		return orderDao.getAllCountByUserId(id);
	}

	@Override
	public Order getByUserAndOrder(Long userId, String orderSerial) {
		return orderDao.getByUserAndOrder(userId, orderSerial);
	}

	@Override
	public List<Order> getAllOrdersByUserid(Long userId, Integer currPage, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("currPage", (currPage - 1) * pageSize);
		map.put("pageSize", pageSize);
		return orderDao.getAllOrdersByUserid(map);
	}

	/*
	 * private void getCartItemList(User user,ShoppingCart cart){
	 * 
	 * if(null ==cart || null == cart.getCartItems() ){ message = "购物车列表为空";
	 * throw new OrderException(message); } cartItemList = cart.getCartItems();
	 * logger.info("232购物车列表:{}",cartItemList); }
	 */

}
