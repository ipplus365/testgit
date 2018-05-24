<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link rel="stylesheet" href="/statics/css/common.css">
    <link rel="stylesheet" href="/statics/css/register.css">
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    <style>
        .wrap{
            width: 1805px;
        }
        a:hover{
            text-decoration: none;
        }
        .input-tip {
            width: 400px;
            margin: auto;
        }
        .form-item{
            margin: auto;
        }
        #userAuth{
            padding: 30px;
        }
        #a{
            width: 400px;
            margin: 20px auto;
        }
        #sub{
            width:400px;
            padding: 10px 0;
            color: #fff;
            font-size:18px;
            background: #00b1ff;
            border: none;
            outline: none;
        }
        .safe-icon-box {
            margin-left: 350px;
            margin-top: 150px;
        }
        .safe-icon-box {
            position: relative;
            height: 48px;
            overflow: visible;
        }
        .safe-icon-box .icon-succ02 {
            background-position: 0 0;
        }

        .safe-icon-box .m-icon {
            display: inline-block;
            position: absolute;
            top: 0;
            left: 0;
            width: 48px;
            height: 48px;
            background: url(../../statics/images/icon48.png) no-repeat;
        }
        .safe-icon-box .fore {
            margin-left: 58px;
            line-height: 48px;
            height: 48px;
        }
        .ftx-02{
            color: #71b247;
        }
        v-cloak{
            display: none;
        }
    </style>
</head>
<body class="bg-white">
    <!--顶部用户信息栏-->
    <c:import url="../layout/header.jsp"/>
    <!--content-->
    <div class="user-content">
        <div class="wrap cl">
            <div class="user-content-l">
                <h3 class="title-v1">用户中心</h3>
                <ul>
                    <li><a href="/person/user" class="icon-info"><i class="sp-icon"></i>基本信息</a></li>
                    <li><a href="/person/order" class="icon-order"><i class="sp-icon"></i>我的订单</a></li>
                    <li><a href="/person/comment"><i class="inav2"></i>我的评论</a></li>
                    <li><a href="/person/company" class="icon-company"><i class="sp-icon"></i>企业认证</a></li>
                    <li class="current icon-info"><a href=""><i class="sp-icon"></i>修改密码</a></li>
                </ul>
            </div>
            <div id="detail" v-cloak>
                <div class="mod-main mod-comm content">
                    <form id="userAuth" v-show="firstSuccess">
                        <div class="input-tip" >
                            <span id="" class="error">
                                {{errMsg}}
                            </span>
                        </div>
                        <div class="form-item form-item-account" id="form-item-account">
                            <label>旧  密  码</label>
                            <input type="password" id="form-account" class="field" autocomplete="off" placeholder="您的旧密码" v-model="old" v-on:blur="checkOld"/>
                            <i class="i-status"></i>
                        </div>
                        <div class="input-tip" >
                            <span id="form-account-error" class="error">
                                <i v-bind:class="[ oldError ? 'i-error' : '']"></i>
                                {{ oldMsg }}
                            </span>
                        </div>
                        <div class="form-item" >
                            <label>新 密 码</label>
                            <input type="password" id="form-pwd" class="field" maxlength="20"
                                   placeholder="建议至少使用两种字符组合"  v-model="pwd" v-on:blur="checkPwd"/>
                            <i class="i-status"></i>
                        </div>
                        <div class="input-tip">
	                    <span class="error" id="form-pwd-error">
                            <i v-bind:class="[ pwdError ? 'i-error' : '']"></i>
                            {{pwdMsg}}
                        </span>
                        </div>
                        <div class="form-item" >
                            <label>确 认 新 密 码</label>
                            <input type="password" id="form-equalTopwd" class="field" placeholder="请再次输入密码"
                                   maxlength="20" v-model="pwdRepeat" v-on:blur="checkPwdRepeat"/>
                            <i class="i-status"></i>
                        </div>
                        <div class="input-tip">
                            <span class="error" id="form-equalTopwd-error">
                                <i v-bind:class="[ pwdRepeatError ? 'i-error' : '']"></i>
                                {{pwdRepeatMsg}}
                            </span>
                        </div>
                        <div id="a">
                            <input type="button" value="提交" id="sub" @click="submit">
                        </div>
                    </form>
                    <div class="safe-icon-box" v-show="lastSuccess">
                        <s class="icon-succ02 m-icon"></s>
                        <div class="fore">
                            <h3 class="ftx-02">恭喜您，修改密码成功！</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>>
    </div>
</body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.6/vue.min.js"></script>
<script>
        var vue = new Vue({
            el:'#detail',
            data:{
                old:'',
                oldMsg:'',
                oldError:false,
                pwd:'',
                pwdMsg:'',
                pwdError:false,
                pwdRepeat:'',
                pwdRepeatError:false,
                pwdRepeatMsg:'',
                firstSuccess:true,
                lastSuccess:false,
                errMsg:''
            },
            methods:{
                checkOld:function() {
                    if(this.old == '' || this.old == null){
                        this.oldMsg = '请输入您的旧密码';
                        this.oldError = true;
                    }else{
                        this.oldMsg = '';
                        this.oldError = false;
                    }
                },
                checkPwd:function () {
                    if(this.pwd == '' || this.pwd == null){
                        this.pwdMsg = '请输入您的新密码';
                        this.pwdError = true;
                    }else if(this.pwd == this.old){
                        this.pwdMsg = '新旧密码一致';
                        this.pwdError = true;
                    }else{
                        this.pwdMsg = '';
                        this.pwdError = false;
                    }
                },
                checkPwdRepeat:function () {
                    if(this.pwdRepeat == '' || this.pwdRepeat == null){
                        this.pwdRepeatMsg = '请再次输入您的新密码';
                        this.pwdRepeatError = true;
                    }else if(this.pwdRepeat !== this.pwd){
                        this.pwdRepeatMsg = '密码不一致';
                        this.pwdRepeatError = true;
                    }else{
                        this.pwdRepeatMsg = '';
                        this.pwdRepeatError = false;
                    }
                },
                submit:function () {
                    this.checkOld();
                    this.checkPwd();
                    this.checkPwdRepeat();
                    var self = this;
                    if(this.oldError == false && this.pwdError == false && this.pwdRepeatError == false){
                        $.ajax({
                            type:"post",
                            url:"/user/changepwd",
                            async:true,
                            data:{password:self.old.trim(),newPwd:self.pwd.trim(),confirmPwd:self.pwdRepeat},
                            datatype:'json',
                            success:function(res){
                               if(res.success){
                                   self.errMsg = '';
                                   alert('修改成功请重新登陆');
                                   window.location.href = '/user/login';
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

