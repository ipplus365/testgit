package com.ipplus360.util;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.CartItem;
import com.ipplus360.entity.Discount;
import com.ipplus360.entity.PricePackage;
import com.ipplus360.exception.OrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderUtil {
	private static Logger logger = LoggerFactory.getLogger(OrderUtil.class);

	public static String getOrderId() {
		String head = "IP-";
		return head + getNowDate() + getUUIDHasCode();

	}

	public static String getNowDate() {

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		return formatter.format(currentTime);
	}

	public static String getUUIDHasCode() {

		Integer uuid = UUID.randomUUID().toString().hashCode();
		if (uuid < 0)
			uuid = -uuid;
		return uuid.toString();
	}

	public static String getUUID32() {

		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String doubleTrans(Double num) {
		DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
		return decimalFormat.format(num);
	}

	/**
	 * 计算购物车总价,从购物项列表中获取每一个购物项项价钱,进行累加后返回订单总价
	 * 
	 * @param cartItemList
	 */
	public static BigDecimal calculateCart(List<CartItem> cartItemList, SessionUser user) {

		try {
			if (null == cartItemList || cartItemList.size() == 0) {
				return null;
			}
			BigDecimal totalPrice = new BigDecimal(0);
			for (CartItem ci : cartItemList) {
				totalPrice = ci.getPrice().add(totalPrice);
				logger.info("Email:{},ip:{},购物车ID:{},购物项:{}-{}次,单价:{}{}, 数量为:{}, 购物项价格:{}元", user.getEmail(),
						user.getLastLoginFrom(), ci.getCartId(), ci.getProduct().getProductName(),
						ci.getPricePackage().getAmountStr(), ci.getPricePackage().getPrice(),
						ci.getPricePackage().getUnit(), ci.getItemNum(), ci.getPrice());
			}
			logger.info("Email:{},ip:{},购物车总价为:{}元", user.getEmail(), user.getLastLoginFrom(), totalPrice);
			return totalPrice;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OrderException("计算购物车总价出错", e);
		}

	}

	/**
	 * 根据精度级别ID,价格包ID,购买数量获取单价,并计算订单总价</br>
	 * 目前只提供大额流量包连续购买的计算方式</br>
	 * 先获取相关价格包的折扣信息,如果满足折扣要求则通过折扣表里面的及进行计算,如果不满足折扣要求则通过价格套餐里的信息进行计算</br>
	 * 原始价格计算公式为: 价格包单价(元/千次) * 价格包所含的流量数量(次) * 购物项数量(个) / 1000 (单位转换)</br>
	 * 折扣价格计算公式为: 折扣后单价(元/千次) * 价格包所含的流量数量(次) * 购物项数量(个) / 1000 (单位转换)
	 *
	 * 计算公式修改(2017-7-20 edit by wangguoyan) 原始价格计算公式为: 价格包总价(元) * 购物项数量(个)</br>
	 * 折扣价格计算公式为: 折扣后价格包总价(元) * 购物项数量(个)
	 */
	public static void calculateItem(Discount discount, CartItem cartItem, SessionUser user) {
		try {
			PricePackage pricePackage = cartItem.getPricePackage();
			BigDecimal prices;// 实际价格
			BigDecimal originalPrices;// 原始价格
			BigDecimal num = new BigDecimal(cartItem.getItemNum());// 购物项数量（个）
			BigDecimal amounts = new BigDecimal(pricePackage.getAmount());// 价格包所含的流量数量（此）

			// originalPrices = new BigDecimal(pricePackage.getPrice(),
			// MathContext.DECIMAL32).multiply(amounts,
			// MathContext.DECIMAL32).multiply(num,
			// MathContext.DECIMAL32).divide(new BigDecimal(1000),
			// MathContext.DECIMAL32);
			originalPrices = new BigDecimal(pricePackage.getTotalPrice(), MathContext.DECIMAL32).multiply(num,
					MathContext.DECIMAL32);
			if (null != discount) {

				// prices = new BigDecimal(discount.getPrice(),
				// MathContext.DECIMAL32).multiply(num,
				// MathContext.DECIMAL32).multiply(amounts,
				// MathContext.DECIMAL32).divide(new BigDecimal(1000),
				// MathContext.DECIMAL32);
				prices = new BigDecimal(discount.getTotalPrice(), MathContext.DECIMAL32).multiply(num,
						MathContext.DECIMAL32);

				cartItem.setDiscount(discount.getDiscount());
				/* 有折扣时,分别添加原始价格和折扣价格 */
				cartItem.setOriginalPrice(originalPrices);
				cartItem.setPrice(prices);
				logger.info("Email:{},ip:{},满足优惠要求(折扣包ID):{},购买数量为:{}, 折扣为:{}折, 原价格为:{}元, 折扣后价格为:{}元", user.getEmail(),
						user.getLastLoginFrom(), discount.getId(), num, discount.getDiscount(), originalPrices, prices);

			} else {

				cartItem.setDiscount(pricePackage.getDiscount());
				/* 无折扣时,实际价格等于原始价格 */
				cartItem.setOriginalPrice(originalPrices);
				cartItem.setPrice(originalPrices);

				logger.info("Email:{},ip:{},不满足优惠要求,购买数量为:{}, 使用原始价格包(ID):{}进行计算, 折扣为:{},原价格为:{} 元", user.getEmail(),
						user.getLastLoginFrom(), num, pricePackage.getId(), pricePackage.getDiscount(), originalPrices);

			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Email:{},ip:{},计算价格出错:{}", user.getEmail(), user.getLastLoginFrom(), e);
			throw new OrderException("计算价格出错");
		}
	}

	/**
	 * 判断是否超过购买限制</br>
	 * 判断1:价格套餐包是否有购买限制,`counts`大于零时表示一次性购买次数量不能大于`counts`</br>
	 * 判断2:如果价格套餐包限制数`counts`大于零,且价格套餐包属于测试包(type值为 1
	 * ),则价格套餐包的数量限制为当前购买数量(`itemNum`)加上历史购买数量(`user.testCount`)不能大于`counts`值</br>
	 * 返回true时说明超过购买限制,返回false说明未超过购买限制
	 */
	public static boolean isLimit(PricePackage pricePackage, CartItem cartItem, SessionUser user,
			Map<String, Object> errmsg) {

		String message;
		Integer limitCounts = pricePackage.getCounts();// 购买次数限制
		/* 判断商品购买次数限制,大于0为有购买次数限制,其余则无购买次数限制,测试包目前一定有购买限制 */

		System.err.println("pricePackage:" + pricePackage);
		if (0 < limitCounts) {
			/* 判断价格套餐包类型,-1为测试包,其余为正式商品 */
			if (-1 == pricePackage.getType()) {

				/* 判断测试包是否超过购买限制 */
				int testCount = user.getTestCount();// 已购买数量
				int itemNum = cartItem.getItemNum();// 当前购物车数量
				int counts = itemNum + testCount;

				System.err.println("counts:" + counts);
				System.err.println("itemNum:" + itemNum);
				System.err.println("limitCounts:" + limitCounts);
				if (limitCounts < counts) {

					if (testCount < limitCounts) {
						Integer count = limitCounts - testCount;
						errmsg.put("count", count);
						errmsg.put("msg", "每个用户只享有3次购买优惠");
						return true;
					}

					message = "购买次数已达到三次";
					logger.error("Email:{},ip:{}," + message + ",新增购买次数:{}次,已购买了:{}次,累计购买次数:{}次,购买限制次数:{}次",
							user.getEmail(), user.getLastLoginFrom(), itemNum, testCount, counts, limitCounts);
					errmsg.put("msg", "您的" + message);
					return true;
				}

			}

			/* 判断除测试包外其他有限制的商品购买次数限制 */
			if (limitCounts < cartItem.getItemNum()) {

				message = "添加失败,超过该套餐一次性购买限制";
				logger.error("Email:{},ip:{}," + message + ",新增购买次数:{}次,购买限制次数:{}次", user.getEmail(),
						user.getLastLoginFrom(), cartItem.getItemNum(), limitCounts);
				errmsg.put("msg", message);
				return true;
			}
		}
		errmsg.put("msg", null);
		return false;
	}

	/**
	 * 离线场景库下载价格计算
	 * 
	 * 离线库连续更新购买享受优惠折扣，一次性购买3年可享9.0折优惠。 其余就是按照原价格进行结算
	 * 
	 * @param cartItem
	 * @param user
	 */
	public static void calculateSceneLibraryPrice(CartItem cartItem, SessionUser user, Integer freeApi) {
		try {
			BigDecimal prices;// 实际价格
			BigDecimal originalPrices;// 原始价格
			if (freeApi == null) {

				PricePackage pricePackage = cartItem.getPricePackage();
				BigDecimal number = new BigDecimal(cartItem.getItemNum());// 购买的数量

				originalPrices = new BigDecimal(pricePackage.getTotalPrice(), MathContext.DECIMAL32).multiply(number,
						MathContext.DECIMAL32);
				if (number.intValue() >= 3) {
					// 折扣后的实际价格
					prices = originalPrices.multiply(new BigDecimal(0.9, MathContext.DECIMAL32), MathContext.DECIMAL32);
					// cartItem.setDiscount(discount.getDiscount());
					cartItem.setOriginalPrice(originalPrices);
					cartItem.setDiscount("90");
					cartItem.setPrice(prices);
				} else {
					cartItem.setOriginalPrice(originalPrices);
					cartItem.setDiscount("100");
					cartItem.setPrice(originalPrices);
				}
			} else {
				// 免费区县库活动
				cartItem.setOriginalPrice(new BigDecimal(0.0));
				cartItem.setPrice(new BigDecimal(0.0));
				cartItem.setDiscount("100");
			}

		} catch (Exception e) {
			logger.error("Email:{},ip:{},计算价格出错:{}", user.getEmail(), user.getLastLoginFrom(), e);
			throw new OrderException("计算价格出错");
		}
	}
}