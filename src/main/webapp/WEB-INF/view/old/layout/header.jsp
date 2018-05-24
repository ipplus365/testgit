<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<!--顶部用户信息栏-->
<div class="gnb-w">
    <div class="gnb-box">
        <ul class="gnb" id="header_admin" tag="moderline" flag="101">
            <li class="gnb-log">
                <i class="gnb-icon"></i>
                <c:choose>
                    <c:when test="${session_user!=null}">欢迎您<a href="<c:url value="/user/info"/>"/>${session_user.email}</a></c:when>
                    <c:otherwise><a href="<c:out value="/user/login"/>">登录&nbsp;|</a></c:otherwise>
                </c:choose>
            </li>
            <li class="gnb-reg">
            	<i class="gnb-icon2"></i>
                <c:choose>
                    <c:when test="${session_user==null}"><a href="<c:out value="/user/reg"/>">注册</a>|</c:when>
                    <c:otherwise><a href="<c:out value="/user/logout"/>">退出</a>|</c:otherwise>
                </c:choose>
            </li>
            <li class="gnb-con">
                <a href="/user/help" target="_blank">使用帮助</a>|
            </li>
            <li class="gnb-con">
                <a href="http://www.ipplus360.com/about/us/" target="_blank"><i class="gnb-icon3"></i> 联系我们</a>
            </li>

        </ul>
    </div>
</div>
<!--logo-->
<div class="header-w" style="display: inline-blcok;">
    <div class="header-new  inner-header">
        <div class="logo-new"><a href="/">
            <img src="/statics/images/logo-big.jpg" alt="埃文科技" width="234px" height="65px"></a>
        </div>
    </div>
</div>