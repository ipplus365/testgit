<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			html,body{
				width: 100%;
				height: 100%;
			}
			a{
				color: #00abff;
			}
			#wrap{
				width: 800px;
				/*height: 500px;*/
				background: #fff;
				padding: 50px 20px 20px;
				margin: 0 auto;
			}
			.top{
				position: relative;
			}
			.top img{
				width: 50px;
				margin-bottom: -10px;
			}
			.top>span:nth-child(2){
				font-size: 20px;
			}
			.top>span:nth-child(3){
				font-size: 24px;
				color: #C5C5C5;
				position: absolute;
				right: 0;
				top: 18px;
			}
			.msg{
				width: 100%;
				margin-top: 15px;
			}
			.msg td{
				padding: 15px 20px;
				text-align: center;
				background: rgb(245,245,245);
				border: 1px solid #fff;
			}
			
			.left{
				display: inline-block;
				width: 75%;
				text-align: left;
			}
			.msg td>span:nth-child(1){
				color: #C5C5C5;;
			}
			.detail{
				width: 100%;
				margin-top: 10px;
			}
			.detail th{
				padding: 10px 20px;
				text-align: center;
				background: rgb(245,245,245);
				color: gray;
			}
			.detail td{
				padding: 30px 10px;
				text-align: center;
				position: relative;
			}
			.detail td span{
				width: 100%;
				position: absolute;
				top: 3px;
				left: 0;
				
			}
			.big{
				font-size: 20px;
			}
			.detail td img{
				width: 80px;
				margin-bottom: -35px;
			}
			p{
				padding: 20px 0;
				text-align: center;
				color: #C5C5C5;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div class="top">
				<img src="https://mall.ipplus360.com/statics/images/favicon.ico" alt="" />
				<span>埃文科技</span>
				<span>购买成功</span>
			</div>
			<table  cellspacing="0" cellpadding="0" class="msg">
				<tr >
					<td width="35%">
						<span>账户名称</span><br />
						<a href="">${user.email}</a>
					</td>

			</table>
			<table  cellspacing="0" cellpadding="0" class="detail">
				<tr>
					<th>
						产品名称
					</th>
					<th>
						产品描述
					</th>
					<th>
						数量
					</th>
					<th>
						价格
					</th>
				</tr>
				
				<#list orderItems as orderItem> 
					<tr>
						<td>
							<span>
								<img src="https://mall.ipplus360.com${orderItem.product.iconLarge}">
								${orderItem.product.productName}
							</span>
						</td>
						<td>
							<#if (orderItem.pricePackage)??>${(orderItem.pricePackage.amountStr)!}次</#if>
						</td>
						<td>
							${orderItem.itemNum}
						</td>
						<td>
							${orderItem.price}
						</td>
					</tr>
				</#list> 
				
			
			</table>
			<%--<p>如有任何关于IP问问的问题，请访问<a href="https://mall.ipplus360.com/">如何验证IP地址定位的准确率？</a> | <a href="https://mall.ipplus360.com/">高精准IP地址定位技术的特点及应用领域</a></p>--%>
		</div>
	</body>
</html>
