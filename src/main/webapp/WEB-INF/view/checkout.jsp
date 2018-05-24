<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>结算</title>
		<link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/iconfont.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/nav.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/footer.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/confirm.css"/>
		<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
	</head>
	<body>
		<!--导航栏开始-->
		<div class="nav">
			<div class="nav_inner">
				<div class="nav_inner_left">
					<li>
						<a href="http://www.ipplus360.com/">首页</a>
					</li>
					<li>
						<a href="javascript:void(0)">产品中心</a>
						<div class="list">
							<a href="http://www.ipplus360.com/pros/iph/">IP问问-高精准</a>
							<a href="http://www.ipplus360.com/pros/ipm/">IP问问-区县级</a>
							<a href="http://www.ipplus360.com/pros/ips/">IP Scene(应用场景)</a>
						</div>
					</li>
					<li>
						<a href="javascript:void(0)">应用领域</a>
						<div class="list">
							<a href="http://www.ipplus360.com/apparea/ad/">互联网广告投放</a>
							<a href="http://www.ipplus360.com/apparea/anti-cheating/">互联网广告反作弊</a>
							<a href="http://www.ipplus360.com/apparea/anti-fraud/">反欺诈风控</a>
							<a href="http://www.ipplus360.com/apparea/bigdata/">位置大数据分析</a>
						</div>
					</li>
					<li>
						<a href="javascript:void(0)">技术支持</a>
						<div class="list">
							<a href="http://www.ipplus360.com/tech/faq/">常见问题</a>
							<a href="http://www.ipplus360.com/tech/baike/">技术文档</a>
							<a href="http://www.ipplus360.com/tech/api/">API实例</a>
						</div>
					</li>
					<li>
						<a href="javascript:void(0)">公司介绍</a>
						<div class="list">
							<a href="http://www.ipplus360.com/about/us/">埃文介绍</a>
							<a href="http://www.ipplus360.com/about/join/">加入我们</a>
							<a href="http://www.ipplus360.com/news/">新闻资讯</a>
						</div>
					</li>
					<li>
						<a href="javascript:;">实用工具<img src="/statics/images/search-red.png"/></a>
						<div class="list">
							<a href="http://www.ipplus360.com/ip/">IP查询</a>
							<a href="http://www.ipplus360.com/tools/">IP Tools</a>
						</div>
					</li>
				</div>
				<div class="nav_inner_right">
					<li>
						<a href="http://www.ipplus360.com/tech/faq/">帮助与支持</a>
					</li>
					<li class="login_box">
						<!--<a href="javascript:void(0)" id="login">登录</a>-->
					</li>
				</div>
			</div>
		</div>
		<!--导航栏结束-->
		<div class="main">
			<form class="inner" action="/alipay/online" method="get">
				<h2>核对订单</h2>
				<input type="hidden" id="isInvoice" value="0" />
				<input type="hidden" name="orderSerial" id="orderSerial" value="" />
				<table  cellspacing="" cellpadding="0" class="orderList">
					<thead>
						<tr>
							<th>产品服务</th>
							<th>产品规格</th>
							<th>计费单价</th>
							<th>购买时长</th>
							<th>购买数量</th>
							<th>订单金额</th>
							<th>支付金额</th>
						</tr>
					</thead>
					<tbody>
						<!--<tr>
							<td>IP问问-高精准</td>
							<td>10万</td>
							<td>¥99.00/10万次</td>
							<td>6个月</td>
							<td>2个</td>
							<td>¥198.00</td>
							<td>¥<em>198.00</em></td>
						</tr>
						<tr>
							<td>IP问问-高精准</td>
							<td>10万</td>
							<td>¥99.00/10万次</td>
							<td>6个月</td>
							<td>2个</td>
							<td>¥198.00</td>
							<td>¥<em>198.00</em></td>
						</tr>-->
					</tbody>
				</table>
				<!--<p>系统会根据订单对应的产品服务、订单金额匹配可用的代金券、一笔订单只能使用一张。</p>
				<div class="certificate">
					<input type="checkbox" name="" id="" value="" disabled/>
					<label for="">使用代金券</label>
					<div class="select">
						<p>该笔订单无可用的代金券</p>
						<i class="iconfont">&#xe62d;</i>
						<ul>
							<p>暂无券可用</p>
							<li>xxxxxxxx</li>
							<li>xxxxxxxxxxxxxxxx</li>
						</ul>
					</div>
					<a href="">？</a>
				</div>-->
				<div class="invoice">
					发票信息
				</div>
				<div class="invoice_choice">
					<input type="radio" name="a" id="a" value="0" checked=""/><label for="a">不开发票</label>
					<input type="radio" name="a" id="b" value="1" /><label for="b">增值税发票</label>
				</div>
				<div class="invoice-form">
					<div style="display: block;margin-left: 10px;" class="clearfix hide" id="special-form">
						<div class="pull-form">
							<div id="vat_companyName_div" class="list">
								<span class="label"><em>*</em>单位名称：</span>
								<div class="field">
									<input type="text" class="textbox orgName" value="" id="vat_companyName">
									<span class="error orgNameError" >
										<i class="i-error"></i>
										请填写您的单位名称
									</span>
								</div>
							</div>
							<div id="vat_code_div" class="list">
								<span class="label"><em>*</em>纳税人识别号：</span>
								<div class="field">
									<input type="text" class="textbox taxId" value="" id="vat_code">
									<span class="error taxIdError">
										<i class="i-error"></i>
										请填写您的纳税人识别号
									</span>
								</div>
							</div>
							<div id="" class="list">
								<span class="label"><em>*</em>注册地址：</span>
								<div class="field">
									<input type="text" class="textbox regAddress" id="vat_address" v-model="invoice.regAddress">
									<span class="error regAddressError">
										<i class="i-error"></i>
											请填写您注册地址
									</span>
								</div>
							</div>
							<div id="vat_phone_div" class="list">
								<span class="label"><em>*</em>注册电话：</span>
								<div class="field">
									<input type="text" class="textbox regMobile"  id="vat_phone" v-model="invoice.regMobile">
									<span class="error regMobileError">
										<i class="i-error"></i>
										请填写您的注册电话
									</span>
								</div>
							</div>
							<div id="vat_bankName_div" class="list">
								<span class="label"><em>*</em>开户银行：</span>
								<div class="field">
									<input type="text" class="textbox bank" value="" id="vat_bankName">
									<span class="error bankError">
										<i class="i-error"></i>
											请填写您的开户银行
									</span>
								</div>
							</div>
							<div id="vat_bankAccount_div" class="list">
								<span class="label"><em>*</em>银行帐户：</span>
									<div class="field">
										<input type="text" class="textbox bankAccount" value="" id="vat_bankAccount">
										<span class="error bankAccountError">
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
									<input type="text" maxlength="20" value="" id="invoice_consignee_name" class="textbox userContacts">
									<span class="error userContactsError">
										<i class="i-error"></i>
											请填写您的姓名
									</span>
								</div>
							</div>
							<div id="vat_call_div" class="list">
								<span class="label"><em>*</em>收票人手机：</span>
								<div class="field">
									<div class="phone">
										<input type="text" maxlength="11" value="" id="invoice_consignee_mobile" class="textbox userMobile">
										<span class="error userMobileError">
											<i class="i-error"></i>
												请正确填写您的手机号码
										</span>
									</div>
								</div>
							</div>
							<div id="vat_address_div" class="list full-address">
								<span class="label"><em>*</em>详细地址：</span>
								<div class="field">
									<input type="text" maxlength="50" value="" id="invoice_consignee_address" class="textbox userAddress">
									<span class="error userAddressError">
										<i class="i-error"></i>
											请填写您的详细地址
									</span>
								</div>
							</div>
							<div class="pull-tips">
								<ul class="invoice-tips">
									<li class="fore1">
										<span style="color:rgb(255, 102, 0);font-size: 12px;">
											开发票须知：<br />
											1、默认消费满2000元才可以开具发票<br />
											2、请按照要求填写开具发票所需信息<br />
											3、如果填写信息有误请及时联系客服修改信息<br />
											4、发票默认为增值税普通发票，如有其它需要请联系客服<br />
											客服QQ：2415968308；客服电话：400-0371-911
										</span>
									</li>
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
				<div class="money">
					<p>支付金额：<span>¥<em class="total">0.00</em></span></p>
				</div>
				<div class="tips">
					提示：1.由于软件产品的特殊性，埃文软件产品与服务经售出后，不再提供退款服务。<br />
					2.如您需要开具增值税专票，请提供订单号，并联系客服人员。
					<p>
						<input type="checkbox" name="" id="" value="" checked disabled/>
						<label for="">同意<a href="/order/protocol" target="_blank">《埃文科技服务条款》</a></label>
					</p>
				</div>
				<div class="pay clearfix">
					<p>
						<!--<a href="">修改订单>></a>-->
						<input type="button" value="立即付款" class="payButton"/>
						
					</p>
					<div id="submit_check_info_message" class="submit-check-info">
                       	<span>您需先保存<span id="save_info" style="color:#005EA7;">收票人地址</span>，再同意协议并付款></span>
                    </div>
				</div>	
				</form>
		</div>
		<div class="footer">
    		<div class="footer-inner">
    			<div class="footer-top">
    				<!--<a href="https://mall.ipplus360.com/" target="_blank">注册有礼</a>
    				<a href="https://mall.ipplus360.com/" target="_blank" class="two">免费试用</a>-->
    			</div>
    			<div class="footer-mid">
				    <ul>
				   		<li class="first">产品中心</li>
				   		<li class="other two"><a href="http://www.ipplus360.com/pros/iph" >IP问问-高精准</a></li>
                  		<li class="other two"><a href="http://www.ipplus360.com/pros/ipm" >IP问问-区县级</a></li>
                  		<li class="other two"><a href="http://www.ipplus360.com/pros/ips" >IP Scene(应用场景)</a></li>
                	</ul>
                	<ul>
	                	<li class="first">应用领域</li>
	                	<li class="other two"><a href="http://www.ipplus360.com/apparea/ad" >互联网广告投放</a></li>
	     				<li class="other two"><a href="http://www.ipplus360.com/apparea/anti-cheating" >互联网广告反作弊</a></li>
	                	<li class="other two"><a href="http://www.ipplus360.com/apparea/anti-fraud" >反欺诈风控</a></li>
	                  	<li class="other two"><a href="http://www.ipplus360.com/apparea/bigdata" >位置大数据分析</a></li>
                	</ul>
                	<ul>
						<li class="first">技术支持</li>
		  				<li class="other two"><a href="http://www.ipplus360.com/tech/faq" >常见问题</a></li>
		                <li class="other two"><a href="http://www.ipplus360.com/tech/baike" >技术文档</a></li>
		                <li class="other two"><a href="http://www.ipplus360.com/tech/api" >API示例</a></li>
                	</ul>
                	<ul>
	                	<li class="first">公司介绍</li>
	                	<li class="other two"><a href="http://www.ipplus360.com/about/us" >埃文介绍</a></li>
	                 	<li class="other two"><a href="http://www.ipplus360.com/about/join" >加入我们</a></li>
	                 	<li class="other two"><a href="http://www.ipplus360.com/news" >新闻资讯</a></li>
                	</ul>
                	<ul>
	                	<li class="first">实用工具</li>
	                	<li class="other two"><a href="http://www.ipplus360.com/ip/" >IP查询</a></li>
	                  	<li class="other two"><a href="http://www.ipplus360.com/tools/" >IP Tools</a></li>
                	</ul>
					<ul class="five">
    					<li class="first">联系我们</li>
    					<li class="other two"><a href="javascript:;"><i class="iconfont">&#xe71b;</i>contacts@ipplus360.com</a></li>
    					<li class="other"><a href="javascript:;"><i class="iconfont">&#xe7dd;</i>400-0371-911</a></li>
    					<li class="other"><a href="javascript:;"><i class="iconfont">&#xe637;</i>@埃文科技IPPLUS</a></li>
    					<li class="other"><a href="javascript:;"><i class="iconfont">&#xe63a;</i>2415968308</a></li>
    					<li class="last"><a href="javascript:;">（工作日 08：30-18：00）</a></li>
    				</ul>
    				<ul class="six">
    					<i class="iconfont">&#xe659;</i>
    					<img src="/statics/images/weixin.jpg"/>
    				</ul>
    			</div>
    			<div class="footer-bot">
    				© 2012-2017 ipplus360.com , All Rights Reserved.<a href="http://www.miitbeian.gov.cn/" target="_blank" style="color:#808488;">豫ICP备12022601号-1</a>| 豫公网安备 11010802021761
    			</div>
    		</div>
    	</div>
    	<!--登录模块-->
    	<div id="pop" style="display: none;">
    		<h2>
    			<span style="color:#7e7e7e;font-size: 22px;">登录</span>
    			<span class="two">还没有账号？点击<a id="register" href="javascript:;">立即注册</a></span>
    		</h2>
    		<input type="text" name="" id="name" value="" placeholder="账户"/>
    		<input type="password" name="" id="pwd" value="" placeholder="密码"/>
    		
    		<div class="yzm">
    			<span> 
    				<img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/>
    			</span>
    			<input type="text" name="" id="yzm" value="" placeholder="请输入验证码"/>
    		</div>
    		<p class="regError login_error">
    			<!--<i class="i-error"></i>
    			账号密码错误-->
    		</p>
    		<button class="login">立即登录</button>
    		<p style=""><a href="">忘记密码</a></p>
    	</div>
    	<!--注册模块-->
    	<div id="reg" style="display: none;">
    		<h2>
    			<span class="active">个人账户<i></i></span><span>企业账户<i></i></span>
    		</h2>
    		<div class="box">
    			<div class="person">
    				<p>个人注册：可免费申请IP问问区县级别数据库 </p>
    				<!--<li class="mt32">
    					<span>昵称</span>
    					<input type="text" placeholder="设置后不可更改。最长五个汉字或十个英文" class="per_name"/>
    				</li>
    				<p class="error per_name_error">
    					<i class="i-error"></i>
    					请您输入昵称
    				</p>-->
    				<li class="mt32">
    					<span>注册邮箱</span>
    					<input type="text" placeholder="请输入个人邮箱账号" class="per_email"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError per_email_error">
    					<!--<i class="i-error"></i>
    					请您输入邮箱-->
    				</p>
    				<li class="">
    					<span>登录密码</span>
    					<input type="password" placeholder="6-20位，不能出现空格" id="regPwd" class="per_pwd"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError per_pwd_error">
    					<!--<i class="i-error"></i>
    					请您输入密码-->
    				</p>
    				<li class="">
    					<span>重复密码</span>
    					<input type="password" id="repeatPwd" placeholder="重复密码" class="per_repeatPwd"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError per_repeatPwd_error">
    					<!--<i class="i-error"></i>
    					请您再次输入密码-->
    				</p>
    				<li>
    					<span>手机号码</span>
    					<input type="tel" id="repeatPwd" placeholder="请输入有效的手机号码" class="per_mobile"/>
    					<i class="i-success"></i>
    				</li>
    				<li class="spec">
    					<button class="getMobile">获取验证码</button>
    					<input type="text" name="" id="" value="" placeholder="请输入验证码" class="per_mobileCode"/>
    				</li>
    				<p class="regError per_mobileCode_error">
    					<!--<i class="i-error"></i>
    					手机验证码错误-->
    				</p>
    				<li class="mobileyzm">
    					<span><img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/></span>
    					<input type="text" placeholder="请输入验证码" class="per_captcha"/>
    				</li>
    				<p class="regError per_captcha_error">
    					<!--<i class="i-error"></i>
    					验证码错误-->
    				</p>
    				<button class="per_register">注册</button>
    			</div>
    			<div class="company">
    				<p>企业注册：专享IP Scene（应用场景）服务</p>
    				<!--<li>
    					<span>昵称</span>
    					<input type="text" placeholder="设置后不可更改。最长五个汉字或十个英文"/>
    				</li>-->
    				<li class="mt32">
    					<span>注册邮箱</span>
    					<input type="text" placeholder="请输入个人邮箱账号" class="com_email"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError com_email_error">
    					
    				</p>
    				<li>
    					<span>登录密码</span>
    					<input type="password" placeholder="6-20位，不能出现空格" id="regPwdCom" class="com_pwd"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError com_pwd_error">
    					
    				</p>
    				<li>
    					<span>重复密码</span>
    					<input type="password" id="repeatPwdCom" placeholder="重复密码" class="com_repeatPwd"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError com_repeatPwd_error">
    					
    				</p>
    				<li>
    					<span>企业名称</span>
    					<input type="text"  placeholder="请输入公司名称" class="com_orgname"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError com_orgname_error">
    					
    				</p>
    				<li>
    					<span>联系人</span>
    					<input type="text"  placeholder="请输入联系人" class="com_contact"/>
    					<i class="i-success"></i>
    				</li>
    				<p class="regError com_contact_error">
    					
    				</p>
    				<li>
    					<span>手机号码</span>
    					<input type="tel"  placeholder="请输入有效的手机号码" class="com_mobile"/>
    					<i class="i-success"></i>
    				</li>
    				<li class="spec">
    					<button class="getComMobile">获取验证码</button>
    					<input type="text" name="" id="" value="" placeholder="请输入验证码" class="com_mobileCode"/>
    				</li>
    				<p class="regError com_mobileCode_error">
    					
    				</p>
    				<li class="shoujiyzm">
    					<span><img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/></span>
    					<input type="text" placeholder="请输入验证码" class="com_captcha"/>
    				</li>
    				<p class="regError com_captcha_error">
    					
    				</p>
    				<button class="com_register">注册</button>
    			</div>
    		</div>
    		<h3>注册即表示同意我们的<a href="/user/protocol" target="_blank">服务条款</a></h3>
    		<h4>已经拥有账户<a href="javascript:;">登录</a></h4>
    	</div>
	</body>
</html>
<script src="/statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/placeholder.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/confirm.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/login.js" type="text/javascript" charset="utf-8"></script>


