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
		<title>核对订单信息</title>
		<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
		<link rel="stylesheet" href="/statics/css/info.css">
		<style type="text/css">
			#pro:hover{
				color:#3e95ee;
			}
			#pro{
				margin-right: 20px;
			}
			.i-error{
				background:url(../../statics/images/icon.png) no-repeat;
			}
			.i-error{
				background-position: -17px -100px;
			}
			.error{
				color: #e22;
			}
			.error i {
				display: inline-block;
				width: 16px;
				height: 16px;
				vertical-align:middle;
			}
			.step2 dt{
				background-position: 0 -20px;
			}
            .orange{
                color: #FF6600;
            }
			v-cloak{
				display: none;
			}
			.mb{
				width: 100%;
				height: 100%;
				background: rgba(0,0,0,0.3);
				position: absolute;
				top:0;
				/*display: none;*/
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
				padding: 20px 0;
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
				background: #e22;
				font-size:16px;
				margin-left: 60px;
			}
		</style>
	</head>
	<body>
		<c:import url="../layout/header.jsp"/>
			<%--<div class="topHd">--%>
				<%--<div class="wrap">--%>
					<%--<div class="payStep mt10">--%>
						<%--<dl class="step2">--%>
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
					<h1 class="">核对订单信息</h1>
					<div class="clear"></div>
				</div>
				<div class="o_detal">
				<form action="/pay/submit" method="post">
					<table id="checkout" class="order_data checkout-steps" cellpadding="0" cellspacing="0" v-cloak>
						<input type="hidden" id="orderSerial" name="orderSerial" v-model="order">
						<tbody>
							<tr>
								<th colspan="9">
									<h5>新订购服务</h5>
									<%-- <input type="hidden" id="orderSerial" value="${order.orderSerial}"> --%>
								</th>
							</tr>
							<tr>
							    <th>&nbsp;</th>
							    <th>名称</th>
							    <th>SKU编号</th>
							    <th>订购项目</th>
							    <th>订购数量</th>
							    <th>价格(元)</th>
							    <th>优惠(元)</th>
							    <th>实付(元)</th>
							</tr>
							<c:forEach items="${cart.cartItems}" var="list" varStatus="i">
							
							<tr>
								<%-- <input type="hidden" value="${list.id}"  name="pricePackageId"/> --%>
						        <td align="center">${i.index+1}</td>
						        <td align="center">${list.product.productName }</td>
						        <td align="center">${list.product.skuId }</td>
						        <td align="center">${list.pricePackage.accuracy.accuracy} - ${list.pricePackage.amountStr}次</td>
						        <td align="center" style="position:relative;"> 
						        	<input type="text" value="${list.itemNum}" class="cartNum" disabled/>
								</td>
						        <td align="center">${list.originalPrice }</td>
						        <td align="center">${list.discount }折</td>
						        <td align="center">${list.price }</td>
						    </tr>
						    </c:forEach>
						    <tr>
							    <td colspan="9" align="center">付款小计：<b id="payPriceB">${cart.price}</b>元</td>
							</tr>
							<tr>
								<th colspan="9">
									<h5>发票信息</h5>
								</th>
							</tr>
							<tr>
						        <td colspan="9" align="left">
						            <div class="ll">发票内容：
						                <input type="radio" id="noInvoice"   name="isInvoice" value="0" v-model="Invoice"/><label for="noInvoice">不开发票</label>
						              	<input type="radio" id="has_Invoice" name="isInvoice" value="1" v-model="Invoice"/> <label for="has_Invoice">增值税发票</label>
						            </div>
						        </td>
    						</tr>
							<tr id="invoiceVatTr" style="display: table-row;" v-show="form">
								<td colspan="9">
									<div class="invoice-form">
										<div style="display: block;margin-left: 10px;" class="clearfix hide" id="special-form">
											<div class="pull-form">
												<div id="vat_companyName_div" class="list">
													<span class="label"><em>*</em>单位名称：</span>
													<div class="field">
														<input type="text" class="textbox" value="" id="vat_companyName" v-model="invoice.orgName">
														<span class="error" v-show="orgNameError">
															<i class="i-error"></i>
															请填写您的单位名称
														</span>
													</div>
												</div>
												<div id="vat_code_div" class="list">
													<span class="label"><em>*</em>纳税人识别号：</span>
													<div class="field">
														<input type="text" class="textbox" value="" id="vat_code" v-model="invoice.taxId">
														<span class="error" v-show="taxIdError">
															<i class="i-error"></i>
															请填写您的纳税人识别号
														</span>
													</div>
												</div>
												<div id="" class="list">
													<span class="label"><em>*</em>注册地址：</span>
													<div class="field">
														<input type="text" class="textbox" id="vat_address" v-model="invoice.regAddress">
														<span class="error" v-show="regAddressError">
															<i class="i-error"></i>
															请填写您注册地址
														</span>
													</div>
												</div>
												<div id="vat_phone_div" class="list">
													<span class="label"><em>*</em>注册电话：</span>
													<div class="field">
														<input type="text" class="textbox"  id="vat_phone" v-model="invoice.regMobile">
														<span class="error" v-show="regMobileError">
															<i class="i-error"></i>
															请填写您的注册电话
														</span>
													</div>
												</div>
												<div id="vat_bankName_div" class="list">
													<span class="label"><em>*</em>开户银行：</span>
													<div class="field">
														<input type="text" class="textbox" value="" id="vat_bankName" v-model="invoice.bank">
														<span class="error" v-show="bankError">
															<i class="i-error"></i>
															请填写您的开户银行
														</span>
													</div>
												</div>
												<div id="vat_bankAccount_div" class="list">
													<span class="label"><em>*</em>银行帐户：</span>
													<div class="field">
														<input type="text" class="textbox" value="" id="vat_bankAccount" v-model="invoice.bankAccount">
														<span class="error" v-show="bankAccountError">
															<i class="i-error"></i>
															请填写您的银行账户
														</span>
													</div>
												</div>
											</div>
										</div>
										<div style="padding: 20px; display: block;" id="invoiceConsignee" class="pull-form step-current">
											<div class="step-stitle"><strong>收票人地址</strong></div>
											<div class="hide" id="invoiceConsigneeDiv" style="display: block;">
												<div id="vat_name_div" class="list">
													<span class="label"><em>*</em>收票人姓名：</span>
													<div class="field">
														<input type="text" maxlength="20" value="" id="invoice_consignee_name" class="textbox" v-model="invoice.userContacts">
														<span class="error" v-show="userContactsError">
															<i class="i-error"></i>
															请填写您的姓名
														</span>
													</div>
												</div>
												<div id="vat_call_div" class="list">
													<span class="label"><em>*</em>收票人手机：</span>
													<div class="field">
														<div class="phone">
															<input type="text" maxlength="11" value="" id="invoice_consignee_mobile" class="textbox" v-model="invoice.userMobile">
															<span class="error" v-show="userMobileError">
																<i class="i-error"></i>
																请正确填写您的手机号码
															</span>
														</div>
													</div>
												</div>
												<div id="vat_address_div" class="list full-address">
													<span class="label"><em>*</em>详细地址：</span>
													<div class="field">
														<input type="text" maxlength="50" value="" id="invoice_consignee_address" class="textbox" v-model="invoice.userAddress">
														<span class="error" v-show="userAddressError">
															<i class="i-error"></i>
															请填写您的详细地址
														</span>
													</div>
												</div>
												<div class="pull-tips">
													<ul class="invoice-tips">
														<li class="fore1"><span style="color:rgb(255, 102, 0);">注意：1.此账户订单的发票将邮寄到该地址。如果地址变更，所有待邮寄的发票都将寄到更新后的地址。<br/>
                                                            2.如需发票请填写详细信息，工作日8:30~18:00申请发票将在当日完成，并在3~5个工作日邮寄至贵司。如遇国家法定节假日及周末，发票将顺延至节假日、周末结束的第一个工作日开具。<br/>
                                                            3.如有疑问请联系客服，客服QQ：2415968308；400电话：400-0371-911。<br/>4.如单次开票金额不足1000元，我们会收取邮费。</span></li>
													</ul>
												</div>
											</div>
											<div class="form-btn group" id="save-invoice-div">
												<a href="javascript:;" class="btn-submit" @click="submitInvoice">
													<span>保存收票人地址</span>
												</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th colspan="9" class="bg_2">
									<h5>支付方式</h5>
								</th>
							</tr>
							<tr>
						        <td colspan="9" align="left">
						            <div class="ll">支付方式:
										<input type="radio" value="0" name="payType" id="payOnline" v-model="payType"/><label for="payOnline">在线支付</label>
                                        <input type="radio" value="1" name="payType" id="payOutline" v-model="payType"/><label for="payOutline">其他方式</label>
						            </div>
						        </td>
    						</tr>
							<tr>
								<td colspan="9" align="right">
									<input type="checkbox" disabled checked>
									同意<a href="/order/protocol" id="pro">软件购买协议</a>
								</td>
							</tr>
    						<tr>
    							<td colspan="9" class="bg_2" align="right" style="background:#ededed;">
                                    <div style="position: relative;">
                                        <button id="submitBtn" class="btn_pay fr active_btn" v-bind:disabled="!isPay" v-bind:class="[!isPay ? 'disable' : '']" @click="checkPayType">确认订单并付款</button>
                                        <div style="top:-40px;" id="submit_check_info_message" class="submit-check-info" v-show="!isPay">
                                            <span>您需先保存<span id="save_info" style="color:#005EA7;">收票人地址</span>，再同意协议并付款></span>
                                        </div>
                                    </div>
    							</td>
							</tr>
						</tbody>
					</table>
				</form>
				</div>
			</div>
		</section>
		<div class="mb" v-show="mbShow" v-cloak>
			<div class="payTips">
				<p>请在新打开的页面中完成付款</p>
				<div class="href">
					<a href="/person/order">付款完成</a>
					<a href="/person/order">付款失败</a>
				</div>
			</div>
		</div>
	</body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script type="text/javascript">
    var vue = new Vue({
        el:'#checkout',
        data:{
            Invoice:'0',
			invoice:{
             	orgName:'',
				taxId:'',
				regAddress:'',
				regMobile:'',
				bank:'',
				bankAccount:'',
				userContacts:'',
				userMobile:'',
				userAddress:'',
			},
			isPay:true,
			orgNameError:false,
            taxIdError:false,
            regAddressError:false,
            regMobileError:false,
            bankError:false,
            bankAccountError:false,
            userContactsError:false,
            userMobileError:false,
            userAddressError:false,
            order:'',
			payType:'0',

        },
        computed:{
            form:function(){
                if(this.Invoice == 0){
                    this.isPay = true;
					return false;
                }else{
                    this.submitOrder();
                    return true;
                }
            }
		},
		methods:{
            checkOrgName:function () {
				if(this.invoice.orgName == ''){
                    this.orgNameError = true;
				}else{
				    this.orgNameError = false;
				}
            },
            checkTaxId:function () {
                if(this.invoice.taxId == ''){
                    this.taxIdError = true;
				}else{
                    this.taxIdError = false;
				}
            },
            checkRegAddress:function () {
                if(this.invoice.regAddress == ''){
                    this.regAddressError = true;
				}else{
                    this.regAddressError = false;
				}
            },
            checkRegMobile:function () {
                if(this.invoice.regMobile == ''){
                    this.regMobileError = true;
				}else{
                    this.regMobileError = false;
                }
            },
            checkBank:function () {
                if(this.invoice.bank == ''){
                    this.bankError = true;
				}else{
                    this.bankError = false;
				}
            },
            checkBankAccount:function () {
                if(this.invoice.bankAccount == ''){
                    this.bankAccountError = true;
                }else{
                    this.bankAccountError = false;
				}
            },
            checkUserContacts:function () {
                if(this.invoice.userContacts == ''){
                    this.userContactsError = true;
				}else{
                    this.userContactsError = false;
				}
            },
            checkUserMobile:function () {
                if(this.invoice.userMobile == ''){
                    this.userMobileError = true;
				}else{
                    this.userMobileError = false;
				}
            },
            checkUserAddress:function () {
                if(this.invoice.userAddress == ''){
                    this.userAddressError = true;
				}else{
                    this.userAddressError = false;
				}
            },
			submitInvoice:function () {
				this.checkOrgName();
				this.checkTaxId();
				this.checkRegAddress();
				this.checkRegMobile();
				this.checkBank();
				this.checkBankAccount();
				this.checkUserContacts();
				this.checkUserMobile();
				this.checkUserAddress();
				this.invoice.orderSerial = $('#orderSerial').val();
				if(this.orgNameError == false && this.taxIdError == false && this.regAddressError == false && this.regMobileError == false && this.bankError == false && this.bankAccountError == false && this.userContactsError == false && this.userMobileError == false && this.userAddressError == false){
				    this.isPay = true;
				    var self = this;
				    console.log('可以发送发票信息到后台校验');//TODO
					$.ajax({
                        type:"post",
                        url:"/invoice/add",
                        async:true,
                        data:self.invoice,
                        datatype:'json',
						success:function(res){
							if(res.success){
							    alert(res.msg);
								self.order = res.data;
							}
                        }
                    });
				}
            },
			submitOrder:function () {
				if(this.invoice.orgName == '' || this.invoice.taxId == '' || this.invoice.regAddress == '' || this.invoice.regMobile == '' || this.invoice.bank == '' || this.invoice.bankAccount == "" || this.invoice.userContacts == '' || this.invoice.userMobile == '' || this.invoice.userAddress == ''){
					this.isPay = false;
				}
            },
			checkPayType:function () {
				if(this.payType == "0"){
					mb.mbShow = true;
					$('form').attr('target','_blank');
                }else{
                    mb.mbShow = false;
                    $('form').removeAttr('target');
				}
            }
		}
    });
	var mb = new Vue({
		el:'.mb',
		data:{
            mbShow:false
		},
	});
</script>
