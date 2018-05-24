package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.*;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DiscountService countService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private PricePackageService pricePackageService;
	@Autowired private UserService userService;

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout() {
		return "/checkout";
	}

	/**
	 * 获取用户购物车size
	 *
	 * @param request
	 *            request
	 * @return size
	 */
	@ResponseBody
	@RequestMapping(value = "/size", method = RequestMethod.GET)
	public Result cartSize(HttpServletRequest request) {
		try {
			ShoppingCart cart = getCart(request);
			if (null != cart) {

				List<CartItem> items = cart.getCartItems();

				int size = 0;
				if (null != items && !items.isEmpty() && items.size() > 0) {
					size = items.size();
				}

				return new Result<>(true, size, "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
		}
		return new Result<>(false, 0, "查询购物车出错");
	}

	/**
	 * 用户购物车列表
	 *
	 * @param request
	 *            request
	 * @return 购物车
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(HttpServletRequest request) {
		try {
			SessionUser user = SessionUtil.getSessionUser(request);
			ShoppingCart cart = getCart(request);

			if (null != cart) {
				List<CartItem> items = cart.getCartItems();
				// 区县库下载购物车信息返回
				shoppingCartService.getIPDistrictDownloadInfo(items);
				cart.setCartItems(items);
				if (OrderUtil.calculateCart(items, user) == null) {
					cart.setPrice(BigDecimal.valueOf(0));
				} else {
					cart.setPrice(OrderUtil.calculateCart(items, user));
				}
			} else {
				return new Result(false, "请先登录");
			}

			return new Result<>(true, cart, "SUCCESS");
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "查询购物车列表出错");
		}
	}

	/**
	 * 添加购物车
	 * 
	 * @param productId
	 *            商品id
	 * @param priceId
	 *            价格包id
	 * @param productCount
	 *            商品数量
	 * @param request
	 *            request
	 * @return 购物车
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestParam("pId") Long productId, @RequestParam("priceId") Long priceId,
			@RequestParam("accuracyId") Integer accuracyId, @RequestParam("count") Integer productCount,
			@RequestParam("cartItemType") String cartItemType, @RequestParam(value = "freeApi", required = false) Integer freeApi,
					  HttpServletRequest request) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (!isValidNum(productId, priceId, productCount)) {
				return new Result(false, "请使用合法参数");
			}
			ShoppingCart cart = getCart(request);
			if (null == cart) {
				cart = newCart(request);
			}
			if (null != freeApi && freeApi == 1) {
				User user = SessionUtil.convertToUser(sessionUser, userService);
				if (StringUtils.isBlank(user.getMobile())) {
					return new Result(false, "请您进入“个人中心—安全设置—绑定手机”后即可参与活动！");
				}
				/*if (null != user.getIsOrg() && user.getIsOrg() != 1) {
					return new Result(false, "您还不是企业用户，请先进行企业认证");
				}*/
				if (user.isHasFreeDistrictApi()) {
					return new Result(false, "您已享受过此优惠");
				}
				productCount = 1;
				priceId = 16L;
			}
			shoppingCartService.insert(sessionUser, cart, productId, priceId, accuracyId, productCount, cartItemType, freeApi);
			return new Result<>(true, cart, "添加成功");
		} catch (OrderException e) {
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "添加购物车失败");
		}
	}

	/**
	 * 离线场景库添加购物车
	 * 
	 * @param productId
	 * @param priceId
	 * @param productCount
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addSceneLibrary", method = RequestMethod.POST)
	public Result addSceneLibrary(@RequestParam("pId") Long productId, @RequestParam("priceId") List<Long> priceId,
			@RequestParam("accuracyId") Integer accuracyId, @RequestParam("count") Integer productCount,
			HttpServletRequest request) {
		try {

			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			User user = SessionUtil.convertToUser(sessionUser, userService);

			if (!isValidNum(productId, priceId, productCount)) {
				return new Result(false, "请使用合法参数");
			}
			if (user.getIsOrg() != 1) {
				return new Result(false, "您还不是企业用户，暂不能购买应用场景");
			}
			ShoppingCart cart = getCart(request);
			if (null == cart) {
				cart = newCart(request);
			}
			shoppingCartService.addSceneLibrary(SessionUtil.getSessionUser(request), cart, productId, priceId,
					accuracyId, productCount);

			return new Result<>(true, cart, "添加成功");
		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "添加购物车失败");
		}
	}

	/**
	 * 区县库添加到购物车
	 * 
	 * @param coordId
	 *            坐标系ID
	 * @param versionId
	 *            版本ID
	 * @param hId
	 *            历史版本ID
	 * @param productId
	 *            产品ID
	 * @param request
	 *            request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addDictrict", method = RequestMethod.POST)
	public Result addDistrict(@RequestParam(value = "cid") Integer coordId,
			@RequestParam(value = "vid") Integer versionId, @RequestParam(value = "hid", required = false) Integer hId,
			@RequestParam(value = "pid") Long productId, HttpServletRequest request) {
		try {
			if (null == coordId || versionId == null || productId != 5) {
				return new Result(false, "请使用合法参数");
			}

			ShoppingCart cart = getCart(request);
			if (null == cart) {
				cart = newCart(request);
			}
			shoppingCartService.addDistrict(SessionUtil.getSessionUser(request),
					cart, productId, coordId, versionId, hId);

			return new Result<>(true, cart, "添加成功");
		} catch (OrderException e) {
			logger.error("{}", e.getMessage());
			return new Result(false, e.getMessage());

		} catch (Exception e) {
			logger.error("{}", e.getMessage(), e);
			return new Result(false, "添加购物车失败");
		}
	}

	private boolean isValidNum(Long productId, Long priceId, Integer productCount) {
		return (null != productId && productId > 0) && (null != priceId && priceId > 0)
				&& (null != productCount && productCount > 0 && productCount < 100);
	}

	private boolean isValidNum(Long productId, List<Long> priceId, Integer productCount) {
		return (null != productId && productId > 0) && (null != priceId && priceId.size() > 0)
				&& (null != productCount && productCount > 0 && productCount < 100);
	}

	/**
	 * 获取购物车
	 * 
	 * @param request
	 *            request
	 * @return 购物车
	 */
	private ShoppingCart getCart(HttpServletRequest request) {
		SessionUser user = SessionUtil.getSessionUser(request);
		if (user != null) {
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
			return cart;
		} else {
			return null;
		}
	}

	/**
	 * 更新购物车
	 *
	 * @param session
	 * @param itemId
	 *            购物项ID
	 * @param itemNum
	 *            购物项数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<ShoppingCart> updateCartItem(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "itemId") Long itemId, @RequestParam(value = "itemNum") Integer itemNum) {
		String message = null;
		SessionUser user = null;
		String ip = IPUtil.getUserIP(request);
		try {
			user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
			if (null == user) {

				return new Result<>(false, "登录超时");
			}
			/* 获取用户当前购物车 */
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());

			if (null == cart) {
				newCart(user);
				return new Result<>(false, "购物车为空,请添加商品");
			}

			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}

			if (null == itemId || null == itemNum) {

				logger.error("非法请求,用户{}前台传入参数为空--IP地址:{}, itemId={}, itemNum={}", user.getEmail(), ip, itemId, itemNum);
				return new Result<>(false, cart, "请不要输入空值");
			}

			if (0 >= itemId || 0 >= itemNum || 100 < itemNum) {

				logger.error("非法请求,用户{}前台传入值为负数--IP地址:{}, itemId={}, itemNum={}", user.getEmail(), ip, itemId, itemNum);
				return new Result<>(false, cart, "请输入1-100的正整数");
			}

			CartItem cartItem = cartItemService.getById(itemId, cart.getId());

			if (null == cartItem || !cartItem.getCartId().equals(cart.getId())) {
				logger.error("未找到用户{}相关购物项信息--IP地址:{}, itemId={}, itemNum={}", user.getEmail(), ip, itemId, itemNum);
				return new Result<>(false, shoppingCartService.getByUserId(user.getId()), "查无此项,请在商品详情页面添加");
			}

			cartItem.setItemNum(itemNum);

			/* IP定位计算 */
			Long productId = cartItem.getProduct().getId();// 获取当前购物项产品ID
			Long pricePackageId = cartItem.getPricePackage().getId();// 当前购物项的价格包ID
			Integer accuracyId = cartItem.getPricePackage().getAccuracy().getId();// 当前购物项的场景ID
			PricePackage pricePackage;
			Discount discount;

			if (1 == productId) {

				pricePackage = pricePackageService
						.getByPricePackage(new PricePackage(pricePackageId, productId, accuracyId));
				cartItem.setPricePackage(pricePackage);

				logger.debug("更新购物项为:{}-{}次,更新数量为:{}", cartItem.getProduct().getProductName(),
						cartItem.getPricePackage().getAmountStr(), cartItem.getItemNum());

				discount = countService
						.getDiscounts(new Discount(pricePackage.getAccuracy().getId(), pricePackage.getId(), itemNum));

			} else if (3 == productId) {

				pricePackage = pricePackageService
						.getByPricePackage(new PricePackage(pricePackageId, productId, accuracyId));
				cartItem.setPricePackage(pricePackage);

				logger.debug("更新购物项为:{}-{}次,更新数量为:{}", cartItem.getProduct().getProductName(),
						cartItem.getPricePackage().getAmountStr(), cartItem.getItemNum());

				discount = countService
						.getDiscounts(new Discount(pricePackage.getAccuracy().getId(), pricePackage.getId(), itemNum));

			} else if (5 == productId) {

				pricePackage = pricePackageService
						.getByPricePackage(new PricePackage(pricePackageId, productId, accuracyId));
				cartItem.setPricePackage(pricePackage);

				logger.debug("更新购物项为:{}-{}次,更新数量为:{}", cartItem.getProduct().getProductName(),
						cartItem.getPricePackage().getAmountStr(), cartItem.getItemNum());

				discount = countService
						.getDiscounts(new Discount(pricePackage.getAccuracy().getId(), pricePackage.getId(), itemNum));

			} else {
				// calculateAndAddCartItem(productId, itemNum, user);
				throw new OrderException("暂未提供此项服务");
			}
			Map<String, Object> errMsg = new HashMap<>();

			boolean isLimit = OrderUtil.isLimit(cartItem.getPricePackage(), cartItem, user, errMsg);

			/* 添加购物项成功,计算总价,更新购物车 */
			if (isLimit) {

				Integer count = (Integer) errMsg.get("count");
				message = (String) errMsg.get("msg");

				if (null != count && 0 < count) {
					cartItem.setItemNum(count);

				} else {
					cart = shoppingCartService.getByUserId(user.getId());
					for (CartItem cim : cart.getCartItems()) {
						cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
					}
					message = (String) errMsg.get("msg");
					logger.debug(message + "更新失败!!!");
					return new Result<>(false, cart, message);
				}
			}

			OrderUtil.calculateItem(discount, cartItem, user);// 计算购物项价格
			cartItemService.update(cartItem);// 更新购物项信息

			List<CartItem> cartItemList = shoppingCartService.getByUserId(user.getId()).getCartItems();// 获取购物项列表

			// 更新购物项价格
			cart.setPrice(OrderUtil.calculateCart(cartItemList, user));
			shoppingCartService.update(cart);

			cart = shoppingCartService.getByUserId(user.getId());

			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}

			if (StringUtils.isNotEmpty(message)) {
				return new Result<>(false, cart, message);
			}

			return new Result<>(true, cart, "更新成功");

		} catch (Exception e) {

			if (null == user) {

				return new Result<>(false, "登录超时");
			}
			e.printStackTrace();
			logger.error("购物项更新异常--{}", e.getMessage());
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}
			return new Result<>(false, shoppingCartService.getByUserId(user.getId()), message);
		}

	}

	/**
	 * 删除购物项
	 *
	 * @param request
	 * @param session
	 * @param itemId
	 *            购物项ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Result<ShoppingCart> deleteCartItem(HttpServletRequest request, HttpSession session,
			@RequestParam(required = true, value = "itemId") Long itemId) {

		SessionUser user = null;
		String ip = IPUtil.getUserIP(request);
		try {
			// user = (User) session.getAttribute(Constants.CURRENT_USER);
			user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
			if (null == user) {

				return new Result<>(false, "登录超时");
			}

			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());

			if (null == cart) {
				newCart(user);
				return new Result<>(false, "购物车为空,请添加商品");
			}

			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}

			if (null == itemId) {

				logger.error("非法请求,前台传入参数为空--IP地址:{}, itemId={}, itemNum={}", ip, itemId);
				return new Result<>(false, cart, "请不要输入空值");
			}

			if (0 >= itemId) {

				logger.error("非法请求,前台传入值为负数--IP地址:{}, itemNum={}", ip, itemId);
				return new Result<>(false, cart, "请输入正整数");
			}

			/* 校验是否是该用户的购物项 */
			CartItem cartItem = cartItemService.getById(itemId, cart.getId());
			/*
			 * List<CartItem> cartItemLists = cart.getCartItems(); for(CartItem
			 * ci:cartItemLists){
			 * if(ci.getItemType().equals("IPDistrictDownload")){ cartItem =
			 * cartItemService.getByCartItem(itemId, cart.getId()); }else{
			 * cartItem = cartItemService.getById(itemId, cart.getId()); } }
			 */
			if (null == cartItem || cartItem.getCartId().longValue() != cart.getId().longValue()) {
				logger.error("未找到相关购物项信息--IP地址:{}, itemId={}, itemNum={}", ip, itemId);

				/*
				 * cart = shoppingCartService.getByUserId(user.getId());
				 * 
				 * for(CartItem cim : cart.getCartItems()){
				 * cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.
				 * getDiscount()))); }
				 */
				return new Result<>(false, cart, "查无此项");
			}

			/**
			 * 删除购物项,重新获取购物信息,计算后更新购物车,返回购物车给前台
			 */
			cartItemService.delete(itemId);
			List<CartItem> cartItemList = shoppingCartService.getByUserId(user.getId()).getCartItems();
			shoppingCartService.getIPDistrictDownloadInfo(cartItemList);
			cart.setCartItems(cartItemList);
			cart.setPrice(OrderUtil.calculateCart(cartItemList, user));
			shoppingCartService.update(cart);
			logger.info("用户{} 购物项{} 已删除", user.getEmail(), cartItem);

			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}
			return new Result<>(true, cart, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("购物项删除异常--{}", e.getMessage());
			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());
			for (CartItem cim : cart.getCartItems()) {
				cim.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cim.getDiscount())));
			}
			return new Result<>(false, cart, "购物项删除异常");
		}

	}

	/**
	 * 为用户新建一个购物车
	 *
	 * @param request
	 *            request
	 */
	private ShoppingCart newCart(HttpServletRequest request) {
		SessionUser user = SessionUtil.getSessionUser(request);
		return newCart(user);
	}

	private ShoppingCart newCart(SessionUser user) {

		ShoppingCart cart = new ShoppingCart();
		cart.setUserId(user.getId());
		cart.setDateCreated(new Date());
		cart.setStatus(0);
		shoppingCartService.insert(cart);
		return cart;
	}

	/**
	 * 获取购物车信息
	 *
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getCartInfo(HttpServletRequest request, HttpSession session, Model model) {
		SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		String userIP = IPUtil.getUserIP(request);
		logger.info("Email:{},ip:{},查询购物车", user.getEmail(), userIP);
		try {

			ShoppingCart cart = shoppingCartService.getByUserId(user.getId());

			/* 如果购物车为空,为该用户新建一个购物车 */
			if (null == cart) {
				newCart(user);
				return "/cart/info";
			}
			List<CartItem> cartItemList = cart.getCartItems();
			if (null != cartItemList && !cartItemList.isEmpty() && 0 < cartItemList.size()) {
				for (CartItem cit : cartItemList) {
					logger.debug("购物车列表1:{}", cit.getDiscount());
					cit.setDiscount(OrderUtil.doubleTrans(Double.parseDouble(cit.getDiscount())));
					logger.debug("购物车列表:{}", cit);
				}
				// cart.setCartItems(cartItemList);
			}
			model.addAttribute("cart", cart);
			String message = (String) session.getAttribute("msg");
			if (null != message) {
				model.addAttribute("msg", message);
				session.removeAttribute("msg");
			}
			return "/cart/info";
		} catch (Exception e) {

			e.printStackTrace();
			// model.addAttribute("msg", e.getMessage());
			logger.error("购物车查询异常:{}", e);
			return "redirect:/error/info";
		}
	}

}
