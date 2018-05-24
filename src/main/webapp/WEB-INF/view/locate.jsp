<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>定位</title>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
<link href="/statics/css/reset.css" rel="stylesheet" type="text/css"/>
<link href="/statics/css/commons.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="/statics/css/iconfont.css"/>
<link href="//at.alicdn.com/t/font_ow94jezx57trcnmi.css" rel="stylesheet" type="text/css"/>
<link href="//at.alicdn.com/t/font_405410_tez8g1f819vn29.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="/statics/images/favicon.ico"/>
</head>
<body>
<div class="nav clearfix">
			<div class="left">
				<li>
					<a href="/product/buy">
						<img src="/statics/images/logo.png"/>
						返回首页
					</a>
				</li>
			</div>
			<div class="middle">
				<span id="data_version">当前数据版本</span>
				<span id="update_time">更新日期</span>
			</div>
			<div class="right">
				<li class="login_account">
					<span></span>
					<i class="iconfont">&#xe662;</i>
					<div class="list">
						<i class="iconfont">&#xe9c8;</i>退出
					</div>	

				</li>
				<li>
					简体中文
				</li>
			</div>
		</div>
<!--<div class="infobar l">
	<span><b id="accuracy">国家</b>级<img src="./images/location.png"/></span>
</div>
<div class="infobar m">
	<span>新数据上线通知</span>
</div>
<div class="infobar r">
	<span>操作指导</span>
</div>-->
<div class="mainContainter">
	<div class="leftBar">
		<div class="searchInput">
			<i class="iconfont icon-ip"></i>
			<div class="iptbox active">
				<input type="text" id="searchipt" placeholder="搜索IP地址、数字型IP" maxlength="256" autocomplete="off" class="active">
				<div id="searchbtn" class="usel">
					<i class="iconfont icon-qingkong input-clear" title="清空" style="display: none;"></i>
					<i class="iconfont icon-sousuo-sousuo searchlogo" style="display: block;" title="点击查询"></i>
					<span id="searchloading" class="ring" style="display: none;"></span>
				</div>
			</div>
		</div>
		<div class="amap-search-panctrl" style="display: none;">
			展开搜索结果
		</div>
		<div class="showResult" id="showResult" style="height:auto;">
			<ul class="ul-list">
				<li id="inip" class="str">IP地址：&nbsp;<span>**</span></li>
				<li id="zhuangtai" class="str">状态码：&nbsp;<span>**</span></li>
				<li id="jingdu">定位精度：<span>**</span></li>
				<li id="owner">&#12288;运营商：<span>**</span></li>
				<li id="continent">&#12288;&#12288;&#12288;洲：<span>**</span></li>
				<li id="country">&#12288;&#12288;国家：<span>**</span></li>
				<li id="zipcode">邮政编码：<span>**</span></li>
				<li id="timezone">&#12288;&#12288;时区：<span>**</span></li>
				<li id="scene">应用场景：<span>**</span></li>
				<li class="tag">此IP可能分布在以下区域</li>
				<li class="pois">
				<ul class="more-pois">
				</ul>
				</li>
				<li id="zhunquedu" style="">&#12288;准确度：<span></span></li>
				<li id="yizhixing" style="">&#12288;一致性：<span></span></li>
			</ul>
			<ul class="ul-list-s">
				<li id="info-message" style="">**</li>
			</ul>
		</div>
		<div class="showResult" id="noShowResult" style="display: none;">
			<ul class="serp-list">
				<li class="poibox poibox-normal amap-marker poibox-empty">
				<div class="poi-empty">
					<h5 class="empty-sorry">   抱歉，没有找到与IP“<span class="empty-keywords">sfdsfsdfwersf</span>”相关的信息</h5>
					<div class="poi-empty-tip">
							 您还可以：
					</div>
					<div class="empty-tips">
						<ul>
							<li> 检查输入的IP是否有误 </li>
							<li> 尝试更换不同的IP </li>
							<!-- <li><a class="empty-feedback" target="_blank" href="javascript:void(0)">提交这个IP</a></li>
							 -->
						</ul>
					</div>
				</div>
				</li>
			</ul>
		</div>
		<div class="showResult" id="notShowResult">
			<ul class="serp-list">
				<li class="poibox poibox-normal amap-marker poibox-empty">
				<div class="poi-empty">
					<h5 class="empty-sorry">  今日查询次数已满，请明日再来</h5>
				</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="rightBar">
		<div class="measure" id="measure_map">
			<span id="traffic_control" class="last traffic"></span>
			<i>测距</i>
		</div>
	</div>
	<div class="mymap" id="mymap">
	</div>
</div>
<footer>
<div class="content">
	<ul>
		<li><i class="iconfont icon-youjian"></i></li>
		<li><i class="iconfont icon-weibo"></i></li>
		<li><i class="iconfont icon-qq"></i></li>
		<li><i class="iconfont icon-weixin"></i></li>
	</ul>
	<span class="introinfo">
				&copy;2012-2017 ipplus360.com , All Rights Reserved.
	<a href="http://www.miitbeian.gov.cn/" rel="nofollow" target="_balnk">豫ICP备12022601号-1</a>| 豫公网安备 11010802021761
	</span>
</div>
</footer>
<div class="popmsg">
	<span class="tit">提醒<i class="iconfont icon-close48"></i></span>
	<span class="msg">您今日查询次数已满</span>
	<span class="button">
	<b class="tomorrow">确定</b>
	<a href="https://mall.ipplus360.com/"><b>升级套餐</b></a>
	</span>
</div>
<!--<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>-->
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=1600348fc30b9037f8edd0d930faf2db"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
<script type="text/javascript" src="/statics/myjs/date.format.js"></script>
<script type="text/javascript" src="/statics/js/commons.js"></script>
</body>
</html>