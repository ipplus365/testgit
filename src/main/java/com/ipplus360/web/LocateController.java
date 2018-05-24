package com.ipplus360.web;

import static com.ipplus360.common.Constants.API_LOCATE;
import static com.ipplus360.common.Constants.WEB_LOCATE;
import static com.ipplus360.enums.StatusCode.INTERNAL_ERROR;
import static com.ipplus360.enums.StatusCode.INVALID_KEY;
import static com.ipplus360.enums.StatusCode.INVALID_REQUEST;
import static com.ipplus360.enums.StatusCode.INVALID_REQUEST_FREQUENCY;
import static com.ipplus360.enums.StatusCode.INVALID_RESULT;
import static com.ipplus360.enums.StatusCode.INVALID_SERVICE;
import static com.ipplus360.enums.StatusCode.OK;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.ipplus360.entity.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.IPResult;
import com.ipplus360.dto.LocateProps;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.GeoIP;
import com.ipplus360.entity.PositionFeedback;
import com.ipplus360.entity.WeatherMessage;
import com.ipplus360.exception.InvalidTokenException;
import com.ipplus360.exception.RequestFrequentlyException;
import com.ipplus360.service.IPService;
import com.ipplus360.service.PositionFeedbackService;
import com.ipplus360.service.TokenService;
import com.ipplus360.service.redis.RedisService;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.SessionUtil;
import com.ipplus360.util.WeatherUtil;

/**
 * IP定位controller Created by 辉太郎 on 2017/2/15.
 */
@Controller
@RequestMapping("/ip")
public class LocateController {

	private final static Logger LOGGER = LoggerFactory.getLogger(LocateController.class);

	private final static String WEBLOCATE_LIMIT_PREFIX = "webLocateLimited";

	private final IPService ipService;
	private final TokenService tokenService;
	private final RedisService redisService;
	private final PositionFeedbackService positionFeedbackService;

	@Autowired
	public LocateController(IPService ipService, TokenService tokenService, RedisService redisService,
			PositionFeedbackService positionFeedbackService) {
		this.ipService = ipService;
		this.tokenService = tokenService;
		this.redisService = redisService;
		this.positionFeedbackService = positionFeedbackService;
	}

	/**
	 * 定位页面
	 * @param id tokenId
	 * @param coord 坐标系
	 * @return 定位页面
	 */
	@RequestMapping(value = "/locatepage", method = RequestMethod.GET)
	public String locatePage(HttpServletRequest request,
							 @RequestParam(value = "tokenId", required = false) Long id,
							 @RequestParam(value = "ip", required = false) String ip,
							 @RequestParam(value = "coord", required = false ) String coord,
							 @RequestParam(value = "hasScene", required = false) Boolean hasScene) {

		String userIP = IPUtil.getUserIP(request);
		request.setAttribute("user_ip", userIP);
		request.setAttribute("ip", ip);
		request.setAttribute("tokenId", id);
		request.setAttribute("coord", coord);
		request.setAttribute("hasScene", hasScene);

		return "locate";
	}

	/**
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/getweather", method = RequestMethod.GET)
	public @ResponseBody Result<WeatherMessage> getWeather(String city) {
		WeatherMessage weatherMessage = WeatherUtil.getWeatherByCity(city);
		boolean success = false;
		String msg = "";
		if (weatherMessage.getStatus().equals("ok")) {
			success = true;
			msg = "请求成功";
		} else {
			success = false;
			msg = "请求失败";
		}
		return new Result<WeatherMessage>(success, weatherMessage, msg);
	}

	/**
	 * IP定位-API
	 * 
	 * @param ip 定位ip
	 * @param key 用户key
	 * @param coord 坐标系标识
	 * @param request request
	 * @return 定位dto
	 */
	@ResponseBody
	@RequestMapping(value = "/locate/api", method = RequestMethod.GET)
	public IPResult locate(@RequestParam("ip") String ip, @RequestParam("key") String key,
			@RequestParam(value = "coordsys", required = false) String coord, HttpServletRequest request) {

		String user_ip = IPUtil.getUserIP(request);
		try {
			if (!isValidIp(ip)) {
				return new IPResult(INVALID_REQUEST.getCode(), INVALID_REQUEST.getMsg());
			}

			String coordSuffix = parseCoord(coord);
			GeoIP geo_ip = ipService.locate(ip, key, user_ip, API_LOCATE, coordSuffix, false);

			if (geo_ip != null) {
				return new IPResult<>(OK.getCode(), true, ip, geo_ip, OK.getMsg(), coord);
			}

			return new IPResult<>(INVALID_RESULT.getCode(), true, ip, null, INVALID_RESULT.getMsg());

		} catch (RequestFrequentlyException e) {

			LOGGER.info("接口调用频繁 {} from {}", key, user_ip);
			return new IPResult<>(INVALID_REQUEST_FREQUENCY.getCode(), true, ip, null,
					INVALID_REQUEST_FREQUENCY.getMsg());

		} catch (InvalidTokenException e) {

			LOGGER.info("invalid key {} from {}", key, user_ip);
			return new IPResult<>(INVALID_KEY.getCode(), true, ip, null, INVALID_KEY.getMsg());

		} catch (Exception e) {

			LOGGER.error("key {} from {} 定位失败 {}", key, user_ip, e);
			return new IPResult<>(INTERNAL_ERROR.getCode(), true, ip, null, INTERNAL_ERROR.getMsg());
		}

	}

