<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>忘记密码</title>
        <link rel="stylesheet" type="text/css" href="/statics/css/common.css"/>
        <link rel="stylesheet" type="text/css" href="/statics/css/forgetPwd.css"/>
        <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
        <style type="text/css">
        	.wrap{
        		margin: 30px auto;
        	}
        	#sflex03 {
 			   width: 480px;
 			   height: 30px;
			}
			.form{
				margin-left: 360px;
			}
			.safe-icon-box {
			    margin-left: 495px;
			}
            v-cloak{
                display: none;
            }
	    </style>
    </head>
    <body class="bg-white">
    	<!--顶部用户信息栏-->
        <c:import url="layout/header.jsp"/>
        <div class="mod-main mod-comm wrap mt20" v-cloak>
            <div class="mc">
                <div id="sflex03" class="stepflex">
                    <dl class="first" v-bind:class="[ firstSuccess ? 'doing' : '' ]">
                        <dt class="s-num">1</dt>
                        <dd class="s-text">验证身份<s></s><b></b></dd>
                    </dl>
                    <dl class="normal" v-bind:class="[ secondSuccess ? 'doing' : '' ]">
                        <dt class="s-num">2</dt>
                        <dd class="s-text">设置新密码<s></s><b></b></dd>
                    </dl>
                    <dl class="last" v-bind:class="[ lastSuccess ? 'doing' : '' ]">
                        <dt class="s-num">&nbsp;</dt>
                        <dd class="s-text">完成<s></s><b></b></dd>
                    </dl>
                </div>
                <div class="form" v-show="firstSuccess">
                    <div class="msg-error" style="padding-left: 150px">{{errMsg}}</div>
                    <div class="clr"></div>
                    <div class="item">
                        <span class="label">邮箱：</span>
                        <div class="fl">
                            <input class="itxt" tabindex="1" type="text" v-model="email" v-on:blur="checkEmail">
                            <div class="msg-error">{{emailErrMsg}}</div>
                            <div class="clr"></div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="item">
                        <span class="label">请填写邮箱校验码：</span>
                        <div class="fl">
                            <input class="itxt" tabindex="1" id="code" type="text" v-model="yz" v-on:blur="checkYz">
                            <button href="javascript:void(0)"  id="sendMobileCode" class="ml10" @click="subYz" v-bind:class="[isGet ? 'btn-14' : 'btn-10']" v-bind:disabled="isGet"><s></s>获取邮箱校验码</button>
                            <div class="clr"></div>
                            <div id="countDown" class="msg-text" v-show="isGet">校验码已发出，请注意查收邮件，如果没有收到，你可以在<strong class="ftx-01">10</strong>分钟后要求系统重新发送</div>
                            <div class="msg-error" id="sendCode_error" >{{yzErrorMsg}}</div>
                            <div id="code_error" class="msg-error" style="display:none"></div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="item">
                        <span class="label">验证码：</span>
                        <div class="fl">
                            <input class="itxt" tabindex="2" v-model="authCode" type="text" v-on:blur="checkAuthCode">
                            <label><img id="JD_Verification1" class="ml10" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()" alt="验证码" style="cursor:pointer;width:100px;height:26px;"></label>
                            <div class="clr"></div>
                            <div class="msg-error">{{authCodeErrMsg}}</div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="item">
                        <span class="label">&nbsp;</span>
                        <div class="fl">
                            <a class="btn btn-5" href="javascript:void(0);" @click="submitFirst">提交</a>
                        </div>
                        <div class="clr"></div>
                    </div>
                </div>
                <div class="form" v-show="secondSuccess">
                    <%--<div class="msg-error" style="padding-left: 150px">{{errMsg}}</div>--%>
                    <div class="item">
                        <span class="label"><em>*</em>新的登录密码：</span>
                        <div class="fl">
                            <input type="password"  id="password" class="itxt" v-model="newPwd" v-on:blur="checkPwd"/>
                            <div class="clr"></div>
                            <div class="msg-error">{{newPwdMsg}}</div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="item">
                        <span class="label"><em>*</em>请再输入一次密码：</span>
                        <div class="fl">
                            <input type="password" class="itxt"  id="rePassword" v-model="newPwdRe" v-on:blur="checkPwdRe"/>
                            <div class="clr"></div>
                            <div class="msg-error">{{newPwdReMsg}}</div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="item">
                        <span class="label"></span>
                        <div class="fl">
                            <a class="btn-5" href="javascript:void(0);" @click="subNewPwd">提交</a>
                        </div>
                        <div class="clr"></div>
                    </div>
                </div>
                <div class="safe-icon-box" v-show="lastSuccess">
                    <s class="icon-succ02 m-icon"></s>
                    <div class="fore">
                        <h3 class="ftx-02">恭喜您，修改密码成功！</h3>
                    </div>
                </div>
            </div>
        </div>
 	</body>
