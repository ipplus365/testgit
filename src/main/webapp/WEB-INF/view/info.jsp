<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="/statics/css/reset.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/iconfont.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/page.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/company.css"/>
		<link rel="stylesheet" type="text/css" href="/statics/css/data.css">
		<link rel="stylesheet" type="text/css" href="/statics/fonts/iconfont.css">
		<style type="text/css">
			html,body{
				width: 100%;
				height: 100%;
				min-width: 1900px;
			}
			li{
				list-style: none;
			}
			.nav{
				width: 100%;
				background: #40494f;
				/*overflow: hidden;*/
			}
			.nav .left{
				float: left;
			}
			.nav .left>li{
				padding: 0 15px;
				height: 54px;
				line-height: 54px;
				font-size: 14px;
				border-right: 1px solid #666d72;
			}
			.nav .left>li>a>img{
				vertical-align: middle;
				margin-bottom: 3px;
			}
			.nav .middle{
				float: left;
			}
			.nav .middle>span{
				color: #7A7F83;
				font-size: 100%;
				line-height: 54px;
				padding-left: 32px;
			}
			.nav .right{
				float: right;
			}
			.nav .right>li{
				padding: 0 15px;
				height: 54px;
				line-height: 54px;
				font-size: 14px;
				border-right: 1px solid #666d72;
				float: left;
			}

			.nav .right>li.login_account{
				cursor: pointer;
				position: relative;
			}
			.nav .right>li.login_account div.list{
				background: #40494f;
			    font-size: 14px;
			    padding: 0 15px;
			    position: absolute;
			    z-index: 99;
			    display: none;
				height: 28px;
				line-height: 28px;
				right: 0;
				top: 100%;
				-moz-user-select: none;
				-webkit-user-select: none;
				-ms-user-select: none;
				color: #fff;
			}
			.nav .right>li.login_account div.list  i{
				vertical-align: bottom;
				margin-right: 5px;
				margin-bottom: 3px;
			}

			#main{
				width: 100%;
				/*overflow-y: auto;*/
				background: #e4e4e4;
				/*height: 94.3%;*/
				overflow: hidden;
			}
			#main .main_left{
				float: left;
				background: #fff;
				height: 890px;
			}
			#main .main_left>li{
				color: #373d41;
				padding: 18px 28px;
				cursor: pointer;
				
			}
			#main .main_left>li.active{
				background: #00b1ff;
				color: #fff;
			}
			#main .main_left>li.active>i{
				color: #fff;
			}
			#main .main_left>li>i{
				color: #373d41;
				margin-right: 5px;
				font-size: 20px;
				margin-bottom: -3px;
				vertical-align: text-bottom;
			}
			#security li {
			    text-align: center;
			    line-height: 50px;
			    color: #373d41;
			    display: none;
			}
			#security li:hover{
			    cursor: pointer;
			}
			#security li i{
				padding-right: 8px;
			   font-size: 20px;
			    color: #373d41;
			}
			#security li.currents {
			    background: #67bfff;
			    color: #fff;
			}
			#security li.currents i{
			    color: #fff;
			}

			#main .main_mid{
				width: 68%;
				height: 100%;
				float: left;
				background: transparent;
				padding: 12px;
				
			}
			/*个人信息样式*/
			#main .main_mid #message{
				width: 100%;
				display: none;
			}
			#main .main_mid #message .top{
				width: 100%;
				background: #FFFFFF;
				padding: 30px 40px;
			}
			#main .main_mid #message .top>h2{
				color: #363636;
				font-size: 18px;
				font-weight: bolder;
				padding-bottom: 20px;
			}
			#main .main_mid #message .top>p{
				color: #373d41;
				padding-bottom: 10px;
				font-size: 14px;
			}
			#main .main_mid #message .top>p>span{
				color: #373d41;
				font-size: 14px;
			}
			#main .main_mid #message .top>p>span.change{
				color: #0a7ef6;
				margin-left: 10px;
			}
			#main .main_mid #message .top>p>span.renzheng{
				color: #ff9900;
			}
			#main .main_mid #message .bot{
				margin-top: 12px;
				width: 100%;
				background: #FFFFFF;
				position: relative;
				/*padding: 30px 40px;*/
			}
			#main .main_mid #message .bot>h2{
				color: #363636;
				font-size: 18px;
				font-weight: bolder;
				padding: 20px 0 20px 30px;
			}
			#main .main_mid #message .bot table{
				width: 100%;
				
			}
			#main .main_mid #message .bot table thead{
				background: #eeeeee;
			}
			
			#main .main_mid #message .bot table th{
				color: #363636;
				padding: 23px 0;
				border: 1px solid #e5e5e5;
				font-weight: bolder;
				font-family: '黑体';
			}
			#main .main_mid #message .bot table td{
				color: #8b8b8b;
				padding: 23px 0;
				border: 1px solid #e5e5e5;
				text-align: center;
				font-size: 14px;
				vertical-align: middle;
				position: relative;
			}
			#main .main_mid #message .bot table td.allow{
				color: #0eb852;
			}
			#main .main_mid #message .bot table td>p{
				color: #8b8b8b;
				font-size: 14px;
				padding-bottom: 3px;
				line-height: 20px;
			}
			#main .main_mid #message .bot table td>p.isable_cannotlocate{
				color: #fff;
				white-space:nowrap;  
			    background: #979797;
			    font-size:8px;
			    font-weight: 200;
			    padding: 0 6px;
			    border-radius: 3px;
			    margin-top: -30px;
			    position: absolute;
			    left:-10px;
			    display: none; 
			}
			#main .main_mid #message .bot table td>p.isable_cannotlocate i{
				position: absolute;
			    border: 6px solid #979797;
			    border-bottom-color: transparent;
			    border-left-color: transparent;
			    border-right-color: transparent;
			    bottom: -12px;
			    left: 78px;
			}
			#main .main_mid #message .bot table td>span.reset{
				color: #0195ff;
				display: inline-block;
				cursor: pointer;
				border-right: 1px solid #0195ff;
				padding: 4px 5px 4px 0px;
			}
			#main .main_mid #message .bot table td>span.token_link{
				color: #0195ff;
				display: inline-block;
				cursor: pointer;
			}
			#main .main_mid #message span.locate{
				background: #0195ff;
				display: inline-block;
				cursor: pointer;
				color: #fff;
				padding: 4px 5px 4px 5px;
				margin-left: 5px;
			}
			#main .main_mid #message span.nolocate{
				background: #868686;
				display: inline-block;
				cursor: pointer;
				color: #fff;
				padding: 4px 5px 4px 5px;
				margin-left: 5px;
			}
			#message #token_detail_popup{
				display: none;
			    color: #fff;
			    white-space: nowrap;
			    background: #979797;
			    font-size: 8px;
			    font-weight: 200;
			    padding: 0 6px;
			    line-height: 20px;
			    border-radius: 3px;
			    position: absolute;
			    left: 80px;
			    top: -28px;
			}
			#message #token_detail_popup i{
				border: 6px solid #979797;
			    border-bottom-color: transparent;
			    border-left-color: transparent;
			    border-right-color: transparent;
			    position: absolute;
			    bottom: -12px;
			    left: 63px;
			}
			/*个人信息样式结束*/
			/*订单样式*/
			#main .main_mid #dingdan .order_link{
				color: #0195ff;
				cursor: pointer;
				display: inline-block;
			}
			#main .main_mid #dingdan{
				padding: 17px 20px;
				background: #fff;
				display: none;
			}
			#main .main_mid #dingdan .go_to_page{
				float: right;
				
				margin:30px 0 0 0; 
			}
			#main .main_mid #dingdan .go_to_page span{ 
				padding: 0 6px 0 6px; 
				height: 25px; 
				line-height: 25px; 
				cursor: pointer; 
				float: left; 
				font-size: 12px;
				color:#373d41;  
				display: block; 
				border: 1px solid #ccc;
				margin-left: 5px; 
			}
			#main .main_mid #dingdan .go_to_page span.active{ 
				background: #0e9aff; color: #fff; 
			}
			#main .main_mid #dingdan .go_to_use p{
				padding-top: 70px;
				padding-left: 6px;
				font-size: 14px;
				color: #373d41;
			}
			#main .main_mid #dingdan .go_to_use p a{
				color: #00a0e9;
				font-size: 14px;
			}

			#main .main_mid #dingdan>h2{
				color: #363636;
				font-size: 18px;
				font-weight: bolder;
				padding: 20px 0 20px 30px;
			}
			#main .main_mid #dingdan  table{
				width: 100%;
				
			}
			#main .main_mid #dingdan table thead{
				background: #eeeeee;
			}
			
			#main .main_mid #dingdan table th{
				color: #363636;
				padding: 18px 0;
				border: 1px solid #e5e5e5;
				font-weight: bolder;
				font-family: '黑体';
			}
			#main .main_mid #dingdan table td{
				color: #8b8b8b;
				padding: 18px 0;
				border: 1px solid #e5e5e5;
				text-align: center;
				font-size: 14px;
				vertical-align: middle;
			}
			#main .main_mid #dingdan  table td.allow{
				color: #0eb852;
			}
			#main .main_mid #dingdan  table td>p{
				color: #8b8b8b;
				line-height: 24px;
				font-size: 14px;
				overflow: hidden;
				text-overflow: ellipsis;
				-webkit-line-clamp:4;
				-webkit-box-orient:vertical;
				display: -webkit-box;
				padding-bottom: 3px;
				text-align: left;
				margin-left: 25px;

			}
			#main .main_mid #dingdan  table td>a{
				color: #00a0e9;
			}
			#main .main_mid #dingdan table td .operation_link{
				color: #00a0e9;
			}
			/*订单样式结束*/

			/*订单详情页*/
			#main .main_mid #orderdetails{
				padding: 17px 20px;
				padding: 17px 20px 60px 20px;
				background: #fff;
				display: none;
				position: relative;
			}
			#main .main_mid #orderdetails>h2{
				color: #363636;
				font-size: 18px;
				font-weight: bolder;
				padding: 20px 0 20px 30px;
			}


			#main .main_mid #orderdetails span.esc{
				color: #00a0e9;
				position: absolute;
				top: 40px;
				right: 36px;
				font-size: 14px;
				cursor: pointer;
			}
			#main .main_mid #orderdetails ul.orderdetails_title {
				height: 38px;
				background: #f4f4f4;
			}
			#main .main_mid #orderdetails ul.orderdetails_title li{
				font-size: 14px;
				float: left;
				line-height: 38px;
				padding-left: 45px;
				background: #f4f4f4;
				color: #8b8b8b;
			}
			#main .main_mid #orderdetails ul.orderdetails_title li span{
				color: #8b8b8b;;
				font-size: 14px;
			}
			#main .main_mid #orderdetails ul.orderdetails_title li:last-child{
				float: right;
			}
			#main .main_mid #orderdetails ul.orderdetails_title li a{
				color: #00a0e9;
				margin-right: 45px;
			}
			#main .main_mid #orderdetails table{
				width: 100%;
			}
			#main .main_mid #orderdetails table thead{
				background: #eeeeee;
			}
			
			#main .main_mid #orderdetails table th{
				color: #363636;
				padding: 18px 0;
				border: 1px solid #e5e5e5;
				font-weight: bolder;
			}
			#main .main_mid #orderdetails table td{
				color: #8b8b8b;
				padding: 18px 0;
				border: 1px solid #e5e5e5;
				text-align: center;
				font-size: 14px;
				vertical-align: middle;
			}
			#main .main_mid #orderdetails  table td.allow{
				color: #0eb852;
			}
			#main .main_mid #orderdetails  table td>p{
				color: #8b8b8b;
				font-size: 14px;
				padding-bottom: 3px;
			}
			#main .main_mid #orderdetails table td .operation_link{
				color: #00a0e9;
			}
			#main .main_mid #orderdetails  table td.first_column{
				color: #ff8000;
			}
			/*订单详情页结束*/

			/*我的优惠样式*/
			#main .main_mid #discount{
				padding: 32px;
				background: #fff;
				display: none;
			}
			#main .main_mid #discount>h2{
				padding-bottom: 25px;
				color: #363636;
				font-weight: bold;
			}
			#main .main_mid #discount>p>select{
				padding: 3px 30px;
			}
			#main .main_mid #discount>p>a{
				color: #00a0e9;
				font-size: 14px;
				float: right;
			}
			#main .main_mid #discount .content{
				width: 100%;
				height: 600px;
				text-align: center;
				/*line-height: 600px;*/
			}
			#main .main_mid #discount .content>p{
				color: #c0c0c0;
				font-size: 20px;
				padding-left: 30px;
				padding-top: 15px;
			}
			#main .main_mid #discount .content>img{
				vertical-align: middle;
				margin-top: 150px;
			}
			/*我的优惠样式结束*/
			/*企业认证样式*/

