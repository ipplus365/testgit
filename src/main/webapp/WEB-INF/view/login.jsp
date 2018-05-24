<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="../../statics/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="../../statics/css/login.css"/>
        <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    </head>
    <body>
    	<div class="head">
    		<a href="/product/buy"><img src="../../statics/images/logo-big.jpg"/></a>
    	</div>
    	<!--登录模块-->
    	<div id="pop">
    		<h2>
    			<span style="color:#7e7e7e;font-size: 22px;">登录</span>
    			<span class="two">还没有账号？点击<a id="register" href="/user/toReg">立即注册</a></span>
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
    		<p style=""><a href="/user/forget">忘记密码</a></p>
    	</div>
 	</body>
</html>
<script src="../../statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../../statics/js/loginPage.js" type="text/javascript" charset="utf-8"></script>