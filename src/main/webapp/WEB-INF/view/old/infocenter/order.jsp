<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	response.setHeader("Expires", "0");
	response.setHeader("Pragma","no-cache"); 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link rel="stylesheet" href="/statics/css/reset.css">
    <link rel="stylesheet" href="/statics/css/common.css">
    <link rel="stylesheet" type="text/css" href="/statics/css/data.css"/>
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    <style>
        .wrap{
            width: 1805px;
        }
        .title h2{
            padding-left: 250px;
        }
    </style>
</head>
<body class="bg-white">
    <!--顶部用户信息栏-->
    <c:import url="../layout/header.jsp"/>
    <!--content-->
    <div class="user-content">
        <div class="wrap cl">
            <div class="user-content-l">
                <h3 class="title-v1">用户中心</h3>
                <ul>
                    <li><a href="/person/user" class="icon-info"><i class="sp-icon"></i>基本信息</a></li>
                    <li class="current"><a href="" class="icon-order"><i class="sp-icon"></i>我的订单</a></li>
                    <li><a href="/person/comment"><i class="inav2"></i>我的评论</a></li>
                    <li><a href="/person/company" class="icon-company"><i class="sp-icon"></i>企业认证</a></li>
                    <li><a href="/person/changPwd" class="icon-info"><i class="sp-icon"></i>修改密码</a></li>
                </ul>
            </div>
            <div id="detail">
                
                <section class="content" style="display: block;">
                    <div class="title">
                        <h2>我的订单</h2>
                    </div>
                    <table class="tbl">
                        <colgroup>
                            <col width="240">
                            <col width="240">
                            <col width="240">
                            <col width="150">
                            <col width="150">
                            <col width="150">
                            <col width="150">
                            <col width="150">
                            <col width="150">
                            <%--  <col width="110"> --%>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>订单号</th>
                            <th>下单时间</th>
                            <th>付款时间</th>
                           <!--  <th>价格</th> -->
                            <th>数量</th>
                            <th>总额</th>
                            <th>付款方式</th>
                            <th>订单状态</th>
                            <th>操作</th>
                           <!-- <th>评论</th> -->
                            <th>是否需要发票</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderList }" var="order">
	                        	<%-- <c:forEach items="${order.orderItems }" var="item"> --%>
			                        <tr>
			                            <td>
			                           		<a href="/order/info?orderId=${order.orderSerial}" target="_blank" class="org">${order.orderSerial }</a>
			                            </td>
			                            <td>
			                            	<fmt:formatDate value="${order.dateCreated }" pattern="yyyy-MM-dd HH:mm:ss"/>
			                            </td>
			                            
			                            <c:choose>
			                            	<c:when test="${order.status == 2}">
			                            		 <td><fmt:formatDate value="${order.dateUpdated }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			                            	</c:when>
			                            	<c:when test="${order.status == 4}">
			                            		 <td><fmt:formatDate value="${order.dateUpdated }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<td></td>
			                            	</c:otherwise>
			                            </c:choose>
			                        <!--     <td>
			                            	￥2.00
			                            </td> -->
			                            <td>×${fn:length(order.orderItems)}</td>
			                            <td>${order.price }元</td>
                                        <c:choose>
                                            <c:when test="${order.paymentMethod == 0}">
                                                <td>在线支付</td>
                                            </c:when>
                                            <c:when test="${order.paymentMethod == 1}">
                                                <td>线下支付</td>
                                            </c:when>
                                        </c:choose>
			                            <c:choose>
			                            	<c:when test="${order.status == 0}">
			                            		<td class="tcenter" id="status_24556"><strong class="red">未提交</strong></td>
			                            		<td></td>
			                            	</c:when>
			                            	<c:when test="${order.status == 1}">
			                            		<c:choose>
				                            		<c:when test="${order.paymentMethod == 0}">
					                            		<td class="tcenter" id="status_24556"><strong class="org">待付款</strong></td>
					                            		<td><strong ><a href="/alipay/online?orderSerial=${order.orderSerial}" target="_blank">付款</a></strong></td>
				                            		</c:when>
				                            		<c:when test="${order.paymentMethod == 1}">
					                            		<td class="tcenter" id="status_24556"><strong class="org">待付款</strong></td>
					                            		<td><strong ><a href="/pay/info?orderSerial=${order.orderSerial}" target="_blank">付款</a></strong></td>
				                            		</c:when>
			                            		</c:choose>
			                            	</c:when>
			                            	<c:when test="${order.status == 2}">
			                            		<td class="tcenter" id="status_24556"><strong class="green">已付款</strong></td>
			                            		<td></td>
			                            	</c:when>
			                            	<c:when test="${order.status == 3}">
			                            		<td class="tcenter" id="status_24556"><strong class="gray">已取消</strong></td>
			                            		<td></td>
			                            	</c:when>
			                            	<c:when test="${order.status == 4}">
			                            		<td class="tcenter" id="status_24556"><strong class="blue">已完成</strong></td>
			                            		<td></td>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<td class="tcenter" id="status_24556"><strong class="gray">其他</strong></td>
			                            		<td></td>
			                            	</c:otherwise>
			                            </c:choose>
			                            
			                            <!-- <td id="ops_24556">
			                                <p><a href="/market/packet/10137">再次购买</a></p>
			                            </td> -->
			                            <!-- <td>
			                                <strong class="org"><a href="/market/packet/10137?flags=1">未评论</a></strong>
			                            </td> -->
			                            <c:choose>
			                            	<c:when test="${order.isInvoice == 1}">
			                            		 <td>是</td>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<td>否</td>
			                            	</c:otherwise>
			                            </c:choose>

			                        </tr>
	                        </c:forEach>
                        </tbody>
                    </table>
                </section>
             </div>
        </div>
    </div>
</body>
</html>

