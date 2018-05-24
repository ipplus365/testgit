package com.ipplus360.web;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.*;
import com.ipplus360.service.*;
import com.ipplus360.util.DateUtil;
import com.ipplus360.util.IPUtil;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/info")
public class PersonalCenter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	@Autowired
	private FileOrderService fileOrderService;
	@Autowired
	private IPService iPService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private PricePackageService pricePackageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String infoCenter(HttpServletRequest request) {

		SessionUser sessionUser = SessionUtil.getSessionUser(request);
		if (null != sessionUser) {
			Long userId = sessionUser.getId();
			String mobile = sessionUser.getMobile();
			Organization org = orgService.getOneByeUserId(userId);
			User user = userService.getById(userId);
			if (null != org) {
				request.setAttribute("org_committed", true);
				request.setAttribute("org", org);
			} else {
				request.setAttribute("org_committed", false);
			}

			if (null != user) {
				if (StringUtils.isBlank(user.getMobile())) {
					request.setAttribute("hasMobile", false);
				} else {
					request.setAttribute("hasMobile", true);
				}
			}

			boolean hasScene = false;
			boolean canWebLocate = false;

			Set<UserToken> tokens = tokenService.getByUserId(userId);
			for (UserToken userToken : tokens) {
				if (userToken.hasScene() && userToken.sceneCountValid()) {
					hasScene = true;
				}
				if (userToken.canWebLocate()) {
					canWebLocate = true;
				}
			}

			request.setAttribute("content", request.getAttribute("content"));
			request.setAttribute("canWebLocate", canWebLocate);
			request.setAttribute("hasScene", hasScene);
			request.setAttribute("mobile", mobile);

			String userIp = IPUtil.getUserIP(request);
			request.setAttribute("user_ip", userIp);
		}
		return "info";
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Result getUserInfo(HttpServletRequest request) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (null != sessionUser) {

				User user = userService.getByEmail(sessionUser.getEmail());
				String city = iPService.getLocation(user.getLastLoginFrom());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user", user);
				map.put("city", city);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				map.put("lastlogin", DateUtil.date2LocalDateTime(user.getLastLogin()).atZone(ZoneId.systemDefault())
						.format(formatter));
				map.put("reg", DateUtil.date2LocalDateTime(user.getDateCreated()).atZone(ZoneId.systemDefault())
						.format(formatter));
				return new Result<>(true, map, "");
			}
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "获取用户信息失败");
		}
		return new Result(false, "未获取用户信息");
	}

	/**
	 * 获取用户token列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tokens", method = RequestMethod.GET)
	public Result tokenList(HttpServletRequest request,
			@RequestParam(value = "currPage", required = false) Integer currPage,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);

			if (null != sessionUser) {

				if (null == currPage) {
					currPage = 1;
				}

				if (null == pageSize) {
					pageSize = 10;
				}
				Set<UserToken> tokens = tokenService.getTokensByUserId(sessionUser.getId(), currPage, pageSize);

				for (UserToken token : tokens) {
					token.setCanWebLocate(token.canWebLocate());
				}

				// token总数量
				int allCount = tokenService.getAllCountByUserId(sessionUser.getId());

				if (allCount > (currPage - 1) * pageSize) {

					return new Result<>(true, tokens, "");
				}
			}

		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "获取用户Token失败");
		}
		return new Result(false, "获取用户Token失败");
	}

	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public Result getOrders(HttpServletRequest request,
			@RequestParam(value = "currPage", required = false) Integer currPage,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);

			if (null == currPage) {
				currPage = 1;
			}

			if (null == pageSize) {
				pageSize = 10;
			}
			
			List<Order> orderList = orderService.getAllOrdersByUserid(sessionUser.getId(), currPage, pageSize);
			for (Order order : orderList) {
				List<OrderItem> orderItems = order.getOrderItems();
				if (null != orderItems && orderItems.size() > 0) {
					for (OrderItem item : orderItems) {
						Long pid = item.getPricePackageId();
						if (null != pid) {
							PricePackage pricePackage = pricePackageService.getById(pid);
							if (null != pricePackage && pricePackage.isAvailable()) {
								item.setPricePackage(pricePackage);
							}
						}
					}
				}
			}

			int allCount = orderService.getAllCountByUserId(sessionUser.getId());

			if (allCount > (currPage - 1) * pageSize) {

				return new Result<>(true, orderList, "");
			} else {
				return new Result<>(false, "获取用户订单失败");
			}

		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "获取用户订单失败");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/fileOrders", method = RequestMethod.GET)
	public Result getFileOrders(HttpServletRequest request,
			@RequestParam(value = "currPage", required = false) Integer currPage,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (null != sessionUser) {
				Long userId = sessionUser.getId();
				if (null == currPage) {
					currPage = 1;
				}

				if (null == pageSize) {
					pageSize = 10;
				}
				List<FileOrder> fileOrders = fileOrderService.getByUserId(userId, currPage, pageSize);
				
				int allCount = fileOrderService.getAllCountByUserId(userId);

				if (allCount > (currPage - 1) * pageSize) {

					return new Result<>(true, fileOrders, "");
				} else {
					return new Result<>(false, "获取IP区县库订单失败");
				}
			}

		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "获取IP区县库订单失败");
		}
		return new Result(false, "系统检测到您未登录，请先登录");
	}

	@ResponseBody
	@RequestMapping(value = "hasScene", method = RequestMethod.GET)
	public Result hasScene(HttpServletRequest request) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			Set<UserToken> tokens = tokenService.getByUserId(sessionUser.getId());

			for (UserToken userToken : tokens) {
				if (userToken.hasScene() && userToken.sceneCountValid()) {
					return new Result(true, "");
				}
			}
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "获取场景失败");
		}
		return new Result(false, "请购买场景后再用");
	}

	/*
	 * // @SuppressWarnings("null")
	 * 
	 * @RequestMapping(value = "/{url}", method = RequestMethod.GET) public
	 * String infoCenter(HttpServletRequest request, HttpSession session, Model
	 * model, @PathVariable String url) { String msg; try { String userIP =
	 * IPUtil.getUserIP(request); // User user = (User)
	 * session.getAttribute(Constants.CURRENT_USER); SessionUser sessionUser =
	 * (SessionUser) session.getAttribute(Constants.SESSION_USER);
	 * 
	 * User user = userService.getById(sessionUser.getId()); if
	 * (StringUtils.isBlank(user.getMobile())) {
	 * model.addAttribute("completeMobile", "请完善手机号"); }
	 * logger.debug("用户中心登录用户为:{},登录IP为:{}", sessionUser.getEmail(), userIP); //
	 * model.addAttribute("index",url);
	 * 
	 * switch (url) { case "user": List<Map> userToken =
	 * tokenService.getForPerson(sessionUser.getId()); if
	 * (tokenService.canLocate(sessionUser.getId())) {
	 * model.addAttribute("canLocate", true); } else {
	 * model.addAttribute("canLocate", false); } String message = (String)
	 * session.getAttribute("msg"); if (StringUtils.isNotBlank(message)) {
	 * model.addAttribute("msg", message); session.removeAttribute("msg"); } if
	 * (null != userToken) { model.addAttribute("tokenList", userToken);
	 * logger.debug("用户中心查询UserToken:{}", userToken); return "/infocenter/user";
	 * } model.addAttribute("msg", "余量列表为空"); return "/infocenter/user"; case
	 * "order": msg = (String) session.getAttribute("msg");
	 * 
	 * if (!StringUtils.isEmpty(msg)) { model.addAttribute("msg", msg);
	 * session.removeAttribute("msg"); }
	 *//* 获取订单信息 *//*
					 * List<Order> orderList =
					 * orderService.getAll(sessionUser.getId()); if
					 * (orderList.size() != 0) { model.addAttribute("orderList",
					 * orderList); return "/infocenter/order"; }
					 * model.addAttribute("msg", "订单列表为空"); return
					 * "/infocenter/order"; case "comment":
					 *//* 获取评论信息 *//*
								 * model.addAttribute("comment",
								 * commentService.getCommentByUserId(sessionUser
								 * .getId())); return "/infocenter/comment";
								 * case "company":
								 * 
								 * msg = (String) session.getAttribute("msg");
								 * if (!StringUtils.isEmpty(msg)) {
								 * model.addAttribute("msg", msg);
								 * session.removeAttribute("msg"); }
								 * 
								 * Organization org = (Organization)
								 * session.getAttribute("company"); if (null !=
								 * org) { model.addAttribute("company", org);
								 * session.removeAttribute("company"); return
								 * "/infocenter/company"; }
								 * 
								 *//* 企业认证信息 *//*
												 * List<Organization> company =
												 * organizationService.
												 * getByUserId(sessionUser.getId
												 * ());
												 * 
												 * logger.info(
												 * "用户中心查询企业认证信息:{}", company);
												 * 
												 * if (null != company &&
												 * company.size() > 0) {
												 * model.addAttribute("company",
												 * company.get(0)); }
												 * 
												 * return "/infocenter/company";
												 * case "changPwd":
												 *//* 修改登录密码 *//*
															 * return
															 * "/infocenter/changPwd";
															 * default: model.
															 * addAttribute(
															 * "msg", "暂无数据");
															 * return
															 * "/infocenter/user";
															 * }
															 * 
															 * } catch
															 * (Exception e) {
															 * e.printStackTrace
															 * (); return
															 * "redirect:/error/info";
															 * } }
															 */

}