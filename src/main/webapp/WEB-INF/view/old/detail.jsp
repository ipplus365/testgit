<!DOCTYPE html>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

	<meta charset="utf-8"/>
	<meta name="keywords" content="IP定位_埃文科技_IP实时定位_ip区县库_ip免费库_全球ip库_高精度ip定位_埃文商城">
	<meta name="description" content="埃文商城(mall.ipplus360.com) -埃文科技(www.ipplus360.com)">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<title>埃文商城-IP问问-商品详情</title>
	<link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="/statics/css/footer.css"/>
	<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
	<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
	<script src="/statics/js/dlist.js"></script>
	<style>
		tbody {
			display: table-row-group;
			vertical-align: middle;
			border-color: inherit;
		}
		tr {
			display: table-row;
			vertical-align: inherit;
			border-color: inherit;
		}
		.order_data th{
			text-align:center;
			font-size: 13px;
			font-family: "Microsoft YaHei","SimHei";
			background: #f7f7f7;
			height: 30px;
			line-height: 30px;
			border: #ededed 1px solid;
			min-width: 120px;
		}
		.order_data td {
			font-family: "Microsoft YaHei","SimHei";
			font-size: 13px;
			padding: 12px 5px;
			border-width: 1px;
			border-style: solid;
			border-color: rgb(237, 237, 237);
			border-image: initial;
			max-width: 60%;
		}
		.cart{
			width: 1205px;
			margin: 0 auto;
			position: relative;
			top:-50px;
		}
		.inner{
			display: inline-block;
			width: 150px;
			border:1px solid #ccc;
			text-align: center;
			font-size:14px;
			padding: 10px 0;
			color: #1296db;
			cursor: pointer;
			float: right;
		}
		.gwc{
			width: 18px;
			height:18px;
			vertical-align: middle;
		}
		.cartnumber{
			display: inline-block;
			width: 18px;
			height: 18px;
			border-radius: 50%;
			line-height: 18px;
			background: #1296db;
			color: #fff;
		}
		.disableBtn{
			cursor: not-allowed;
			background: #CCCCCC;
		}
		.allow{
			background:#ff8000;
			cursor: pointer;
		}
	</style>
</head>
<body class="bg-white">
<!--顶部部用户信息信息栏-->
<c:import url="/WEB-INF/view/layout/header.jsp"/>
<%--购物车--%>
<div class="cart">
	<a href="/cart/info" class="inner">
		<img src="/statics/images/gouwuche.png" class="gwc">
		我的购物车
		<c:if test="${cartSize != null}"><span class="cartnumber">${cartSize}</span></c:if>
		<c:if test="${cartSize == null}"><span class="cartnumber">0</span></c:if>
	</a>
