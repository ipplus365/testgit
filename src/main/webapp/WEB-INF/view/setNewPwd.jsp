<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新密码设置</title>
        <link rel="stylesheet" type="text/css" href="../../statics/css/reset.css"/>
        <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
        <style type="text/css">
        	.head{
				padding: 20px 0;
			    width: 1200px;
			    margin: auto ;
			}
			 .content {
			    width: 400px;
			    margin: 50px auto 0;
			}
			.content .form-item {
			    position: relative;
			    border: solid 1px #ddd;
			    width: 398px;
			    height: 52px;
			    z-index: 0;
			    margin-bottom: 20px;
			}
			 .content .form-item label {
			    float: left;
			    width: 90px;
			    height: 52px;
			    line-height: 52px;
			    padding-left: 20px;
			    font-size: 14px;
			    color: #363636;
			}
			.content .form-item input {
			    border: 0 none;
			    font-size: 14px;
			    width: 300px;
			    padding: 15px 0;
			    outline: none;
			    box-sizing: border-box;
			}
			 .content>button {
			    cursor: pointer;
			    display: block;
			    outline: none;
			    border: none;
			    background: #0e9aff;
			    color: #fff;
			    width: 100%;
			    line-height: 50px;
			    margin-top: 10px;
			    font-size: 18px;
			}
			.content .error{
				height: 26px;
				line-height: 26px;
				text-align: left;
				font-size: 14px;
				text-indent: 0;
				color: #EE2222;
			}
			.content .success{
				height: 26px;
				line-height: 26px;
				text-align: left;
				font-size: 14px;
				text-indent: 0;
				color: rgb(122,189,24);
			}
        </style>
    </head>
    <body>
    	<div class="head">
    		<a href="/product/buy"><img src="../../statics/images/logo-big.jpg"/></a>
    	</div>
    	<div class="content">
			<div class="form-item">
				<label for="oldPwd">新密码</label>
				<input type="password" name="" id="new_password" value="" placeholder="建议至少使用两种字符组合"/>
			</div>
			<div class="form-item" style="margin-bottom: 0;">
				<label for="oldPwd">重复密码</label>
				<input type="password" name="" id="confirm_passsord" value="" placeholder="请再次输入您的新密码"/>
			</div>
			<p class="error"></p>
			<button id="password_commit">提交</button>
		</div>
    </body>
</html>
<script src="../../statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function GetQueryString(name){
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}
	var mobile = GetQueryString('id');
	var email =  GetQueryString('user');
//	console.log(mobile);
	var fPwd = false;
	var sPwd = false;
	$('#password_commit').click(function(){
		var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\~\!@#\$\%\^\&\*]{8,16}$/;
		if($('#new_password').val().trim() == ''){
			fPwd = false;
			jQuery('.error').text('请输入您的新密码');
		}else if(reg.test($('#new_password').val().trim()) == false){
			fPwd = false;
			jQuery('.error').text('请输入合法的密码格式');
		}else{
			fPwd = true;
		}
		if($('#new_password').val().trim() !== '' && fPwd == true){
			if($('#confirm_passsord').val().trim() == ''){
				sPwd = false;
				jQuery('.error').text('请再次输入您的新密码');
			}else if($('#confirm_passsord').val().trim() !== $('#new_password').val().trim()){
				sPwd = false;
				jQuery('.error').text('密码不一致');
			}else{
				sPwd = true;
			}
		}
		if(fPwd == true && sPwd == true){
//			console.log(1111111111111);
			if(mobile == '' || mobile == null){
				$.ajax({
					type:"get",
					url:"/user/resetPwd",
					async:true,
					data:{email:email,newPwd:$('#new_password').val().trim(),confirmPwd:$('#confirm_passsord').val().trim()},
					success:function(res){
						if(res.success){
							jQuery('.content p').removeClass('error');
							jQuery('.content p').addClass('success');
							jQuery('.content p').text('设置密码成功，即将跳转登录页面');
							setTimeout(function(){
								window.location.href = '/user/toLogin';
							},2000);
						}else{
							alert(res.msg);
						}
					}
				});
			}else{
				$.ajax({
					type:"get",
					url:"/user/resetPwd",
					async:true,
					data:{mobile:mobile,newPwd:$('#new_password').val().trim(),confirmPwd:$('#confirm_passsord').val().trim()},
					success:function(res){
						if(res.success){
							jQuery('.content p').removeClass('error');
							jQuery('.content p').addClass('success');
							jQuery('.content p').text('设置密码成功，即将跳转登录页面');
							setTimeout(function(){
								window.location.href = '/user/toLogin';
							},2000);
						}else{
							alert(res.msg);
						}
					}
				});
				
			}
		}
	});
</script>