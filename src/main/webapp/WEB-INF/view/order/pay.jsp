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
		<meta charset="UTF-8">
		<title>线下支付</title>
		<link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
		<link rel="stylesheet" href="/statics/css/orderInfo.css" />
		<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
		<style type="text/css">
			.pay {
				padding: 20px;
				margin: 20px auto;

			}
			.pay .tit{
				padding: 5px 0;
				text-align: left;
				font-size: 14px;
				font-weight: 500;
			}
			.pay>h3,h2{
				margin: 20px 0;
			}
			.center{
				text-align: center;
				font-size: 16px;
				margin: 10px 0;
			}
			.orange{
				color: #FF6600;
			}
		</style>
	</head>
	<body class="bg-white">
		<c:import url="../layout/header.jsp"/>
		<section class="wrap mt15">
			<div class="order_detal">
				<div class="pay">
					<h2>郑州埃文计算机科技有限公司</h2>
					<table id="checkout" class="order_data checkout-steps" cellpadding="0" cellspacing="0">
						<tbody>
						<tr>
							<th colspan="5">
								<h5>
									您的订单:<strong class="red">${order.orderSerial }</strong>
								</h5>
							</th>
						</tr>
						<tr>
							<th>编号</th>
							<th>订单详情</th>
							<th>订购数量</th>
							<th>折扣</th>
							<th>实付金额</th>
						</tr>
						<c:forEach items="${order.orderItems }" var="list" varStatus="i">
						<tr>
							<td align="center">${i.index+1}</td>
							<td align="center">${list.product.productName } - ${list.pricePackage.accuracy.accuracy} - ${list.pricePackage.amountStr}次</td>
							<td align="center">${list.itemNum }</td>
							<td align="center">${list.discount }折</td>
							<td align="center">${list.price }元</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="5" align="center">
								总金额：<strong class="red">${order.price }</strong>元
							</td>
						</tr>
						</tbody>
					</table>
					<h3 class="orange">线下汇款提示:</h3>
					<p class="tit">
						目前线下支付支持银行转账/汇款的方式。选择此支付方式，提交订单后您可到任一家银行柜台，或ATM自动柜员机进行汇款转账，或网银转账方式，将货款汇入我司对公账户（如下）。汇款时请务必在电汇单的用途栏内注明<strong class="orange">订单号</strong>。
					</p>
					<table id="" class="order_data checkout-steps" cellpadding="0" cellspacing="0" style="width: 50%;margin: auto;">
						<tbody>
						<tr>
							<td align="center">账户名</td>
							<td align="center">郑州埃文计算机科技有限公司</td>
						</tr>
						<tr>
							<td align="center">开户银行</td>
							<td align="center">招商银行郑州分行金水路支行</td>
						</tr>
						<tr>
							<td align="center">银行账户</td>
							<td align="center">3719-0321-8410-404</td>
						</tr>
						</tbody>
					</table>
					<p class="red center">流程指南：①提交订单　→　②柜台/网银转账汇款 → ③准备付款凭证及订单号 →　④联系客服核实汇款　→　⑤下单成功</p>
					<h3 class="orange">银行转账/汇款提示:</h3>
					<p class="tit">用户可以通过全国任何一家银行，向“埃文科技”在<span class="orange">中国招商银行</span>开立的账户汇款，详细信息如下。到款时间一般为款汇出去以后的2-7个工作日内。我们将在收到您的款项后的1个工作日内受理您的订单，并尽快为您办理配送手续。</p>
					<p class="tit">（1）办理银行汇款/转账时，请您务必在汇款单的用途栏内注明与此汇款相关联的订单号（为了确保及时收到您的货款，请尽量避免使用ATM机转账功能）</p>
					<p class="tit">（2）汇款后请您通过以下两种方式告知我们您的汇款已经汇出，电话告知方式：<span class="orange">400-0371-911</span>，联系时间：<span class="orange">工作日8:30—18:00</span>，邮件告知方式：<span class="orange">contacts@ipplus360.com</span><br />客服QQ号：<span class="orange">2415968308</span>，我们会在核对完款项后尽快为您发货。下单完成后请将付款凭证和扫描件提供给我们公司</p>
					<p class="tit">（3）如果汇款一段时间之后，您的订单仍为“未付款”状态，或还没有收到商品，请与我们联系核实。联系时请通过邮件提供<strong class="orange">汇款单扫描件</strong>及<strong class="orange">订单号</strong>。</p>
					<p class="tit">（4）付款审核工作时间为工作日的8:30~18:00，该时间段审核将在当日完成。如遇国家法定节假日及周末，付款审核将顺延至节假日、周末结束的第一个工作日审核。或您可以通过在线支付，购买我们的产品。如有疑问请联系客服。</p>
				</div>
			</div>
		</section>
	</body>
</html>

