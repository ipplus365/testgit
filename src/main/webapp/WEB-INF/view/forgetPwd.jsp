<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>忘记密码</title>
	<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
	<style type="text/css">
			.head{
				padding: 20px 0;
			    width: 1200px;
			    margin: auto;
			}
			.register{
				width: 850px;
				margin: 0 auto;
			}
			.register>h2>span{
			display: inline-block;
			width: 50%;
			color: #3fa9f5;
			text-align: center;
			line-height: 65px;
			font-size: 16px;
			border-bottom: 2px solid #dfe2e2;
			position: relative;
			cursor: pointer;
		}
		.register>h2>span>i{
			position: absolute;
			border: 9px solid #3fa9f5;
			border-top-color: transparent;
			border-left-color: transparent;
			border-right-color: transparent;
			bottom: 0;
			left: 50%;
			margin-left: -5px;
			display: none;
		}
		.register>h2>span.active>i{
			display: block;
		}
		.register>h2>span.active{
			border-bottom: 2px solid #3fa9f5;
		}
		.register table{
			width: 850px;
			/*margin: 126px auto;*/
			font-family: '黑体';
			font-size: 16px;
		}
		.register table td:first-child{
			text-align: right;
			height: 60px;
			width: 150px;
		}
		.register table td input{
			padding-left: 20px;
			width: 445px;
			height: 45px;
			border-style: none;
			border:1px solid #909090;
		}
		.register table td input.new{
			width: 285px;
			float: left;
			margin-right: 12px;
		}
		.register table td button{
			background: #3fa9f5;
			color: #fff;
			height: 48px;
			width: 150px;
			line-height: 48px;
			text-align: center;
			display: block;
			float:left;
			margin-right: 12px;
			border: none;
			outline: 0;
			cursor: pointer;
			
		}
		.register table td span{
			background: #909090;
			color: #fff;
			height: 48px;
			width: 150px;
			line-height: 48px;
			text-align: center;
			display: block;
			float:left;
			margin-right: 12px;
			cursor: pointer;
		}
		.register table td button.disabled{
			cursor: not-allowed;
			background: #909090;
		}
		.register table td.submit{
			color: #ee2222;
		}
		.register table td input[type=submit]{
			background: #3fa9f5;
			border: 0px;
			color: #fff;
			font-family: '黑体';
			font-size: 16px;
			cursor: pointer;
		}
		.register table td p{
			width: 445px;
			text-align: center;
			line-height: 14px;
		}
		.register table td a{
			color: #3fa9f5;
			text-decoration: none;
		}
		.register .email_form{
			display: none;
		}
	</style>
