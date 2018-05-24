$(function(){
		jQuery('body').on('click','#reg>h2>span',function(){
			jQuery('#reg>h2>span').removeClass('active');
			jQuery(this).addClass('active');
			jQuery('#reg>h2>span').find('i').hide();
			jQuery(this).find('i').show();
			if(jQuery(this).index() === 1){
				jQuery('.company').show();
				jQuery('.person').hide();
				jQuery('.layui-layer-content').height('835px');
				jQuery('.layui-layer').css({
					'top':'16px'
				})
			}
			if(jQuery(this).index() === 0){
				jQuery('.company').hide();
				jQuery('.person').show();
				jQuery('.layui-layer-content').height('696px');
				jQuery('.layui-layer').css({
					'top':'93px'
				})
			}
		})
	//  登录注册功能 
		var requestUrl = '/';
		/************个人注册*************/
		var per_email = false;
		var per_pwd = false;
		var per_repeatPwd = false;
		var per_mobileCode = false;
		var per_captcha = false;
		var per_mobile = false;
		$('.per_register').click(function(){
			checkPersonEmail();
			checkPersonPwd();
			checkPersonRepeatPwd();
			checkPersonMobileCode();
			checkPersonCaptcha();
			$.ajax({
				type:"get",
				url:requestUrl + "user/checkMobileCode",
				dataType:'json',
				async:false,
				data:{phoneNum:$('.per_mobile').val(),code:$('.per_mobileCode').val()},
				success:function(res){
					if(res.success == true){
						per_mobileCode = true;
					}else{
						errorTips($('.per_mobileCode_error'),res.msg);
						per_mobileCode = false;
					}
				}
			});
			if(per_email == true && per_pwd == true && per_repeatPwd == true && per_mobileCode == true && per_captcha == true){
				$.ajax({
					type:"post",
					url:requestUrl + "user/reg",
					dataType:'json',
					async:true,
					data:{email:$('.per_email').val(),password:$('.per_pwd').val(),confirm_pwd:$('.per_repeatPwd').val(),mobile:$('.per_mobile').val(),moblieCode:$('.per_mobileCode').val(),regType:'person',captcha:$('.per_captcha').val(),username:'',orgName:'',orgPersonName:''},
					success:function(res){
						if(res.success){
							window.location.href = '/user/toLogin';
						}else{
							errorTips($('.per_captcha_error'),res.msg);
						}
					}
				});
			}
		});
		//错误提示的公共函数
		function errorTips(obj,msg){
			obj.html('<i class="i-error"></i>' + msg);
		}
		//获取手机验证码
		$('.getMobile').click(function(){
			var mobileReg = /^1[0-9]{10}$/;
			//检测手机号码
			if($('.per_mobile').val().trim() == ''){
				$('.per_mobile').parent().addClass('regError');
				errorTips($('.per_mobileCode_error'),'请您输入手机号码');
				per_mobile = false;
			}else if(mobileReg.test($('.per_mobile').val().trim()) !== true || $('.per_mobile').val().trim().length < 11){
				$('.per_mobile').parent().addClass('regError');
				errorTips($('.per_mobileCode_error'),'请您输入正确的手机号码');
				per_mobile = false;
			}else{
				$('.per_mobile').parent().removeClass('regError');
				$('.per_mobile').parent().addClass('form_success');
				$('.per_mobileCode_error').html('');
				per_mobile = true;
			}
			if(per_mobile == true){
				$('.getMobile').attr('disabled',true);
				$('.getMobile').addClass('disabled');
				var timeNum = 59;
				$('.getMobile').text(timeNum + 's后可重新发送')
				$('.getMobile').addClass('disabled');
				var int = setInterval(function(){
					timeNum--;
					$('.getMobile').text(timeNum + 's后可重新发送')
					if(timeNum == 0){
						clearInterval(int);
						$('.getMobile').removeAttr('disabled');
						$('.getMobile').removeClass('disabled');
						$('.getMobile').text('获取验证码')
					}
				},1000);
				$.ajax({
					type:"get",
					url:requestUrl + "user/sendSms",
					dataType:'json',
					async:true,
					data:{phoneNum:$('.per_mobile').val()},
					success:function(res){
						if(res.success == true){
							$('.per_mobileCode_error').html('<i class="success"></i>获取验证码成功');
							$('.per_mobileCode_error').addClass('successCode');
							$('.per_mobileCode_error').removeClass('regError');
							per_mobileCode = true;
						}else{
							errorTips($('.per_mobileCode_error'),res.msg);
							per_mobileCode = true;
						}
					}
				});
			}
		});
		$('.per_email').on('blur',function(){
			checkPersonEmail();
		});
		$('.per_pwd').on('blur',function(){
			checkPersonPwd();
		});
		$('.per_repeatPwd').on('blur',function(){
			checkPersonRepeatPwd();
		});
//		$('.per_mobileCode').on('blur',function(){
//			checkPersonMobileCode();
//		});
//		$('.per_captcha').on('blur',function(){
//			checkPersonCaptcha();
//		});
		//校验邮箱
		function checkPersonEmail(){
			var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
			if($('.per_email').val() == ''){
				$('.per_email').parent().addClass('regError');
				$('.per_email').parent().removeClass('form_success');
				errorTips($('.per_email_error'),'请输入您的邮箱');
				per_email = false;
			}else if(emailReg.test($('.per_email').val().trim()) == false){
				$('.per_email').parent().addClass('regError');
				$('.per_email').parent().removeClass('form_success');
				errorTips($('.per_email_error'),'请输入合法邮箱格式');
				per_email = false;
			}else{
				$('.per_email').parent().removeClass('regError');
				$('.per_email').parent().addClass('form_success');
				$('.per_email_error').html('');
				per_email = true;
			}
		}
		//校验密码
		function checkPersonPwd(){
			if($('.per_pwd').val().trim() == ''){
				$('.per_pwd').parent().addClass('regError');
				$('.per_pwd').parent().removeClass('form_success');
				errorTips($('.per_pwd_error'),'请输入您的密码');
				per_pwd = false;
			}else if($('.per_pwd').val().trim().length < 6){
				$('.per_pwd').parent().addClass('regError');
				$('.per_pwd').parent().removeClass('form_success');
				errorTips($('.per_pwd_error'),'密码长度不能小于6位');
				per_pwd = false;
			}else if($('.per_pwd').val().trim().length > 20){
				$('.per_pwd').parent().addClass('regError');
				$('.per_pwd').parent().removeClass('form_success');
				errorTips($('.per_pwd_error'),'密码长度不能大于20位');
				per_pwd = false;
			}else if($('.per_pwd').val().trim().indexOf(' ') !== -1){
				$('.per_pwd').parent().addClass('regError');
				$('.per_pwd').parent().removeClass('form_success');
				errorTips($('.per_pwd_error'),'密码不能出现空格');
				per_pwd = false;
			}else{
				$('.per_pwd').parent().removeClass('regError');
				$('.per_pwd').parent().addClass('form_success');
				$('.per_pwd_error').html('');
				per_pwd = true;
			}
		}
		function checkPersonRepeatPwd(){
			//重复校验密码
			if($('.per_repeatPwd').val().trim() == ''){
				$('.per_repeatPwd').parent().addClass('regError');
				$('.per_repeatPwd').parent().removeClass('form_success');
				errorTips($('.per_repeatPwd_error'),'请再次输入您的密码');
				per_repeatPwd = false;
			}else if($('.per_pwd').val() !== $('.per_repeatPwd').val().trim()){
				$('.per_repeatPwd').parent().addClass('regError');
				$('.per_repeatPwd').parent().removeClass('form_success');
				errorTips($('.per_repeatPwd_error'),'密码不一致');
				per_repeatPwd = false;
			}else{
				$('.per_repeatPwd').parent().removeClass('regError');
				$('.per_repeatPwd').parent().addClass('form_success');
				$('.per_repeatPwd_error').html('');
				per_repeatPwd = true;
			}
		}
		function checkPersonMobileCode(){
			//手机验证码校验
			if($('.per_mobileCode').val().trim() == ''){
				$('.per_mobileCode').parent().addClass('regError');
				errorTips($('.per_mobileCode_error'),'请您输入手机接收到的验证码');
				$('.per_mobileCode_error').addClass('regError');
				$('.per_mobileCode_error').removeClass('successCode');
				per_mobileCode = false;
			}else{
				$('.per_mobileCode').parent().removeClass('regError');
				$('.per_mobileCode_error').html('');
				per_mobileCode = true;
			}
		}
		function checkPersonCaptcha(){
			//图片验证码
			if($('.per_captcha').val().trim() == ''){
				$('.per_captcha').parent().addClass('regError');
				errorTips($('.per_captcha_error'),'请您输入验证码');
				per_captcha = false;
			}else{
				$('.per_captcha').parent().removeClass('regError');
				$('.per_captcha_error').html('');
				per_captcha = true;
			}
		}
	/***************企业注册*****************/
		var com_email = false;
		var com_pwd = false;
		var com_repeatPwd = false;
		var com_mobileCode = false;
		var com_captcha = false;
		var com_mobile = false;
		var com_contact = false;
		var com_orgname = false;
		$('.com_register').click(function(){
			checkCompanyEmail();
			checkCompanyPwd();
			checkCompanyRepeatPwd();
			checkCompanyOrgname();
			checkCompanyContact();
			checkCompanyMobileCode();
			checkCompanyCaptcha();
			$.ajax({
				type:"get",
				url:requestUrl + "user/checkMobileCode",
				dataType:'json',
				async:false,
				data:{phoneNum:$('.com_mobile').val(),code:$('.com_mobileCode').val()},
				success:function(res){
					if(res.success == true){
						com_mobileCode = true;
					}else{
						errorTips($('.com_mobileCode_error'),res.msg);
						com_mobileCode = false;
					}
				}
			});
			if(com_email == true && com_pwd == true && com_repeatPwd == true && com_mobileCode == true && com_captcha == true && com_contact == true && com_orgname == true){
				$.ajax({
					type:"post",
					url:requestUrl + "user/reg",
					dataType:'json',
					async:true,
					data:{email:$('.com_email').val(),password:$('.com_pwd').val(),confirm_pwd:$('.com_repeatPwd').val(),mobile:$('.com_mobile').val(),moblieCode:$('.com_mobileCode').val(),regType:'org',captcha:$('.com_captcha').val(),username:'',orgName:$('.com_orgname').val(),orgPersonName:$('.com_contact').val()},
					success:function(res){
						if(res.success){
							window.location.href = '/user/toLogin';
						}else{
							errorTips($('.com_captcha_error'),res.msg);
						}
					}
				});
			}
		});
		//获取手机验证码
		$('.getComMobile').click(function(){
			var mobileReg = /^1[0-9]{10}$/;
			//检测手机号码
			if($('.com_mobile').val().trim() == ''){
				$('.com_mobile').parent().addClass('regError');
				errorTips($('.com_mobileCode_error'),'请您输入手机号码');
				com_mobile = false;
			}else if(mobileReg.test($('.com_mobile').val().trim()) !== true || $('.com_mobile').val().trim().length < 11){
				$('.com_mobile').parent().addClass('regError');
				errorTips($('.com_mobileCode_error'),'请您输入正确的手机号码');
				com_mobile = false;
			}else{
				$('.com_mobile').parent().removeClass('regError');
				$('.com_mobile').parent().addClass('form_success');
				$('.com_mobileCode_error').html('');
				com_mobile = true;
			}
			if(com_mobile == true){
				$('.getComMobile').attr('disabled',true);
				$('.getComMobile').addClass('disabled');
				var timeNum = 59;
				$('.getComMobile').text(timeNum + 's后可重新发送')
				$('.getComMobile').addClass('disabled');
				var int = setInterval(function(){
					timeNum--;
					$('.getComMobile').text(timeNum + 's后可重新发送')
					if(timeNum == 0){
						clearInterval(int);
						$('.getComMobile').removeAttr('disabled');
						$('.getComMobile').removeClass('disabled');
						$('.getComMobile').text('获取验证码')
					}
				},1000);
				$.ajax({
					type:"get",
					url:requestUrl + "user/sendSms",
					dataType:'json',
					async:true,
					data:{phoneNum:$('.com_mobile').val()},
					success:function(res){
//						console.log(res);
						if(res.success == true){
							$('.com_mobileCode_error').html('<i class="success"></i>获取验证码成功');
							$('.com_mobileCode_error').addClass('successCode');
							$('.com_mobileCode_error').removeClass('regError');
							com_mobileCode = true;
						}else{
							com_mobileCode = false;
							errorTips($('.com_mobileCode_error'),res.msg);
						}
					}
				});
			}
		});
		$('.com_email').on('blur',function(){
			checkCompanyEmail();
		});
		$('.com_pwd').on('blur',function(){
			checkCompanyPwd();
		});
		$('.com_repeatPwd').on('blur',function(){
			checkCompanyRepeatPwd();
		});
		$('.com_orgname').on('blur',function(){
			checkCompanyOrgname();
		});
		$('.com_contact').on('blur',function(){
			checkCompanyContact();
		});
//		$('.per_mobileCode').on('blur',function(){
//			checkPersonMobileCode();
//		});
//		$('.per_captcha').on('blur',function(){
//			checkPersonCaptcha();
//		});
		//校验邮箱
		function checkCompanyEmail(){
			var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
			if($('.com_email').val() == ''){
				$('.com_email').parent().addClass('regError');
				$('.com_email').parent().removeClass('form_success');
				errorTips($('.com_email_error'),'请输入您的邮箱');
				com_email = false;
			}else if(emailReg.test($('.com_email').val().trim()) == false){
				$('.com_email').parent().addClass('regError');
				$('.com_email').parent().removeClass('form_success');
				errorTips($('.com_email_error'),'请输入合法邮箱格式');
				com_email = false;
			}else{
				$.ajax({
					type:"get",
					url:requestUrl + "user/isOrgEmail",
					dataType:'json',
					async:true,
					data:{email:$('.com_email').val().trim()},
					success:function(res){
//						console.log(res);
						if(res.success == true){
							$('.com_email').parent().removeClass('regError');
							$('.com_email').parent().addClass('form_success');
							$('.com_email_error').html('');
							com_email = true;
						}else{
							$('.com_email').parent().addClass('regError');
							$('.com_email').parent().removeClass('form_success');
							errorTips($('.com_email_error'),res.msg);
							com_email = false;
						}
					}
				});
			}
		}
		//校验密码
		function checkCompanyPwd(){
			if($('.com_pwd').val().trim() == ''){
				$('.com_pwd').parent().addClass('regError');
				$('.com_pwd').parent().removeClass('form_success');
				errorTips($('.com_pwd_error'),'请输入您的密码');
				com_pwd = false;
			}else if($('.com_pwd').val().trim().length < 6){
				$('.com_pwd').parent().addClass('regError');
				$('.com_pwd').parent().removeClass('form_success');
				errorTips($('.com_pwd_error'),'密码长度不能小于6位');
				com_pwd = false;
			}else if($('.com_pwd').val().trim().length > 20){
				$('.com_pwd').parent().addClass('regError');
				$('.com_pwd').parent().removeClass('form_success');
				errorTips($('.com_pwd_error'),'密码长度不能大于20位');
				com_pwd = false;
			}else if($('.com_pwd').val().trim().indexOf(' ') !== -1){
				$('.com_pwd').parent().addClass('regError');
				$('.com_pwd').parent().removeClass('form_success');
				errorTips($('.com_pwd_error'),'密码不能出现空格');
				com_pwd = false;
			}else{
				$('.com_pwd').parent().removeClass('regError');
				$('.com_pwd').parent().addClass('form_success');
				$('.com_pwd_error').html('');
				com_pwd = true;
			}
		}
		function checkCompanyRepeatPwd(){
			//重复校验密码
			if($('.com_repeatPwd').val().trim() == ''){
				$('.com_repeatPwd').parent().addClass('regError');
				$('.com_repeatPwd').parent().removeClass('form_success');
				errorTips($('.com_repeatPwd_error'),'请再次输入您的密码');
				com_repeatPwd = false;
			}else if($('.com_pwd').val().trim() !== $('.com_repeatPwd').val().trim()){
				$('.com_repeatPwd').parent().addClass('regError');
				$('.com_repeatPwd').parent().removeClass('form_success');
				errorTips($('.com_repeatPwd_error'),'密码不一致');
				com_repeatPwd = false;
			}else{
				$('.com_repeatPwd').parent().removeClass('regError');
				$('.com_repeatPwd').parent().addClass('form_success');
				$('.com_repeatPwd_error').html('');
				com_repeatPwd = true;
			}
		}
		function checkCompanyOrgname(){
			//企业名称校验
			if($('.com_orgname').val().trim() == ''){
				$('.com_orgname').parent().addClass('regError');
				$('.com_orgname').parent().removeClass('form_success');
				errorTips($('.com_orgname_error'),'请输入您的公司名称');
				com_orgname = false;
			}else{
				$('.com_orgname').parent().removeClass('regError');
				$('.com_orgname').parent().addClass('form_success');
				$('.com_orgname_error').html('');
				com_orgname = true;
			}
		}
		function checkCompanyContact(){
			//企业联系人校验
			var contactReg =  /^[\u4e00-\u9fa5]+$/;
			//企业名称校验
			if($('.com_contact').val().trim() == ''){
				$('.com_contact').parent().addClass('regError');
				$('.com_contact').parent().removeClass('form_success');
				errorTips($('.com_contact_error'),'请输入联系人');
				com_contact = false;
			}else if(contactReg.test($('.com_contact').val().trim()) == false){
				$('.com_contact').parent().addClass('regError');
				$('.com_contact').parent().removeClass('form_success');
				errorTips($('.com_contact_error'),'只能输入汉字');
				com_contact = false;	
			}else{
				$('.com_contact').parent().removeClass('regError');
				$('.com_contact').parent().addClass('form_success');
				$('.com_contact_error').html('');
				com_contact = true;
			}
		}
		function checkCompanyMobileCode(){
			//手机验证码校验
			if($('.com_mobileCode').val().trim() == ''){
				$('.com_mobileCode').parent().addClass('regError');
				errorTips($('.com_mobileCode_error'),'请您输入手机接收到的验证码');
				$('.com_mobileCode_error').addClass('regError');
				$('.com_mobileCode_error').removeClass('successCode');
				com_mobileCode = false;
			}else{
				$('.com_mobileCode').parent().removeClass('regError');
				$('.com_mobileCode_error').html('');
				com_mobileCode = true;
			}
		}
		function checkCompanyCaptcha(){
			//图片验证码
			if($('.com_captcha').val().trim() == ''){
				$('.com_captcha').parent().addClass('regError');
				errorTips($('.com_captcha_error'),'请您输入验证码');
				com_captcha = false;
			}else{
				$('.com_captcha').parent().removeClass('regError');
				$('.com_captcha_error').html('');
				com_captcha = true;
			}
		}
  });