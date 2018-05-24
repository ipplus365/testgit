<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	response.setHeader("Expires", "0");
	response.setHeader("Pragma","no-cache"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>订单详情</title>
		<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
        <link rel="stylesheet" type="text/css" href="/statics/css/orderInfo.css"/>
        <style type="text/css">
			.pay-again{
				text-align: right;
				padding: 0 53px 20px 0 ;
			}
            .mb{
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.3);
                position: absolute;
                top:0;
                display: none;
            }
            .payTips{
                width: 400px;
                height: 150px;
                background: #fff;
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                margin:auto;
                border-radius: 5px;
            }
            .payTips>p{
                padding: 12px 0;
                color: #000;
                font-size:16px;
                text-align: center;
            }
            .href{
                margin-top: 30px;
            }
            .href>a{
                padding:10px 20px;
                color: #fff;
                border-radius: 4px;
                background:#00b1ff;
                font-size:16px;
                margin-left: 60px;
            }
            .close{
                text-align: right;
                padding:5px 20px 0 20px;
            }
            .close>span{
                font-size:25px;
                cursor: pointer;
            }
			.aliPay .payTips{
				width: 400px;
				height: 150px;
				background: #fff;
				position: absolute;
				top: 0;
				left: 0;
				right: 0;
				bottom: 0;
				margin:auto;
				border-radius: 5px;
			}
			.aliPay .payTips>p{
				padding: 20px 0;
				color: #000;
				font-size:16px;
				text-align: center;
			}
			.aliPay .href{
				margin-top: 30px;
			}
			.aliPay .href>a{
				padding:10px 20px;
				color: #fff;
				border-radius: 4px;
				background: #00b1ff;
				font-size:16px;
				margin-left: 60px;
			}
        </style>
    </head>
    <body class="bg-white">
    	<!--顶部部用户信息信息栏-->
    <c:import url="../layout/header.jsp"/>
    <section class="wrap mt20">
			<div class="order_detal">
				<div class="o_top">
					<h1 class="">我的订单信息</h1>
					<div class="clear"></div>
				</div>
				<div class="o_top pay-again" style="text-align: right">
					<a href="/" class="btn">再次购买</a>
				</div>
				<div class="o_detal">
					<table id="checkout" class="order_data checkout-steps" cellpadding="0" cellspacing="0">
						<tbody>
						<%--<tr>--%>
							<%--<td colspan="5" align="right">--%>
								<%--<a href="/product/list" class="btn">再次购买</a>--%>
							<%--</td>--%>
						<%--</tr>--%>
							<tr>
								<th colspan="6">
									<h5 style="padding-left: 25px;">
										<span>订单编号:${order.orderSerial }</span>
										
		                            	<%-- <c:if test="${order.status == 2}">
		                            		 
		                            	</c:if> --%>
										<c:choose>
			                            	<c:when test="${order.status == 1}">
			                            		<span>提交时间:<fmt:formatDate value="${order.dateUpdated }" pattern="yyyy年MM月dd日 HH时mm分"/></span>
			                            	</c:when>
			                            	<c:when test="${order.status == 2}">
			                            		<span>结算时间:<fmt:formatDate value="${order.dateUpdated }" pattern="yyyy年MM月dd日 HH时mm分"/></span>
			                            	</c:when>
			                            	<c:when test="${order.status == 3}">
			                            		<span>取消时间:<fmt:formatDate value="${order.dateUpdated }" pattern="yyyy年MM月dd日 HH时mm分"/></span>
			                            	</c:when>
			                            	<c:when test="${order.status == 4}">
			                            		<span>完成时间:<fmt:formatDate value="${order.dateUpdated }" pattern="yyyy年MM月dd日 HH时mm分"/></span>
			                            	</c:when>
			                            </c:choose>
										
										<span>实付金额:${order.price}元</span>
										<input type="hidden" value="${order.status}" id="status">
										<c:choose>
			                            	<c:when test="${order.status == 0}">
			                            		<span>订单状态:未确认</span>
			                            	</c:when>
			                            	<c:when test="${order.status == 1}">

													<span>订单状态:待付款</span>
													<a onclick="return cancel()" href="/order/cancel?orderId=${order.orderSerial}" style="color:#00b1ff;cursor:pointer;margin-left:280px" class="orange">取消订单</a>

													<c:if test="${order.paymentMethod == 0}">
														<a href="/alipay/online?orderSerial=${order.orderSerial}" id="alipay" target="_blank" style="color:#00b1ff;cursor:pointer;margin-left:25px">去付款</a>
													</c:if>
													<c:if test="${order.paymentMethod == 1}">
														<a href="/pay/info?orderSerial=${order.orderSerial}" style="color:#00b1ff;cursor:pointer;margin-left:25px">去付款</a>
													</c:if>
			                            	</c:when>
			                            	<c:when test="${order.status == 2}">
			                            		<span>订单状态:已付款</span>
											</c:when>
			                            	<c:when test="${order.status == 3}">
			                            		<span>订单状态:已取消</span>
			                            	</c:when>
			                            	<c:when test="${order.status == 4}">
			                            		<span>订单状态:已完成</span>
			                            		<a href="/person/user" style="color:#00b1ff;cursor:pointer;margin-left:300px" target="_blank">查看Token</a>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<span>订单状态:其他</span>
			                            	</c:otherwise>
			                            </c:choose>
									</h5>
								</th>
							</tr>
							<tr>
								<th>编号</th>
							    <th>订单详情</th>
							    <th>订购数量</th>
							    <th>折扣</th>
							    <th>付款方式</th>
							    <th>应付金额</th>
							</tr>
							<c:forEach items="${order.orderItems}" var="list" varStatus="i">
							<tr>
						        <td align="center">${i.index+1}</td>
						        <td align="center">${list.product.productName } - ${list.pricePackage.accuracy.accuracy} - ${list.amountStr}次</td>
						        <td align="center">${list.itemNum }</td>
						        <td align="center">${list.discount }折</td>
						        <c:choose>
	                                <c:when test="${order.paymentMethod == 0}">
	                                    <td align="center">在线支付</td>
	                                </c:when>
	                                <c:when test="${order.paymentMethod == 1}">
	                                    <td align="center">线下支付</td>
	                                </c:when>
                                </c:choose>
						        <td align="center">${list.price }元</td>
						    </tr>
						    </c:forEach>
							<tr>
							    <td colspan="5" align="center">应付总额</td>
							    <td colspan="1" align="center">${totalPayable}元</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</section>
        <div class="mb token">
            <div class="payTips">
                <div class="close"><span>×</span></div>
                <p>请在新打开的页面中完成付款</p>
                <div class="href">
                    <a href="/person/user" target="_blank">查看Token</a>
                    <a href="javascript:;" id="stayHere">留在本页</a>
                </div>
            </div>
        </div>
		<div class="mb aliPay">
			<div class="payTips">
				<p>请在新打开的页面中完成付款</p>
				<div class="href">
					<a href="/alipay/status?orderSerial=${order.orderSerial}">付款完成</a>
					<a href="/order/info?orderId=${order.orderSerial}">付款失败</a>
				</div>
			</div>
		</div>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
$(function(){ 
	var msg = '${msg}';
	if(msg !=''){
		alert(msg);
		msg='';
	}
});
function cancel(){
	 return confirm("是否取消订单?");
}
$(function () {
	if($('#status').val() == 4){
        $('.token').show();
	}else{
        $('.token').hide();
    }
    $('#stayHere').click(function (){
        $('.token').hide();
    });
    $('.close>span').click(function (){
        $('.token').hide();
    });
    $('#alipay').click(function(){
		$('.aliPay').show();
	});
});

</script>
</body>
</html>