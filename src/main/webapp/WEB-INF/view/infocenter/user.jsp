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
        .tbl td{
            border: 1px solid #ddd;
            padding: 5px 3px;
            max-width: 200px;
            word-wrap: break-word;
         }


        .tbl th{
            border: 1px solid #ddd;
            font-family: "Microsoft YaHei","SimHei";
        }
        /*.user-content-l{*/
            /*margin-left: 49px;*/
        /*}*/
        /*.user-content-r{*/
            /*width: 1568px;*/
            /*float: left;*/
        /*}*/
        .wrap{
            width: 1805px;
        }
    </style>
    <!-- 百度统计 -->
    <script>
        (function(){
            var msg = '${msg}';
            if(msg != ''){
                alert(msg);
                msg='';
            }
        })();
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?7f306f80db473a1d58a064d5a0f236ca";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>
<body class="bg-white">
    <!--顶部用户信息栏-->
    <c:import url="/WEB-INF/view/layout/header.jsp"/>
    <!--content-->
    <div class="user-content">
        <div class="cl wrap">
            <div class="user-content-l">
                <h3 class="title-v1">用户中心</h3>
                <ul>
                    <!-- <li v-for="(item,index) in data" v-bind:class="[item.cur ? 'current' : '']" v-on:click="tab(index)" style='cursor: pointer;'>
                        <a class="icon-info"><i class="sp-icon"></i>{{item.txt}}</a>
                    </li> -->
                    <li class="current"><a href="" class="icon-info"><i class="sp-icon"></i>基本信息</a></li>
                    <li><a href="/person/order" class="icon-order"><i class="sp-icon"></i>我的订单</a></li>
                    <li><a href="/person/comment"><i class="inav2"></i>我的评论</a></li>
                    <li><a href="/person/company" class="icon-company"><i class="sp-icon"></i>企业认证</a></li>
                    <li><a href="/person/changPwd" class="icon-info"><i class="sp-icon"></i>修改密码</a></li>
                </ul>
            </div>
            <div id="detail">
                <div class="user-content-r" style="display:block;">
                    <h3 class="title-v1">基本信息</h3>
                    <div class="content-t cl">
                        <div class="content-l cl">
                            <%--<div class="user-img"><a href="#" class="sp-icon"></a></div>--%>
                            <div class="user-info">
                                <dl>
                                    <dt>用户账号：</dt>
                                    <dd>${session_user.email}</dd>
                                </dl>
                                <%--<dl>
                                    <dt>邮箱地址：</dt>
                                    <dd>${user.email}</dd>
                                </dl>--%>
                            </div>
                        </div>
                    </div>
                    <h4 class="title-v2">我的应用</h4>
                    <div class="content-b">
                        <div class="app cl">

                            <div class="content-l">
                                <div class="app-img"><img src="/statics/images/IPlocate-big.jpg" width="80"></div>
                                <div class="app-r">
                                <c:if test="${canLocate}">
                                    <a href="/ip/locatepage" id="locate">前往定位</a>
                                </c:if>
                                <c:if test="${!canLocate}">
                                    <a href="/product/detail?productId=1" id="locate">购买定位产品</a>
                                </c:if>
                                </div>
                            </div>

                            <%--<div class="content-r">--%>
                                <%--<ul class="cl">--%>
                                    <%--<a href="/product/list">还未调用任何数据,去api市场逛逛</a>--%>
                                <%--</ul>--%>
                            <%--</div>--%>
                        </div>
                        <table class="tbl">
                            <col width="150">
                            <%-- <col width="70">
                            <col width="70">
                            <col width="70"> --%>
                            <col width="70">
                            <col width="50">
                            <col width="30">
                            <col width="60">
                            <col width="30">
                            <%--<col width="70">--%>
                            <%--<col width="30">--%>
                            <col width="65">
                            <thead>
                                <tr>
                                	<th>token</th>
                                   <!--  <th>生成时间</th>
                                    <th>更新时间</th>
                                    <th>生效时间</th> -->
                                    <th>过期时间</th>
                                    <th>适用产品</th>
                                    <th>状态</th>
                                    <th>剩余次数</th>
                                    <th>测试包</th>
                                    <%--<th>测试包次数</th>--%>
                                    <%--<th>描述</th>--%>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${tokenList}" var="tokenList">
                            		<tr>
                            			<td>${tokenList.token}</td>
	                                    <%-- <td><fmt:formatDate value="${tokenList.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                    <td><fmt:formatDate value="${tokenList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                    <td><fmt:formatDate value="${tokenList.effectiveDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
	                                    <td><fmt:formatDate value="${tokenList.expireDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                    <td>${tokenList.productIds}</td>
	                                    <td>${tokenList.available}</td>
	                                    <%--<td>${tokenList.counts}</td>--%>
                                        <c:choose>
                                            <c:when test="${tokenList.isTest}">
                                                <td>${tokenList.testCount}</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${tokenList.counts}</td>
                                            </c:otherwise>
                                        </c:choose>
	                                    <td>${tokenList.test}</td>
	                                    <%--<td>${tokenList.testCount}</td>--%>
	                                    <%--<td>${tokenList.description}</td>--%>
                                        <td><a onclick="return confirm('是否重新生成Token?');" href="/token/resetToken?token=${tokenList.token}">重新生成Token</a></td>
	                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>