<%@ page language="Java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="detail-right">
    <div class="order">
        <h3 class="tit">推荐数据</h3>
        <ul class="rec-list">

            <c:forEach items="${productList}" var="product">
				<li>
		            <a href="/product/detail?productId=${product.id}" target="_blank" >
		                 <img src="${product.iconLarge}" alt="${product.productName}" width="37" height="37">
		            </a>
		            <dl>
	                    <dt>
	                        <a href="/product/detail?productId=${product.id}" target="_blank" title="${product.productName}">${product.productName}</a>
	                    </dt>
	                    <dd>
	                        ${product.price}
	                    </dd>
	                </dl>
	            </li>
			</c:forEach>
        </ul>

    </div>
</div>