	/**
	 * web页面定位接口
	 * @param ip 定位ip
	 * @param tokenId 用户tokenId
	 * @param coord 坐标系
	 * @param request request
	 * @return 定位dto
	 */
	@ResponseBody
	@RequestMapping(value = "/locate/web", method = RequestMethod.GET)
	public IPResult webLocate(@RequestParam(value = "ip", required = false) String ip, @RequestParam(value = "tokenId", required = false) Long tokenId,
						  @RequestParam("coord") String coord, @RequestParam(value = "hasScene", required = false) Boolean hasScene,
						  HttpServletRequest request) {

		System.err.println("LocateController hasScene:" + hasScene);
		SessionUser user = SessionUtil.getSessionUser(request);
		String email = null;
		String user_ip = IPUtil.getUserIP(request);
		String header = request.getHeader("User-Agent");

		if (!isValidIp(ip)) {
			return new IPResult(INVALID_REQUEST.getCode(), INVALID_REQUEST.getMsg());
		}

		String token = null;
		try {
			if (null == tokenId) {
				boolean canLocate = tokenService.canLocate(user.getId());
				if (!canLocate) {
					return new IPResult(INVALID_SERVICE.getCode(), true, null, INVALID_SERVICE.getMsg());
				} else {
					// 没有携带key参数, 随机指定可用key
					token = tokenService.getValidKey(user.getId());
				}
			} else {
				UserToken userToken = tokenService.getById(tokenId);
				if (null != userToken) {
					token = userToken.getToken();
				}
			}

			// web页面定位1s一次
			if (isLimitedWeb(token)) {
				return new IPResult<>(INVALID_REQUEST_FREQUENCY.getCode(), true, ip, null,
						INVALID_REQUEST_FREQUENCY.getMsg());
			}

			String coordSuffix = parseCoord(coord);
			email = user.getEmail();
			if (null == hasScene) {
				hasScene = false;
			}
			System.err.println("hasScene:" + hasScene);
			GeoIP geo_ip = ipService.locate(ip, token, user_ip, WEB_LOCATE, coordSuffix, hasScene);
			if (geo_ip != null) {
				return new IPResult<>(OK.getCode(), true, ip, geo_ip, OK.getMsg(), coord);
			}

		} catch (InvalidTokenException e) {
			e.printStackTrace();
			LOGGER.info("invalid key {} from {} of {} using {}", token, user_ip, email, header);
			return new IPResult<>(INVALID_KEY.getCode(), true, ip, null, INVALID_KEY.getMsg());

		} catch (Exception e) {
			LOGGER.error("key {} from {} of {} using {} 定位失败 {}", token, user_ip, email, header, e);
			return new IPResult<>(INTERNAL_ERROR.getCode(), true, ip, null, INTERNAL_ERROR.getMsg());
		}

		return new IPResult<>(INVALID_RESULT.getCode(), true, ip, null, INVALID_RESULT.getMsg());

	}

