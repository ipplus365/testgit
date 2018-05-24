package com.ipplus360.web;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.InvoiceOutput;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.Invoice;
import com.ipplus360.entity.Order;
import com.ipplus360.entity.ShoppingCart;
import com.ipplus360.service.InvoiceService;
import com.ipplus360.service.OrderService;
import com.ipplus360.service.ShoppingCartService;
import com.ipplus360.util.OrderUtil;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result<?> addInvoice(HttpServletRequest request, HttpSession session, Model model, Invoice invoice) {

		SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		String ip = request.getRemoteAddr();

		try {

			if (null == user) {
				return new Result<>(false, "登录超时");
			}

			logger.info("用户登录IP为:{},邮箱为:{}", ip, user.getEmail());
			
			//获取购物车信息
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
		
			if(null != cart){
				if(cart.getCartItems().isEmpty()){
					return new Result<>(false, "购物车为空,发票添加失败");
				}
			}else{
				return new Result<>(false, "购物车为空,发票添加失败");
			}
			

			if (null == invoice) {
				return new Result<>(false, "发票信息为空");
			}

			String orderSerial = null;
			if (invoice.getOrderId() == null || invoice.getOrderId().equals("") ) {
				orderSerial = OrderUtil.getOrderId();
			} else {
				orderSerial = invoice.getOrderId();
			}

			Order order = orderService.getOrder(user.getId(), orderSerial);
			if (null != order) {
				return new Result<>(false, "未查询到相关订单信息,传入订单号为:" + orderSerial);
			}

			invoice.setUserId(user.getId());
			invoice.setOrderId(orderSerial);

			// 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断

			for (Field f : invoice.getClass().getDeclaredFields()) {

				f.setAccessible(true);
				logger.info("Invoice--{}", invoice);
				if ((null == f.get(invoice) || "" == f.get(invoice) || f.get(invoice).equals(""))
						&& !f.getName().equals("id")) {

					return new Result<>(false, "发票信息中存在空值");
				}
			}

			String message;
			Invoice inv = invoiceService.getByOrderId(user.getId(), orderSerial);
			if (null != inv) {
				invoiceService.update(invoice);
				message = "修改成功";
			} else {
				message = "添加成功";
				invoiceService.insert(invoice);
			}

			// order.setIsInvoice(1);
			// orderService.update(order);
			return new Result<>(true, orderSerial, message);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发票信息添加异常--{}", e.getMessage());
			return new Result<>(false, "添加失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<InvoiceOutput> getInvoiceInfo(HttpServletRequest request, HttpSession session, Model model) {

		SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		String ip = request.getRemoteAddr();

		try {

			if (null == user) {
				return new Result<>(false, "登录超时");
			}

			logger.info("用户登录IP为:{},邮箱为:{}", ip, user.getEmail());

			// Order order = orderService.getOrder(user.getId(), orderSerial);

			// if(null == order){
			// return new Result<>(false,"未查询到相关订单信息,请确定订单号是否正确");
			// }

			Invoice invoice = invoiceService.getByUserId(user.getId());
			if (null != invoice) {
				return new Result<>(true, (new InvoiceOutput(invoice)), "查询成功");
			}

			return new Result<>(false, "未查询到相关发票信息,请确定是否已选择开具发票信息");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发票信息查询异常--{}", e.getMessage());
			return new Result<>(false, e.getMessage());
		}
	}
}
