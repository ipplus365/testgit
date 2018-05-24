package com.ipplus360.web;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.dto.Result;
import com.ipplus360.entity.NotOrgEmail;
import com.ipplus360.entity.SalesActivity;
import com.ipplus360.entity.User;
import com.ipplus360.exception.*;
import com.ipplus360.service.SalesActivityService;
import com.ipplus360.service.UserService;
import com.ipplus360.service.captcha.CustomGenericManageableCaptchaService;
import com.ipplus360.util.CodeUtil;
import com.ipplus360.util.IPUtil;
import com.ipplus360.util.SMSUtil;
import com.ipplus360.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 辉太郎 on 2017/3/4.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CustomGenericManageableCaptchaService captchaService;

	@Autowired
	private SalesActivityService activityService;

	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/toReg", method = RequestMethod.GET)
	public String toReg() {
		return "reg";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Result loginForm(HttpServletRequest request, HttpServletResponse response) {

		String returnUrl = request.getParameter("returnurl");
		request.setAttribute("returnurl", returnUrl);

		// User user
		// =(User)request.getSession().getAttribute(Constants.CURRENT_USER);
		SessionUser user = (SessionUser) request.getSession().getAttribute(Constants.SESSION_USER);
		if (user != null) {
			return new Result<>(true, user.getEmail(), "已登录");
		} else {
			return new Result(false, "未登录");
		}

	}

	@RequestMapping(value = "/protocol", method = RequestMethod.GET)
	public String regProtocol() {
		return "regProtocol";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public Result login(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("captcha") String captcha, HttpServletRequest request, HttpSession session) {
		Result result = null;
		String returnUrl = request.getParameter("returnurl");
		try {

			if (StringUtils.isEmpty(captcha) || !captchaService.validateResponseForID(session.getId(), captcha)) {
				return new Result(false, "该验证码不正确或已过期");
			}

			SessionUser user = userService.login(request, email, password);

			SessionUtil.setSessionUser(request, user);
			/*
			 * if (StringUtils.isEmpty(returnUrl)) { returnUrl = "/"; }
			 */
			result = new Result<>(true, returnUrl, "登录成功");

		} catch (LoginException e) {
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("{} catpcha: {} {}", new Object[] { email, captcha, e.getMessage() });
			return new Result(false, "登录失败");
		}
		// captchaService.removeCaptcha(session.getId());
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/chpwd", method = RequestMethod.POST)
	public Result chpwd(@RequestParam("password") String password, @RequestParam("newPwd") String newPwd,
			@RequestParam("confirmPwd") String confirmPwd, HttpSession session) {

		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		User user = SessionUtil.convertToUser(sessionUser, userService);
		try {
			userService.changepwd(user, password, newPwd, confirmPwd);
			session.removeAttribute(Constants.SESSION_USER);
			return new Result<>(true, "/product/buy", "修改密码成功");
		} catch (ChangepwdException e) {
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "修改密码失败");
		}
	}

	/**
	 * 个人中心修改密码
	 * 
	 * @param password
	 * @param newPwd
	 * @param confirmPwd
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public Result changepwd(@RequestParam("password") String password, @RequestParam("newPwd") String newPwd,
			@RequestParam("confirmPwd") String confirmPwd, HttpServletRequest request, HttpSession session) {

		Result result = null;
		// User user = (User) session.getAttribute(Constants.CURRENT_USER);
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		User user = SessionUtil.convertToUser(sessionUser, userService);
		result = new Result<>(true, "redirect:/user/login", "修改密码成功");
		try {
			userService.changepwd(user, password, newPwd, confirmPwd);
		} catch (ChangepwdException e) {
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "修改密码失败");
		}
		session.removeAttribute(Constants.SESSION_USER);
		return result;
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Result logout(HttpSession session) {

		try {
			session.removeAttribute(Constants.SESSION_USER);
			return new Result(true, "退出成功");
		} catch (Exception e) {
			return new Result(false, "退出失败");
		}
	}

	/**
	 * 注册
	 * 
	 * @param email
	 * @param mobile
	 * @param password
	 * @param confirmPwd
	 * @param captcha
	 * @param regType
	 * @param moblieCode
	 * @param orgName
	 * @param orgPersonName
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public Result register(@RequestParam("email") String email, @RequestParam("mobile") String mobile,
			@RequestParam("password") String password, @RequestParam("confirm_pwd") String confirmPwd,
			@RequestParam("captcha") String captcha, @RequestParam("regType") String regType,
			@RequestParam("moblieCode") String moblieCode, @RequestParam("orgName") String orgName,
			@RequestParam("orgPersonName") String orgPersonName, HttpServletRequest request, HttpSession session) {
		try {
			if (StringUtils.isEmpty(captcha)) {
				return new Result(false, "验证码不能为空");
			}

			if (!captchaService.validateResponseForID(session.getId(), captcha)) {
				return new Result(false, "图片验证码不正确");
			}

			// 判断且是激活的手机号是否已经存在
			if (userService.getByMobile(mobile) != null) {
				return new Result(false, "手机号已经被注册");
			}

			User user = new User();
			user.setEmail(email);
			user.setMobile(mobile);
			user.setPassword(password);

			String regFrom = IPUtil.getUserIP(request);
			String header = request.getHeader("User-Agent");
			user.setRegFrom(regFrom);
			LOGGER.info("reg {} {} {}", new Object[] { user.getEmail(), regFrom, header });
			/*String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
					+ request.getContextPath() + "/";*/
			String path = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
			if (regType.equals("org")) {
				// 判断邮箱是否为企业邮箱
				String emailForOrg = email.substring(email.indexOf("@") + 1, email.length());
				NotOrgEmail notOrgEmail = userService.getNotOrgEmailByEmail(emailForOrg);
				if (notOrgEmail != null) {
					return new Result(false, "注册失败，该注册邮箱不是企业邮箱,请使用企业邮箱进行注册");
				}
				userService.register(user, confirmPwd, path, orgName, orgPersonName);
			} else {
				userService.register(user, confirmPwd, path);
			}
		} catch (RegisterException e) {
			e.printStackTrace();
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString());
			return new Result(false, "注册失败");
		}

		captchaService.removeCaptcha(session.getId());
		return new Result(true, "/user/login", "注册成功");
	}

	/**
	 * 判断是否是企业邮箱
	 * 
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isOrgEmail", method = RequestMethod.GET)
	public Result isOrgEmail(@RequestParam("email") String email) {
		try {
			// 判断邮箱是否为企业邮箱
			String emailForOrg = email.substring(email.indexOf("@") + 1, email.length());
			NotOrgEmail notOrgEmail = userService.getNotOrgEmailByEmail(emailForOrg);
			if (notOrgEmail != null) {
				return new Result(false, "该注册邮箱不是企业邮箱,请使用企业邮箱进行注册");
			}
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return new Result(false, "该注册邮箱不是企业邮箱,请使用企业邮箱进行注册");
		}
		return new Result(true, "");
	}

	/**
	 * 邮箱激活
	 * 
	 * @param task
	 * @param activeToken
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String active(@RequestParam String task, @RequestParam("activation") String activeToken, Model model) {
		try {
			userService.active(task, activeToken, System.currentTimeMillis());
			model.addAttribute("active", true);
			model.addAttribute("msg", "激活成功");
		} catch (ActiveFailException e) {
			model.addAttribute("active", false);
			model.addAttribute("msg", "用户已激活或此用户不存在");
		} catch (ActiveTimeOutException exception) {
			model.addAttribute("active", false);
			model.addAttribute("msg", "激活时间已过，请重新发送激活邮件");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			model.addAttribute("active", false);
			model.addAttribute("msg", "激活失败");
		}
		return "active";
	}

	/* 发送邮件验证码 */
	@ResponseBody
	@RequestMapping(value = "/sendCode", method = RequestMethod.GET)
	public Result sendEmailCheck(@RequestParam("email") String email, HttpServletRequest request, HttpSession session) {

		Result result = null;
		result = new Result(true, "发送邮件验证码成功");
		try {
			userService.sendEmailCheck(email);
		} catch (ResetPwdException e) {
			e.printStackTrace();
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "发送邮件验证码失败");
		}
		return result;
	}

	/* 老版本忘记密码重置密码邮件 */
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public @ResponseBody Result checkEmailCheck(@RequestParam("email") String email,
			@RequestParam("checkCode") int checkCode, @RequestParam("captcha") String captcha,
			HttpServletRequest request, HttpSession session) {
		Result result = null;
		result = new Result(true, "验证邮件验证码成功");
		try {
			if (StringUtils.isEmpty(captcha) || !captchaService.validateResponseForID(session.getId(), captcha)) {
				return new Result(false, "验证码不正确");
			}
			userService.checkEmailCheck(email, checkCode);
			/*String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
					+ request.getContextPath() + "/";*/
			String path = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
			userService.sendResetEmail(email, path);
		} catch (checkEmailCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (checkEmailTimeOutException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "验证邮件验证码失败");
		}
		return result;
	}

	/* 重置密码 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.GET)
	public @ResponseBody Result resetPwd(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "mobile", required = false) String mobile, @RequestParam("newPwd") String newPwd,
			@RequestParam("confirmPwd") String confirmPwd, HttpSession session) {
		Result result = null;
		result = new Result<>(true, "redirect:/user/toLogin", "重置密码成功");
		try {
			if (email != null) {
				String token = (String) session.getAttribute("resetToken");
				if (null == token) {
					throw new ResetPwdException("验证用户信息失败");
				}
				String task = (String) session.getAttribute("restTask");
				if (StringUtils.isEmpty(task) || !"resetPassword".equals(task)) {
					throw new ResetPwdException("验证用户信息失败");
				}

				userService.checkEmailCheck(email, token);
				userService.resetPwd(email, newPwd, confirmPwd);
			} else if (mobile != null) {
				User user = userService.getByMobile(mobile);
				if (user != null) {
					userService.resetPwdforMobile(mobile, newPwd, confirmPwd);
				} else {
					return new Result(false, "重置密码失败");
				}
			} else {
				return new Result(false, "重置密码失败");
			}

		} catch (ResetPwdException e) {
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (checkEmailCheckException e) {
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (checkEmailTimeOutException e) {
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "重置密码失败");
		}
		return result;
	}

	// 忘记密码手机号重置
	@RequestMapping(value = "/setNewPwd", method = RequestMethod.GET)
	public String setNewPwd(HttpSession session, Model model) {
		return "setNewPwd";
	}

	/**
	 * 通过邮箱重置密码链接
	 * 
	 * @param task
	 * @param email
	 * @param resetToken
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPwdTest(@RequestParam String task, @RequestParam("user") String email,
			@RequestParam("reset") String resetToken, HttpSession session, Model model) {
		try {
			userService.checkEmailCheck(email, resetToken);
			session.setAttribute("resetToken", resetToken);
			session.setAttribute("restTask", task);
			model.addAttribute("task", task);
			model.addAttribute("email", email);
			model.addAttribute("firstSuccess", false);
			model.addAttribute("secondSuccess", true);
			return "setNewPwd";
		} catch (ResetPwdException e) {
			model.addAttribute("errMsg", e.getMessage());
		} catch (checkEmailCheckException e) {
			model.addAttribute("errMsg", e.getMessage());
		} catch (checkEmailTimeOutException e) {
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return "forgetPwd";
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help() {
		return "old/help";
	}

	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public String forget() {
		return "forgetPwd";
	}

	// 忘记密码
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public @ResponseBody Result forgetPassword(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam("account") String account, @RequestParam("code") String code,
			@RequestParam("captcha") String captcha, @RequestParam("type") String type) {

		if (StringUtils.isEmpty(captcha) || !captchaService.validateResponseForID(session.getId(), captcha)) {
			return new Result(false, "验证码不正确");
		}

		try {
			// 找回密码的方式mobile、email

			if (type.equals("mobile")) {
				// 验证手机验证码是否正确
				userService.checkMobileCodeCheck(account, code);
				// 验证用户是否已有手机号
				User user = userService.getByMobile(account);
				if (user == null) {
					return new Result(false, "该手机号还未被注册，请通过邮箱找回");
				}
				return new Result(true, "验证手机验证码成功");
			}

			if (type.equals("email")) {
				// 验证邮箱验证码是否正确
				userService.checkEmailCheck(account, Integer.valueOf(code));
				/*String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
						+ request.getContextPath() + "/";*/
				String path = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
				userService.sendResetEmail(account, path);
				return new Result(true, "验证邮箱验证码成功");
			}

		} catch (checkMobileCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (checkEmailCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (checkEmailTimeOutException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "重置失败");
		}
		return new Result(true, "验证邮件验证码失败");

	}

	/**
	 * 忘记密码 和修改手机号时发送手机验证码
	 * 
	 * @param request
	 * @param response
	 * @param phoneNum
	 */
	@RequestMapping(value = "/sendSmsForPws", method = RequestMethod.GET)
	public @ResponseBody Result sendSmsForPws(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("phoneNum") String phoneNum) {

		String code = CodeUtil.getSix();
		LOGGER.info("mobile code:" + code);
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNum", phoneNum);
		map.put("code", code);
		map.put("expireCode", "30");
		try {
			boolean success = SMSUtil.sendSms(map);
			if (success) {
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss");
				Date createTime = new Date();
				Date expireTime = new Date(createTime.getTime() + 60 * 30 * 1000);
				String type = "0";
				userService.insertMobileCode(phoneNum, code, createTime, expireTime, type);
				return new Result(success, "发送手机验证码成功");
			} else { 
				return new Result(false, "发送手机验证码失败");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "发送手机验证码失败");
		}
	}

	/**
	 * (注册时) 发送手机验证码
	 * 
	 * @param request
	 * @param response
	 * @param phoneNum
	 */
	@RequestMapping(value = "/sendSms", method = RequestMethod.GET)
	public @ResponseBody Result sendSms(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("phoneNum") String phoneNum) {

		// 判断手机号是否已经存在
		if (userService.getByMobile(phoneNum) != null) {
			return new Result(false, "手机号已经被注册");
		}

		String code = CodeUtil.getSix();
		LOGGER.info("mobile code:" + code);
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNum", phoneNum);
		map.put("code", code);
		map.put("expireCode", "30");
		try {
			boolean success = SMSUtil.sendSms(map);
			if (success) {
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
				// HH:mm:ss");
				Date createTime = new Date();
				Date expireTime = new Date(createTime.getTime() + 60 * 30 * 1000);
				String type = "0";
				userService.insertMobileCode(phoneNum, code, createTime, expireTime, type);
				return new Result(success, "发送手机验证码成功");
			} else {
				return new Result(false, "发送手机验证码失败");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "发送手机验证码失败");
		}
	}

	/**
	 * 验证手机验证码
	 * 
	 * @param phoneNum
	 * @param code
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkMobileCode", method = RequestMethod.GET)
	public @ResponseBody Result checkMobileCode(@RequestParam("phoneNum") String phoneNum,
			@RequestParam("code") String code, HttpServletRequest request, HttpSession session) {
		Result result = null;
		result = new Result(true, "验证手机验证码成功");
		try {
			if (StringUtils.isEmpty(code)) {
				return new Result(false, "验证码不能为空");
			}
			userService.checkMobileCodeCheck(phoneNum, code);
		} catch (checkMobileCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, "手机验证码错误");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new Result(false, "手机验证码错误");
		}
		return result;
	}

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String toActivity(HttpServletRequest request) {
		LOGGER.info("scene activity visited from {}", IPUtil.getUserIP(request));
		return "/activity/scene";
	}

	@ResponseBody
	@RequestMapping(value = "/sceneActivity", method = RequestMethod.GET)
	public Result joinScene(HttpServletRequest request) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (null == sessionUser) {
				return new Result<>(false, "not_login", "登陆后才能参加");
			}
			String userIP = IPUtil.getUserIP(request);
			Long userId = sessionUser.getId();
			User user = userService.getById(userId);
			if (user.getIsOrg() == 0) {
				return new Result(false, "此活动仅限企业用户参加");
			}
			if (StringUtils.isEmpty(user.getMobile())) {
				return new Result(false, "请完善手机号后再参加");
			}
			List<SalesActivity> activities = activityService.getByUserId(userId);
			if (null != activities && activities.size() > 0) {
				return new Result<>(true, "您好，您已经提交过申请，埃文客服会在1~3个工作日内联系您，核实申请信息，请您耐心等待");
			}

			SalesActivity salesActivity = new SalesActivity();
			salesActivity.setUserId(userId);
			salesActivity.setCreateDate(new Date());
			salesActivity.setName("IPScene");
			salesActivity.setIp(userIP);

			activityService.add(salesActivity);
			return new Result<>(true, "您好，您已经申请成功，埃文客服会在1~3个工作日内联系您，核实申请信息，请您耐心等待");
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "参与失败，请联系客服");
		}
	}

	/**
	 * 完善个人信息或企业信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/completionInformation", method = RequestMethod.GET)
	public Result completionInformation(HttpServletRequest request, HttpSession session) {
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			if (null == sessionUser) {
				return new Result(false, "请先登录");
			}
			User user = userService.getById(sessionUser.getId());
			if (user.getIsOrg() == 1) {
				// 企业用户完善
				return new Result(true, "请您进入“个人中心—企业认证”后即可参与活动！");
			} else {
				// 个人用户完善
				return new Result(true, "请您进入“个人中心—安全设置—绑定手机”后即可参与活动！");
			}
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "内部错误");
		}
	}

	/**
	 * 用户个人手机号信息完善
	 * 
	 * @param request
	 * @param session
	 * @param phoneNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public Result personalInformationSubmit(HttpServletRequest request, HttpSession session,
			@RequestParam("phoneNum") String phoneNum, @RequestParam("code") String code,
			@RequestParam("captcha") String captcha) {
		try {
			if (StringUtils.isEmpty(captcha) || !captchaService.validateResponseForID(session.getId(), captcha)) {
				return new Result(false, "验证码不正确");
			}

			userService.checkMobileCodeCheck(phoneNum, code);
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			User user = userService.getById(sessionUser.getId());
			if (phoneNum == null) {
				return new Result(false, "用户手机号为空");
			}
			user.setMobile(phoneNum);
			// 添加用户手机号
			userService.updateMobile(user);

			return new Result(true, "绑定手机号成功");
		} catch (checkMobileCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, "手机验证码错误");
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "绑定手机号失败");
		}
	}

	/**
	 * 修改手机号
	 * 
	 * @param request
	 * @param session
	 * @param oldPhoneNum
	 * @param newPhoneNum
	 * @param code
	 * @param captcha
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMobile", method = RequestMethod.GET)
	public Result updateMobile(HttpServletRequest request, HttpSession session,
			@RequestParam("oldPhoneNum") String oldPhoneNum, @RequestParam("newPhoneNum") String newPhoneNum,
			@RequestParam("code") String code, @RequestParam("captcha") String captcha) {

		try {

			if (StringUtils.isEmpty(captcha) || !captchaService.validateResponseForID(session.getId(), captcha)) {
				return new Result(false, "验证码不正确");
			}

			if (StringUtils.isEmpty(oldPhoneNum) || StringUtils.isEmpty(newPhoneNum)) {
				return new Result(false, "手机号不能为空");
			}

			if (StringUtils.isEmpty(code)) {
				return new Result(false, "手机验证码不能为空");
			}
			User user = userService.getByMobile(oldPhoneNum);

			if (user == null) {
				return new Result(false, "原手机号未绑定此账号");
			}
			// 验证手机号验证码
			userService.checkMobileCodeCheck(newPhoneNum, code);

			user.setMobile(newPhoneNum);
			// 修改用户手机号
			userService.updateMobile(user);
			return new Result(true, "修改手机号码成功");

		} catch (checkMobileCheckException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			return new Result(false, "手机验证码错误");
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "修改手机号码失败");
		}
	}

	/**
	 * 判断用户是否已有手机号
	 */
	@ResponseBody
	@RequestMapping(value = "/isHavenMobile", method = RequestMethod.GET)
	public Result isHavenMobile(HttpServletRequest request, HttpSession session,
			@RequestParam("phoneNum") String phoneNum) {
		
		try {
			SessionUser sessionUser = SessionUtil.getSessionUser(request);
			String mobile = sessionUser.getMobile();
			if (mobile != null) {
				return new Result(true, "该用户已有手机号");
			} else {
				return new Result(true, "该用户手机号为空");
			}
		} catch (Exception e) {
			LOGGER.error("{}", e.getMessage(), e);
			return new Result(false, "查询用户手机号失败");
		}

	}

}