/*修改  安全设置*/
		    #main .main_mid #company{
		      background: #fff;
		      padding: 32px;
		      display: none;
		    }

			#main .main_mid #changePwd{
				padding: 40px;
				background: #fff;
				display: none;
			}
			#main .main_mid #changePwd li>h2{
				padding-bottom: 25px;
				color: #363636;
				font-weight: bold;
			}
			#main .main_mid #changePwd li{
				display: none;
			}
			#main .main_mid #changePwd li.currents{
				display: block;
			}
			#main .main_mid #changePwd li .content{
				width: 400px;
				margin: auto;
			}
			#main .main_mid #changePwd li .content .form-item{
				position: relative;
			    border: solid 1px #ddd;
			    width: 398px;
			    height: 52px;
			    z-index: 0;
			    margin-bottom: 20px;
			}
			#main .main_mid #changePwd li .content .form-item label{
				float: left;
			    width: 90px;
			    height: 52px;
			    line-height: 52px;
			    padding-left: 20px;
			    font-size: 14px;
			    color: #363636;
			}
			#main .main_mid #changePwd li .content .form-item input{
				border: 0 none;
			    font-size: 14px;
			    width: 300px;
			    padding: 15px 0;
			    outline: none;
			    box-sizing: border-box;
			}
			#main .main_mid #changePwd li .content>button{
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

			#main .main_mid #changePwd li .content .form-item span{
				 width: 120px;
				 height: 50px;
				 text-align: center;
				 line-height: 50px;
				 background: #aaa;
				 display: inline-block;
				 float: left;
				 
			}
			#main .main_mid #changePwd li .content .veriy{
				border: 0px;
			}
			#main .main_mid #changePwd li .content .veriy input{
				padding-left: 20px;
				 width: 268px;
				 height: 50px;
				 float: right;
				 border: solid 1px #ddd;
			}
			
