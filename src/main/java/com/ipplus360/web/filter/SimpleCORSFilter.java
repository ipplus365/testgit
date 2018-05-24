package com.ipplus360.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipplus360.common.Constants;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.User;
import com.ipplus360.service.UserService;
import com.ipplus360.util.SessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ipplus360.entity.User;
import com.ipplus360.util.SessionUtil;

/**
 * Created by 辉太郎 on 2017/3/6.
 */
@Component
public class SimpleCORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setContentType("application/json;charset=utf-8");
		response.setDateHeader("Expires",0);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}