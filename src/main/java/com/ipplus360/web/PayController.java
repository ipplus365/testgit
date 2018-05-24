package com.ipplus360.web;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.*;
import com.ipplus360.enums.OrderType;
import com.ipplus360.exception.OrderException;
import com.ipplus360.service.*;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.OrderUtil;
import com.ipplus360.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/pay")
public class PayController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderLogService orderLogService;


	/**
	 * 订单结算</br>
	 * 订单号只有在需要开发票时候才会传入,如果传入订单号,先检查是否已存在订单,如果订单存在则抛出异常
	 * @param request
	 * @param session
	 * @param model
	 * @param orderSerial 订单序列号
	 * @param isInvoice 是否需要发票信息
	 * @param payType  支付方式
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String pay(HttpServletRequest request, HttpSession session, Model model,
					  @RequestParam(required = false, value = "orderSerial") String orderSerial,
					  @RequestParam(value = "isInvoice") Integer isInvoice,
					  @RequestParam(value = "payType") Integer payType){
		String userIP = IPUtil.getUserIP(request);
		SessionUser user = SessionUtil.getSessionUser(request);
		logger.info("登录用户为:{},登录IP为:{},提交订单请求,订单号为:{}",user.getEmail(),userIP,orderSerial);
		try{
			//查询订单是否已经存在,如果订单已存在则返回错误信息
			if(!StringUtils.isEmpty(orderSerial)){
				Order order = orderService.getOrderByOrderSerial(orderSerial);
				if(null != order){
					logger.error("用户{}订单生成错误,传入订单号为:{},订单号已存在或者不是本人订单");
					throw new OrderException("订单生成错误");
				}
			}

			/*根据用户,添加订单*/
			Order order = orderService.insert(SessionUtil.convertToUser(user, userService), orderSerial, isInvoice, payType);

			/*添加订单操作日志*/
			orderLogService.insert(new OrderLog(order.getDateCreated(),"用户", OrderType.CREATE.getMessage(),order.getOrderSerial()));

			/*获取订单*/
			//Order order = orderService.getById(orderId);

			model.addAttribute("order", order);

			/*清空购物车*/
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
			cartItemService.deleteByCartId(cart.getId());
			cart.setPrice(new BigDecimal(0));
			shoppingCartService.update(cart);

			/*订单生成,更新session中的User信息*/
			User newUser = userService.getByEmail(user.getEmail());
			SessionUtil.setSessionUser(request, SessionUtil.convertToSessionUser(newUser));
			logger.debug("SessionTestCount:{}",user.getTestCount());

			
			if(0 == payType){
				return "redirect:/alipay/online?orderSerial="+order.getOrderSerial();
			}else{
				return "redirect:/pay/info?orderSerial="+order.getOrderSerial();
			}


		}catch (Throwable e) {

			logger.error("订单添加异常,登录用户为:{},登录IP为:{},提交订单请求,订单号为:{}",user.getEmail(),userIP,orderSerial);
			if(e instanceof OrderException){

				logger.error("订单添加异常--{}",e.getMessage());
				session.setAttribute("msg", e.getMessage());
			}else{
				logger.error("订单添加异常--{}",e);
				e.printStackTrace();
			}
			//message = e.getMessage();
			//logger.error("订单添加异常--{}",e);

//			if(message.equals("订单生成错误")){
//				session.setAttribute("msg", message);
//			}

			return "redirect:/error/info";
		}
	}

	/**
	 * 查询付款信息
	 * @param request
	 * @param session
	 * @param model
	 * @param orderSerial 订单序列号
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getPayInfo(HttpServletRequest request, HttpSession session, Model model, @RequestParam(value = "orderSerial") String orderSerial){

		String userIP = IPUtil.getUserIP(request);
		SessionUser user = SessionUtil.getSessionUser(request);


		logger.info("登录用户为:{},登录IP为:{},查询订单支付信息,查询订单号为:{}",user.getEmail(),userIP,orderSerial);
		try{



			Order order = orderService.getByUserIdAndOrderId(user.getId(), orderSerial);
			if (null == order) {
				order = orderService.getByUserAndOrder(user.getId(), orderSerial);
			}
			List<OrderItem> orderItems = order.getOrderItems();
			/*if( 0 == order.getStatus()){
				model.addAttribute("msg", "您尚未提交订单信息!");
				logger.error("查询错误--{}","未提交订单!");
				return "/error/info";
			}*/
			if(null == order){
				session.setAttribute("msg","未查询到订单");
				return "redirect:/error/info";
			}
			for(OrderItem oim : orderItems){
				oim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(oim.getDiscount())));
			}

			/*如果订单已取消*/
			if(3 == order.getStatus()){
				session.setAttribute("msg", "此订单您已经取消");
				return "redirect:/order/info?orderId="+order.getOrderSerial();
			}

			model.addAttribute("order", order);

			if(2==order.getStatus()){
				session.setAttribute("msg", "此订单您已经付款成功");
				return "redirect:/order/info?orderId="+order.getOrderSerial();
			}
			return "/order/pay";
		}catch(Exception e){
			e.printStackTrace();
			logger.error("登录用户为:{},登录IP为:{},查询订单支付信息,查询订单号为:{}",user.getEmail(),userIP,orderSerial);
			//logger.error("查询付款信息错误错误--P{}","查询付款信息错误出现错误");
			return "info";
		}
	}

}