</html>
<<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script>
    $(function () {
       var errorMsg = '${errMsg}';
       if(''!=errorMsg){
           alert(errorMsg);
       }
    });
    var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    //var pwdReg = /^(\w){6,20}$/;
    var pwdReg = /(?=^.{6,20}$)(^(?=.*[a-z])(?=.*[A-Z])|^(?=.*[0-9])(?=(?:.*?[a-zA-Z! @#$%*()_+^}{:;?.\[\]]))|^(?=.*[a-zA-Z])(?=(?:.*?[! @#$%*()_+^}{:;?.\[\]]))).*$/;
    var forgetPwd = new Vue({
        el:'.mod-main',
        data:{
            email:'',
            emailErrMsg:'',
            authCode:'',
            authCodeErrMsg:'',
            yz:'',
            yzErrorMsg:"",
            firstSuccess:true,
            secondSuccess:false,
            lastSuccess:false,
            isGet:false,
            newPwd:'',
            newPwdRe:'',
            newPwdMsg:'',
            newPwdReMsg:'',
            newPwdError:false,
            newPwdReError:false,
            errMsg:''
        },
        methods:{
            checkEmail:function(){
                if(this.email == ''){
                    this.emailErrMsg = '请输入您的邮箱';
                    return false;
                }else if(reg.test(this.email) !== true){
                    this.emailErrMsg = '邮箱格式错误';
                    return false;
                }else{
                    this.emailErrMsg = '';
                    return true;
                }
            },
            checkAuthCode:function (){
                if(this.authCode == ''){
                    this.authCodeErrMsg = '请输入邮箱校验码';
                    return false;
                }else{
                    this.authCodeErrMsg = '';
                    return true;
                }
            },
            checkYz:function (){
                if(this.yz == ''){
                    this.yzErrorMsg = '请输入验证码';
                    return false;
                }else{
                    this.yzErrorMsg = '';
                    return true;
                }
            },
            subYz:function () {
                this.checkEmail();
                if(this.checkEmail()) {
                    var self = this;
                    $.ajax({
                        type: "get",
                        url: "/user/sendCode",
                        async: true,
                        data: {email: self.email},
                        datatype: 'json',
                        success: function (res) {
                            console.log(res);
                            if (res.success) {
                                self.isGet = true;
                                self.errMsg = '';
                            } else {
                                self.errMsg = res.msg;
                            }
                        }
                    });
                    return false;
//                this.isGet = true;
//                if(this.isGet){
//                    var a = setInterval(function(){
//                        forgetPwd.num--;
//                        if(forgetPwd.num <= 0){
//                            forgetPwd.isGet = false;
//                            forgetPwd.num = 5;
//                            clearInterval(a);
//                        }
//                    },1000);
//                }
                }
            },
            submitFirst:function (){
                this.checkAuthCode();
                this.checkEmail();
                this.checkYz();
                if(this.checkAuthCode() && this.checkEmail()){
                    console.log('可验证第一步');//TODO
                    var self = this;
                    $.ajax({
                        type:"get",
                        url:"/user/checkCode",
                        async:true,
                        data:{email:self.email,checkCode:self.yz,captcha:self.authCode},
                        datatype:'json',
                        success:function(res){
                            console.log(res);
                            if(res.success){
                                /*self.firstSuccess = false;
                                self.secondSuccess = true;*/
                                self.authCodeErrMsg = "密码重置链接已发出，请注意查收邮件，如果没有收到，你可以在10分钟后要求系统重新发送";
                            }else{
                                self.errMsg = res.msg;
                            }
                        }
                    });
                }else{
                    console.log('出现错误');
                }
            },
            checkPwd:function () {
                if(this.newPwd == ''){
                    this.newPwdMsg = '请您输入新的密码';
                    return false;
                }else if(pwdReg.test(this.newPwd) != true){
                    this.newPwdMsg = '有被盗风险,建议使用字母、数字和符号两种及以上组合';
                    return false;
                }else{
                    this.newPwdMsg = '';
                    return true;
                }
            },
            checkPwdRe:function (){
                if(this.newPwdRe == ''){
                    this.newPwdReMsg = '请您再次输入新的密码';
                    return false;
                }else if(this.newPwdRe !== this.newPwd){
                    this.newPwdReMsg = '密码不一致';
                    return false;
                }else if(pwdReg.test(this.newPwdRe) != true){
                    this.newPwdMsg = '有被盗风险,建议使用字母、数字和符号两种及以上组合';
                    return false;
                }else{
                    this.newPwdReMsg = '';
                    return true;
                }
            },
            subNewPwd:function (){
                this.checkPwd();
                this.checkPwdRe();
                var self = this;
                if(this.checkPwd() && this.checkPwdRe()){
                    $.ajax({
                        type:"get",
                        url:"/user/resetPwd",
                        async:true,
                        data:{email:self.email,checkCode:self.yz,newPwd:self.newPwd,confirmPwd:self.newPwdRe},
                        datatype:'json',
                        success:function(res){
                            console.log(res);
                            if(res.success){
                                self.firstSuccess = false;
                                self.secondSuccess = false;
                                self.lastSuccess = true;
                                self.errMsg = '';
                            }else{
                                self.errMsg = res.msg;
                            }
                        }
                    });
                }
            }
        }
    });
</script>