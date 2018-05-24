$(function(){
	jQuery('.cart_button').click(function(){
  		popupCart();
  	});
  	jQuery('.cart>h2').click(function(){
  		jQuery('.cart').animate({
  			'right':'-262px'
  		},500);
  		jQuery('.cart_button').animate({
  			"right":'0',
  			"opacity":'1'
  		},500);
  	});
  	function popupCart(){
  		jQuery('cart_button').animate({
  			"right":'-69px',
  			"opacity":'0'
  		},500)
  		jQuery('.cart').animate({
  			'right':'0'
  		},500);
  	}
  	jQuery('.main_inner>.left li').click(function(){
  		
  		jQuery('.main_inner>.left li').removeClass('active');
  		jQuery(this).addClass('active');
  		if(jQuery(this).hasClass('one')){
  			jQuery('.street').show();
  			jQuery('.district').hide();
  			jQuery('.scene').hide();
  			jQuery(this).find('img').attr('src','/statics/images/t_s_title1_white.png');
  			jQuery('.two').find('img').attr('src','/statics/images/t_s_title2.png');
  			jQuery('.three').find('img').attr('src','/statics/images/t_s_title3.png');
  		}
  		if(jQuery(this).hasClass('two')){
  			jQuery('.street').hide();
  			jQuery('.district').show();
  			jQuery('.scene').hide();
  			jQuery(this).find('img').attr('src','/statics/images/t_s_title2_white.png');
  			jQuery('.one').find('img').attr('src','/statics/images/t_s_title1.png');
  			jQuery('.three').find('img').attr('src','/statics/images/t_s_title3.png');
  		}
  		if(jQuery(this).hasClass('three')){
  			jQuery('.street').hide();
  			jQuery('.district').hide();
  			jQuery('.scene').show();
  			jQuery(this).find('img').attr('src','/statics/images/t_s_title3_white.png');
  			jQuery('.two').find('img').attr('src','/statics/images/t_s_title2.png');
  			jQuery('.one').find('img').attr('src','/statics/images/t_s_title1.png');
  		}
  	});
  	jQuery('.district_API').click(function(){
  		var _this = jQuery(this);
  		jQuery(this).next().slideToggle(function(){
  			if($(this).is(":visible")){
	           _this.find('h2').find('i').html('&#xe7de;');
	           $('.district_api_pay').show();
	           $('.district_download_pay').hide();
	           $('.district_download2_pay').hide();
	        }else{
	           _this.find('h2').find('i').html('&#xe6ca;');
	           $('.district_api_pay').hide();
	           if(versionId == '3'){
	           	  	$('.district_download_pay').show();
	           		$('.district_download2_pay').hide();	
	           }else{
	           		$('.district_download_pay').hide();
	           		$('.district_download2_pay').show();	
	           }
	        }
  		});
  		jQuery('.district_download').next().slideToggle(function(){
  			if($(this).is(":visible")){
	           jQuery('.district_download').find('h2').find('i').html('&#xe7de;');
	           $('.district_api_pay').hide();
	           if(versionId == '3'){
	           	  	$('.district_download_pay').show();
	           		$('.district_download2_pay').hide();	
	           }else{
	           		$('.district_download_pay').hide();
	           		$('.district_download2_pay').show();	
	           }
	        }else{
	           jQuery('.district_download').find('h2').find('i').html('&#xe6ca;');
	           $('.district_api_pay').show();
	           $('.district_download_pay').hide();
	           $('.district_download2_pay').hide();
	        }
  		});
  	});
  	jQuery('.district_download').click(function(){
  		var _this = jQuery(this);
  		jQuery(this).next().slideToggle(function(){
  			if($(this).is(":visible")){
	           _this.find('h2').find('i').html('&#xe7de;');
	           $('.district_api_pay').hide();
	           if(versionId == '3'){
	           	  	$('.district_download_pay').show();
	           		$('.district_download2_pay').hide();	
	           }else{
	           		$('.district_download_pay').hide();
	           		$('.district_download2_pay').show();	
	           }
	        }else{
	           _this.find('h2').find('i').html('&#xe6ca;');
	           $('.district_api_pay').show();
	           $('.district_download_pay').hide();
	           $('.district_download2_pay').hide();
	        }
  		});
  		jQuery('.district_API').next().slideToggle(function(){
  			if($(this).is(":visible")){
	           jQuery('.district_API').find('h2').find('i').html('&#xe7de;');
	           $('.district_api_pay').show();
	           $('.district_download_pay').hide();
	           $('.district_download2_pay').hide();
	        }else{
	           jQuery('.district_API').find('h2').find('i').html('&#xe6ca;');
	           $('.district_api_pay').hide();
	           if(versionId == '3'){
	           	  	$('.district_download_pay').show();
	           		$('.district_download2_pay').hide();	
	           }else{
	           		$('.district_download_pay').hide();
	           		$('.district_download2_pay').show();	
	           }
	        }
  		});
  	});
//	jQuery('.scene_API').click(function(){
//		var _this = jQuery(this);
//		jQuery(this).next().slideToggle(function(){
//			if($(this).is(":visible")){
//	           _this.find('h2').find('i').html('&#xe7de;');
//	           $('.scene_api_pay').show();
//	           $('.scene_download_pay').hide();
//	        }else{
//	           _this.find('h2').find('i').html('&#xe6ca;');
//	           $('.scene_api_pay').hide();
//	           $('.scene_download_pay').show();
//	        }
//		});
//		jQuery('.scene_download').next().slideToggle(function(){
//			if($(this).is(":visible")){
//	           jQuery('.scene_download').find('h2').find('i').html('&#xe7de;');
//	           $('.scene_api_pay').hide();
//	           $('.scene_download_pay').show();
//	        }else{
//	           jQuery('.scene_download').find('h2').find('i').html('&#xe6ca;');
//	           $('.scene_api_pay').show();
//	           $('.scene_download_pay').hide();
//	        }
//		});
//	});
  	jQuery('.scene_download').click(function(){
  		var _this = jQuery(this);
  		jQuery(this).next().slideToggle(function(){
  			if($(this).is(":visible")){
	           _this.find('h2').find('i').html('&#xe7de;');
	            $('.scene_api_pay').hide();
	           $('.scene_download_pay').show();
	        }else{
	           _this.find('h2').find('i').html('&#xe6ca;');
	           $('.scene_api_pay').show();
	           $('.scene_download_pay').hide();
	        }
  		});
  		jQuery('.scene_API').next().slideToggle(function(){
  			if($(this).is(":visible")){
	           jQuery('.scene_API').find('h2').find('i').html('&#xe7de;');
	            $('.scene_api_pay').show();
	           $('.scene_download_pay').hide();
	        }else{
	           jQuery('.scene_API').find('h2').find('i').html('&#xe6ca;');
	            $('.scene_api_pay').hide();
	           $('.scene_download_pay').show();
	        }
  		});
  	});
  	function alertMsg(msg){
  		layer.open({
		    title: '温馨提示',
		    move:false,
		    content:msg
        });  
  	}
  	/*********** 进入页面需要准备的几个部分 *********/
  	var requestUrl = '/';
  	//获取购物车的数量
  	$.ajax({
  		type:"get",
  		dataType: 'json',
  		url:requestUrl + "cart/size",
  		async:true,
  		success:function(res){
			if(res.success == true){
				$('.cart_button .inner span').text(res.data);
			}
  		}
  	});
  	//获取用户购物车列表
  	$.ajax({
	  	type:"get",
	  	url:requestUrl + "cart/list",
	  	async:true,
	  	dataType: 'json',
	  	data:{},
	  	success:function(data){
        var cartListStr = '';
	  		if(data.success == true){
				$('.cart_button .inner span').text(data.data.cartItems.length);
				if(data.data.cartItems.length == 0 || data.data == null){
					$('.cart .content').height('179px');
					cartListStr += '<li class="null">' + '购物车是空的,快去选购产品吧 '+ '</li>';
					$('.cart .content').html(cartListStr);
					$('.cart .cart_pay p em').html('¥0.00');
				}	
				if(data.data.cartItems.length == 1){
					$('.cart .content').height('179px');
					for(var i = 0;i < data.data.cartItems.length; i++){
						cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
					}
					$('.cart .content').html(cartListStr);
					$('.cart .cart_pay p em').html('¥' + data.data.price);
				}
				if(data.data.cartItems.length > 1){
					$('.cart .content').height('321px');
					for(var i = 0;i < data.data.cartItems.length; i++){
						cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
					}
					$('.cart .content').html(cartListStr);
					$('.cart .cart_pay p em').html('¥' + data.data.price);
				}
			}else{
				$('.cart .content').height('179px');
				cartListStr += '<li class="null">' + '购物车是空的,快去选购产品吧 '+ '</li>';
				$('.cart .content').html(cartListStr);
				$('.cart .cart_pay p em').html('¥0.00');
			}
		}
  	});
  	//删除购物车商品
  	$('body').on('click', '.cart .content li h2 i', function(){
  		var cartId = $(this).parent().find('span').text();
//		console.log(cartId);
  		$.ajax({
	  		type:"get",
	  		url:requestUrl + "cart/delete",
	  		async:true,
	  		dataType: 'json',
	  		data:{itemId:cartId},
	  		success:function(res){
				 deleteCartList(res);
	  		}
  		});
  	});
  	/*********** IP问问高精准部分代码 ************/ 
  	var streetNumber = 1;
  	var streetPriceId = 1;
  	var streetPricePackage = [];
  	//请求获得IP问问高精准的商品详情
  	$.ajax({
  		type:"get",
  		url:requestUrl + "product/detail",
  		async:true,
  		dataType: 'json',
  		data:{pid:1},
  		success:function(res){
//			console.log(res);
  			if(res.success == true){
//				console.log(res.data.pricePackage);
  				streetPricePackage.push(res.data.pricePackage);
  				var streetList = '';
  				for(var i = 0; i < res.data.pricePackage.length; i++){
  					streetList += '<li priceId=' + res.data.pricePackage[i].id + '>' + res.data.pricePackage[i].amountStr + '</li>';
  				}
  				$('.street_model_list').html(streetList);
  				$('.streetPrice').html(res.data.pricePackage[0].price);
  				$('.streetTips').html(res.data.pricePackage[0].tips);
  				$('.streetTotal').html(res.data.pricePackage[0].totalPrice);
  				$('.street_model_list li').eq(0).addClass('active');
  				$('.street_model_list li').eq(5).addClass('last');
  			}else{
  				alertMsg(res.msg);
  			}
  		}
  	});
  	//请求获得IP问问高精准的价格包信息
  	$.ajax({
  		type:"get",
  		url:requestUrl + "product/prices",
  		async:true,
  		dataType: 'json',
  		data:{pid:1},
  		success:function(res){
//			console.log(res);
  		}
  	});
  	
  	//点击获取当前的价格包信息
  	$('body').on('click','.street_model_list li',function(){
  		$('.street_model_list li').removeClass('active');
  		$(this).addClass('active');
  		streetPriceId = $(this).attr('priceId');
//		console.log(streetPricePackage);
  		var index = $(this).index();
  		$('.streetPrice').html(streetPricePackage[0][index].price);
  		$('.streetTips').html(streetPricePackage[0][index].tips);
  		$('.streetTotal').html(streetPricePackage[0][index].totalPrice);
  	});
  	
  	$('.street .reduce').click(function(){
  		streetNumber--;
  		$('#number').val(streetNumber);
  		if(streetNumber <= 1){
  			streetNumber = 1;
  			$('#number').val(streetNumber);
  			return false;
  		}
  	});
  	$('.street .add').click(function(){
  		streetNumber++;
  		$('#number').val(streetNumber);
  		if(streetNumber >= 1000){
  			streetNumber = 1000;
  			$('#number').val(streetNumber);
  			return false;
  		}
  	});
  	//添加IP问问高精准到购物车
  	$('.street .addCart').click(function(){
  		$.ajax({
	  		type:"post",
	  		url:requestUrl + "cart/add",
	  		async:true,
	  		 dataType: 'json',
	  		data:{ pId:1, priceId:streetPriceId,accuracyId:1,count:streetNumber,cartItemType:''},
	  		success:function(res){
				createCartList(res);
		  	}
  		});
  	});
    //删除购物车列表方法
  function deleteCartList(data){
    if(data.success == true){
      popupCart();
      $('.cart_button .inner span').text(data.data.cartItems.length);
      var cartListStr = '';
      if(data.data.cartItems.length == 0 || data.data == null){
        $('.cart .content').height('179px');
        cartListStr += '<li class="null">' + '购物车是空的,快去选购产品吧 '+ '</li>';
        $('.cart .content').html(cartListStr);
        $('.cart .cart_pay p em').html('¥0.00');
      } 
      if(data.data.cartItems.length == 1){
        $('.cart .content').height('179px');
        for(var i = 0;i < data.data.cartItems.length; i++){
          cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
        }
        $('.cart .content').html(cartListStr);
        $('.cart .cart_pay p em').html('¥' + data.data.price);
      }
      if(data.data.cartItems.length > 1){
        $('.cart .content').height('321px');
        for(var i = 0;i < data.data.cartItems.length; i++){
          cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
        }
        $('.cart .content').html(cartListStr);
        $('.cart .cart_pay p em').html('¥' + data.data.price);
      }
    }else{
      $('.cart .content').height('179px');
      cartListStr += '<li class="null">' + '购物车是空的,快去选购产品吧 '+ '</li>';
      $('.cart .content').html(cartListStr);
      $('.cart .cart_pay p em').html('¥0.00');
    }
  } 
  	//添加购物车列表方法
	function createCartList(data){
		if(data.success == true){
			popupCart();
			$('.cart_button .inner span').text(data.data.cartItems.length);
			var cartListStr = '';
			if(data.data.cartItems.length == 0 || data.data == null){
				$('.cart .content').height('179px');
				cartListStr += '<li class="null">' + '购物车是空的,快去选购产品吧 '+ '</li>';
				$('.cart .content').html(cartListStr);
				$('.cart .cart_pay p em').html('¥0.00');
			}	
			if(data.data.cartItems.length == 1){
				$('.cart .content').height('179px');
				for(var i = 0;i < data.data.cartItems.length; i++){
					cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
				}
				$('.cart .content').html(cartListStr);
				$('.cart .cart_pay p em').html('¥' + data.data.price);
			}
			if(data.data.cartItems.length > 1){
				$('.cart .content').height('321px');
				for(var i = 0;i < data.data.cartItems.length; i++){
					cartListStr += '<li><h2>'+ data.data.cartItems[i].product.productName + '<span>' + data.data.cartItems[i].id + '</span>' +'<i class="iconfont">&#xe618;</i></h2><p>购买规格：'+ (data.data.cartItems[i].pricePackage.amountStr ? data.data.cartItems[i].pricePackage.amountStr : '') +'</p><p>购买数量：'+ data.data.cartItems[i].itemNum +'个</p><p>计费单价：'+ (data.data.cartItems[i].pricePackage.totalPrice ? '¥' + data.data.cartItems[i].pricePackage.totalPrice : '') +'</p><p>计费金额：¥'+ data.data.cartItems[i].originalPrice +'</p><p>支付金额：<em>¥'+ data.data.cartItems[i].price +'</em></p></li>'
				}
				$('.cart .content').html(cartListStr);
				$('.cart .cart_pay p em').html('¥' + data.data.price);
			}
		}else{
			if(data.msg == '您还没有登录'){
				indexLogin = layer.open({
					type: 1,
					area: ['534px'],
					shadeClose: true, //点击遮罩关闭
					resize:false,
					move:false,
					title:false,
					closeBtn:1,
					fixed:false,
					content:$('#pop')
	//				content: '<div id="pop"><h2><span style="color:#7e7e7e;font-size: 22px;">登录</span><span class="two">还没有账号？点击<a id="register" href="javascript:;">立即注册</a></span></h2><input type="text" name="" id="name" value="" placeholder="账户"/><input type="password" name="" id="pwd" value="" placeholder="密码"/><div class="yzm"><span> <img src=""/></span><input type="text" name="" id="yzm" value="" placeholder="请输入验证码"/></div><button>立即登录</button><p style=""><a href="">忘记密码</a></p></div>',
				});
				jQuery('.layui-layer-close').removeClass('layui-layer-close2');
				jQuery('.layui-layer-close').addClass('layui-layer-close1');
			}else{
		        layer.open({
		        	title: '温馨提示',
		          	move:false,
		          	content: data.msg
        		});     
      }
		}
	}	
  	/*********** IP问问区县级部分代码 ************/
  	var districtApiPricePackage = [];
  	var districtApiPriceId = 7;
  	var districtApiYear = 1;
  	var versionId = 3;
  	var coordId = 1;
  	var hId = '';
  	//请求获得IP问问区县级的商品详情
  	$.ajax({
  		type:"get",
  		url:requestUrl + "product/detail",
  		async:true,
  		dataType: 'json',
  		data:{pid:5},
  		success:function(res){
//			console.log(res);
  			if(res.success == true){
//				console.log(res.data.pricePackage);
//  				var districtApiList = '';
  				districtApiPricePackage.push(res.data.pricePackage);
//  				for(var i = 0; i < res.data.pricePackage.length; i++){
//  					districtApiList += '<li districtApiPriceId=' + res.data.pricePackage[i].id + '>' + res.data.pricePackage[i].amountStr +' <span>'+ res.data.pricePackage[i].totalPrice.split('.')[0] + '/' + res.data.pricePackage[i].unit +'<b></b></span> </li>';
//  				}
//  				$('.district_API_amout').html(districtApiList);
//  				$('.district_api_total').html(res.data.pricePackage[0].totalPrice);
//  				$('.district_API_amout li').eq(0).addClass('active');
//  				$('.district_API_amout li').eq(3).addClass('last');
  			}else{
  				alertMsg(res.msg);
  			}
  		}
  	});
  	var districtApiIndex;
  	//区县API 价格包的点击事件
  	$('body').on('click','.district_API_amout li', function(){
  		$('.district_API_amout li').removeClass('active');
  		$(this).addClass('active');
  		districtApiPriceId = $(this).attr('districtApiPriceId');
//		console.log(districtApiPriceId);
  		districtApiIndex = $(this).index();
  		if(districtApiIndex !== 4){
         	$('.free_tips').hide();
  			$('.district_api_total').html(districtApiPricePackage[0][districtApiIndex].totalPrice);
        	$('.district_api_single').html(districtApiPricePackage[0][districtApiIndex].totalPrice + '元/年');
  			$('.time_list').html('<li districtApiYear="1" class="active">1年</li><li districtApiYear="2">2年</li><li districtApiYear="3" class="last">3年</li>');
  		}else{
  			$('.district_api_total').html("0.00");
        	$('.district_api_single').html('限时免费');
  			$('.time_list').html('<li class="active last">1年</li>');
        	$('.free_tips').show();
  		}
  	});
  	//区县API 购买年限的选择
  	$('body').on('click','.time_list li',function(){
  		$('.time_list li').removeClass('active');
  		$(this).addClass('active');
  		districtApiYear = $(this).attr('districtApiYear');
  	});
  	//区县API 添加购物车
  	$('.district_api_pay .addCart').click(function(){
//		console.log(districtApiIndex);
  		if(districtApiIndex !== 4){
  			$.ajax({
  		  		type:"post",
  		  		url:requestUrl + "cart/add",
  		  		async:true,
  		  		 dataType: 'json',
  		  		data:{ pId:5, priceId:districtApiPriceId,accuracyId:2,count:districtApiYear,cartItemType:'IPDistrictAPI'},
  		  		success:function(res){
  					createCartList(res);
  			  	}
  	  		});
  		}else{
  			$.ajax({
  		  		type:"post",
  		  		url:requestUrl + "cart/add",
  		  		async:true,
  		  		dataType: 'json',
  		  		data:{ pId:5, priceId:districtApiPriceId,accuracyId:2,count:1,cartItemType:'IPDistrictAPI',freeApi:1},
  		  		success:function(res){
  					createCartList(res);
  			  	}
  	  		});
  		}
  		
  	});
  	//区县库下载 版本选择
  	$('.district_version li').click(function(){
  		$('.district_version li').removeClass('active');
  		$(this).addClass('active');
  		versionId = $(this).attr('versionId');
  		if($(this).index() == 0){
  			$('.district_now').show();
  			$('.district_history').hide();
  			
  			$('.district_api_pay').hide();
	        $('.district_download_pay').show();
	        $('.district_download2_pay').hide();
  		}else{
  			$('.district_now').hide();
  			$('.district_history').show();
  			
  			$('.district_api_pay').hide();
	        $('.district_download_pay').hide();
	        $('.district_download2_pay').show();
  		}
  	});
  	//区县库下载 坐标系选择
  	$('.coordinate li').click(function(){
  		$('.coordinate li').removeClass('active');
  		$(this).addClass('active');
  		coordId = $(this).attr('coordId');
  		if(coordId == 1){
  			$('.district_download_total').text('0.00');
  		}else{
  			$('.district_download_total').text('3680.00');
  		}
  	});
  	//请求获得 区县库的历史版本 月份
  	$.ajax({
  		type:"get",
  		url:requestUrl + "product/getPreVers",
  		async:true,
  		data:{pid:5},
  		dataType:'json',
  		success:function(res){
			if(res.success == true){
//				console.log(res);
				var districtHistoryStr = '';
				for(var i = 0; i < res.data.length; i++){
					districtHistoryStr += '<li hId=' + res.data[i].id + '>' + res.data[i].attrValue + '</li>'
				}
				$('.district_history ul').html(districtHistoryStr);
				$('.district_history ul li').eq(0).addClass('active');
				hId = res.data[0].id;
			}
  		}
  	});
  	//区县库下载历史版本月份选择
  	$('body').on('click','.district_history ul li', function(){
  		$('.district_history ul li').removeClass('active');
  		$(this).addClass('active');
  		hId = $(this).attr('hId');
  	});
  	//区县库下载 当前版本增加到购物车
  	$('.district_download_pay .addCart').click(function(){
  		$.ajax({
	  		type:"post",
	  		url:requestUrl + "cart/addDictrict",
	  		async:true,
	  		 dataType: 'json',
	  		data:{ pid:5, cid:coordId,vid:versionId,cartItemType:'IPDistrictDownload'},
	  		success:function(res){
				createCartList(res);
		  	}
  		});
  	});
  	//区县库下载 历史版本增加到购物车
  	$('.district_download2_pay .addCart').click(function(){
  		$.ajax({
	  		type:"post",
	  		url:requestUrl + "cart/addDictrict",
	  		async:true,
	  		dataType: 'json',
	  		data:{ pid:5, cid:coordId,vid:versionId,hid:hId,cartItemType:'IPDistrictDownload'},
	  		success:function(res){
				createCartList(res);
		  	}
  		});
  	});
  	/*********** IP场景部分代码 ************/
  	var sceneNumber = 1;
  	var scenePricePackage = [];
  	var scenePriceId = 11;
  	//请求IP场景 商品详情
  	$.ajax({
  		type:"get",
  		url:requestUrl + "product/detail",
  		async:true,
  		dataType:'json',
  		data:{pid:3},
  		success:function(res){
			if(res.success == true){
//				console.log(res.data.pricePackage);
  				scenePricePackage.push(res.data.pricePackage);
  				var sceneList = '';
  				for(var i = 0; i < res.data.pricePackage.length; i++){
  					sceneList += '<li priceId=' + res.data.pricePackage[i].id + '>' + res.data.pricePackage[i].amountStr + '</li>';
  				}
  				$('.scene_model_list').html(sceneList);
  				$('.scenePrice').html(res.data.pricePackage[0].price);
  				$('.sceneTotal').html(res.data.pricePackage[0].totalPrice);
  				$('.scene_model_list li').eq(0).addClass('active');
  				$('.scene_model_list li').eq(4).addClass('last');
  			}else{
  				alertMsg(res.msg);
  			}
  		}
  	});
  	//点击获取当前的价格包信息
  	$('body').on('click','.scene_model_list li',function(){
  		$('.scene_model_list li').removeClass('active');
  		$(this).addClass('active');
  		scenePriceId = $(this).attr('priceId');
//		console.log(scenePriceId);
  		var index = $(this).index();
  		$('.scenePrice').html(scenePricePackage[0][index].price);
  		$('.sceneTotal').html(scenePricePackage[0][index].totalPrice);
  	});
  	$('.scene .reduce').click(function(){
  		sceneNumber--;
  		$('#sceneNumber').val(sceneNumber);
  		if(sceneNumber <= 1){
  			sceneNumber = 1;
  			$('#sceneNumber').val(sceneNumber);
  			return false;
  		}
  	});
  	$('.scene .add').click(function(){
  		sceneNumber++;
  		$('#sceneNumber').val(sceneNumber);
  		if(sceneNumber >= 1000){
  			sceneNumber = 1000;
  			$('#sceneNumber').val(sceneNumber);
  			return false;
  		}
  	});
   	//场景API添加到购物车
   	$('.scene_api_pay .addCart').click(function(){
   		$.ajax({
	  		type:"post",
	  		url:requestUrl + "cart/add",
	  		async:true,
	  		 dataType: 'json',
	  		data:{ pId:3, priceId:scenePriceId,accuracyId:1,count:sceneNumber,cartItemType:'IPSceneAPI'},
	  		success:function(res){
				createCartList(res);
		  	}
  		});
   	});
   	//根据条件 选择场景 确定价格
   	$('.scene_download_single_list li').click(function(){
   		$('.scene_download_more_list li').removeClass('active');
   		if($(this).index() == 1 || $(this).index() == 5){
   			$('.scene_download_single_list li').removeClass('active');
   			$(this).addClass('active');
   			$(this).next().addClass('active');
   			var str = $(this).find('span').text();
   			$('.sceneDownloadTotal').text(str.substr(0,str.length-3));
   		}else if($(this).index() == 2 || $(this).index() == 6){
   			$('.scene_download_single_list li').removeClass('active');
   			$(this).addClass('active');
   			$(this).prev().addClass('active');
   			var str1 = $(this).prev().find('span').text();
   			$('.sceneDownloadTotal').text(str1.substr(0,str1.length-3));
   		}else{
   			var str3 = $(this).find('span').text();
   			$('.sceneDownloadTotal').text(str3.substr(0,str3.length-3));
   			$('.scene_download_single_list li').removeClass('active');
   			$(this).addClass('active');
   		}
   	});
   	$('.scene_download_more_list li').click(function(){
   		$('.scene_download_more_list li').addClass('active');
   		$('.scene_download_single_list li').removeClass('active');
   		var str2 = $('.scene_download_more_list li').eq(0).find('span').text();
   		$('.sceneDownloadTotal').text(str2.substring(0,str2.length - 3));
   	});
   	var height = $(window).height(); //浏览器当前窗口可视区域高度
    $('#main .cart').css({
      'top':(height - 460)/2
    });
    
});


