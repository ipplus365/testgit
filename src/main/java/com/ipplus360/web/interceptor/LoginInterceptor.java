package com.ipplus360.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ipplus360.dto.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.User;
import com.ipplus360.util.SessionUtil;

import java.io.PrintWriter;

/**
 * Created by 辉太郎 on 2017/4/18.
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String returnUrl = request.getRequestURI();
		//System.out.println(request.getRequestURL().toString());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		SessionUser user = SessionUtil.getSessionUser(request);
		PrintWriter out = null;
		//User user = (User) request.getSession().getAttribute("user");
		if (user == null) {

			/*if ("/ip/download".equals(returnUrl)) {
				returnUrl = "/product/detail?productId=5";
			}
			if ("/cart/add".equals(returnUrl) || returnUrl.startsWith("/error")) {
				returnUrl = "";
			}
			response.sendRedirect("/user/login" + "?returnurl=" + returnUrl);*/
			out = response.getWriter();
			Result res = new Result();
			res.setSuccess(false);
			res.setData("user_not_login");
			res.setMsg("您还没有登录");
			out.write(JSON.toJSONString(res));
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
