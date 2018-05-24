package com.ipplus360.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.Order;
import com.ipplus360.entity.OrderItem;
import com.ipplus360.entity.OrderLog;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.entity.ProductAttr;
import com.ipplus360.entity.ShoppingCart;
import com.ipplus360.entity.User;
import com.ipplus360.enums.OrderType;
import com.ipplus360.exception.OrderException;
import com.ipplus360.service.CartItemService;
import com.ipplus360.service.InvoiceService;
import com.ipplus360.service.OrderLogService;
import com.ipplus360.service.OrderService;
import com.ipplus360.service.ProductAttrService;
import com.ipplus360.service.ShoppingCartService;
import com.ipplus360.service.UserService;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.OrderUtil;
import com.ipplus360.util.SessionUtil;

/**
 * 获取订单列表</br>
 * Created by 辉太郎 on 2017/3/14.</br>
 * 
 * @author wangguoyan
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductAttrService productAttrService;

	/**
	 * 软件协议
	 * 
	 * @return 展示软件协议
	 */
	@RequestMapping(value = "/protocol", method = RequestMethod.GET)
	public String showProtocol() {
		return "protocol";
	}

	/**
	 * 根据订单序列号查询订单详情
	 * 
	 * @param request
	 * @param session
	 * @param model
	 * @param orderId
	 *            订单序列号
	 * @return 订单信息
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String orderInfo(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam(value = "orderId") String orderId) {

		String userIP = IPUtil.getUserIP(request);
		SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		if (null == user) {
			return "redirect:/user/toLogin";
		}

		logger.info("登录用户为:{},登录IP为:{},订单查询,查询订单号为:{}", user.getEmail(), userIP, orderId);

		try {

			if (null == orderId || StringUtils.isEmpty(orderId)) {
				return "redirect:/info";
			}

			Order order = orderService.getByUserIdAndOrderId(user.getId(), orderId);
			if (null == order) {
				order = orderService.getByUserAndOrder(user.getId(), orderId);
				if (null == order) {
					// session.setAttribute("msg", "查无此项");
					return "redirect:/500";
				}
			}
			BigDecimal totalPayable = new BigDecimal(0);
			for (OrderItem oim : order.getOrderItems()) {
				oim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(oim.getDiscount())));
				totalPayable = totalPayable.add(oim.getPrice());
			}

			String msg = (String) session.getAttribute("msg");

			if (!StringUtils.isEmpty(msg)) {
				model.addAttribute("msg", msg);
				session.removeAttribute("msg");
			}
			model.addAttribute("order", order);
			model.addAttribute("totalPayable", totalPayable);
			return "/info";

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询订单异常--{}", e.getMessage());
			model.addAttribute("msg", e.getMessage());
			return "500";
		}

	}

	/**
	 * 订单提交</br>
	 * 前台点击提交后跳转到订单确认页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addOrder(HttpServletRequest request, Model model) {
		String userIP = IPUtil.getUserIP(request);
		SessionUser user = SessionUtil.getSessionUser(request);

		try {
			logger.info("登录用户为:{},登录IP为:{},添加订单操作", user.getEmail(), userIP);

			return "redirect:/order/confirm";

		} catch (Exception e) {
			logger.error("跳转确认界面异常--{}", e.getMessage());
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "500";
		}
	}

	/**
	 * 订单确认</br>
	 * 点击订单提交或者直接访问该页面是将购物车中的商品展示到结算确认页面，此页面购物项相关的操作不能修改
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public Result confirmOrder(HttpServletRequest request, Integer isInvoice, String orderSerial) {

		String userIP = IPUtil.getUserIP(request);
		SessionUser user = SessionUtil.getSessionUser(request);

		logger.info("登录用户为:{},登录IP为:{},确认订单信息", user.getEmail(), userIP);

		try {

			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
			if (null == cart) {
				return new Result(false, "购物车为空");
			}

			List<CartItem> cartItemList = cart.getCartItems();
			if (null != cartItemList && !cartItemList.isEmpty()) {

				for (CartItem cit : cartItemList) {
					String cartItemType = cit.getItemType();
					if (StringUtils.isBlank(cartItemType)) {
						PricePackage pricePackage = cit.getPricePackage();
						if (null != pricePackage) {
							if (!pricePackage.isAvailable()) {
								throw new OrderException("存在已失效的商品，请重新购买");
							}
						}
					} else {
						if(cartItemType.equals("IPSceneAPI")){
							PricePackage pricePackage = cit.getPricePackage();
							if (null != pricePackage) {
								if (!pricePackage.isAvailable()) {
									throw new OrderException("存在已失效的商品，请重新购买");
								}
							}
							
						}
						if(cartItemType.equals("IPDistrictAPI")){
							PricePackage pricePackage = cit.getPricePackage();
							if (null != pricePackage) {
								if (!pricePackage.isAvailable()) {
									throw new OrderException("存在已失效的商品，请重新购买");
								}
							}
							
						}
						if (cartItemType.equals("IPDistrictDownload")) {
							String attrIds = cit.getAttrIds();
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
					cit.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cit.getDiscount())));
				}

				logger.info("登录用户为:{},登录IP为:{},提交订单请求,订单号为:{}", user.getEmail(), userIP, orderSerial);
				// 查询订单是否已经存在,如果订单已存在则返回错误信息
				if (StringUtils.isEmpty(orderSerial)) {
					// 实时添加订单号
					orderSerial = OrderUtil.getOrderId();
				}
				logger.info("登录用户为:{},登录IP为:{},提交订单请求,订单号为:{}", user.getEmail(), userIP, orderSerial);
				// 查询订单是否已经存在,如果订单已存在则返回错误信息
				if (!StringUtils.isEmpty(orderSerial)) {
					Order order = orderService.getOrderByOrderSerial(orderSerial);
					if (null != order) {
						logger.error("用户{}订单生成错误,传入订单号为:{},订单号已存在或者不是本人订单");
						throw new OrderException("订单生成错误");
					}
				}

				/* 根据用户,添加订单 */
				Order order = orderService.insert(SessionUtil.convertToUser(user, userService), orderSerial, isInvoice,
						0);

				/* 添加订单操作日志 */
				orderLogService.insert(new OrderLog(order.getDateCreated(), "用户", OrderType.CREATE.getMessage(),
						order.getOrderSerial()));

				/* 清空购物车 */
				cart = shoppingCartService.getByUserId(user.getId());
				cartItemService.deleteByCartId(cart.getId());
				cart.setPrice(new BigDecimal(0));
				shoppingCartService.update(cart);

				/* 订单生成,更新session中的User信息 */
				User newUser = userService.getByEmail(user.getEmail());
				SessionUtil.setSessionUser(request, SessionUtil.convertToSessionUser(newUser));
				logger.debug("SessionTestCount:{}", user.getTestCount());

				return new Result<>(true, orderSerial, "添加成功");

			} else {
				return new Result(false, "购物车为空");
			}

		} catch (Exception e) {
			logger.error("订单添加异常--{}", e.getMessage(), e);
		}
		return new Result(false, "订单添加异常");
	}

	/**
	 * 订单取消</br>
	 * 用户点击取消订单是执行，将订单状态设置为取消状态
	 * 
	 * @param request
	 * @param session
	 *            用户信息
	 * @param model
	 * @param orderId
	 *            订单序列号
	 * @return 跳转到订单详情页面
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public Result cancel(HttpServletRequest request, HttpSession session, Model model,
			@RequestParam(required = true, value = "orderId") String orderId) {
		String userIP = IPUtil.getUserIP(request);
		SessionUser user = SessionUtil.getSessionUser(request);

		logger.info("登录用户为:{},登录IP为:{},取消订单操作,取消订单号为:{}", user.getEmail(), userIP, orderId);
		try {
			model.addAttribute("content","dingdan");
			if (null == orderId || orderId.isEmpty()) {
				model.addAttribute("msg", "订单号不能为空");
				return new Result(false,"订单号不能为空");
			}

			Order order = orderService.getByUserIdAndOrderId(user.getId(), orderId);

			if (null == order) {
				model.addAttribute("msg", "未找到该订单");
				logger.info("用户为:{},登录IP:{},取消订:{} 失败", user.getEmail(), userIP, orderId);
				return new Result(false,"未找到该订单");
			}

			if (3 == order.getStatus()) {
				session.setAttribute("msg", "订单已经取消,请勿重复提交");
				return new Result(false,"订单已经取消,请勿重复提交");
			}

			// 更新用户TestCount信息
			for (OrderItem oim : order.getOrderItems()) {
				oim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(oim.getDiscount())));
				if (-1 == oim.getPricePackage().getType()) {
					user.setTestCount(user.getTestCount() - oim.getItemNum());
					userService.updateTestCount(SessionUtil.convertToUser(user, userService));// 更新用户TestCount信息
				}
			}
			Date date = new Date();
			// 更新订单状态
			order.setStatus(3);
			order.setDateUpdated(date);
			orderService.update(order);
			orderLogService.insert(new OrderLog(date, "用户", OrderType.CANCEL.getMessage(), order.getOrderSerial()));
			SessionUtil.setSessionUser(request, user);
			session.setAttribute("msg", "取消订单成功");
			logger.info("用户为:{},登录IP:{},取消订:{} 成功", user.getEmail(), userIP, orderId);
			return new Result(true,"取消订单成功");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消订单异常--{}", e.getMessage());
			logger.info("用户为:{},登录IP:{},取消订:{} 失败", user.getEmail(), userIP, orderId);
			model.addAttribute("msg", e.getMessage());
			return new Result(false,"取消订单异常");
		}
	}

}