/*修改  安全设置end*/
			#main .main_right{
				width: 22.5%;
				height: 100%;
				background: transparent;
				float: left;
				padding: 12px 12px 0 0;
				height: 850px;	
			}
			#main .main_right .top{
				padding: 42px 100px;
				background: #fff;
			}
			#main .main_right .top>p{
				font-size: 14px;
				color: #0e9aff;
				font-weight: bolder;
			}
			#main .main_right .top>p>span{
				font-size: 14px;
				color: #0e9aff;
				font-weight: bolder;
			}
			#main .main_right .top>p>img{
				margin-right: 10px;
				vertical-align: middle;
				margin-bottom: 5px;
			}
			#main .main_right .top>input{
				width: 100%;
				border:1px solid #dfdfdf;
				padding: 6px 0;
				background: #f7f7f7;
			}
			#main .main_right .top>div.scene_isable {
			    font-size: 14px;
			    color: #0e9aff;
			    font-weight: bolder;
			    margin-top: 6px;
			}
			#main .main_right .top .scene_isable p.isable_prompt {
			    color: #fff;
			    background: #979797;
			    font-size: 8px;
			    font-weight: 200;
			    padding: 3px 6px;
			    border-radius: 3px;
			    margin-top: 6px;
			    float: left;
			    position: relative;
			     display: none; 
			}
			#main .main_right .top .scene_isable p.isable_prompt i{
			    position: absolute;
			    border: 6px solid #979797;
			    border-top-color: transparent;
			    border-left-color: transparent;
			    border-right-color: transparent;
			    bottom: 17px;
			    left: 6px;
			}
			#main .main_right .top .goto_padding{
				position: relative;
			}
			#main .main_right .top .goto_padding>button{
				width: 100%;
				display: block;
				outline: none;
				border: none;
				background: #0e9aff;
				color: #fff;
				width: 100%;
				line-height: 31px;
				margin-top: 10px;
				cursor: pointer;
			}
			#main .main_right .top .goto_padding>button.inactive{
				background: #868686;

			}

			#main .main_right .top .goto_padding p.isable_buy{
			    color: #fff;
			    background: #979797;
			    font-size: 8px;
			    font-weight: 200;
			    padding: 3px 6px;
			    border-radius: 3px;
			    margin-top: 6px;
			    float: left;
			    position: absolute;
			    display: none; 
			}

			#main .main_right .top .goto_padding p.isable_buy i{
				position: absolute;
			    border: 6px solid #979797;
			    border-top-color: transparent;
			    border-left-color: transparent;
			    border-right-color: transparent;
			    bottom: 17px;
			    left: 6px;
			}
/*			#main .main_right .top>button{
				display: block;
				outline: none;
				border: none;
				background: #0e9aff;
				color: #fff;
				width: 100%;
				line-height: 31px;
				margin-top: 10px;
				cursor: pointer;
			}*/
			#main .main_right .bot{
				padding: 43px 28px;
				background: #fff;
				margin-top: 12px;
				height: 80%;
			}
			#main .main_right .bot>li{
				padding: 15px 0;
			}
			#main .main_right .bot>li>p{
				color: #363636;
				font-size: 14px;
				padding-left: 8px;
				border-left:2px solid #8fbbe2;
				margin-bottom: 5px;
			}
			#main .main_right .bot>li>button{
				width: 45%;
				border: 1px solid #dfdfdf;
				outline: none;
				background: #f7f7f7;
				margin: 10px 10px 0 0;
				line-height: 31px;
				cursor: pointer;
			}

			/*TOKEN列表样式*/
			#main .main_mid #message .token{
				position: relative;
				width: 100%;
				margin-top: 12px;
				background: #fff;
				padding: 30px 40px;
				display: none;
			}
			#main .main_mid #message .token>p{ 
				color: #373d41;
				margin-bottom: 10px;
				font-size: 14px;
			}
			#message .token>p>span{
				color: #373d41;
				font-size: 14px;
			}
			#message .token>p>span:first-child{
				width: 85px;
				display: inline-block;
				color: #373d41;
				font-size: 14px;
			}
			#message .token>p>span.fright{
				color: #0e9aff;
				float:right;
				cursor: pointer;
			}
			#message .token>p>span.available{
				color: #0eb852;
			}
			#message .token>p>span.reset{
				padding: 4px 6px;
				text-align: center;
				color: #0195ff;
				display: inline-block;
				cursor: pointer;
				border-right: 1px solid #0195ff;
			}
			#message .token>p>span.active{
				background: #0e9aff;
				color: #fff;
				margin-left: 4px;
			}
			/*TOKEN列表样式结束*/
			.getMobile{
				border: none;
			    outline: none;
			    background: #3fa9f5;
			    color: #fff;
			    width: 120px;
			    text-align: center;
			    border-radius: 5px;
			    font-size: 14px;
			    height: 50px;
			    cursor: pointer;
			}
			.disabled{
				background: #909090;
    			cursor: not-allowed;
			}
			.token_explain{
				color: #0a7ef6;
				position: absolute;
				top: 24px;
				right: 18px;
				font-size: 14px;
			}
			.token_explain>i{
				color: #0a7ef6;
			}

