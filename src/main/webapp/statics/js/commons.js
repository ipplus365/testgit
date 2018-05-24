// 版本处理
var api_url = "";
$.ajax({
    type : "get",
    url : "/version/current",
    dataType : "json",
    success : function(obj){
        var mydata = obj.data;
        $("#data_version")[0].innerText = "当前数据版本 : " + mydata.publicVersion.replace("ip_","V").replace(/_/g,".");
        $("#update_time")[0].innerText = "更新日期 : " + new Date(mydata.startTime).format("yyyy年mm月dd日");
    },
    error:function(){
        console.log("Error");
    }
});
// 用户信息
$.ajax({
        type : "get",
        url : "/info/user",
        dataType : "json",
        success : function(obj){
            // console.log(obj);
            if (obj.success == true){
                var user_data = obj.data.user;
                // 用户名处理
                $(".login_account span").each(function(){
                    //$(this)[0].innerText = user_data.email;
                    $(this)[0].innerHTML = "<a href='/info'>" + user_data.email + "</a>";
                });
            }else{
//              alert('为了您的安全，请您先登录');
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

        },
        error : function(){
            console.log("Error");
        }
    });

/////////////////////////////////////
function initialize() {
  map = new BMap.Map('mymap');  
  map.centerAndZoom(new BMap.Point(116.404269,39.914714), 15);  
  map.enableScrollWheelZoom();   
 // map.setMapStyle({style:'grayscale'});     
  map.addEventListener("dragend", hideLeftInfo);  
  
  var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT});   //设置版权控件位置
	map.addControl(cr); //添加版权控件

	cr.addCopyright({id: 1, content: "<div class='supported'>数据 © <a href='http://www.ipplus360.com/' target='_blank'>埃文科技</a> supported</div>"});   
	//Copyright(id,content,bounds)类作为CopyrightControl.addCopyright()方法的参数
	var myDis = new BMapLib.DistanceTool(map);    
	jQuery("#measure_map").click(function(){
		myDis.open(); 
	});  
}     

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function hideLeftInfo(){	
if(jQuery("#noShowResult").is(':hidden') && jQuery("#searchipt").val()!=''){
	jQuery(".showResult").slideUp(100,function(){
		jQuery(".amap-search-panctrl").slideDown();
		jQuery(".pois").hide(); 
		jQuery("#more i").removeClass("active");
	});		
}
}
jQuery(function(){
	initialize();	
	var url_search_ip = GetQueryString("ip");
    if (url_search_ip){
        jQuery("#searchipt").val(url_search_ip); 
    }else{
        ;
    }
    
	 //============getip===========
 //getRealIp();  // 第一次查询
	 jQuery(".amap-search-panctrl").mouseover(function(){
	 jQuery(this).hide();
	 jQuery("#showResult").show();
	 jQuery(".pois").slideDown();
 });
 jQuery("#searchipt").keyup(function(event){
	 jQuery(this).removeClass("waring");
	var _input = jQuery(this).val().trim();
	if(_input!=''){
		jQuery(".input-clear").show();
	}else{
		jQuery(".input-clear").hide();
		return ;
	}
	
	
	if(event.keyCode==13){
		//enter 键提交查询
    if(isInputIp(_input)){
		getIpInfo(_input);
	}
	if(isIP2(_int2iP(_input))&&isNumber(_input)){
		getIpInfo(_int2iP(_input));
	}
	}
	
 }); 
  jQuery(".input-clear").click(function(){
	 jQuery("#searchipt").val("").focus();
	 jQuery(this).hide();
	 jQuery("#showResult").slideUp(1000);
	 jQuery(".pois").hide(1500);
 });
 
 
  jQuery(".icon-sousuo-sousuo").click(function(){
	 var _input = jQuery("#searchipt").val().trim();
	 if(_input==''){
		 return ;
	 }	 
    if(isInputIp(_input)){
		getIpInfo(_input);
	}	 
	if(isIP2(_int2iP(_input))&&isNumber(_input)){
		getIpInfo(_int2iP(_input));
	}
 });
 
 jQuery(".icon-close48,.tomorrow").click(function(){
	 jQuery(".popmsg").hide();
 });
});
function isInputIp(_input){
	 if(isIP2(_input) || (isIP2(_int2iP(_input))&&isNumber(_input))){
		 if(isIP2(_int2iP(_input))&&isNumber(_input)){
			 jQuery("#searchipt").val(_int2iP(_input));
		 }
		 return true;
	 }else{
		 jQuery("#searchipt").val("");
		 jQuery("#searchipt").attr("placeholder","请输入正确的IP！").addClass("waring");
		 jQuery(".input-clear").hide();
		 return false;
	 }	
}
function isIP2(ip)   
{   
    var re =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/   
    return re.test(ip);   
}  
function _int2iP(num) 
{
    var str;
    var tt = new Array();
    tt[0] = (num >>> 24) >>> 0;
    tt[1] = ((num << 8) >>> 24) >>> 0;
    tt[2] = (num << 16) >>> 24;
    tt[3] = (num << 24) >>> 24;
    str = String(tt[0]) + "." + String(tt[1]) + "." + String(tt[2]) + "." + String(tt[3]);
    return str;
}
function isNumber(obj) {  
   return obj%1 === 0
} 
function getIpInfo(_input,validate){
	jQuery("#inip span").html(_input);	
	jQuery("#searchloading").show();
	jQuery(".icon-sousuo-sousuo").hide();
	jQuery(".amap-search-panctrl").hide();
	getLocalIPInfo(_input,validate);	
}
function getLocalIPInfo(_input,validate){
	jQuery(".leftBar").show();
	jQuery("#searchloading").show();
	jQuery(".icon-sousuo-sousuo").hide();
	jQuery("#address").hide();
    var url_tokenId = GetQueryString("tokenId");
    if (url_tokenId){
        var data_obj = {
                    ip:_input,
                    tokenId:GetQueryString("tokenId"),
                    coord:GetQueryString("hasScene"),
                    hasScene:false
                }
    }else{
        var data_obj = {
                    ip:_input,
                    coord:GetQueryString("coord"),
                    hasScene:GetQueryString("hasScene")
                }
    }
	jQuery.ajax({
		url:api_url + '/ip/locate/web',
		type:'get',
		data:data_obj,
		dataType:'json',
		success:function(data){
			jQuery("#embed-captcha").hide();
			jQuery("#zhuangtai span").html(data.code);
				if(data.code===402){
//						console.log(data.code);
						jQuery(".icon-sousuo-sousuo").show();
						jQuery("#searchloading").hide();
				}
				if(data.code===200){	//局域网IP 共享IP 保留IP						
						jQuery(".icon-sousuo-sousuo").show();
						jQuery("#searchloading").hide();
						jQuery(".ul-list").find("li:lt(2)").show();
						jQuery(".ul-list").find("li:gt(1)").hide();
						jQuery("#showResult").show();
						jQuery(".ul-list-s").show();
						jQuery("#info-message").html(data.type);
				}		
				if(data.code===200){	// 定位IP		
						jQuery(".icon-sousuo-sousuo").show();
						jQuery("#searchloading").hide();
						jQuery(".ul-list-s").hide();
						jQuery(".ul-list").find("li").show();
						jQuery("#showResult").show();
						
						jQuery("#accuracy").text('');
						jQuery("#jingdu span").html('');
						jQuery("#owner span").html('');
						jQuery("#continent span").html('');
						jQuery("#country span").html('');
						jQuery("#zipcode span").html('');
						jQuery("#timezone span").html('');
						jQuery("#scene span").html('');
						
						jQuery("#accuracy").text(data.data.accuracy);
						jQuery("#jingdu span").html(data.data.accuracy);
						jQuery("#owner span").html(data.data.owner);
						jQuery("#continent span").html(data.data.continent);
						jQuery("#country span").html(data.data.country);
						jQuery("#zipcode span").html(data.data.zipcode);
						jQuery("#timezone span").html(data.data.timezone);
						jQuery("#scene span").html(data.data.scene);
						//多区域列表
						var _multiarea = data.data.multiAreas;
						if(_multiarea.length>0){
						map.clearOverlays();  //清除覆盖物
						var _liHtml = '' ; 
						for(var item in _multiarea){
							_liHtml  += '<li class="info" data-lat='+_multiarea[item].lat+' data-lng='+_multiarea[item].lng+'>' ; 
							_liHtml += '<span class="name"><i class="num">'+(parseInt(item)+1)+'</i>省/直辖市：'+_multiarea[item].prov+'</span>';
							_liHtml += '<span class="addr"><b>&#12288;&#12288;城市：</b>'+_multiarea[item].city+'</span>';
							_liHtml += '<span class="addr"><b>区（县）：</b>'+_multiarea[item].district+'</span>';
							_liHtml += '<span class="addr"><b>&#12288;&#12288;经度：</b>'+_multiarea[item].lng+'</span>';
							_liHtml += '<span class="addr"><b>&#12288;&#12288;纬度：</b>'+_multiarea[item].lat+'</span>';
							 _liHtml += '<span class="addr"><b>覆盖半径：</b>'+ (_multiarea[item].radius ? _multiarea[item].radius + 'KM' : "") +'</span>';	
							_liHtml +='</li>';			
							addMarkerToMap({
								lng:_multiarea[item].lng,
								lat:_multiarea[item].lat,
								radius:_multiarea[item].radius * 1000,
							});								
						}				
						jQuery(".more-pois").html(_liHtml);		
						jQuery(".more-pois").find("li").click(function(e){
							mapFlyTo(this);
						});
						}								
						jQuery("#zhunquedu span").html("★★★★★☆☆☆☆☆".slice(5-data.data.correctness , 10-data.data.correctness ));
						jQuery("#yizhixing span").html("★★★★★☆☆☆☆☆☆".slice(5-data.data.consistency , 10-data.data.consistency ));
						
				}					
				if(data.code !=200 && data.success==false){
					jQuery(".showResult,.amap-search-panctrl").hide();
					jQuery(".popmsg").show();
					jQuery(".popmsg .msg").html(data.msg);
						jQuery(".icon-sousuo-sousuo").show();
						jQuery("#searchloading").hide();						
				}
		}

	});		
} 
////获取本机真实IP
//function  getRealIp(){
//	jQuery.ajax({
//		url:'./action/action.php',
//		type:'post',
//		data:{
//			ip:'',
//			flag:'getip'
//		},
//		success:function(data){
//		var _input = data;
//		if(isInputIp(_input)){
//			getIpInfo(_input);
//			
//		}	
//			
//		}
//	});
//}
function addMarkerToMap(paras){	
	var point = new BMap.Point(paras.lng,paras.lat);
	map.centerAndZoom(point, 12); 
	var myIcon = new BMap.Icon("/statics/images/ipLocation_ac75181.gif", new BMap.Size(12,13));
    marker = new BMap.Marker(point,{icon:myIcon}); 
	map.addOverlay(marker);    //添加红点
	
       circle = new BMap.Circle(point,paras.radius,{
	   strokeColor:"#03A9F4", 
	   strokeWeight:1, 
	   strokeOpacity:0.5,
	   fillOpacity:0.1,
	   fillColor:'#03A9F4'
	   }); //创建圆	
	map.addOverlay(circle);    //添加圆	
}
function mapFlyTo(obj){
//	console.log(jQuery(obj).data("lat"));
	map.panTo(new BMap.Point(jQuery(obj).data("lng"),jQuery(obj).data("lat"))); 
}
//退出功能
$('.login_account').click(function(){
	$('.login_account .list').slideToggle();
});
$('.login_account .list').click(function(){
	//退出功能添加
	$.ajax({
		type:"get",
		url: "/user/logout",
		dataType:'json',
		async:true,
		success:function(res){
			if(res.success == true){
				window.location.href = '/product/buy';
			}else{
				alert(res.msg);
			}
		}
	});
});