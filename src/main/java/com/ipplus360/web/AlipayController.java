package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.*;
import com.ipplus360.enums.OrderType;
import com.ipplus360.exception.AiwenException;
import com.ipplus360.exception.AliPayException;
import com.ipplus360.exception.OrderException;
import com.ipplus360.pay.config.AlipayConfig;
import com.ipplus360.pay.util.AlipayNotify;
import com.ipplus360.service.*;
import com.ipplus360.service.mail.MailService;
import com.ipplus360.util.SessionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlipayService alipayService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private MailService mailService;

	@Autowired
	private OrderLogService orderLogService;

	@Autowired
	private PayConfirmService payConfirmService;

	@Autowired
	private DeliveryService deliveryService;
	@Autowired private UserService userService;
	@Autowired private GeoIPVersionService versionService;
	@Autowired private ProductAttrService productAttrService;

	@RequestMapping(value = "/return")
	public String payonline(HttpServletRequest request, HttpSession session, Model model) {
		try {

			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			if (AlipayNotify.verify(params)) { // 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					Order order = orderService.getOrderByOrderSerial(out_trade_no);
					if (null == order) {
						String check_return = "无此订单信息";
						model.addAttribute("check_return", check_return);
						return "pay/return_url";
					}
					BigDecimal total_fee_BigDecimal = new BigDecimal(total_fee);
					if ((order.getPrice().compareTo(total_fee_BigDecimal) == 0)
							&& AlipayConfig.partner.equals(seller_id)) {
						return "redirect:/order/info?orderId=" + out_trade_no;
					} else {
						// 请求时的total_fee、seller_id与通知时获取的total_fee、seller_id不一致
						String check_return = "订单信息错误";
						model.addAttribute("check_return", check_return);
						return "pay/return_url";
					}
				} else {
					String check_return = "付款支付未成功";
					model.addAttribute("check_return", check_return);
					return "pay/return_url";
				}
			} else {// 验证失败
				String check_return = "fail";
				model.addAttribute("check_return", check_return);
				return "pay/return_url";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("付款同步回调运行异常--{}", e);
			return "redirect:/error/info";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/notify")
	public String notify(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}
			String subject = new String(request.getParameter("subject"));
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			String body = new String(request.getParameter("body"));
			String gmt_payment = new String(request.getParameter("gmt_payment").getBytes("ISO-8859-1"), "UTF-8");
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");
			long id = 0;
			String email = "";
			Map payResult = new HashMap();
			payResult.put("subject", subject);
			payResult.put("out_trade_no", out_trade_no);
			payResult.put("trade_no", trade_no);
			payResult.put("trade_status", trade_status);
			payResult.put("total_fee", total_fee);
			payResult.put("body", body);
			payResult.put("buyer_email", buyer_email);
			payResult.put("gmt_payment", gmt_payment);

			if (AlipayNotify.verify(params)) { // 验证成功

				/*
				 * logger.info(
				 * "subject:{},out_trade_no:{},trade_no:{},trade_status:{},total_fee:{},body:{},user_id:{},user_email:{},alipay_buyer:{},gmt_payment:{}",
				 * new
				 * Object[]{subject,out_trade_no,trade_no,trade_status,total_fee
				 * ,body,id,email,buyer_email,gmt_payment});
				 *//*
					 * logger.info("--订单号:{}",out_trade_no);
					 * logger.info(+"---支付宝交易号:{}",trade_no);
					 * logger.info(+"---支付返回状态:{}",trade_status);
					 * logger.info(+"---交易金额:{}",total_fee);
					 * logger.info(+"---支付购买详情:{}",body);
					 * logger.info(+"---买家支付宝账号:{}",buyer_email);
					 * logger.info(+"---买家付款时间:{}",gmt_payment);
					 * logger.info(+"---支付返回状态:{}",trade_status);
					 */
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					Order order = orderService.getOrderByOrderSerial(out_trade_no);
					logger.info("AlipayController order:" + order);
					if (null == order) {
						String check_return = "fail";
						logger.error("无此订单{}信息", out_trade_no);
						model.addAttribute("check_return", check_return);
						return "pay/return_url";
					}
					logger.info(
							"subject:{},out_trade_no:{},trade_no:{},trade_status:{},total_fee:{},body:{},user_id:{},alipay_buyer:{},gmt_payment:{}",
							new Object[] { subject, out_trade_no, trade_no, trade_status, total_fee, body,
									order.getUserId(), buyer_email, gmt_payment });

					BigDecimal total_fee_BigDecimal = new BigDecimal(total_fee);
					logger.info("order.getPrice():" + order.getPrice());
					logger.info("total_fee_BigDecimal:" + total_fee_BigDecimal);
					logger.info("(order.getPrice().compareTo(total_fee_BigDecimal) == 0:" + (order.getPrice().compareTo(total_fee_BigDecimal) == 0));
					logger.info("AlipayConfig.partner:" + AlipayConfig.partner);
					logger.info("seller_id:" + seller_id);
					logger.info("(order.getPrice().compareTo(total_fee_BigDecimal) =AlipayConfig.partner.equals(seller_id)= 0:" +
							((order.getPrice().compareTo(total_fee_BigDecimal) == 0) && AlipayConfig.partner.equals(seller_id)));

					if ((order.getPrice().compareTo(total_fee_BigDecimal) == 0)
							&& AlipayConfig.partner.equals(seller_id)) {
						if (1 == order.getStatus()) {
							/* 将订单状态更新为已付款 */
							order.setStatus(2);
							order.setDateUpdated(new Date());
							orderService.update(order);
							orderLogService.insert(new OrderLog(new Date(), "系统", OrderType.PAYMENT.getMessage(),
									order.getOrderSerial()));

							/* 支付记录确认,记录支付用户ID、订单号、支付确认日期、支付金额、支付方式、收款人 */
							PayConfirm payConfirm = new PayConfirm(order.getUserId(), order.getOrderSerial(),
									new Date(), order.getPrice(), "在线支付", "系统");
							payConfirmService.insert(payConfirm);
							logger.info("添加支付信息:{}", payConfirm);
							logger.info("用户支付成功，订单{}状态更新为已付款", order.getOrderSerial());
							/* 发货（生成token） */
							// tokenService.add(userToken);
							List<OrderItem> orderItems = orderItemService.getByOrderId(order.getId());
							if (null == orderItems || orderItems.size() == 0) {
								orderItems = orderItemService.getByOrderIdWithoutPricePackage(order.getId());
							}
							//Set<UserToken> tokenList = tokenService.getByUserId(order.getUserId());

							boolean isUpdate = false;// flag,判断是否已存在非测试包token,如果已存在则只对现有token进行更新
							boolean hasUpdated = false;
							Set<Long> tokenIds = new HashSet<>();// 发货TokenID列表
							Long counts = 0L;// 发货总次数
							Long amount = 0L;// 非测试包发货次数
							String deliveryToken = null;// 发货的Token

							/* 记录发货Token信息 */
							Delivery delivery = new Delivery(order.getUserId(), out_trade_no, "系统");


							for (int i = 0; i < orderItems.size(); i++) {

								OrderItem item = orderItems.get(i);

								// counts
								// +=orderItems.get(i).getItemNum()*orderItems.get(i).getPricePackage().getAmount();//计算总发货次数
								if (null != orderItems.get(i).getAmount()) {
									counts += orderItems.get(i).getItemNum() * orderItems.get(i).getAmount();// 计算总发货次数
								} else {
									counts = Long.valueOf(orderItems.get(i).getItemNum());
								}
								String itemType = item.getItemType();

								logger.info("AlipayController itemType :" + itemType);

								// 保留之前发货流程
								if (StringUtils.isBlank(itemType)) {

									if (1 == orderItems.get(i).getPricePackage().getType()) {
										Set<UserToken> tokenList = tokenService.getByUserId(order.getUserId());
										Iterator<UserToken> tokenIt = tokenList.iterator();
										while (tokenIt.hasNext()) {
											UserToken token = tokenIt.next();
											if ((token.getProductId() == 1L) && !token.isTest()) {
												deliveryToken = token.getToken();
												isUpdate = true;// 此时已存在非测试包token
												break;// 找到存在的非测试包token,跳出循环
											}
										}
										hasUpdated = true;
										// amount +=
										// orderItems.get(i).getPricePackage().getAmount()*orderItems.get(i).getItemNum();//计算非测试包发货次数
										amount += orderItems.get(i).getAmount() * orderItems.get(i).getItemNum();// 计算非测试包发货次数

										if (!isUpdate) {
											deliveryToken = addToken(order.getUserId(), orderItems.get(i), tokenIds);
											isUpdate = true;// 此时已存在非测试包token
										} else {
											deliveryToken = updateToken(order.getUserId(), orderItems.get(i), tokenIds);
										}
									} else {
										/* 为每一个购买的测试包添加token */
										addTestToken(order.getUserId(), orderItems.get(i), tokenIds, delivery);
										/* 保存测试包Token的发货信息 */
										// logger.info("发货信息:{}",delivery);
									}
								} else { // 发货新流程, 根据订单类型选择相应流程, 含区县库API/下载.
											// 场景库API by LL
									if (itemType.equals("IPDistrictAPI")) {
										if (item.getPrice().compareTo(new BigDecimal(0.0)) == 0) {
											Long userId = order.getUserId();
											User user = userService.getById(userId);
											if (!user.isHasFreeDistrictApi()) {
												alipayService.processFreeDistrictAPI(item, userId);
											} else {
												//throw new OrderException("您已享受过此优惠");
											}
										} else {
											alipayService.processDistrictAPI(item, order.getUserId());
										}

									} else if (itemType.equals("IPDistrictDownload")) {
										logger.info("IPDistrictDownload 0.01---------------------------");
										String attrIds = item.getAttrIds();
										String[] idStr = attrIds.split(",");
										Integer[] ids = new Integer[idStr.length];
										for (int j=0; j<idStr.length; j++) {
											ids[j] = Integer.parseInt(idStr[j]);
											logger.info("ids i:" + j + " --- " + ids[j]);
										}
										List<ProductAttr> attrs = productAttrService.getAttrsByIds(ids);
										String version = "";
										for (ProductAttr attr : attrs) {
											if ("version2".equals(attr.getAttrKey())) {
												version = attr.getAttrType().trim();
											}
										}

										if (StringUtils.isBlank(version)) {
											GeoIPVersion geoIPVersion = versionService.getCurrentVersion();
											version = geoIPVersion.getPublicVersion();
										}
										logger.info("version:" + version);
										alipayService.processDistrictDownload(item, order.getUserId(), version, false);

									} else if (itemType.equals("IPSceneAPI")) {
										alipayService.processSceneAPI(item, order.getUserId());

									} else {
										throw new AiwenException("没有此产品，发货失败");
									}

								}
							}

							orderLogService.insert(new OrderLog(new Date(), "系统", OrderType.SHIPPING.getMessage(),
									order.getOrderSerial()));

							/* 记录Token发货详情 */
							if (isUpdate && hasUpdated) {
								delivery.setDeliveryDate(new Date());
								delivery.setToken(deliveryToken);
								delivery.setCounts(amount);
								deliveryService.insert(delivery);
								logger.info("发货信息:{}", delivery);
							}

							/* 支付确认记录更新,记录发货的Token ID和总发货次数 */
							payConfirm.setDateUpdated(new Date());
							payConfirm.setCounts(counts);
							payConfirm.setTokenIdsStr(longIdsToStr(tokenIds));
							payConfirmService.update(payConfirm);
							logger.info("更新收款信息:{}", payConfirm);

							logger.info("订单{}发货成功,TokenID为:{}", order.getOrderSerial(), tokenIds);
							/* 订单状态修改为已完成 */
							order.setShippingStatus(1);
							order.setStatus(4);
							orderService.update(order);

							logger.info("订单{}已完成，订单状态更新为已完成", order.getOrderSerial());
							orderLogService.insert(new OrderLog(new Date(), "系统", OrderType.COMPLETE.getMessage(),
									order.getOrderSerial()));
							// 发送订单
							alipayService.sendOrderEmail(payResult);
							logger.info("AlipayController start-------------------------------------------");
							response.getWriter().write("success");
							logger.info("AlipayController end-------------------------------------------");
							return "success";
						}
					} else {
						// 请求时的total_fee、seller_id与通知时获取的total_fee、seller_id不一致
						logger.error("订单:{} notify方法-提交的total_fee:{}, seller_id:{}, 通知时获取的total_fee:{}, seller_id:{}",
								new Object[] { out_trade_no, String.valueOf(order.getPrice()), AlipayConfig.partner,
										total_fee_BigDecimal, seller_id });
						String check_return = "fail";
						orderLogService.insert(
								new OrderLog(new Date(), "系统", OrderType.FAIL.getMessage(), order.getOrderSerial()));
						model.addAttribute("check_return", check_return);
						//return "pay/return_url";
						return "fail";
					}
				} else {
					logger.error("客户{} 购买的 {} 订单:{}支付状态为等待付款，等待卖家收款或交易关闭",
							new Object[] { buyer_email, subject, out_trade_no });
					String check_return = "fail";
					orderLogService.insert(new OrderLog(new Date(), "系统", OrderType.FAIL.getMessage(), out_trade_no));
					model.addAttribute("check_return", check_return);
					//return "pay/return_url";
					return "fail";
				}
				return "fail";
			} else {// 验证失败a

				Order order = orderService.getOrderByOrderSerial(out_trade_no);
				if (null != order) {
					payResult.put("id", order.getUserId());
				}
				orderLogService.insert(new OrderLog(new Date(), "系统", OrderType.OTHER.getMessage(), out_trade_no));
				logger.error("客户{} 购买的 {} 订单:{}付款返回信息验证失败", new Object[] { buyer_email, subject, out_trade_no });
				// 客户付款成功但为验证成功提示客服
				//mailService.sendWarningMail("@ipplus360.com", "埃文商城报警邮件", payResult);
				return "fail";
			}
		} catch (AliPayException e) {
			e.printStackTrace();
			logger.error("异步订单邮件发送异常--{}", e);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("付款异步回调运行异常--{}", e);
			return "success";
		}
	}

	// 第一次购买新建token
	public String addToken(Long userId, OrderItem orderItem, Set<Long> tokenIds) {
		String token = RandomStringUtils.randomAlphanumeric(64);
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(userId);
		userToken.setCreatedDate(new Date());
		userToken.setEffectiveDate(new Date());

		System.err.println("orderItem.getProduct().getId():" + orderItem.getProduct().getId());
		//String productIdsStr = String.valueOf(orderItem.getProduct().getId());
		//userToken.setProductIdsStr(productIdsStr);
		userToken.setProductId(orderItem.getProduct().getId());
		Long counts = orderItem.getAmount() * orderItem.getItemNum();
		userToken.setCounts(counts);
		userToken.setTest(false);
		userToken.setTestCount(0L);
		userToken.setDailyLimit(0);
		userToken.setAvailable(true);
		userToken.setNotified(false);
		tokenService.add(userToken);

		tokenIds.add(userToken.getId());
		// delivery.setToken(token);

		return token;
	}

	// 更新token
	public String updateToken(Long userId, OrderItem orderItems, Set<Long> tokenIds) {
		Set<UserToken> tokenList = tokenService.getByUserId(userId);
		String token = null;
		Iterator<UserToken> tokenIt = tokenList.iterator();
		while (tokenIt.hasNext()) {
			UserToken userToken = tokenIt.next();
			if (!userToken.isTest()) {
				/*
				 * 更新token可用于的商品id String productIdsStr=
				 * String.valueOf(orderItems.getProduct().getId());
				 * userToken.setProductIdsStr(productIdsStr);
				 */
				userToken.setUpdateDate(new Date());
				if (null == userToken.getCounts()) {
					userToken.setCounts(0L);
				}
				// Long counts=
				// orderItems.getPricePackage().getAmount()*orderItems.getItemNum()+userToken.getCounts();
				Long counts = orderItems.getAmount() * orderItems.getItemNum() + userToken.getCounts();
				userToken.setCounts(counts);
				userToken.setNotified(false);
				tokenService.update(userToken);

				tokenIds.add(userToken.getId());
				token = userToken.getToken();
				break;
				// delivery.setToken(userToken.getToken());

			}
		}
		return token;
	}

	// 第一次购买新建testtoken
	public String addTestToken(Long userId, OrderItem orderItems, Set<Long> tokenIds, Delivery delivery) {
		for (int i = 0; i < orderItems.getItemNum(); i++) {
			String token = RandomStringUtils.randomAlphanumeric(64);
			UserToken userToken = new UserToken();
			userToken.setToken(token);
			userToken.setUserId(userId);
			userToken.setCreatedDate(new Date());
			userToken.setEffectiveDate(new Date());

			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, 1);
			userToken.setExpireDate(c.getTime());

			//String productIdsStr = String.valueOf(orderItems.getProduct().getId());
			//userToken.setProductIdsStr(productIdsStr);
			userToken.setProductId(orderItems.getProduct().getId());
			Long counts = orderItems.getAmount();
			userToken.setCounts(0L);
			userToken.setTest(true);
			userToken.setTestCount(counts);
			userToken.setAvailable(true);
			userToken.setDailyLimit(0);
			tokenService.add(userToken);

			tokenIds.add(userToken.getId());

			delivery.setCounts(counts);
			delivery.setDeliveryDate(new Date());
			delivery.setToken(token);
			deliveryService.insert(delivery);
			logger.info("发货信息:{}", delivery);

		}
		return null;
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String orderstatus(String orderSerial) {

		try {
			Order order = orderService.getOrderByOrderSerial(orderSerial);
			if (4 == order.getStatus()) {
				return "redirect:/person/user";
			} else {
				return "redirect:/order/info?orderId=" + orderSerial;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	@RequestMapping(value = "/online", method = RequestMethod.GET)
	public String payonline(HttpSession session, Model model, String orderSerial) {

		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		User user = SessionUtil.convertToUser(sessionUser, userService);
		/*
		 * 测试订单发送邮件 Map payResult = new HashMap(); payResult.put("subject",
		 * "IP定位"); payResult.put("out_trade_no", "IP-1705121960408335");
		 * payResult.put("total_fee", "0.01"); payResult.put("gmt_payment",
		 * "2017"); alipayService.sendOrderEmail(payResult);
		 */
		try {
			if (StringUtils.isEmpty(orderSerial)) {
				throw new OrderException("未找到订单信息");
			}

			Order order = orderService.getOrderByOrderSerial(orderSerial);
			if (null == order) {
				throw new OrderException("未找到订单信息");
			}

			// 查询订单多项
			List<OrderItem> orderItemList = orderItemService.getOrderItemByOrderId(order.getId());
			if (null == orderItemList || orderItemList.size() == 0) {
				logger.info("online -------------------------------------");
				orderItemList = orderItemService.getByOrderIdWithoutPricePackage(order.getId());
			}
			try {
				// 0元订单处理
				if (order.getPrice().compareTo(new BigDecimal(0.0)) == 0) {

					if (null != orderItemList && orderItemList.size() > 0) {
						for (OrderItem orderItem : orderItemList) {
							String itemType = orderItem.getItemType();
							if (itemType.equals("IPDistrictDownload")) {
								GeoIPVersion version = versionService.getCurrentVersion();
								String currVer = version.getPublicVersion();
								alipayService.processDistrictDownload(orderItem, user.getId(), currVer, true);
								logger.info("orderid {} type {} price 0 success", orderItem.getOrderId(), itemType);
							}

							if (itemType.equals("IPDistrictAPI")) {
								if (!user.isHasFreeDistrictApi()) {
									alipayService.processFreeDistrictAPI(orderItem, user.getId());
									logger.info("orderid {} type {} price 0 success", orderItem.getOrderId(), itemType);
								} else {
									throw new OrderException("您已享受过此优惠");
								}
							}
						}
						order.setStatus(4);
						orderService.update(order);
					} else {
						throw new OrderException("订单列表不能为空");
					}
					model.addAttribute("content", "dingdan");
					return "redirect:/info";
				}

			} catch (OrderException e) {
				return "buy";
			}

			Order orderInfo = orderService.getByUserIdAndOrderId(user.getId(), order.getOrderSerial());

			if (null == orderInfo) {
				// 区县库历史版本订单
				orderInfo = orderService.getByUserAndOrder(user.getId(), order.getOrderSerial());

				if (null == orderInfo) {
					throw new OrderException("未找到订单信息");
				}
			}

			if (orderInfo.getPaymentMethod() != 0) {
				throw new OrderException("支付方式错误");
			}

			if (order.getStatus() == 2 || order.getStatus() == 4) {
				//throw new OrderException("订单已完成");
				return "redirect:/info";
			}
			if (order.getStatus() == 3) {
				throw new OrderException("订单已取消");
			}

			if (orderService.isExitsExpiredItem(order.getId())) {
				throw new OrderException("存在已失效的商品，请重新购买");
			}

			orderLogService.insert(new OrderLog(new Date(), "用户", OrderType.SUBMIT.getMessage(), orderSerial));
			// System.out.println("orderInfo.getId=="+orderInfo.getId());
			List<OrderItem> orderItemInfo = orderItemService.getByOrderId(order.getId());
			if (null == orderItemInfo || orderItemInfo.isEmpty() || orderItemInfo.size() == 0) {
				orderItemInfo = orderItemService.getByOrderIdWithoutPricePackage(order.getId());
			}

			Map payParam = new HashMap();
			payParam.put("out_trade_no", order.getOrderSerial());
			payParam.put("subject", orderItemInfo.get(0).getProduct().getProductName());
			payParam.put("total_fee", String.valueOf(orderInfo.getPrice()));

			String body = "购买内容为：";
			logger.info("用户'{}'发起支付请求，请求时的out_trade_no:{},total_fee:{}", user.getEmail(), order.getOrderSerial(),
					orderInfo.getPrice());
			for (int i = 0; i < orderItemInfo.size(); i++) {
				body = body + orderItemInfo.get(i).getProduct().getProductName() + "（"
						+ orderItemInfo.get(i).getAmountStr() + "次）X" + orderItemInfo.get(i).getItemNum() + ";";
			}
			payParam.put("body", body);

			String payHtmlText = alipayService.payOnline(payParam);
			model.addAttribute("payHtmlText", payHtmlText);
			return "pay/alipay";
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			if (e instanceof OrderException) {
				logger.error("订单支付异常--{}", e.getMessage());
				session.setAttribute("msg", e.getMessage());
			} else {
				logger.error("订单支付异常--{}", e);
			}

			// TODO  错误信息展示
			return "500";
		}
	}

	private String longIdsToStr(Set<Long> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Long id : ids) {
			sb.append(id);
			sb.append(",");
		}
		return sb.toString();
	}
}