/*实名认证（已认证）*/
			#main .main_mid #company_certified{
		      background: #fff;
		      padding: 32px;
		      display: none;
		    }
		    #main .main_mid #company_certified .certified-v1{
			    color: #363636;
			    text-align: left;
			    font-weight: bold;
			    padding-bottom: 15px;
			    border-bottom: 1px solid #FFDCDC;
			}
			#main .main_mid #company_certified .certified-v1 .certified{
				color: #0eb852;
				font-size: 14px;
			}
			#main .main_mid #company_certified .certified_content {
			    padding: 0 70px;
			    width: 900px;
			    margin: auto;
			}
			#main .main_mid #company_certified .certification_item{
			    border-bottom: 1px solid #ddd;
			    padding-bottom: 47px;
			}
			#main .main_mid #company_certified .certification_item:last-child{
				border:none;
			}
			#main .main_mid #company_certified .certification_item h4{
				width: 100%;
			    padding-top: 15px;
			    padding-bottom: 30px;
			    font-size: 16px;
			    color: #333;
			}
			#main .main_mid #company_certified .certification_item table{
				width: 600px;
				margin: 0 auto;
			}
			#main .main_mid #company_certified .certification_item table td{
		        padding: 8px 0;
		        padding-left: 10px;
			    font-size: 14px;
			    color: #666;
			    line-height: 32px;
			    vertical-align: top; 
			}
			#main .main_mid #company_certified .certification_item table td span{
		      	padding:0 5px;
			    font-size: 12px;
			    color: #666;
			    font-weight: 400;
			}
			#main .main_mid #company_certified .certification_item table td:first-child{
				width: 213px;
			    text-align: right;
			    line-height: 32px;
			}
			#main .main_mid #company_certified .certification_item table td .required {
			    display: inline-block;
			    margin-right: 5px;
			    vertical-align: middle;
			    color: #ff8000;
			    font-style: normal;
			}
/*实名认证（已认证）End*/
/*更换手机号*/
			#main .main_mid #changePwd li .change_number{
			    width: 400px;
			    margin: auto;
			    margin-top: auto;
			    margin-right: auto;
			    margin-bottom: auto;
			    margin-left: auto;
			}
			#main .main_mid #changePwd li .change_number .form-item {
			    position: relative;
			    border: solid 1px #ddd;
			    width: 398px;
			    min-height: 52px;
			    z-index: 0;
			    margin-bottom: 20px;
			}
			#main .main_mid #changePwd li .change_number .form-item label {
			    float: left;
			    width: 100px;
			    height: 52px;
			    line-height: 52px;
			    padding-left: 20px;
			    font-size: 14px;
			    color: #363636;
			}
			#main .main_mid #changePwd li .change_number .form-item p{
				white-space:nowrap; 
				color: #c9c9c9;
				font-size: 14px;
				line-height: 36px;
				/*display: none;*/
			} 
			#main .main_mid #changePwd li .change_number .form-item input {
			    border: 0 none;
			    font-size: 14px;
			    width: 290px;
			    padding: 15px 0;
			    outline: none;
			    box-sizing: border-box;
			}
			#main .main_mid #changePwd li .change_number .veriy {
			    border: 0px;
			}

			#main .main_mid #changePwd li .change_number .getMobile{
			    border: none;
			    outline: none;
			    background: #3fa9f5;
			    color: #fff;
			    width: 120px;
			    text-align: center;
			    border-radius: 5px;
			    font-size: 14px;
			    height: 50px;
			    cursor: pointer;
			}
			#main .main_mid #changePwd li .change_number .veriy input {
			    padding-left: 20px;
			    width: 268px;
			    height: 50px;
			    float: right;
			    border: solid 1px #ddd;
			}
			#main .main_mid #changePwd li .change_number .form-item span{
			    width: 120px;
			    height: 50px;
			    text-align: center;
			    line-height: 50px;
			    background: #aaa;
			    display: inline-block;
			    float: left;
			}
			#main .main_mid #changePwd li .change_number>button {
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
/*更换手机号end*/
		</style>
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
<!-- 				<li>
					<i class="iconfont">&#xe695;</i>
					搜索
				</li>
				<li>
					<i class="iconfont">&#xe608;</i>
					<span></span>
				</li>
				<li>
					帮助与文档
				</li> -->
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
		<div id="main">
			<div class="main_left">
				<li>
					<i class="iconfont">&#xe68f;</i>
					我的订单
				</li>
				<li>
					<i class="iconfont">&#xe6b4;</i>
					TOKEN服务
				</li>
				<li>
					<i class="iconfont">&#xe84c;</i>
					数据下载
				</li>
				<li>
					<i class="iconfont">&#xe636;</i>
					我的优惠
				</li>
				<li>
					<i class="iconfont">&#xe63b;</i>
					企业认证
				</li>
				<li>
					<i class="iconfont">&#xe614;</i>
					安全设置
				</li>
				<ul id="security">
					<li> <i class="iconfont">&#xe620;</i>密码修改</li>
					<li id="menu_bind_mobile"> <i class="iconfont">&#xe66f;</i>绑定手机</li>
					<li id="menu_change_mobile"> <i class="iconfont">&#xe66f;</i>修改手机</li>
				</ul>

