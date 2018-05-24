<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
    <meta name="keywords" content="IP定位_埃文科技_IP实时定位_ip区县库_ip免费库_全球ip库_高精度ip定位_埃文商城">
    <meta name="description" content="埃文商城(mall.ipplus360.com) -埃文科技(www.ipplus360.com)">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
	<link rel="stylesheet" type="text/css" href="/statics/css/login.css"/>
	<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
</head>
<body>
<input type="hidden" name="returnurl" id="returnurl" value="${returnurl}">
	   <div class="box">
	   		<div class="logo">
	   			<a href="/"><img src="/statics/images/logo-small.jpg" alt="埃文科技"/></a>
	   			<!--<span class="welcome">欢迎登陆</span>-->
	   		</div>
	   </div>
	   <div id="content">
	   		<div id="wrap">
				<div class="box">
					<div id="box-bg">
						<div class="log-form">
							<div class="log-bar">
								账户登录
							</div>
							<div class="log-box">
								<div class="msg-wrap">
									<div class="msg-wran" v-show="checked"  v-cloak>
										<b></b>
										公共场所不建议记住密码，以防账号丢失
									</div>
									<div class="msg-error" v-show="noPwd" v-text="err_msg" v-cloak>
										<b></b>
										{{err_msg}}
									</div>
								</div>
								<div class="form">
									<form id="formlogin" method="post" onsubmit="return false">
										<div class="item item-fore1">
											<label for="loginname" class="login-label name-label"></label>
											<input type="text" v-model="loginname" id="loginname" class="itxt" placeholder="请输入邮箱"/>
											<span class="clear-btn" v-on:click="clearUser"></span>
										</div>
										<div class="item item-fore2">
											<label for="loginpwd" class="login-label pwd-label"></label>
											<input type="password" v-model="loginpwd" id="loginpwd" class="itxt" placeholder="请输入密码"/>
											<span class="clear-btn" v-on:click="clearPwd"></span>
										</div>
										<div id="sub">
											<div class="item item-fore1 yz">
												<label for="captcha" class="yz-label">验证码</label>
												<input type="text" v-model="captcha" id="captcha" class="yz-input" />
											</div>
											<img class="yz-img" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"/>
										</div>
										<div class="item item-fore4">
											<div class="safe">
												<%--<span>--%>
													<%--<input id="autoLogin" name="chkRememberMe" type="checkbox" class="jdcheckbox" tabindex="3" v-model="checked" >--%>
													<%--<label for="autoLogin">记住密码</label>--%>
												<%--</span>--%>
												<span class="a">
													<a href="/user/forget" class="" target="_blank">忘记密码</a>
												</span>
											</div>
										</div>
										<div class="item item-fore5">
											<div class="log-btn" v-on:click="submit()">
												<a class="btn-img">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
											</div>
										</div>
										<div class="item item-fore6">
											<a href="<c:url value="/user/reg/"/>" target="_blank">没有账号？立即注册</a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
	   		</div>	 
	   </div>
	<div id="form-footer" class="footer w">
    <div class="links">
        <a rel="nofollow" target="_blank" href="http://www.ipplus360.com/">关于我们</a>
        <a rel="nofollow" target="_blank" href="http://www.ipplus360.com/about/">联系我们</a>
        <a rel="nofollow" target="_blank" href="http://www.ipplus360.com/about/join/">人才招聘</a>
    </div>
    <div class="copyright">
        Copyright&copy;2012-2017&nbsp;&nbsp;郑州埃文计算机科技有限公司&nbsp;版权所有
    </div>
    </div>
</body>
</html>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script type="text/javascript">
	 var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
	 var form = new Vue({
	 		el:'.log-form',
	 	  	data:{
				checked:false,
				noPwd:false,
				err_msg:'',
				loginname:'',
				loginpwd:'',
                captcha:''
			},
		 	methods:{
	 		setCookie:function (name,pwd,date) {
                var exdate=new Date();
                exdate.setDate( exdate.getDate() + date);
                document.cookie=name+ "=" + escape(pwd) + ((date==null) ? "" : ";expires="+ exdate.toGMTString());
            },
	 	  	submit:function(){
	 	  		this.noPwd = false;
	 	  		this.err_msg = '';
	 	  		if(this.loginname == ''){
	 	  			this.noPwd = true;
	 	  			this.err_msg = '请输入账号';
	 	  		}else{
	 	  			 if(reg.test(this.loginname) != true){
	 	  			 	  this.noPwd = true;
	 	  					this.err_msg = '请输入正确的邮箱';
	 	  			 }else{
	 	  			 	   this.noPwd = false;
	 	  			 }
	 	  		}
	 	  		if(this.loginpwd == ''){
	 	  			this.noPwd = true;
	 	  			this.err_msg = '请输入密码';
	 	  		}
	 	  		if(this.captcha == '' && this.loginpwd == ''){
	 	  			this.noPwd = true;
	 	  			this.err_msg = '请输入密码和验证码';
	 	  		}
	 	  		if(this.captcha == ''){
	 	  			this.noPwd = true;
	 	  			this.err_msg = '请输入验证码';
	 	  		}
	 	  		if(this.loginname == '' && this.loginpwd == ''){
	 	  			this.noPwd = true;
	 	  			this.err_msg = '请输入账号和密码';
	 	  		}
	 	  		if(this.noPwd == false) {

//                    if(this.checked){
//                        this.setCookie('user', this.loginname, 7);
//                        this.setCookie('pwd', this.loginpwd, 7);
//                    }

					var self = this;
                    var data = {email: this.loginname, password: this.loginpwd, captcha: this.captcha, returnurl: $('#returnurl').val()};
                    $.post('<c:url value="/user/login"/>', data, function (data) {
                        if (data.success) {
							window.location.href = data.data;
                        } else {
                            self.noPwd = true;
                            self.err_msg = data.msg;
                        }
                    });
                }
	 	  	},
	 	  	clearUser:function(){
	 	  		this.loginname = '';
	 	  	},
	 	  	clearPwd:function(){
	 	  		this.loginpwd = '';
	 	  	}
	 	  }
	 	 });
		//键盘enter事件
		$(document).keydown(function(event){
			if(event.keyCode == 13){
		 		form.submit();
		 	}
		});
//     function getCookie(c_name){
//         if(document.cookie.length>0){
//             c_start=document.cookie.indexOf(c_name + "=");
//             if (c_start != -1){
//                 c_start=c_start + c_name.length+1;
//                 c_end=document.cookie.indexOf(";",c_start);
//                 if(c_end==-1) {
//                     c_end = document.cookie.length;
//                 }
//                 return unescape(document.cookie.substring(c_start,c_end));
//             }
//         }
//         return "";
//     };
//     function checkCookie() {
//         username = getCookie('user');
//         pwd = getCookie('pwd');
//         if (username != "" && pwd != "") {
//             form.loginname = username;
//             form.loginpwd = pwd;
//             form.checked = true;
//         }
//         else {
//             form.checked = false;
//         }
//     }
//     checkCookie();
</script>