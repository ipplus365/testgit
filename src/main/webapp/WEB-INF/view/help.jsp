<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" type="text/css" href="/statics/help/css/help.css"/>
	</head>
	<body>
		<div class="wrap">
			<h1>埃文商城使用手册</h1>
			<h2>第一步：用户注册</h2>
			<p>1.登录埃文商城首页https://mall.ipplus360.com,<a href="https://mall.ipplus360.com" style="text-decoration: none">点击注册</a></p>
			<div class="images">
				<img src="/statics/help/img/headreg.png"/>
			</div>
			<p>或在登陆界面点击立即注册</p>
			<div class="images">
				<img src="/statics/help/img/loginreg.png"/>
			</div>
			<p>2.进入注册页面根据提示进行注册</p>
			<div class="images">
				<img src="/statics/help/img/reg.png"/>
			</div>
			<p>3.到邮箱进行账号激活</p>
			<div class="images">
				<img src="/statics/help/img/active.png"/>
			</div>
			<p>点击链接即可进行账号激活</p>
			<h2>第二步：查看购买商品</h2>
			<p>1.在使用注册的账号登录之后即可看到商品列表</p>
			<div class="images">
				<img src="/statics/help/img/list.png"/>
			</div>
			<p>2.选择一款需要购买的产品查看详情，<span class="red">以IP定位为例</span></p>
			<div class="images">
				<img src="/statics/help/img/list.png"/>
			</div>
			<p>3.选择需要购买的商品组合套餐，选择数量后点击立即购买，即进入购物车</p>
			<div class="images">
				<img src="/statics/help/img/cart.png"/>
			</div>
			<p>4.确认购物车后可继续选择商品加入购物车或去结算，这里选择去结算</p>
			<div class="images">
				<img src="/statics/help/img/par.png"/>
			</div>
			
			
			<p>5.核对订单请款，并选择是否需要发票以及支付方式，点击确认订单并付款，付款方式分为2种：在线支付，其他方式支付。</p>
			<p>（1）在线支付，选择在线付款，扫码或登录支付宝账号付款后系统自动发货</p>
			<div class="images">
				<img src="/statics/help/img/alipay.png"/>
			</div>
			<p>（2）其他方式支付，选择其他方式支付，按照下面图上流程汇款后联系客服确认后发货。</p>
			<div class="images">
				<img src="/statics/help/img/other.png"/>
			</div>
			<p class="red">注意事项：付款时请注意核对订单内容。</p>
			<p>6.付款成功后进入订单详情页面，可选择查看Token或留在本页，同时您的邮箱将会收到发货邮件提醒，发货邮件如下图：</p>
			<div class="images">
				<img src="/statics/help/img/email.png"/>
			</div>
			<p class="red">注：Token：订单付款后为客户生成的key，在进行API调用需要使用Token，每个Token都有相应的使用次数，及有效时间。</p>
			<div class="images">
				<img src="/statics/help/img/order.png"/>
			</div>
			<p>7.选择查看Token进入用户中心页面，即可查看Token详情</p>
			<div class="images">
				<img src="/statics/help/img/token.png" width="1100px"/>
			</div>
			<h2>第三步：使用IP定位产品</h2>
			<p>
				1.进入用户中心查看Token，每调用一次，可用次数会相应减少，当剩余次数不足一定的数量时会以邮件形式提醒客户。
			</p>
			<div class="images">
				<img src="/statics/help/img/token.png" width="1100px"/>
			</div>
			<p>2.手工测试</p>
			<p>（1）个人中心点击前往定位按钮即可到达手工测试界面，如下图：</p>
			<div class="images">
				<img src="/statics/help/img/personal.png"/>
			</div>
			<p>（2）在手工测试界面输入要测试的IP后点击定位，即可显示定位结果展示。</p>
			<div class="images">
				<img src="/statics/help/img/iplocate.png"/>
			</div>
			<p>3.使用Token进行API调用</p>
			<p>调用格式：https://mall.ipplus360.com/ip/locate/api?ip=<span class="red">您需要定位的IP</span>&key=<span class="red">您购买的Token</span></p>
			<p>注：（1）请用您的参数替换调用格式示例中文字标红部分。</p>
			<p style="text-indent: 2em;">（2）Token包括正式购买的套餐Token和99元100000次的测试Token。</p>
			<p>调用结果： </p>
			<pre>
	{
		"code": 200,
		"ip": "xxx.xxx.xxx.xxx",
		"charge": true,
		"data": {"continent": "亚洲","country": "中国", "zipcode": "100000",
		"timezone": "UTC+8","accuracy": "街道", "owner": "中国联通",
		"source": "地面采集","correctness": 5,"consistency": 5,
		"multiareas": [
			{
				"lat": "39.989251",     <font color="green">//WGS-84坐标系 纬度</font>
				"lng": "116.389168",    <font color="green">//WGS-84坐标系 经度</font>
				"radius": "0.1350",
				"prov": "北京市",
				"city": "北京市",
				"district": "朝阳区",
			}
			],
		},
		"msg": "查询成功"
	}
			</pre>
			<h2>十分感谢您对我们产品的支持，我们会持续提升产品质量，让您的应用更加顺畅。</h2>
		</div>
	</body>
</html>