<!-- 				<ul id="titles">
					<li><i class="iconfont">&#xe84c;</i>IP问问—区县级</li>
					<li><i class="iconfont">&#xe84c;</i>IP Scene</li>
					<li><i class="iconfont">&#xe84c;</i>IP问问高精准库</li>
				</ul> -->
			</div>
			<div class="main_mid">
				<div id="message">
					<div class="top">
						<h2>个人信息</h2>
						<p>登录账号：<span class="login_account_msg"></span></p>
						<p>注册时间：<span class="created_date"></span>&nbsp;&nbsp;<span class="created_time"></span></p>
						<p>上次登录时间：<span class="last_login_date"></span>&nbsp;&nbsp;<span class="last_login_time"></span></p>
						<p>上次登录IP：<span class="last_login_ip"></span></p>
						<p>上次登录地：<span class="last_login_address"></span></p>
					</div>
					<div class="bot" id="bot_token">
						<h2>TOKEN列表</h2>
						<a href="http://www.ipplus360.com/tech/api/" class="token_explain" target="_blank"><i class="iconfont"></i>使用说明</a>
						<table cellspacing="0" cellpadding="0" id="tb_tokens">
							<colgroup>
	                            <col width="100">
	                            <col width="200">
	                            <col width="120">
	                            <col width="100">
	                            <col width="80">
	                            <col width="100">
	                            <col width="100">
	                            <col width="50">
	                            <col width="80">
	                        </colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>token</th>
									<th>过期时间</th>
									<th>适用产品</th>
									<th>状态</th>
									<th>日限额</th>
									<th>剩余次数</th>
									<th>测试包</th>
									<th>操作</th>
								</tr>
							</thead>
							<!-- tokens -->
						</table>
					</div>
					<div class="token">
						<p><span>序号：</span><span id="token_index"></span><span class="fright" id="token_back">&lt;返回</span></p>
						<p><span>token：</span><span id="token_token"></span></p>
						<p><span>生成时间：</span><span id="token_created_date"></span>&nbsp;&nbsp;<span id="token_create_time"></span></p>
						<p><span>更新时间：</span><span id="token_updated_date"></span>&nbsp;&nbsp;<span id="token_updated_time"></span></p>
						<p><span>生效时间：</span><span id="token_active_date"></span>&nbsp;&nbsp;<span id="token_active_time"></span></p>
						<p><span>过期时间：</span><span id="token_expire_date"></span></p>
						<p><span>适用产品：</span><span id="token_suit_products"></span></p>
						<p><span>状态：</span><span class="available" id="token_available"></span></p>
						<p><span>日限额：</span><span id="daily_limits"></span></p>
						<p><span>剩余次数：</span><span id="token_remain_times"></span></p>
						<p><span>测试包：</span><span id="token_is_test"></span></p>
						<p style="position:relative;"><span>操作：</span><span class="reset" id="token_reset">重置</span><span id="token_locate" class="">定位</span>
							<span id="token_detail_popup"><i></i>当前产品不能使用WEB定位</span>
						</p>
					</div>
				</div>
				<div id="dingdan">
					<h2>我的订单</h2>
					<table  cellspacing="0" cellpadding="0" id="tb_dingdan">
						<thead>
							<tr>
								<th>订单号</th>
								<th>下单时间</th>
								<th>更新时间</th>
								<th colspan="2">对应商品</th>
								<th>金额</th>
								<th>付款方式</th>
								<th>订单状态</th>
								<th>操作</th>
								<th>需要发票</th>
							</tr>
						</thead>
					</table>
					<div class="go_to_page">
							<span>上一页</span>
							<span class="active">1</span>
							<span>下一页</span>
					</div>
					<div class="go_to_use">
						<p>*请前往<a href="javascript:void(0)" onclick="(function(){$('.main_left>li')[1].click();}())">TOKEN服务</a>或<a href="javascript:void(0)" onclick="(function(){$('.main_left>li')[2].click();}())">数据下载</a>使用您的产品</p>
					</div>
				</div>

				<div id="orderdetails">
					<h2>我的订单</h2>
					<span class="esc" id="order_detail_back">&lt;返回</span>
					<ul class="orderdetails_title">
						<li>订单编号：<span id="orderdetail_id"></span></li>
						<li>提交时间：<span id="commit_time"></span></li>
						<li>实付金额：<span id="total_fee"></span></li>
						<li>订单状态：<span id="order_status"></span></li>
						<li><a id="order_cancel" href="javascript:void(0)"></a><a target="_blank" id="order_to_pay" href="javascript:void(0)"></a></li>
					</ul>
					<table cellspacing="0" cellpadding="0" id="orderdetails_table">
						<colgroup>
	                        <col width="90">
	                        <col width="500">
	                        <col width="160">
	                        <col width="125">
	                        <col width="180">
	                        <col width="175">
	                    </colgroup>
						<thead>
							<tr>
								<th>编号</th>
								<th>订单详情</th>
								<th>订购数量</th>
								<th>折扣</th>
								<th>付款方式</th>
								<th>应付金额</th>
							</tr>
						</thead>
						<tbody id="tb_order_details">
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">应付总额</td>
								<td id="total_fee_copy"></td>
							</tr>
						</tfoot>
						
					</table>
				</div>

				<div id="discount">
					<h2>我的优惠</h2>
					<p>
						<select id="choice">
							<option value="">全部</option>
						</select>
						<a href="javascript:void(0)">查看优惠券说明></a>
					</p>
					<div class="content">
						<img src="/statics/images/nodiscount.png"/>
						<p>暂无优惠券</p>
					</div>
					<div style="text-align: right;margin:0 auto;">
						<div id="pager" class="pager clearfix"></div>
					</div>
				</div>

                <input type="hidden" id="org_committed" value = ${org_committed} />
				<div id="company">
					<form id="userAuth" action="/company/submit" method="post" autocomplete="off" style="display: block;" enctype="multipart/form-data">
                    	<h3 class="title-v1">实名认证</h3>
                        <div class="certification">
                            <div class="certification-item">
                                <h4>企业基本信息</h4>
                                <dl>
                                    <dt><i class="required">*</i>公司名称：</dt>
                                    <dd>
                                        <input required type="text" class="normal-input"  name="orgName" value="" placeholder="需与营业执照上的名称一致，认证通过，企业名称不可修改。" >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>所属行业：</dt>
                                    <dd>
                                    	<input type="hidden" id="industry" name="industry" value="">
                                        <select required class="normal-input"  id="industryCode" onchange="setSelectValue(this.options[this.selectedIndex].text)">
                                            <option value="">请选择</option>
                                            <option value="1" tag="">互联网行业
                                                (IT、电子、互联网等)
                                            </option>
                                            <option value="2" tag="">金融行业
                                                (银行、保险、基金、证券等)
                                            </option>
                                            <option value="3" tag="">房地产/建筑业
                                                (建筑、装潢、物业等)
                                            </option>
                                            <option value="4" tag="">商业服务
                                                (广告、中介、外包、质检等)
                                            </option>
                                            <option value="5" tag="">贸易零售
                                                (进出口、租赁等)
                                            </option>
                                            <option value="6" tag="">通讯行业
                                                (电信服务、通讯设备等)
                                            </option>
                                            <option value="7" tag="">服务业
                                                (酒店、餐饮、旅游、度假等)
                                            </option>
                                            <option value="8" tag="">文化传媒
                                                (媒体、出版、娱乐等)
                                            </option>
                                            <option value="9" tag="">生产加工制造
                                                (印刷、医药、包装等)
                                            </option>
                                            <option value="10" tag="">物流行业
                                                (交通、运输、物流仓储等)
                                            </option>
                                            <option value="11" tag="">能源环保
                                                (矿产、石油、电气、水利等)
                                            </option>
                                            <option value="12" tag="">政府机构
                                            </option>
                                            <option value="13" tag="">农林牧渔
                                            </option>
                                            <option value="14" tag="">个人使用
                                            </option>
                                            <option value="15" tag="">其他
                                            </option>
                                        </select>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>营业执照所在地/住所：</dt>
                                    <dd>
                                        <input required type="text" class="normal-input" name="licenseAddress" id="entAddress" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>法人：</dt>
                                    <dd>
                                        <input required type="text" class="normal-input" id="legalName" name="legalPerson" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>法人身份证号：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="legalNumber" name="legalPersonId" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactName" name="businessContacts" value="" >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人手机：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactPhone" name="mobile" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人邮箱：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactEmail" name="email" value="">
                                    </dd>
                                </dl>
                            </div>
                            <div class="certification-item">
                                <h4>企业资质信息</h4>
                                <dl>
                                    <dt><i class="required">*</i>统一社会信用代码：</dt>
                                    <dd>
                                        <input required type="text" class="normal-input" id="licenseNumber" name="licenseNumber" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>营业执照有效期/营业期限：</dt>
                                    <dd>
                                                                                                    自&nbsp;<input required class="normal-input w153" type="text" id="validityStart"
                                                      name="licenseStartDate"
                                                      value=""
                                                      readonly="readonly"  onfocus="WdatePicker()" > <i class="dateIcon"></i>
                                                                                                    至&nbsp;<input required type="text" class="normal-input w153" id="validityEnd" name="licenseEndDate"
                                                      value=""
                                                      readonly="readonly" onfocus="WdatePicker()" > <i class="dateIcon"></i>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt class="pt40"><i class="required">*</i>营业执照电子版：</dt>
                                    <dd class="update-img">
                                        <img src="/statics/images/update_default.png" width="160" height="120"
                                             id="licensePicDisplay">
                                        <input required type="file" name="file" id="licensePicUrl">
                                        <i class="required">*</i>图片大小不能超过2M
                                    </dd>
                                </dl>
                            </div>
                            <div class="certification-item">
                                <h4>企业对公账号</h4>
                                <dl>
                                    <dt><i class="required"></i>公司对公账号：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="bankAccount" name="bankAccount" value="">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required"></i>银行开户名：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="accountName" name="bankUserName" value="" maxlength=30>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required"></i>对公账号开户行：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="bankName" name="bank"
                                               value=""
                                                >
                                    </dd>
                                </dl>
                            </div>
                            <input type="hidden" id="userType" name="userType" value="0"/>
                            <div class="btnDiv">
                                <span style="color: #ff0000" id="returnInfo"></span>
                                <dl>
                                    <dd><input type="checkbox" id="authCheckbox"> 我已阅读并同意<a href="/company/protocol" target="_blank">《实名认证协议》</a>
                                    </dd>
                                </dl>
                                <input class="grayBtn" type="submit" id="submitInfo" disabled></input>
								<a href=" " class="grayBtn2">取消</a>
                            </div>
                        </div>
                    </form>
				</div>
