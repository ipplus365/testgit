package com.ipplus360.web.filter;

import com.ipplus360.exception.ServiceException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 辉太郎 on 2017/8/29.
 */
public abstract class SSOFilter implements Filter {

    protected String ssoServerUrl;

    // 排除拦截
    protected List<String> excludeList = null;

    private PathMatcher pathMatcher = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*if (StringUtils.isEmpty(ssoServerUrl = SSOConfig.getProperty("sso.server.url"))) {
            throw new IllegalArgumentException("ssoServerUrl不能为空");
        }*/
        ssoServerUrl = "http://192.168.1.100:9001";

        String excludes = filterConfig.getInitParameter("excludes");
        if (!StringUtils.isEmpty(excludes)) {
            excludeList = Arrays.asList(excludes.split(","));
            pathMatcher = new AntPathMatcher();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (matchExcludePath(httpRequest.getServletPath()))
            chain.doFilter(request, response);
        else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            try {
                doFilter(httpRequest, httpResponse, chain);
            }
            catch (ServiceException e) {
                /*httpResponse.setContentType("application/json;charset=UTF-8");
                httpResponse.setStatus(HttpStatus.OK.value());
                PrintWriter writer = httpResponse.getWriter();
                writer.flush();
                writer.close();*/
                e.printStackTrace();
            }
        }
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, ServiceException;

    private boolean matchExcludePath(String path) {
        if (excludeList != null) {
            for (String ignore : excludeList) {
                if (pathMatcher.match(ignore, path)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void destroy() {

    }
}