	@ResponseBody
	@RequestMapping(value = "/district/api", method = RequestMethod.GET)
	public IPResult districtLocate(@RequestParam("key") String key, @RequestParam("ip") String ip,
			@RequestParam("coord") String coord, HttpServletRequest request) {
		String user_ip = IPUtil.getUserIP(request);

		try {
			if (!isValidIp(ip)) {
				return new IPResult(INVALID_REQUEST.getCode(), INVALID_REQUEST.getMsg());
			}
			String coordSuffix = parseCoord(coord);

			LocateProps props = new LocateProps(key, ip, user_ip, API_LOCATE, coordSuffix);

			GeoIP geo_ip = ipService.districtLocate(props);
			if (geo_ip != null) {
				return new IPResult<>(OK.getCode(), true, ip, geo_ip, OK.getMsg(), coord);
			}

		} catch (RequestFrequentlyException e) {

			LOGGER.info("接口调用频繁 {} from {}", key, user_ip);
			return new IPResult<>(INVALID_REQUEST_FREQUENCY.getCode(), true, ip, null,
					INVALID_REQUEST_FREQUENCY.getMsg());

		} catch (InvalidTokenException e) {
			e.printStackTrace();
			LOGGER.info("invalid key {} from {}", key, user_ip);
			return new IPResult<>(INVALID_KEY.getCode(), true, ip, null, INVALID_KEY.getMsg());

		} catch (Exception e) {
			LOGGER.error("key {} from {} 定位失败 {}", key, user_ip, e);
			return new IPResult<>(INTERNAL_ERROR.getCode(), true, ip, null, INTERNAL_ERROR.getMsg());
		}
		return new IPResult<>(INVALID_RESULT.getCode(), true, ip, null, INVALID_RESULT.getMsg());
	}

	@ResponseBody
	@RequestMapping(value = "/scene/api", method = RequestMethod.GET)
	public IPResult sceneLocate(@RequestParam("key") String key, @RequestParam("ip") String ip,
								HttpServletRequest request) {
		String user_ip = IPUtil.getUserIP(request);
		try {
			if (!isValidIp(ip)) {
				return new IPResult(INVALID_REQUEST.getCode(), INVALID_REQUEST.getMsg());
			}
			LocateProps props = new LocateProps(key, ip, user_ip, API_LOCATE, "osm");

			GeoIP geo_ip = ipService.getScene(props);
			if (geo_ip != null) {
				return new IPResult<>(OK.getCode(), true, ip, geo_ip, OK.getMsg());
			}
		} catch (Exception e) {
			LOGGER.error("key {} with {} from {} 场景查询失败 {}", key, ip, user_ip, e);
		}
		return new IPResult<>(INVALID_RESULT.getCode(), true, ip, null, INVALID_RESULT.getMsg());
	}

	/**
	 * web页面1s一次查询
	 * 
	 * @param token
	 *            token
	 * @return boolean
	 */
	private boolean isLimitedWeb(String token) {
		String key = WEBLOCATE_LIMIT_PREFIX + ":" + token;
		return redisService.locateLimited(key, 1);
	}

	/**
	 * 处理坐标系
	 * 
	 * @param coord 坐标系
	 * @return 定位库后缀
	 */
	private String parseCoord(String coord) {
		if ("BD09".equalsIgnoreCase(coord)) {
			coord = "BD09";
			return "bd";
		} else {
			coord = "WGS84";
			return "osm";
		}
	}

	private boolean isValidIp(String ip) {
		return IPUtil.isFormatValid(ip);
	}

	/**
	 * 定位反馈
	 * 
	 * @param ip
	 * @param lat
	 * @param lon
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/locate/positionFeedback", method = RequestMethod.GET)
	public IPResult positionFeedback(@RequestParam("ip") String ip, @RequestParam("lat") String lat,
			@RequestParam("lon") String lon, HttpServletRequest request) {
		// 用户IP
		String userIp = IPUtil.getUserIP(request);
		if (!isValidIp(ip)) {
			return new IPResult(INVALID_REQUEST.getCode(), INVALID_REQUEST.getMsg());
		}
		try {
			PositionFeedback positionFeedback = new PositionFeedback(IPUtil.ipToLong(ip), IPUtil.ipToLong(userIp), lat,
					lon, new Date());
			positionFeedbackService.insert(positionFeedback);
			return new IPResult(true,"谢谢你的反馈");
		} catch (Exception e) {
			return new IPResult(false,"反馈失败");
		}
	}
}