<!-- 实名认证（已认证） -->
				<div id="company_certified">
                	<h3 class="certified-v1">实名认证 <span class="certified">（审核中）</span></h3>
                    <div class="certified_content">
                        <div class="certification_item">
                            <h4>企业基本信息</h4>
                            <table>
                            	<tr>
                            		<td><i class="required">*</i>公司名称：</td>
                            		<td>${org.orgName}</td>
                            	</tr>
                            	<tr>
                            		<td><i class="required">*</i>所属行业：</td>
                            		<td>${org.industry}</td>
                            	</tr>
                            	<tr>
                            		<td><i class="required">*</i>营业执照所在地/住所：</td>
                            		<td>${org.licenseAddress}</td>
                            	</tr>
                            	<tr>
                            		<td><i class="required">*</i>法人：</td>
                            		<td>${org.legalPerson}</td>
                            	</tr>
                            	<tr>
                            		<td>法人身份证号：</td>
                            		<td>${org.legalPersonId}</td>
                            	</tr>
                            	<tr>
                            		<td>公司业务联系人：</td>
                            		<td>${org.businessContacts}</td>
                            	</tr>
                            	<tr>
                            		<td>公司业务联系人手机：</td>
                            		<td>${org.mobile}</td>
                            	</tr>
                            	<tr>
                            		<td>公司业务联系人邮箱：</td>
                            		<td>${org.email}</td>
                            	</tr>
                            </table>
                        </div>
                        <div class="certification_item">
                            <h4>企业资质信息</h4>
                            <table>
                            	<tr>
                            		<td><i class="required">*</i>统一社会信用代码：</td>
                            		<td>${org.licenseNumber}</td>
                            	</tr>
                            	<tr>
                            		<td><i class="required">*</i>营业执照有效期/营业期限：</td>
                            		<td>自 <span><fmt:formatDate value="${org.licenseStartDate}" pattern="yyyy-MM-dd" /></span>至 <span><fmt:formatDate value="${org.licenseEndDate}" pattern="yyyy-MM-dd" /></span></td>
                            	</tr>
                            	<tr>
                            		<td><i class="required">*</i>营业执照电子版：</td>
                            		<!--<td><img src="${org.licenseDir}" width="160" height="120"></td>-->
									<td>营业执照电子版已提交（暂不展示）</td>
                            	</tr>
                            </table>
                        </div>
                        <div class="certification_item">
                            <h4>企业对公账号</h4>
                            <table>
                            	<tr>
                            		<td>公司对公账号：</td>
                            		<td>${org.bankAccount}</td>
                            	</tr>
                            	<tr>
                            		<td>银行开户名：</td>
                            		<td>${org.bankUserName}</td>
                            	</tr>
                            	<tr>
                            		<td>对公账号开户行：</td>
                            		<td>${org.bank}</td>
                            	</tr>
                            </table>
                        </div>
                    </div>               
				</div>
