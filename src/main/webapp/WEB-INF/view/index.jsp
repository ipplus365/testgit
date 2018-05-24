<!DOCTYPE html>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <meta name="keywords" content="IP定位_埃文科技_IP实时定位_ip区县库_ip免费库_全球ip库_高精度ip定位_埃文商城">
    <meta name="description" content="埃文商城(mall.ipplus360.com) -埃文科技(www.ipplus360.com)">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <title>埃文商城-IP问问-商品列表</title>
    <link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/css/footer.css"/>
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <script src="/statics/js/dlist.js"></script>
</head>
<body class="bg-white">
<!--顶部用户信息栏-->
<c:import url="${pageContext.request.contextPath}/WEB-INF/view/layout/header.jsp"/>
<div class="lnb-w">
    <div class="lnb-box">
        <div class="hot-left">
            <h2 class="hot-left-h2">
                <i class="hot-icon"></i>
                数据分类
            </h2>
        </div>
        <%--<ul class="lnb-lst">
            <li>
                <a href="javascript:void(0)">数据市场</a>
            </li>
            <li>
                <a href="javascript:void(0)">IP信息</a>
            </li>
            <li>
                <a href="javascript:void(0)"></a>
            </li>
            <li>
                <a href="javascript:void(0)"></a>
            </li>
        </ul>--%>
    </div>
</div>
<!-- 商品详细列表 -->
<section class="wrap">
    <div class="position"><i class="new-pos"></i> 当前位置：<a href="">数据市场</a> <span class="song">&gt;</span> 全部数据</div>
    <div class="sort-div">
        <ul class="list-style">
            <li>
                <div class="txt">排序方式：</div>
                <div class="choice">
                    <div class="value" id="conditionSort">综合排序</div>
                    <ul class="value-list" id="sort-style">
                        <li sort="0" id="query_sort_type_multiple">综合排序</li>
                        <li sort="1" id="query_sort_type_new">最新</li>
                    </ul>
                </div>
            </li>
            <li>
                <div class="txt">付费类型：</div>
                <div class="choice">
                    <div class="value" id="conditionCharge">全部</div>
                    <ul class="value-list" id="list-style">
                        <li  isfree="2" id="query_all">全部</li>
                        <li  isfree="0" id="query_charge_type_free">免费</li>
                        <li  isfree="1" id="query_charge_type_nofree">收费</li>
                    </ul>
                </div>
            </li>
            <li>
                <div class="txt">数据类型：</div>
                <div class="choice">
                    <div class="value" id="conditionType">全部</div>
                    <ul class="value-list" id="type-style">
                        <li  datatype="3" id="query_all1">全部</li>
                        <li  datatype="0" id="query_data_api">API</li>
                        <li  datatype="1" id="query_data_packet">数据包</li>
                        <li  datatype="2" id="query_data_report">数据报告</li>
                    </ul>
                </div>
            </li>
        </ul>
        <p class="sort-num"></p>
    </div>
    <div id="con-listTab">
        <ul class="list-new_v1 cl home-list">
            <c:forEach items="${productList}" var="product">
                <li class="boder_v1">
                    <a href="/product/detail?productId=${product.id}" target="_blank">
                        <img src="${product.iconUrl}" width="135" height="61">
                    </a>
                    <h3>
                        <a href="/product/detail?productId=${product.id}" target="_blank">
                                ${product.productName}
                        </a>
                    </h3>
                    <p class="price-show">
                            ${product.price}
                    </p>
                    <div class="icons-div_v1 icons-div cl">
                        <p class="text">浏览(${product.pageviews})&nbsp;&nbsp;&nbsp;&nbsp;评论(${product.evaluate})</p>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <span class="async-page"></span>
    </div>
</section>
<!--底部footer-->
<c:import url="${pageContext.request.contextPath}/WEB-INF/view/layout/footer.jsp"/>

<!--返回顶部-->
<div class="contatct-w">
    <div class="gotop" onclick="query.scrollUp()">
        <i class="gotop-icon"></i>
        <span class="gotop-txt">返回顶部</span>
    </div>
</div>
</body>
</html>

