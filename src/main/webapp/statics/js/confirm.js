$(function(){
	//判断用户是否登陆
	$.ajax({
		type:"get",
		url:"/user/login",
		dataType:'json',
		async:true,
		success:function(res){
			if(res.success == true){
				$(".login_box").html('<a href="javascript:void(0)" class="username">'+ res.data +'</a><div class="list"><a href="/info">个人中心</a><a href="javascript:;" class="layout">退出</a></div>');
				$(".login_box").width('auto');
			}else{
//				alert('为了您的安全，请您先登录');
//      		window.location.href = '/user/toLogin';
        		layer.open({
			        title: '温馨提示',
			        move:false,
			        content:'为了您的安全，请您先登录',
			        yes:function(){
			          window.location.href = '/user/toLogin';
			        },
			        cancel:function(){
			          window.location.href = '/user/toLogin';
			        }
			    }); 
			}
		}
	});
	//获取购物车列表
	//获取用户购物车列表
  	$.ajax({
	  	type:"get",
	  	url:"/cart/list",
	  	async:true,
	  	dataType: 'json',
	  	data:{},
	  	success:function(res){
	  		if(res.success == true){
				createOrderList(res);
			}else if(res.data = 'user_not_login'){
				 $('.orderList tbody').html('<tr><td colspan="7" align="center">您还没有登录，请点击登录按钮登录您的账户 </td></tr>');
  				$('.total').text('0.00');
			}else{
				 layer.open({
		              title: '温馨提示',
		              move:false,
		              // fixed:true,
		              content: res.msg
		          });    
			}
	  	}
  	});
  	$('.payButton').click(function(){
  		$.ajax({
  		  	type:"get",
  		  	url:"/order/confirm",
  		  	async:true,
  		  	dataType: 'json',
  		  	data:{ isInvoice: $('#isInvoice').val(),orderSerial:$('#orderSerial').val()},
  		  	success:function(res){
  				if(res.success == true){
  					$('#orderSerial').val(res.data);
  					$('.inner').submit();
  				}else{
  					layer.open({
		              title: '温馨提示',
		              move:false,
		              content: res.msg
		            });   
  				}
  		  	}
  	  	});
  	});
  	
  	function createOrderList(data){
  		var orderStr = '';
  		if(data.data.cartItems.length <= 0){
  			orderStr += '<tr><td colspan="7" align="center">您还没有选购数据 <a href="/product/buy" class="goShopping">去数据市场逛逛吧</a></td></tr>'
  		}else{
  			for(var i = 0; i < data.data.cartItems.length; i++){
	  			orderStr += '<tr><td>'+ data.data.cartItems[i].product.productName +'</td><td>'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</td><td>'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</td><td>'+ '' +'</td><td>'+ data.data.cartItems[i].itemNum +'</td><td>¥'+ data.data.cartItems[i].originalPrice +'</td><td>¥<em>'+ data.data.cartItems[i].price +'</em></td></tr>';
	  		}
	  	}
  		$('.orderList tbody').html(orderStr);
  		$('.total').text(data.data.price);
  	}
  	$(":radio").click(function(){
  		if($(this).val() == 0){
  			$('.invoice-form').hide();
  			$('.payButton').removeClass('disable');
  			$('.payButton').removeAttr('disabled');
  			$('.submit-check-info').hide();
  		}else{
  			$('.invoice-form').show();
  			$('.payButton').addClass('disable');
  			$('.payButton').attr('disabled',true);
  			$('.submit-check-info').show();
  		}
	    $('#isInvoice').val($(this).val());
	});
	$('.btn-submit').click(function(){
		var invoice = {
             	orgName:$('.orgName').val(),
				taxId:$('.taxId').val(),
				regAddress:$('.regAddress').val(),
				regMobile:$('.regMobile').val(),
				bank:$('.bank').val(),
				bankAccount:$('.bankAccount').val(),
				userContacts:$('.userContacts').val(),
				userMobile:$('.userMobile').val(),
				userAddress:$('.userAddress').val(),
		};
		var orgName = '';
		var taxId = '';
		var regAddress = '';
		var regMobile = '';
		var bank = '';
		var bankAccount = '';
		var userMobile = '';
		var userAddress = '';
		if($('.orgName').val() == ''){
			$('.orgNameError').show();
			orgName = false;
		}else{
			$('.orgNameError').hide();
			orgName = true;
		}
		if($('.taxId').val() == ''){
			$('.taxIdError').show();
			taxId = false;
		}else{
			$('.taxIdError').hide();
			taxId = true;
		}
		if($('.regAddress').val() == ''){
			$('.regAddressError').show();
			regAddress = false;
		}else{
			$('.regAddressError').hide();
			regAddress = true;
		}
		if($('.regMobile').val() == ''){
			$('.regMobileError').show();
			regMobile = false;
		}else{
			$('.regMobileError').hide();
			regMobile = true;
		}
		if($('.bank').val() == ''){
			$('.bankError').show();
			bank = false;
		}else{
			$('.bankError').hide();
			bank = true;
		}
		if($('.bankAccount').val() == ''){
			$('.bankAccountError').show();
			bankAccount = false;
		}else{
			$('.bankAccountError').hide();
			bankAccount = true;
		}
		if($('.userContacts').val() == ''){
			$('.userContactsError').show();
			userContacts = false;
		}else{
			$('.userContactsError').hide();
			userContacts = true;
		}
		if($('.userMobile').val() == ''){
			$('.userMobileError').show();
			userMobile = false;
		}else{
			$('.userMobileError').hide();
			userMobile = true;
		}
		if($('.userAddress').val() == ''){
			$('.userAddressError').show();
			userAddress = false;
		}else{
			$('.userAddressError').hide();
			userAddress = true;
		}
		if(orgName == true && taxId == true && regAddress == true && regMobile == true && bank == true && bankAccount == true && userContacts == true && userMobile == true && userMobile == true){
			if($('#orderSerial').val() == ''){
				$.ajax({
		            type:"post",
		            url:"/invoice/add",
		            async:true,
		            data:invoice,
		            datatype:'json',
					success:function(res){
						if(res.success){
							layer.open({
				              title: '温馨提示',
				              move:false,
				              // fixed:true,
				              content: res.msg
				            });   
							$('#orderSerial').val(res.data);
							$('.payButton').removeClass('disable');
				  			$('.payButton').removeAttr('disabled');
				  			$('.submit-check-info').hide();
						}else{
		  					layer.open({
				              title: '温馨提示',
				              move:false,
				              // fixed:true,
				              content: res.msg
				            });   
		  				}
		            }
		        });
			}else{
				invoice.orderId = $('#orderSerial').val();
				$.ajax({
		            type:"post",
		            url:"/invoice/add",
		            async:true,
		            data:invoice,
		            datatype:'json',
					success:function(res){
						if(res.success){
							layer.open({
				              title: '温馨提示',
				              move:false,
				              // fixed:true,
				              content: res.msg
				            });  
							$('#orderSerial').val(res.data);
							$('.payButton').removeClass('disable');
				  			$('.payButton').removeAttr('disabled');
				  			$('.submit-check-info').hide();
						}else{
		  					layer.open({
				              title: '温馨提示',
				              move:false,
				              // fixed:true,
				              content: res.msg
				            });   
		  				}
		            }
		        });
			}
		}
	});
	//退出登录
	$('body').on('click','.layout',function(){
		$.ajax({
			type:"get",
			url: "/user/logout",
			dataType:'json',
			async:true,
			success:function(res){
				if(res.success == true){
					$(".login_box").html('<a href="javascript:void(0)" id="login">登录</a>');
					$(".login_box").width('134px');
					window.location.reload();
				}else{
					layer.open({
				        title: '温馨提示',
				        move:false,
				        content: res.msg
				    });   
				}
			}
		});
	});
	
});