<!-- 实名认证（已认证）End -->

				<div id="changePwd">
					<li class="shows">
						<h2>修改密码</h2>
						<div class="content">
							<div class="form-item">
								<label for="oldPwd">旧密码</label>
								<input type="password" name="" id="old_password" value="" placeholder="您的旧密码"/>
							</div>
							<div class="form-item">
								<label for="oldPwd">新密码</label>
								<input type="password" name="" id="new_password" value="" placeholder="建议至少使用两种字符组合"/>
							</div>
							<div class="form-item">
								<label for="oldPwd">重复密码</label>
								<input type="password" name="" id="confirm_passsord" value="" placeholder="请再次输入您的新密码"/>
							</div>
							<button id="password_commit">提交</button>
						</div>
					</li>
					<input type="hidden" id="hasMobile" value = ${hasMobile} />
					<li id="bind_mobile">
						<h2>绑定手机号</h2>
						<div class="content">
							<div class="form-item">
								<label for="appPwd">手机号</label>
								<input type="text" class="per_mobile" name="" id="phone_number" value="" placeholder="请输入有效手机号码"/>
							</div>
							<div class="form-item veriy">
								<button class="getMobile">获取验证码</button>
								<input type="text" name="" class="verification per_mobileCode" id="verification" value="" placeholder="请输入验证码"/>
							</div>
							<div class="form-item veriy">
								<span><img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"></span>
								<input type="text" name="" class="verification " id="verification_captcha" value="" placeholder="请输入验证码"/>
							</div>
							<button id="bind_commit">确定</button>
						</div>
					</li>
					<li id="change_mobile">
						<h2>修改手机号</h2>
						<div class="content">
							<div class="form-item">
								<label >原手机号</label>
								<input type="text" value="" id="phone_number_original" placeholder="请输入原手机号码"/>
							</div>
							<div class="form-item">
								<label for="appPwd">新手机号</label>
								<input type="text" class="per_mobile" name="" id="phone_number" value="" placeholder="请输入有效手机号码"/>
							</div>
							<div class="form-item veriy">
								<!-- <span class="verification_time">50S&nbsp;重新获取</span> -->
								<button class="getMobile">获取验证码</button>
								<input type="text" name="" class="verification per_mobileCode" id="verification" value="" placeholder="请输入验证码"/>
							</div>
							<div class="form-item veriy">
								<span><img style="width: 100%;height: 100%;cursor: pointer;" src="/captcha" onclick="this.src='/captcha?d='+new Date().getTime()"></span>
								<input type="text" name="" class="verification " id="verification_captcha" value="" placeholder="请输入验证码"/>
							</div>
							<button id="bind_commit">确定</button>
						</div>
					</li>
				</div>

<!-- 修改 End -->

				<div id="contents">
					<li class="shows">
						<div class="tit">IP问问—区县级</div>
			  			<table cellspacing="0" cellpadding="0">
				  			<colgroup>
				                  <col width="50">
	                              <col width="70">
	                              <col width="75">
	                              <col width="80">
	                              <col width="80">
	                              <col width="80">
	                              <col width="80">
	                              <col width="85">
	                              <col width="170">
	                              <col width="170">
	                              <col width="80">
	                              <col width="80">
				            </colgroup>
				            <thead>
				                  <tr>
				                    <th>编号</th>
				                    <th>产品名称</th>
				                    <th>版本</th>
				                    <th>坐标系</th>
				                    <th>更新频率</th>
				                    <th>数据格式</th>
				                    <th>区域</th>
				                    <th>更新方式</th>
				                    <th>购买时间</th>
				                    <th>有效期</th>
				                    <th>版本号</th>
				                    <th>操作</th>
				                  </tr>
				            </thead>
				            <tbody id="tb_buy_list">
				                
				            </tbody>
			            </table>
						<div class="lisnav">
							<span>上一页</span>
							<span class="active">1</span>
							<span>下一页</span>
						</div>
<!-- 						<div class="lisnav2">
							<span>上一页</span>
							<span class="active">1</span>
							<span class="active">下一页</span>
						</div> -->
					</li>
<!-- 					<li>
						<div class="tit">IP Scene</div>
					</li>
					<li>
						<div class="tit">IP问问高精准库</div>
					</li> -->
				</div>

			</div>
			<div class="main_right">
				<div class="top">
					<p><img src="/statics/images/locate_red.png"/>当前IP：<span id="current_ip">${user_ip}</span></p>
					<input type="text" name="" id="redirect_locateIP" value="" placeholder="请输入IP"/>
					<div class="goto_padding">
						<button id="goto_locate" class="inactive">前往定位</button>
						<p class="isable_buy"><i></i>您还没有购买适用于WEB定位的产品</p>
					</div>
					<div class="scene_isable">
						<input type="checkbox" id="checkbox_scene">应用场景
						<p class="isable_prompt"><i></i>您目前没有购买应用场景，无法使用<span>X</span></p>
					</div>
					<input type="hidden" id="canWebLocate" value = ${canWebLocate} />
					<input type="hidden" id="hasScene" value = ${hasScene} />
				</div>
				<div class="bot">
