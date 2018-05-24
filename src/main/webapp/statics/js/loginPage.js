$(function(){
	//错误提示的公共函数
		function errorTips(obj,msg){
			obj.html('<i class="i-error"></i>' + msg);
		}
	var namePwd = false;
	var username = '';
	var requestUrl = '/';
	$('.login').click(function(){
		username = $('#name').val().trim();
		if($('#name').val().trim() == '' || $('#pwd').val().trim() == ''){
			errorTips($('.login_error'),'账号密码不能为空');
			namePwd = false;
		}else if($('#yzm').val().trim() == ''){
			errorTips($('.login_error'),'验证码不能为空');
			namePwd = false
		}else{
			$('.login_error').html('');
			namePwd = true;
		}
		if(namePwd == true){
			$.ajax({
				type:"post",
				url:requestUrl + "user/login",
				dataType:'json',
				async:true,
				data:{email:$('#name').val(),password:$('#pwd').val(),captcha:$('#yzm').val()},
				success:function(res){
					console.log(res);
					if(res.success){
						window.location.href = '/product/buy';
					}else{
						errorTips($('.login_error'),res.msg);
					}
				}
			});
		}
	});
	document.onkeydown = function(event_e){    
        if(window.event)    
         event_e = window.event;    
         var int_keycode = event_e.charCode||event_e.keyCode;    
         if(int_keycode ==13){   
         	username = $('#name').val().trim();
		if($('#name').val().trim() == '' || $('#pwd').val().trim() == ''){
			errorTips($('.login_error'),'账号密码不能为空');
			namePwd = false;
		}else if($('#yzm').val().trim() == ''){
			errorTips($('.login_error'),'验证码不能为空');
			namePwd = false
		}else{
			$('.login_error').html('');
			namePwd = true;
		}
		if(namePwd == true){
			$.ajax({
				type:"post",
				url:requestUrl + "user/login",
				dataType:'json',
				async:true,
				data:{email:$('#name').val(),password:$('#pwd').val(),captcha:$('#yzm').val()},
				success:function(res){
//					console.log(res);
					if(res.success){
						window.location.href = '/product/buy';
					}else{
						errorTips($('.login_error'),res.msg);
					}
				}
			});
		}
        }  
    }    
});
