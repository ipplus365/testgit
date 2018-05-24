package com.ipplus360.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ipplus360.domain.SessionUser;
import com.ipplus360.entity.User;
import com.ipplus360.exception.ServiceException;
import com.ipplus360.service.UserService;
import com.ipplus360.util.RestTemplateUtil;
import com.ipplus360.util.SessionUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 辉太郎 on 2017/8/29.
 */
public class SSOClientFilter extends SSOFilter {

    public final static String SSO_TOKEN_NAME = "__vt_param__";

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        userService = wac.getBean(UserService.class);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        System.err.println("1111111111111111");

        User user = userService.getById(12L);
        SessionUtil.setSessionUser(request, SessionUtil.convertToSessionUser(user));
        // 1. 取session中sessionUser的token
        String token = getLocalToken(request);

        // 2. token为空, 取request中的参数token进行验证
        if (StringUtils.isEmpty(token)) {
            if (getTokenParam(request) != null) {
                // 重定向到当前页面去掉url中的参数
                response.sendRedirect(request.getRequestURL().toString());
            } else {
                redirectLogin(request, response);
            }
        // 3. 对token进行验证
        } else if (isAuthed(request, token) != null) {
            chain.doFilter(request, response);
        } else {
            // 4. 认证失败, 跳转到登录页面
            redirectLogin(request, response);
        }
    }


    /**
     * 重定向到登录页面
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void redirectLogin(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        if (isAjaxRequest(request)) {
            throw new ServiceException("未登录或已超时");
        } else {
            SessionUtil.invalidate(request);
            String loginUrl = new StringBuilder().append(ssoServerUrl).append("/user/login?returnUrl=")
                    .append(request.getRequestURL()).toString();
            response.sendRedirect(loginUrl);
            return;
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header);
    }

    /**
     * 取出token进行认证
     *
     * @param request
     * @return
     */
    private String getTokenParam(HttpServletRequest request) {
        String token = request.getParameter(SSO_TOKEN_NAME);
        if (!StringUtils.isEmpty(token)) {
            SessionUser sessionUser = verify(request, ssoServerUrl, token);
            if (sessionUser != null) {
                setAuthToSession(request, sessionUser);
                return token;
            }
        }
        return null;
    }

    private SessionUser isAuthed(HttpServletRequest request, String token) {
        return verify(request, ssoServerUrl, token);
    }

    private SessionUser verify(HttpServletRequest request, String ssoServerUrl, String token) {
        String result = RestTemplateUtil.get(
                request, ssoServerUrl + "/sso/getAuthInfo?token=" + token, null);

        try {
            JSONObject obj = JSON.parseObject(result);
            boolean success = (boolean) obj.get("success");
            if (success) {
                SessionUser sessionUser = JSON.parseObject(obj.get("data").toString(), SessionUser.class);
                sessionUser.setSsoToken(token);
                setAuthToSession(request, sessionUser);
                return sessionUser;
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
        }
        return null;
    }

    /**
     * session里保存认证信息
     *
     * @param request
     * @param sessionUser
     */
    private void setAuthToSession(HttpServletRequest request, SessionUser sessionUser) {
        SessionUtil.setSessionUser(request, sessionUser);
    }

    /**
     * 获取session或者cookie中的token
     *
     * @param request
     * @return
     */
    private String getLocalToken(HttpServletRequest request) {
        SessionUser sessionUser = SessionUtil.getSessionUser(request);

        return sessionUser == null ? null : sessionUser.getSsoToken();
    }

}
