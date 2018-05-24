<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="../../statics/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="../../statics/css/reg.css"/>
        <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    </head>
    <body>
    	<div class="head">
    		<a href="/product/buy"><img src="../../statics/images/logo-big.jpg"/></a>
    	</div>
    	<!--注册模块-->
    	<div id="reg">
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
    		<h4>已经拥有账户<a href="/user/toLogin">登录</a></h4>
    	</div>
 	</body>
</html>
<script src="../../statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../statics/js/reg.js" type="text/javascript" charset="utf-8"></script>