</div>
<!-- 详情页 -->
<section class="wrap">
	<div class="position">
		<i class="new-pos"></i>当前位置：
		<a href="/">数据市场</a>
		<!--<span class="song">&gt;</span>-->
		<!--<a href="/market/datas/18">应用开发</a>-->
		<span class="song">&gt;</span>
		<span class="pos-on">${productInfo.productName}</span>

	</div>
	<div class="mb25 cl">
		<form action="/cart/add" method="post" >
			<div class="detail-left">
				<div class="order new-show">
					<div class="new-tmp">
						<img src="${productInfo.iconLarge}" alt="${productInfo.productName}" width="230" height="230">
					</div>
					<div class="info-show" v-cloak>
						<div class="detail-title-new">
							<h1 class="pl15 fl">${productInfo.productName}</h1>
						</div>
						<p class="org new-intro">
							${productInfo.description}
						</p>
						<div class="new-price-show">
							<ul>
								<li class="">
									<span class="tit">价&emsp;格 </span>￥
									<span class="price-num">
	                                        <span class="price-true">{{price.total}}</span>
	                                    </span>
									<span class="orangeTag equivalent-price" v-show="!none">
	                                        {{price.price}}{{price.unit}}
	                                    </span>
									<span class="orangeTag equivalent-price" v-show="tipshow">
											{{price.tips}}
										</span>
								</li>

								<li class="mb">
									<span class="tit">规&emsp;格</span>
									<div style="display: inline-block;position: relative;">
	                                    	<span class="price price-times model" v-for="(item, index) in data" v-bind:class="[item.bor ? 'cur' : '']"  v-on:click="select(index), reqPrice(1)">
	                                        	{{item.amountStr}}
	                                    	</span>
									</div>
								</li>
								<li class="mb">
									<span class="tit" >精&emsp;度</span>
									<span class="price price-times acc" v-for="(acc, index1) in acc" v-bind:class="[acc.sel ? 'cur' : '']"  @click="selectPackagetype(index1), reqPrice(index1+1)">
	                                        {{acc.accuracy}}
	                                    </span>
								</li>
							</ul>
						</div>
						<ul class="info-list">
							<li>
								<span class="tit">标&emsp;签</span>
								<span><a href="list" class="blue">IP地址</a></span>&emsp;
							</li>
							<li>
								<span class="tit">服&emsp;务</span>
								<span class="bill-limit">
										发票限制
										<span class="tooltip">累计1000元起开</span>
									</span>
								<span ><a href="/product/ad" class="bill-limit org" target="_blank">优惠见详情介绍</a></span>
							</li>
						</ul>
						<div>
							<span class="tit pt10 fl">数&emsp;量</span>
							<span class="numBox">
	                            	<input type="text"  class="numInput" name="itemNum" id="selectCount" v-model="count">
	                            	<input type="hidden"  value="${productInfo.id}" name="productId" id="productId">
	                            	<input type="hidden"  value="${productInfo.productName}" id="productName">
								<!-- <input type="hidden"  v-model="currentAmount" name="amount"> -->
					        		<input type="hidden" v-model="currentAcc" name="accuracyId">
					        		<input type="hidden" v-model="pricePackage" name="pricePackageId">
	                                <i class="plus" v-on:click="add"></i>
	                                <i class="reduce" v-on:click="reduce"></i>
	                            </span>
							<span>
		                               	<input  type="submit" value="立即购买" id="purchaseBtn" class="purchaseBtn" v-bind:disabled='none' v-bind:class="[none ? 'disableBtn' : 'allow']" v-show="!allow">  <!-- <a href="javascript:;" id="purchaseBtn" class="purchaseBtn" v-on:click="takeData">立即购买</a> -->
	                            		<a href="/download/ip/district?coord=BD09&type=zip"><input  type="button" value="免费下载" id="purchaseBtn" class="purchaseBtn"  v-show="allow" v-bind:class="[allow ? 'allow' : '']"></input></a>
                                        &nbsp;<input type="checkbox" disabled checked>
                                        同意<a href="/order/protocol" id="pro">软件购买协议</a>
	                            </span>
							<!-- <span>
                                <a href="javascript:;" id="purchaseBtn" class="purchaseBtn"  v-on:click="takeData">立即购买</a>
                            </span> -->
							<span class="dongPadding" >
	                                <div class="im"></div>
	                            </span>
						</div>
					</div>
				</div>
			</div>
		</form>
		<c:import url="/WEB-INF/view/layout/cartright.jsp"/>
	</div>
	<c:import url="/WEB-INF/view/layout/cartfooter.jsp"/>
	<c:import url="/WEB-INF/view/layout/cartad.jsp"/>

</section>
<div class="clear"></div>
<!--底部footer-->
<c:import url="/WEB-INF/view/layout/footer.jsp"/>

<div class="contatct-w">
	<div class="gotop" onclick="query.scrollUp()">
		<i class="gotop-icon"></i>
		<span class="gotop-txt">返回顶部</span>
	</div>
</div>
<script src="https://cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script src="/statics/js/detail.js"></script>
<script type="text/javascript">

    $(function(){
        var msg = '${msg}';
        if(msg != ''){
            alert(msg);
            msg='';
        }
    });
</script>
</body>
</html>