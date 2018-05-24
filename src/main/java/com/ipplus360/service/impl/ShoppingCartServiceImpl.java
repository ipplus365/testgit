package com.ipplus360.service.impl;

import com.ipplus360.dao.ShoppingCartDao;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.*;
import com.ipplus360.exception.OrderException;
import com.ipplus360.service.*;
import com.ipplus360.util.OrderUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PricePackageService pricePackageService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DiscountService countService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	ProductAttrService productAttrService;

	@Autowired
	private FileOrderService fileOrderService;

	@Autowired
	private GeoIPVersionService versionService;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Override
	public ShoppingCart getById(Long id) {

		return shoppingCartDao.getById(id);
	}

	@Override
	public ShoppingCart getByUserId(Long userId) {

		return shoppingCartDao.getAllByUserId(userId);
	}

	@Override
	public List<ShoppingCart> getAll() {

		return shoppingCartDao.getAll();
	}

	@Override
	public List<ShoppingCart> getAll(ShoppingCart entity) {

		return shoppingCartDao.getAllByCart(entity);
	}

	@Override
	@Transactional
	public int update(ShoppingCart entity) {

		return shoppingCartDao.update(entity);
	}

	@Override
	@Transactional
	public int insert(ShoppingCart entity) {

		return shoppingCartDao.insert(entity);
	}

	@Override
	public ShoppingCart getCountByUserId(Long userId) {
		return shoppingCartDao.getCountByUserId(userId);
	}

	@Transactional
	@Override
	public void addDistrict(SessionUser sessionUser, ShoppingCart cart, Long productId, Integer coordId,
			Integer versionId, Integer hId) {

		// 1. 获取产品
		Product product = productService.getById(productId);
		if (null == product) {
			throw new OrderException("产品不存在");
		}

		ProductAttr productAttr = productAttrService.getById(versionId);
		System.err.println("productAttr:" + productAttr);
		String version = versionService.getCurrentVersion().getPublicVersion();
		if (null != productAttr) {
			if ("previous".equals(productAttr.getAttrType())) {
				ProductAttr pversionAttr = productAttrService.getById(hId);
				if (null != pversionAttr) {
					version = pversionAttr.getAttrType().trim();
				}
			}
		}
		logger.info("shopCart version:" + version);
		Integer[] ids = new Integer[] { coordId, versionId, hId };

		// 2. 计算价格
		if (null == hId) {
			ids = new Integer[] { coordId, versionId, 6, 7, 11, 13 };
		}
		String idsStr = StringUtils.join(ids, ",");

		FileOrder fileOrder = fileOrderService.getByAttrIdsAndVersion(sessionUser.getId(),
				idsStr, version);

		if (null != fileOrder) {
			throw new OrderException("您已购买过此版本，请前往个人中心下载");
		}

		List<ProductAttr> attrs = productAttrService.getAttrsByIds(ids);
		for (ProductAttr attr : attrs) {
			String type = attr.getAttrType();
			if (type.startsWith("\t")) {
				type = type.trim();
			}
			System.err.println("type:" + type);
			attr.setAttrType(type);
		}

		// 3. 初始化购物项
		CartItem item = new CartItem(cart.getId(), product, idsStr);

		// 设置购物项类型 IP区县库离线版
		item.setItemType("IPDistrictDownload");
		item.setAttrs(attrs);
		BigDecimal price = productAttrService.calculate(attrs);
		item.setOriginalPrice(price);

		//购买信息
		item.setPrice(price);
		item.setItemNum(1);
		item.setDiscount("100");

		// 4. 入库
		List<CartItem> cartItems = cartItemService.addDistrict(cart, item);
		
		List<CartItem> cartItemList = getByUserId(sessionUser.getId()).getCartItems();
		getIPDistrictDownloadInfo(cartItemList);
		cart.setCartItems(cartItemList);
		cart.setPrice(OrderUtil.calculateCart(cartItemList, sessionUser));
		update(cart);
		
	}

	@Override
	public Map<String, Object> addSceneLibrary(SessionUser user, ShoppingCart cart, Long productId, List<Long> priceId,
			Integer accuracyId, Integer productCount) {
		String message = null;
		// 获取场景离线库产品
		Product product = productService.getById(productId);
		if (null == product) {
			throw new OrderException("未找到相应的产品信息");
		}

		PricePackage pricePackage = null;
		for (Long priId : priceId) {
			pricePackage = pricePackageService.getByPricePackage(new PricePackage(priId, productId, accuracyId));
			if (null == pricePackage) {
				throw new OrderException("未找到相应的价格套餐");
			}
			logger.info("Email:{},ip:{},购买数量为:{},价格包ID:{}", user.getEmail(), user.getLastLoginFrom(), productCount,
					pricePackage.getId());
			// 初始化购物项
			CartItem cartItem = new CartItem(cart.getId(), productId, priId, pricePackage, productCount);

			// 计算购物项价格
			OrderUtil.calculateSceneLibraryPrice(cartItem, user, null);

			// 如果购物项已存在则更新购物项,否则新添加一个购物项
			boolean hasItem = initCartItem(user, cart, cartItem, product, pricePackage, productCount, false, null);

			if (hasItem) {
				cartItemService.update(cartItem);
			} else {
				cartItemService.insert(cartItem);
			}
		}

		List<CartItem> cartItemList = getByUserId(user.getId()).getCartItems();
		cart.setCartItems(cartItemList);
		cart.setPrice(OrderUtil.calculateCart(cartItemList, user));
		update(cart);
		Map<String, Object> cartInfo = new HashMap<>();
		cartInfo.put("cart", cart);
		cartInfo.put("message", message);
		return cartInfo;

	}

	@Override
	public Map<String, Object> insert(SessionUser user, ShoppingCart cart, Long productId, Long pricePackageId,
			Integer accuracyId, Integer itemNum, String cartItemType, Integer freeApi) {
		String message = null;
		/**
		 * 获取产品信息,根据不同的产品进行计算,产品分别为 1.IP定位 2.逆IP定位 3.应用场景 4.IP反作弊 5.区县库 6.用户画像
		 */
		Product product = productService.getById(productId);
		if (null == product) {
			throw new OrderException("未找到相应的产品信息");
		}
		PricePackage pricePackage;

		if (null != freeApi && freeApi == 1) {
			pricePackage = pricePackageService.getByPriceId(16L);
			itemNum = 1;
		} else {
			pricePackage = pricePackageService.getByPricePackage(new PricePackage(pricePackageId, productId, accuracyId));
		}

		if (null == pricePackage) {
			throw new OrderException("未找到相应的价格套餐");
		}
		logger.info("Email:{},ip:{},购买数量为:{},价格包ID:{}", user.getEmail(), user.getLastLoginFrom(), itemNum,
				pricePackage.getId());

		CartItem cartItem = new CartItem(cart.getId(), productId, pricePackageId, itemNum, cartItemType);

		boolean hasItem = initCartItem(user, cart, cartItem, product, pricePackage, itemNum, false, freeApi);


		// 判断是否超过购买限制
		Map<String, Object> errorMsg = new HashMap<>();
		boolean isLimit = OrderUtil.isLimit(pricePackage, cartItem, user, errorMsg);
		if (isLimit) {
			Integer count = (Integer) errorMsg.get("count");
			System.err.println("count:" + count);
			message = (String) errorMsg.get("msg");
			if (null != count && 0 < count) {
				cartItem.setItemNum(count);
			} else {
				throw new OrderException("测试包只能购买3次");
			}

		}

		/* IP定位计算 */
		if (1 == product.getId()) {
			Discount discount = countService.getDiscounts(
					new Discount(pricePackage.getAccuracy().getId(), pricePackage.getId(), cartItem.getItemNum()));
			OrderUtil.calculateItem(discount, cartItem, user);
		} else if (3 == product.getId()) {
			/* 应用场景计算 */
			Discount discount = countService.getDiscounts(
					new Discount(pricePackage.getAccuracy().getId(), pricePackage.getId(), cartItem.getItemNum()));
			OrderUtil.calculateItem(discount, cartItem, user);

		} else if (5 == product.getId()) {
			// 区县库API价格计算, 区县库活动
			OrderUtil.calculateSceneLibraryPrice(cartItem, user, freeApi);
		} else {
			/* 其他产品计算 */
			// calculateAndAddCartItem(productId, itemNum, user);
			throw new OrderException("暂未提供此项服务");
		}

		/* 如果购物项已存在则更新购物项,否则新添加一个购物项 */
		if (hasItem) {
			cartItemService.update(cartItem);
		} else {
			cartItemService.insert(cartItem);
		}

		List<CartItem> cartItemList = getByUserId(user.getId()).getCartItems();
		getIPDistrictDownloadInfo(cartItemList);
		cart.setCartItems(cartItemList);
		cart.setPrice(OrderUtil.calculateCart(cartItemList, user));
		update(cart);

		Map<String, Object> cartInfo = new HashMap<>();
		cartInfo.put("cart", cart);
		cartInfo.put("message", message);
		return cartInfo;
	}

	/**
	 * 初始化购物项</br>
	 * 先判断是否存在该订单项,如果存在则总数量等于已存在的数量加上新的数量, 如果订单项不存在,则新建一个订单项</br>
	 * 注:此版本不做多项同时购买,添加购物车前先清空原购物车,如果需要做多项同时购买的话只需注释掉else里面清空购物项的代码即可</br>
	 * 返回true说明订单项已存在,只需要更新相应订单项即可;</br>
	 * 返回false说明订单项不存在,需要新建订单项
	 * 
	 * @param cart
	 *            新建购物车
	 * @param cartItem
	 *            新建购物项
	 * @param product
	 *            产品ID
	 * @param itemNum
	 *            购买数量
	 * @param isUpdate
	 *            是否为更新
	 * @return
	 */
	private boolean initCartItem(SessionUser user, ShoppingCart cart, CartItem cartItem, Product product,
			PricePackage pricePackage, Integer itemNum, boolean isUpdate, Integer freeApi) {
		try {
			if (isUpdate)
				return true;

			CartItem cartItems = cartItemService.getByCartItem(cartItem);

			// logger.info("Email:{},ip:{},初始化购物项,从数据库中获取购物项信息:{}--{}--{}",user.getEmail(),user.getLastLoginFrom(),
			// cartItems.getProduct().getProductName(),cartItems.getPricePackage().getAmountStr(),cartItems.getPricePackage().getPrice());

			if (null != cartItems) {

				if (null != freeApi && freeApi == 1) {
					cartItem.setItemNum(1);
					throw new OrderException("此活动只能参加一次");
				} else {
					cartItem.setItemNum(itemNum + cartItems.getItemNum());
				}
				cartItem.setCartId(cartItems.getCartId());
				cartItem.setId(cartItems.getId());
				cartItem.setPricePackage(pricePackage);
				cartItem.setProduct(product);
				logger.info("Email:{},ip:{},数据库中找到购物项信息,初始化购物项为:{}--{}次--{}元--x{}个", user.getEmail(),
						user.getLastLoginFrom(), cartItem.getProduct().getProductName(),
						cartItem.getPricePackage().getAmountStr(), cartItem.getPricePackage().getTotalPrice(),
						cartItem.getItemNum());
				return true;

			} else {
				/* 此版本不做多项同时购买,添加购物车前先清空原购物车 */
				// cartItemService.deleteByCartId(cart.getId());

				// cartItem = new CartItem();
				cartItem.setCartId(cart.getId());
				cartItem.setPricePackage(pricePackage);
				cartItem.setProduct(product);
				cartItem.setItemNum(itemNum);

				logger.info("Email:{},ip:{},数据库中未找到购物项信息,初始化购物项为:{}--{}次--{}元--x{}个", user.getEmail(),
						user.getLastLoginFrom(), cartItem.getProduct().getProductName(),
						cartItem.getPricePackage().getAmountStr(), cartItem.getPricePackage().getTotalPrice(),
						cartItem.getItemNum());

				return false;
			}
		} catch (Exception e) {
			logger.error("Email:{},ip:{},初始化购物项出错:{}", user.getEmail(), user.getLastLoginFrom(), e);
			throw new OrderException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProductAttr> getAttrsByIds(String attrIds) {
		String[] arr = attrIds.split(",");
		Integer[] arrInt = new Integer[arr.length] ;
		for(int i=0;i<arr.length;i++){
			arrInt[i] = Integer.valueOf(arr[i]);
		}
		List<ProductAttr> productAttrList = productAttrService.getAttrsByIds(arrInt);
		return productAttrList;
	}

	@Override
	public void getIPDistrictDownloadInfo(List<CartItem> items) {

		for (CartItem item : items) {
			item.setDiscount(OrderUtil.doubleTrans(Double.valueOf(item.getDiscount())));
			if (item.getItemType().equals("IPDistrictDownload")) {
				String attrIds = item.getAttrIds();
				// 得到区县库下载规格
				List<ProductAttr> productAttrList = getAttrsByIds(attrIds);
				String pp = "";
				BigDecimal price = null;
				for (ProductAttr p : productAttrList) {
					pp = pp + p.getAttrValue() + ",";
					price = p.getPrice();
				}
				PricePackage pricePackages = new PricePackage();
				pricePackages.setAmountStr(pp.substring(0, pp.length() - 1));
				pricePackages.setTotalPrice(price.toString());
				item.setPricePackage(pricePackages);
			}
			
			if(item.getItemType().equals("IPDistrictAPI") && item.getOriginalPrice().intValue() == 0){
				PricePackage pricePackages = new PricePackage();
				pricePackages.setAmountStr("限时免费,5000次");
				pricePackages.setTotalPrice("0");
				item.setPricePackage(pricePackages);
				
			}
		}
		
	}
}
