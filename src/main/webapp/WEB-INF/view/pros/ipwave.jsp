<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/css/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/css/nav.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/css/footer.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/css/buy.css"/>
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
</head>
<body>
<!--导航栏开始-->
<div class="nav">
    <div class="nav_inner">
        <div class="nav_inner_left">
            <li style="width:auto;margin-right:30px;">
                <img src="/statics/images/logo_i.png">
            </li>
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
                <a href="Javascript:;">实用工具<img src="/statics/images/search-red.png"/></a>
                <div class="list">
                    <a href="http://www.ipplus360.com/ip/">IP查询</a>
                    <a href="http://www.ipplus360.com/tools/">IP Tools</a>
                </div>
            </li>
            <li>
                <a href='http://www.ipplus360.com/clients/logo.html'>客户专区</a>
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
<div id="main">
    <div class="main_inner">
<!----------LEFT------------------->
        <jsp:include page="/include/leftNav" />
        <!--购买页面 开始-->
        <div class="right">

        </div>
        <!--购买页面 结束-->
        <!-- 蓝色购物车按钮 部分 -->
        <div class="cart_button">
            <div class="inner">
                <i class="iconfont">&#xe600;</i>
                购物车
                <span></span>
            </div>
        </div>
        <!-- 购物车详细内容部分 -->
        <div class="cart">
            <h2><i class="iconfont">&#xe600;</i>购物车<span>></span></h2>
            <div class="content">
                <!--<li>
                    <h2>IP问问-高精准<i class="iconfont">&#xe618;</i></h2>
                    <p>购买规格：十万</p>
                    <p>购买数量：2个</p>
                    <p>计费单价：¥99.00/十万</p>
                    <p>计费价格：¥198.00</p>
                    <p>支付价格：<em>¥198.00</em></p>
                </li>
                <li>
                    <h2>IP问问-区县级<i class="iconfont">&#xe618;</i></h2>
                    <p>购买规格：十万</p>
                    <p>购买数量：2个</p>
                    <p>计费单价：¥99.00/十万</p>
                    <p>计费价格：¥198.00</p>
                    <p>支付价格：<em>¥198.00</em></p>
                </li>-->
                <li class="null">
                    购物车空空的，快去选购吧
                </li>
            </div>
            <div class="cart_pay">
                <p>支付总额：<em>¥0.00</em></p>
                <a href="/cart/checkout">立即购买</a>
            </div>
        </div>
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
                    <li class="other two"><a href="http://www.ipplus360.com/pros/ips" >IP应用场景(IP Scene)</a></li>
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
    				<img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" class="login_yzm" onclick="this.src='/captcha?d='+new Date().getTime()"/>
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
                    <span><img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" class="com_yzm" onclick="this.src='/captcha?d='+new Date().getTime()"/></span>
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
    <div style="display: none;">
        <script>
            var _hmt = _hmt || [];
            (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?69678cb16d4e7678d3f5ce3cb00da386";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
    </div>
</div>
</body>
</html>
<script src="/statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/placeholder.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/login.js" type="text/javascript" charset="utf-8"></script>
<script src="/statics/js/buy.js"></script>
