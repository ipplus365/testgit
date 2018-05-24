<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link rel="stylesheet" href="/statics/css/reset.css">
    <link rel="stylesheet" href="/statics/css/common.css">
    <link rel="stylesheet" type="text/css" href="/statics/css/data.css"/>
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    <style>
        .wrap{
            width: 1805px;
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
                    <li class="current"><a href=""><i class="inav2"></i>我的评论</a></li>
                    <li><a href="/person/company" class="icon-company"><i class="sp-icon"></i>企业认证</a></li>
                    <li><a href="/person/changPwd" class="icon-info"><i class="sp-icon"></i>修改密码</a></li>
                </ul>
            </div>
            <div id="detail">
               
                <section class="content" style="display: block;">
                    <div class="title">
                        <h2>我的评论</h2>
                       <!--  <p class="notice">
                            <select class="selMode w120 mr18" name="state" id="state">
                                <option value="-2"  selected="true" >全部</option>
                                <option value="0" >未回复</option>
                                <option value="1" >已回复</option>
                            </select>
                        </p> -->
                    </div>
                    <table class="tbl">
                        <colgroup>
                            <col width="240">
                            <col width="240">
                            <col width="110">
                            <col width="110">
                            <%-- <col width="110"> --%>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>评论数据名称</th>
                            <th>评论内容</th>
                            <th>评论时间</th>
                            <th>状态</th>
                            <!-- <th>操作</th> -->
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${comment }" var="list">
                        <tr>
                            <td>
                                <a href="/product/detail?productId=${list.product.id}" target="_blank" class="org">${list.product.productName }</a>
                            </td>
                            <td><a>${list.content }</a></td>
                            <td><fmt:formatDate value="${list.commentDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            
                            <c:choose>
                            	<c:when test="${list.status == 1}">
                            		<td class="tcenter" id="status_24556"><strong class="green">正常</strong></td>
                            	</c:when>
                            	<c:otherwise>
                            		<td class="tcenter" id="status_24556"><strong class="green">已评论</strong></td>
                            	</c:otherwise>
                            </c:choose>
                           <!--  <td>
                                <a href="/market/packet/10017?flags=1">查看</a>
                            </td> -->
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
                
            </div>
        </div>
    </div>
</body>
</html>



