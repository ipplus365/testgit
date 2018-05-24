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
		<title>购物车</title>
		<link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
		<link rel="stylesheet" href="/statics/css/info.css">
		<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
		<style>
			.active_btn:hover{
				color: #fff;
			}
			.order_detal .order_data td {
				text-align: center;
			}
			.goShopping{
				color: #00b1ff;
			}
			.goShopping:hover{
				text-decoration: solid;
				color: orange;
			}
		</style>
	</head>
	<body>

			<c:import url="../layout/header.jsp"/>
			<%--<div class="topHd">--%>
				<%--<div class="wrap">--%>
					<%--<div class="payStep mt10">--%>
						<%--<dl class="step1">--%>
							<%--<dt></dt>--%>
							<%--<dd>--%>
								<%--<span>确认购物车信息</span><span>核对订单信息</span>--%>
							<%--</dd>--%>
						<%--</dl>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>
		
		<section class="content wrap mt20">
			<div class="order_detal">
				<div class="o_top">
					<h1 class="">核对购物车信息</h1>
					<div class="clear"></div>
				</div>
				<div class="o_detal">
					<table id="checkout" class="order_data checkout-steps" cellpadding="0" cellspacing="0">
							<thead>
								<tr>
									<th colspan="9">
										<h5>新订购服务</h5>
									</th>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<th>名称</th>
									<th>SKU编号</th>
									<th>订购项目</th>
									<th>订购数量</th>
									<th>价格(元)</th>
									<th>折扣</th>
									<th>实付(元)</th>
									<th>删除</th>
								</tr>
							</thead>
						<tbody id="renew">
							<c:forEach items="${cart.cartItems}" var="list" varStatus="i">
								<tr>
								<input type="hidden" value="${list.id}"  name="pricePackageId"/>
						        <td align="center">${i.index+1}</td>
						        <td align="center">${list.product.productName }</td>
						        <td align="center">${list.product.skuId }</td>
						        <td align="center">${list.pricePackage.accuracy.accuracy} - ${list.pricePackage.amountStr}次</td>
						        <td align="center" style="position:relative;"> 
									<input type="text" value="${list.itemNum}" class="cartNum"/>
									<input type="hidden" value="${list.id}"/>
								</td>
						        <td align="center">${list.originalPrice }</td>
						        <td align="center">${list.discount }折</td>
						        <td align="center">${list.price }</td>
						        <td align="center" class="delete">删除</td>
						    </tr>
						    </c:forEach>
						</tbody>
						    <tr>
								<c:choose>
									<c:when test="${cart.cartItems.size()>0}">
										<td colspan="9" align="center">付款小计：<b id="payPriceB">${cart.price}</b>元</td>
									</c:when>
									<c:otherwise>
										<td colspan="9" align="center">您还没有选购数据 <a href="/" class="goShopping">去数据市场逛逛吧</a></td>
									</c:otherwise>
								</c:choose>
							</tr>
							
    						<tr>
    							<td colspan="9" class="bg_2" align="right" style="background:#ededed;">
        							<div style="position: relative;">
                            			<a id="submitBtn" href="/order/add" class="btn_pay fr active_btn">去结算</a>
                        				<div style="top:-40px;display: none;" id="submit_check_info_message" class="submit-check-info">
                							<!--<span>您需先保存<span id="save_info" style="color:#005EA7;">收票人地址</span>，再同意协议并付款></span>-->
            							</div>
        							</div>
    							</td>
							</tr>
					</table>
				</div>
			</div>
		</section>
	</body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.js"></script>
