<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>登录跳转</title>
	<style type="text/css">
        .box {
            font-size: 14px;
        }
    </style>
</head>
<body>
	   <div class="box">
           <c:if test="${active}">
                   ${msg}，即将跳转到登录页面，如果没有成功跳转请点击<a href="/user/toLogin">登录</a>
                   <script type="text/javascript">
                       setTimeout(function () {
                           window.location.href = "/user/toLogin";
                       }, 3000)
                   </script>
               </c:if>
               <c:if test="${!active}">
                   ${msg}，请重新激活，如还出现此问题，请联系我们<a href="mailto:contacts@ipplus360.com">埃文科技</a>
               </c:if>
       </div>

</body>
</html>