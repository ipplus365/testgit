<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>埃文科技-IP问问</title>
	<link rel="stylesheet" type="text/css" href="../statics/css/leaflet.css"/>
	<link rel="stylesheet" type="text/css" href="../statics/css/locate.css"/>
	<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
</head>
<body>
<div id="box">
	<div id="map">

	</div>
	<div id="wrap">
		<div class="xiala">
			<!--<span class="service-list"><span>服务列表</span><img src="../statics/images/dropdownarrow_black.png"/></span>-->
			<%--<span class="IP">IP:</span><input type="text" class="ipAddress" value="${user_ip}"/>--%>
			<span class="IP">IP:</span><input type="text" class="ipAddress" value="1.36.176.198"/>
		</div><div class="delete"><span><img src="../statics/images/close.png"/></span></div><span class="button">定位</span>
		<div id="warning"><span>×</span>您输入的IP地址有误</div>
		<div id="warning_repeat">请您5秒后再点击</div>
		<!--<div class="list">
            <ul>
                <li>服务列表</li>
                <li>BJ_HK_ONLINEDB</li>
            </ul>
        </div>-->
	</div>
	<!--<div id="noneuser">暂无用户画像信息</div>-->
	<div id="mengban">
		<div id="results">
			<div class="wrap">
				<div class="results_show_one">
					<!--<span>IP地址:</span><span>1.36.178.96</span>
					<span>状态信息:</span><span>定位到街道级别</span>
					<span>状态码:</span><span>100</span>
					<span>定位精度:</span><span>街道</span>
					<span>定位方式:</span><span>地面采集</span>
					<span>用时:</span><span>12074ms</span>-->
				</div>
				<div class="top">
					<!--我是阿贾克斯的疯狂设计费是盛开的时刻金粉世家索拉卡几点啦数据的-->
				</div>
			</div>
			<div class="wrap">
				<div class="results_show_two">
					<!--<span>洲:</span><span>亚洲</span>
                    <span>国家:</span><span>中国</span>
                    <span>省/直辖市:</span><span>北京</span>
                    <span>城市:</span><span>北京市</span>
                    <span>区县:</span><span>海淀区</span>
                    <span>经度:</span><span>116.328993</span>
                    <span>纬度:</span><span>39.95924</span>
                    <span>邮政编码:</span><span>100000</span>
                    <span>时区:</span><span>UTC+8</span>-->
				</div>
				<div class="top">
					<!--我是阿贾克斯的疯狂设计费是盛开的时刻金粉世家索拉卡几点啦数据的-->
				</div>
			</div>
			<div class="wrap">
				<div class="results_show_three">
					<!--<span>R1:</span><span>1.20545KM</span>
                    <span>R2:</span><span>2.81272KM</span>
                    <span>最大误差距离:</span><span>7.93KM</span>
                    <span>准确度:</span><span><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/dark_star.png"/></span>
                    <span>精密度:</span><span><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/></span>
                    <span>应用场景:</span><span>住宅用户</span>
                    <span>应用场景:</span><span>住宅用户</span>
                    <span>应用场景:</span><span>住宅用户</span>-->
				</div>
				<div class="top">

				</div>
			</div>
			<p>此IP可能分布在以下区域</p>
			<div class="down">
				<img src="../statics/images/slide_down.png"/>
			</div>
			<div id="multi_mb">
				<div id="multiAreas">

				</div>
			</div>
			<div id="last">

			</div>
			<!--<span class="more">more</span>-->
		</div>
	</div>
</div>
</body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="../statics/js/leaflet.js"></script>
<script src="../statics/js/d3.js"></script>
<script src="../statics/js/intersect.js"></script>
<script src="../statics/js/locate.js"></script>