<script type="text/javascript">
    $(function(){
        var msg = '${msg}';
        if(msg != ''){
            alert(msg);
            msg='';
        }
    });
	var itemNum;
	var itemId;
	var index;
		$('body').on('change','.cartNum',function(e){
			index = $(event.target).parent().parent().children().eq(1).text();
			itemNum = $(event.target).parent().children().eq(0).val();
			itemId =$(event.target).parent().children().eq(1).val();
			var self = $(event.target);
			$.ajax({
				type:"get",
				url:"/cart/update",
				async:true,
				data:{itemNum:itemNum,itemId:itemId},
				datatype:'json',
				success:function(res){
					if(res.success){
						$('#payPriceB').text(res.data.price);
						self.parent().parent().children().eq(6).text(res.data.cartItems[index - 1].originalPrice);
						self.parent().parent().children().eq(7).text(res.data.cartItems[index - 1].discount + '折');
						self.parent().parent().children().eq(8).text(res.data.cartItems[index - 1].price);
						self.parent().children().eq(0).val(res.data.cartItems[index - 1].itemNum);
					}else{
						alert(res.msg);
                        $('#payPriceB').text(res.data.price);
                        self.parent().parent().children().eq(6).text(res.data.cartItems[index - 1].originalPrice);
                        self.parent().parent().children().eq(7).text(res.data.cartItems[index - 1].discount + '折');
                        self.parent().parent().children().eq(8).text(res.data.cartItems[index - 1].price);
                        self.parent().children().eq(0).val(res.data.cartItems[index - 1].itemNum);
					}

				}
			});
		});
		$('body').on('click', '.cartReduce' ,function(e){
			index = $(event.target).parent().parent().children().eq(1).text() ;
			itemNum = $(event.target).parent().children().eq(1).val();
			itemNum--;
			if(itemNum <= 1){
				itemNum = 1;
			}
			$(event.target).parent().children().eq(1).val(itemNum);
			itemId = $(event.target).parent().children().eq(2).val();
			var self = $(event.target);
			$.ajax({
				type:"get",
				url:"/cart/update",
				async:true,
				data:{itemNum:itemNum,itemId:itemId},
				datatype:'json',
				success:function(res){
					if(res.success){
						$('#payPriceB').text(res.data.price);
						self.parent().parent().children().eq(6).text(res.data.cartItems[index - 1].originalPrice);
						self.parent().parent().children().eq(7).text(res.data.cartItems[index - 1].discount + '折');
						self.parent().parent().children().eq(8).text(res.data.cartItems[index - 1].price);
						self.parent().children().eq(1).val(res.data.cartItems[index - 1].itemNum);
					}else{
						alert(res.msg);
					}
					
				}
			});
		});
		$('body').on('click', '.delete' ,function(e){
			itemId = $(event.target).parent().children().eq(5).children().eq(1).val();
			var self = $(event.target);
			$.ajax({
				type:"get",
				url:"/cart/delete",
				async:true,
				data:{ itemId:itemId },
				datatype:'json',
				success:function(res){
					if(res.success){
						self.parent().remove();
						$('#payPriceB').text(res.data.price);
						var temp = '';
						for(var i = 0; i < res.data.cartItems.length; i++){
							var index = i + 1;
							temp += '<tr><input type="hidden" value="'+ res.data.cartItems[i].id +'"name="pricePackageId"/><td>' + index + '</td><td>' + res.data.cartItems[i].product.productName +'</td><td>'+ res.data.cartItems[i].product.skuId +'</td><td>' + res.data.cartItems[i].pricePackage.accuracy.accuracy + ' - ' + res.data.cartItems[i].pricePackage.amountStr + '次</td><td style="position: relative;">' + '<input type="text" class="cartNum" maxlength="99" value="' + res.data.cartItems[i].itemNum + '"/><input type="hidden" value="'+ res.data.cartItems[i].id +'"/><td>' + res.data.cartItems[i].originalPrice + '</td><td>' + res.data.cartItems[i].discount + '折</td><td>' + res.data.cartItems[i].price + '</td><td class="delete">删除</td></tr>';
						}
						$('#renew').html(temp);
						if(res.data.cartItems.length == 0){
						    window.location.reload();
						}
					}
				}
			});
		});
</script>	
