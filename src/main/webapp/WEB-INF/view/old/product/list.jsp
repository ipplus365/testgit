<!DOCTYPE html>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
	    <meta charset="utf-8"/>
	    <meta http-equiv="Pragma" content="no-cache">
	    <meta http-equiv="Cache-Control" content="no-cache">
	    <meta http-equiv="Expires" content="0">
	    <title>埃文商城-IP问问-商品列表</title>
		<link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
	    <link rel="stylesheet" type="text/css" href="/statics/css/footer.css"/>
	    <link rel="shortcut icon" href="statics/images/favicon.ico"/>
        <script src="/statics/js/dlist.js"></script>
        <!-- 百度统计 -->
        <script>
            var _hmt = _hmt || [];
            (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?7f306f80db473a1d58a064d5a0f236ca";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
    </head>
    <body class="bg-white">
<!--顶部用户信息栏-->
<c:import url="${pageContext.request.contextPath}/WEB-INF/view/layout/header.jsp"/>
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
                        <li  datatype="3" id="query_all">全部</li>
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
				    <a href="detail.html" target="_blank">
				        <img src="/statics/images/IP反作弊4.jpg" width="135" height="61">
				    </a>
				    <h3>
				        <a href="detail.html" target="_blank">
				    <a href="../detail.html" target="_blank">
				        <img src="/statics/images/IP反作弊4.jpg" width="135" height="61">
				    </a>
				    <h3>
				        <a href="../detail.html" target="_blank">
					        ${product.productName}
				        </a>
				    </h3>
				    <p class="price-show">
				        ${product.price}元/千次
				    </p>
				    <div class="icons-div_v1 icons-div cl">
				        <p class="text">浏览(${product.pageviews})&nbsp;&nbsp;&nbsp;&nbsp;评分(${product.evaluate})</p>
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