</head>
<body>
	<div class="head">
    	<a href="/product/buy"><img src="../../statics/images/logo-big.jpg"/></a>
    </div>
	<div class="register">
		<h2>
    		<span class="active">手机<i></i></span><span>邮箱<i></i></span>
    	</h2>
		<table class="mobile_form">
			<tr>
				<td> 手机：</td>
				<td>
					<input type="text" name="" placeholder="请输入你的手机号码" class="mobile">
				</td>
			</tr>
			<tr>
				<td>填写手机验证码：</td>
				<td><input type="text" name="" class="new mobileCode">
				<button class="getMobileCode">获取验证码</button>
				</td>
			</tr>	
			<tr>
				<td></td>
				<td class="mobileCodeTips"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td>
					<span class="yanzheng">
						<img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/>
					</span>
					<input type="text" name="" class="new mobileYzm">
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="submit"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="" value="提交" class="mobile_sub"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<p>已经拥有账户?<a href="/user/toLogin">登录</a></p>
				</td>
			</tr>
		</table>
		<table class="email_form">
			<tr>
				<td> 邮箱：</td>
				<td>
					<input type="text" name="" placeholder="请输入您的邮箱" class="email">
				</td>
			</tr>
			<tr>
				<td>填写邮箱验证码：</td>
				<td>
					<input class="new emailCode" type="text">
					<button class="getEmailCode">获取验证码</button>
				</td>
			</tr>	
			<tr>
				<td></td>
				<td class="emailCodeTips"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td>
					<span class="yanzheng">
						<img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/></span>
						<input type="text" name="" class="new emailYzm">
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="submit email_submit"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="" value="提交" class="email_sub"></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<p>已经拥有账户?<a href="/user/toLogin">登录</a></p>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
<script src="../../statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var requestUrl = '/';
	var per_mobile = false;
	var per_mobileCode = false;
	var per_captcha = false;
	var mobile_input = '';
	jQuery('body').on('click','.register h2 span',function(){
		jQuery('.register h2 span').removeClass('active');
		jQuery(this).addClass('active');
		jQuery('.register h2 span').find('i').hide();
		jQuery(this).find('i').show();
		if(jQuery(this).index() === 0){
			jQuery('.email_form').hide();
			jQuery('.mobile_form').show();
		}
		if(jQuery(this).index() === 1){
			jQuery('.email_form').show();
			jQuery('.mobile_form').hide();
		}
	});
	jQuery('.getMobileCode').click(function(){
		mobile_input = $('.mobile').val().trim();
		var mobileReg = /^1[0-9]{10}$/;
		//检测手机号码
		if(mobile_input == ''){
			jQuery('.mobileCodeTips').text('请输入您的手机号码');
			per_mobile = false;
		}else if(mobileReg.test(mobile_input) !== true || mobile_input.length < 11){
			jQuery('.mobileCodeTips').text('请输入正确的手机号码');
			per_mobile = false;
		}else{
			per_mobile = true;
		}
		if(per_mobile == true){
			$('.getMobileCode').attr('disabled',true);
			$('.getMobileCode').addClass('disabled');
			var timeNum = 59;
			$('.getMobileCode').text(timeNum + 's 重新发送')
			$('.getMobileCode').addClass('disabled');
			var int = setInterval(function(){
				timeNum--;
				$('.getMobileCode').text(timeNum + 's 重新发送')
				if(timeNum == 0){
					clearInterval(int);
					$('.getMobileCode').removeAttr('disabled');
					$('.getMobileCode').removeClass('disabled');
					$('.getMobileCode').text('获取验证码');
				}
			},1000);
			$.ajax({
				type:"get",
				url:requestUrl + "user/sendSmsForPws",
				dataType:'json',
				async:true,
				data:{phoneNum:mobile_input},
				success:function(res){
					if(res.success == true){
						jQuery('.mobileCodeTips').text('验证码已发出，请注意查收短信，如果没有收到，可以1分钟后重新发送');
						
					}else{
						jQuery('.mobileCodeTips').text(res.msg);
					}
				}	
			});
		}
	});
	jQuery('.mobile_sub').click(function(){
		if(jQuery('.mobileCode').val() == ''){
			per_mobileCode = false;
			jQuery('.submit').text('请填写手机验证码');
		}else{
			per_mobileCode = true;
		}
		if(jQuery('.mobileYzm').val() == ''){
			jQuery('.submit').text('请填写验证码');
			per_captcha = false;
		}else{
			per_captcha = true;
		}
		if(per_mobileCode == true && per_captcha == true){
			
			$.ajax({
				type:"get",
				url:requestUrl + "user/forgetPassword",
				dataType:'json',
				async:true,
				data:{account:$('.mobile').val(),type:'mobile',code:$('.mobileCode').val(),captcha:$('.mobileYzm').val()},
				success:function(res){
					if(res.success == true){
						//jQuery('.submit').text('重置链接已发出，请注意查收邮件，如果没有收到，你可以在10分钟后要求系统重新发送');
						window.location.href = '/user/setNewPwd?id=' + $('.mobile').val().trim();
						
					}else{
						jQuery('.submit').text(res.msg);
					}
				}	
			});
		}
	});
	//邮箱用户找回密码
	var email = false;
	jQuery('.getEmailCode').click(function(){
		var mobileReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
		//检测手机号码
		if($('.email').val().trim() == ''){
			jQuery('.emailCodeTips').text('请输入您的邮箱');
			email = false;
		}else if(mobileReg.test($('.email').val().trim()) !== true || $('.email').val().trim().length < 11){
			jQuery('.emailCodeTips').text('请输入正确的邮箱格式');
			email = false;
		}else{
			email = true;
		}
		if(email == true){
			$('.getEmailCode').attr('disabled',true);
			$('.getEmailCode').addClass('disabled');
			var timeNum = 59;
			$('.getEmailCode').text(timeNum + 's 重新发送')
			$('.getEmailCode').addClass('disabled');
			var int = setInterval(function(){
				timeNum--;
				$('.getEmailCode').text(timeNum + 's 重新发送')
				if(timeNum == 0){
					clearInterval(int);
					$('.getEmailCode').removeAttr('disabled');
					$('.getEmailCode').removeClass('disabled');
					$('.getEmailCode').text('获取验证码');
				}
			},1000);
			$.ajax({
				type:"get",
				url:requestUrl + "user/sendCode",
				dataType:'json',
				async:true,
				data:{email:$('.email').val()},
				success:function(res){
					if(res.success == true){
						jQuery('.emailCodeTips').text('验证码已发出，请注意查收邮件，如果没有收到，可以1分钟后重新发送');
					}else{
						jQuery('.emailCodeTips').text(res.msg);
					}
				}	
			});
		}
	});
	var emailCode = false;
	var email_captcha = false;
	jQuery('.email_sub').click(function(){
		if(jQuery('.emailCode').val() == ''){
			emailCode = false;
			jQuery('.email_submit').text('请填写邮箱验证码');
		}else{
			emailCode = true;
		}
		if(jQuery('.emailYzm').val() == ''){
			jQuery('.email_submit').text('请填写验证码');
			email_captcha = false;
		}else{
			email_captcha = true;
		}
		if(emailCode == true && email_captcha == true){
//			console.log($('.emailYzm').val());
			$.ajax({
				type:"get",
				url:requestUrl + "user/forgetPassword",
				dataType:'json',
				async:true,
				data:{account:$('.email').val(),type:'email',code:$('.emailCode').val(),captcha:$('.emailYzm').val()},
				success:function(res){
					if(res.success == true){
						jQuery('.submit').text('重置链接已发出，请注意查收邮件，如果没有收到，你可以在10分钟后要求系统重新发送');
					}else{
						jQuery('.submit').text(res.msg);
					}
				}	
			});
		}
	});
</script>