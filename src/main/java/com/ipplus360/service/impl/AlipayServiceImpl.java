package com.ipplus360.service.impl;

import com.ipplus360.entity.*;
import com.ipplus360.exception.AliPayException;
import com.ipplus360.exception.OrderException;
import com.ipplus360.pay.config.AlipayConfig;
import com.ipplus360.pay.util.AlipaySubmit;
import com.ipplus360.service.*;
import com.ipplus360.service.mail.MailService;
import com.ipplus360.util.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service
public class AlipayServiceImpl implements AlipayService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private MailService mailService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	@Autowired private FileOrderService fileOrderService;
	@Autowired private TokenService tokenService;
	@Autowired private GeoIPVersionService versionService;
	
	@Override
	public String payOnline(Map payParam) {
		 //商户订单号，商户网站订单系统中唯一订单号，必填
        
		//把请求参数打包成数组
		payParam.put("service", AlipayConfig.service);
		payParam.put("partner", AlipayConfig.partner);
		payParam.put("seller_id", AlipayConfig.seller_id);
		payParam.put("_input_charset", AlipayConfig.input_charset);
		payParam.put("payment_type", AlipayConfig.payment_type);
		payParam.put("notify_url", AlipayConfig.notify_url);
		payParam.put("return_url", AlipayConfig.return_url);
		payParam.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		payParam.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
        //如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String payHtmlText = AlipaySubmit.buildRequest(payParam,"post","确认");
		return payHtmlText;
	}

	@Override
	public void sendOrderEmail(Map payResult) {
		LOGGER.info("out_trade_no:" + (String) payResult.get("out_trade_no"));
		Order orderby=orderService.getOrderByOrderSerial((String) payResult.get("out_trade_no"));
		LOGGER.info("orderby:" + orderby);
		LOGGER.info("orderby.getId:" + orderby.getId());
		LOGGER.info("orderby.getUserId:" + orderby.getUserId());
		Order order=orderService.getById(orderby.getId());
		if (null == order) {
			order = orderService.getById1(orderby.getId());
		}
		LOGGER.info("order:" + order);
		User user;
		if (order != null) {
			user = userService.getById(order.getUserId());

		} else {
			user = userService.getById(orderby.getUserId());
		}
		LOGGER.info("order getUserId:" + orderby.getUserId());

		String email = user.getEmail();
		if (StringUtils.isEmpty(email)) {
            throw new AliPayException("用户邮箱不能为空");
        }
        if (!email.matches(User.EMAIL_PATTERN)) {
            throw new AliPayException("用户邮箱格式不正确");
        }
        
        try {
        	
        	//发送邮箱验证码
        	mailService.sendOrderEmail(email, "埃文商城发货通知", order , user , payResult );
            
        } catch (MailException e) {
            LOGGER.error("订单邮件发送失败", (String) payResult.get("out_trade_no"));
            throw new AliPayException("订单邮件发送失败");
        } catch (Exception e) {
            LOGGER.error("订单邮件发送失败", (String) payResult.get("out_trade_no"));
            throw new AliPayException("购买成功订单信息发送失败");
        }
		
	}

	@Override
	public void processDistrictDownload(OrderItem orderItem, Long userId, String version, boolean free) throws OrderException {

		version = version.trim();
		String attrs = orderItem.getAttrs();
		LOGGER.info("processDistrictDownload 22222222222222222222222");
		String attrIds = orderItem.getAttrIds();
		LOGGER.info("processDistrictDownload version:" + version);

		FileOrder fileOrder = new FileOrder();
		if (attrs != null) {
			// 1. 取出用户已购数据
			FileOrder userFileOrder = fileOrderService.getByAttrIdsAndVersion(userId, attrIds, version);
			if (null != userFileOrder) {
				throw new OrderException("您已购买过这个规格的区县库");
			}
			fileOrder.setAttrs(attrs);
			fileOrder.setAttrIds(attrIds);
		}

		fileOrder.setCreateTime(new Date());
		fileOrder.setOrderId(orderItem.getOrderId());
		fileOrder.setUserId(userId);
		fileOrder.setDownloadCounts(0);
		fileOrder.setExpireTime(null);
		fileOrder.setFree(free);
		fileOrder.setVersion(version);
		LOGGER.info("processDistrictDownload fileOrder:" + fileOrder);
		fileOrderService.processAdd(fileOrder);
	}

	@Override
	public String processDistrictAPI(OrderItem orderItem, Long userId) {
		String deliveryToken = null;

		PricePackage districtPackage = orderItem.getPricePackage();
		if (districtPackage.getType() == 1) {
			Long dailyLimit = districtPackage.getAmount();
			LOGGER.info("no free dailyLimit:" + dailyLimit);
			//	购买年限
			int yearCount = orderItem.getItemNum();
			deliveryToken = addDistrictToken(userId, dailyLimit, yearCount);
			/*UserToken userToken = tokenService.getDistrictAPIByUser(userId, 5L);
			if (null == userToken) {
				LOGGER.info("null token {}" + dailyLimit);
				deliveryToken = addDistrictToken(userId, dailyLimit);
			} else {
				LOGGER.info("not null token {}" + dailyLimit);
				deliveryToken = updateDistrictToken(userToken, dailyLimit);
			}*/
		}

		return deliveryToken;
	}

	@Override
	public String processFreeDistrictAPI(OrderItem orderItem, Long userId) {
		return addFreeDistrictToken(userId);
	}

	@Override
	public String processSceneAPI(OrderItem orderItem, Long userId) {

		String deliveryToken = null;
		PricePackage scenePackage = orderItem.getPricePackage();

		LOGGER.info("scenePackage:" + scenePackage);
		if (scenePackage.getType() == 1) {
			Long packageAmount = scenePackage.getAmount();
			LOGGER.info("packageAmount:" + packageAmount);
			int itemNum = orderItem.getItemNum();
			Long count = packageAmount * itemNum;
			UserToken userToken = tokenService.getByUserAndProduct(userId, 3L);
			if (null == userToken) {
				LOGGER.info("1111111111111111111111111111111111");
				deliveryToken = addSceneToken(userId, count);
			} else {
				LOGGER.info("22222222222222222222222222222222222");
				deliveryToken = updateSceneToken(userToken, count);
			}
		}

		return deliveryToken;
	}

	/**
	 * 更新区县库token
	 * @param userToken
	 * @param dailyLimit
	 * @return
	 */
	private String updateDistrictToken(UserToken userToken, Long dailyLimit) {
		LOGGER.info("updateDistrictToken dailyLimit: " + dailyLimit.intValue());
		userToken.setDailyLimit(dailyLimit.intValue());
		tokenService.update(userToken);
		return userToken.getToken();
	}

	/**
	 * 添加区县库token
	 * @param userId
	 * @param dailyLimit
	 * @param yearCount
	 * @return
	 */
	private String addDistrictToken(Long userId, Long dailyLimit, int yearCount) {
		String token = RandomStringUtils.randomAlphanumeric(64);
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(userId);

		Date now = new Date();
		userToken.setCreatedDate(now);
		userToken.setEffectiveDate(now);
		userToken.setExpireDate(DateUtil.localDateTime2Date(DateUtil.getNextXYear(now, yearCount)));

		LOGGER.info("addDistrictToken dailyLimit:" + dailyLimit.intValue());
		userToken.setDailyLimit(dailyLimit.intValue());
		userToken.setProductId(5L);

		userToken.setTest(false);
		userToken.setTestCount(0L);
		userToken.setCounts(0L);
		userToken.setAvailable(true);
		userToken.setNotified(false);
		tokenService.add(userToken);

		return token;
	}

	/**
	 * 添加免费区县TOKEN
	 * @param userId
	 * @return
	 */
	private String addFreeDistrictToken(Long userId) {
		String token = RandomStringUtils.randomAlphanumeric(64);
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(userId);

		Date now = new Date();
		userToken.setCreatedDate(now);
		userToken.setEffectiveDate(now);
		userToken.setExpireDate(DateUtil.localDateTime2Date(DateUtil.getNextYearTime(now)));
		userToken.setDailyLimit(1000);
		userToken.setProductId(5L);

		userToken.setTest(true);
		userToken.setTestCount(0L);
		userToken.setCounts(5000L);
		LOGGER.info("free userToken:" + userToken);
		userToken.setAvailable(true);
		userToken.setNotified(false);
		tokenService.add(userToken);

		User user = userService.getById(userId);
		user.setHasFreeDistrictApi(true);
		userService.updateFreeDistrictApi(userId);

		return token;
	}

	/**
	 * 更新场景token
	 * @param userToken
	 * @param packageAmount
	 */
	private String updateSceneToken(UserToken userToken, Long packageAmount) {
		Long counts = userToken.getSceneCounts();
		LOGGER.info("counts:" + counts);
		userToken.setSceneCounts(counts + packageAmount);

		LOGGER.info("userToken.getSceneCounts:" + userToken.getSceneCounts());
		tokenService.update(userToken);
		return userToken.getToken();
	}

	/**
	 * 添加場景token，用户没有购买过场景
	 * @param userId
	 * @param packageAmount
	 */
	private String addSceneToken(Long userId, Long packageAmount) {
		String token = RandomStringUtils.randomAlphanumeric(64);
		UserToken userToken = new UserToken();
		userToken.setToken(token);
		userToken.setUserId(userId);
		userToken.setCreatedDate(new Date());
		userToken.setEffectiveDate(new Date());
		userToken.setSceneCounts(packageAmount);
		userToken.setCounts(0L);
		userToken.setDailyLimit(0);
		userToken.setProductId(3L);

		userToken.setTest(false);
		userToken.setTestCount(0L);
		userToken.setAvailable(true);
		userToken.setNotified(false);
		tokenService.add(userToken);

		return token;
	}
}