<!-- 					<li>
						<p>常用操作</p>
						<button>续费管理</button>
						<button>设置</button>
						<button>待处理事件</button>
						<button>查看特权</button>
					</li>
					<li>
						<p>重要提醒</p>
					</li>
					<li>
						<p>FAQ疑难解答</p>
					</li> -->
				</div>
			</div>
		</div>
	</body>
	<script src="/statics/js/jquery-1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/statics/js/jquery.page.js" type="text/javascript" charset="utf-8"></script>
	<script src="/statics/datePicker/WdatePicker.js" type="text/javascript" charset="utf-8"></script>
	<script src="/statics/js/company.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$("#pager").zPager({
			totalData: 1
		});
		$('.main_left>li').click(function(){
			$('.main_left>li').removeClass('active');
			$(this).addClass('active');

			if($("#org_committed").val() == "true"){
				$('#company').hide();
				var company_temp = $('#company_certified');
			}else{
				var company_temp = $('#company');
				$('#company_certified').hide();
			}

			if($(this).index() === 0){
				// 订单
				$("#orderdetails").hide();
				$('#message').hide();
				$('#dingdan').show();
				$('#discount').hide();
				company_temp.hide();
				$('#changePwd').hide();
				$('#contents').hide();
				$('#titles').hide();
				$('#security li').hide();
				window.location.href = window.location.href.split("#")[0]+"#[dingdan]";
			}else if($(this).index() === 1){
				// token
				$("#orderdetails").hide();
				$('#message').show();
				$('#dingdan').hide();
				$('#discount').hide();
				company_temp.hide();
				$('#changePwd').hide();
				$('#contents').hide();
				$('#titles').hide();
				$('#security li').hide();
				window.location.href = window.location.href.split("#")[0]+"#[token]";
			}else if($(this).index() === 2){
				// 数据下载
				$("#orderdetails").hide();
				$('#message').hide();
				$('#dingdan').hide();
				$('#discount').hide();
				company_temp.hide();
				$('#changePwd').hide();
				$('#contents').show();
				$('#titles').show();
				$('#security li').hide();
				$("#titles li").first().show().siblings().removeClass('currents').end().addClass('currents');
				window.location.href = window.location.href.split("#")[0]+"#[datafile]";
			}else if($(this).index() === 3){
				// 优惠
				$("#orderdetails").hide();
				$('#message').hide();
				$('#dingdan').hide();
				$('#discount').show();
				company_temp.hide();
				$('#changePwd').hide();
				$('#contents').hide();
				$('#titles').hide();
				$('#security li').hide();
				window.location.href = window.location.href.split("#")[0]+"#[discount]";
			}else if($(this).index() === 4){
				// 企业认证
				$("#orderdetails").hide();
				$('#message').hide();
				$('#dingdan').hide();
				$('#discount').hide();
				company_temp.show();
				$('#changePwd').hide();
				$('#contents').hide();
				$('#titles').hide();
				$('#security li').hide();
				window.location.href = window.location.href.split("#")[0]+"#[company]";
			}else if($(this).index() === 5){
				// 安全设置
				$("#orderdetails").hide();
				$('#message').hide();
				$('#dingdan').hide();
				$('#discount').hide();
				company_temp.hide();
				$('#contents').hide();
				$('#changePwd').show();
				$('#titles').hide();
				$('#security li').show();
				$("#security li").first().show().siblings().removeClass('currents').end().addClass('currents');
				$("#changePwd li").first().show().siblings().hide();
				window.location.href = window.location.href.split("#")[0]+"#[set]";
			}else{
				console.log("Error");
			}

			// 安全菜单项和div设置
			var title_changePwds=document.getElementById("security").getElementsByTagName("li");
			var content_changePwds=document.getElementById("changePwd").getElementsByTagName("li");
			for(var i=0;i<title_changePwds.length;i++){
				title_changePwds[i].onclick=function(){
					$(this).siblings().removeClass('currents').end().addClass('currents');
					var title_buys_index = $(this).index();
					$(content_changePwds[title_buys_index]).siblings().hide().end().show();
				};
			}

			// 已购数据菜单项和div设置
			// var title_buys=document.getElementById("titles").getElementsByTagName("li");
			// var content_buys=document.getElementById("contents").getElementsByTagName("li");
			// for(var i=0;i<title_buys.length;i++){
			// 	title_buys[i].onclick=function(){
			// 		$(this).siblings().removeClass('currents').end().addClass('currents');
			// 		var title_buys_index = $(this).index();
			// 		$(content_buys[title_buys_index]).siblings().hide().end().show();
			// 	};
			// }
		});
		
		var init_item = window.location.href.match(/#\[(.*)\]/);
		if (init_item) {
		    if (init_item[1] == "dingdan") {
		        $('.main_left>li')[0].click();
		    } else if (init_item[1] == "token") {
		        $('.main_left>li')[1].click();
		    } else if (init_item[1] == "datafile") {
		        $('.main_left>li')[2].click();
		    } else if (init_item[1] == "discount") {
		        $('.main_left>li')[3].click();
		    } else if (init_item[1] == "company") {
		        $('.main_left>li')[4].click();
		    } else if (init_item[1] == "set") {
		        $('.main_left>li')[5].click();
		    } else {
		        $('.main_left>li')[0].click();
		    }
		} else {
		    init_item = window.location.href.match(/\?content=(.*)/);
		    if (init_item) {
		        if (init_item[1] == "dingdan") {
		            $('.main_left>li')[0].click();
		        } else if (init_item[1] == "token") {
		            $('.main_left>li')[1].click();
		        } else if (init_item[1] == "datafile") {
		            $('.main_left>li')[2].click();
		        } else {
		            $('.main_left>li')[0].click();
		        }
		    } else {
		        $('.main_left>li')[0].click();
		    }
		}
	</script>
	<script src="/statics/layer/layer.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="/statics/myjs/date.format.js"></script>
	<script type="text/javascript" src="/statics/myjs/personalCenter.js"></script>
</html>
