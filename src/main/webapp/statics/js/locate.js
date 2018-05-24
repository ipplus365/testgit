var map = L.map('map',{zoomControl:false});
L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    id: 'mapbox.streets',
    styleId: 22677,
    attribution:'COPYRIGHT &copy; 2017 郑州埃文计算机科技有限公司'
}).addTo(map);
map.setView(L.latLng(39.915168, 116.403875),3);
map.addControl(L.control.zoom({
    position: 'bottomright'
}));
map.doubleClickZoom.disable();

var marker1;
var page = 1;
var divIconArray = [];
var poiMostArray = [];
var results = [];
var LEDcircle;
var R1circle;
var R2circle;
var multiCircle = [];

var mark = L.icon({
    iconUrl: '../statics/images/marker-red-small.png',
    iconSize:     [44, 48], // size of the icon
    shadowSize:   [5, 2], // size of the shadow
    iconAnchor:   [22, 42], // point of the icon which will correspond to
							// marker's location
    shadowAnchor: [4, 62],  // the same for the shadow
    popupAnchor:  [0, -42] // point from which the popup should open relative
							// to the iconAnchor
});
// 给地图的控制按钮append一个dom结构 制作刷新地图的按钮
var ref = '<a class="leaflet-control-zoom-out" title="刷新"><img src="../statics/images/refresh.png"></a>';
$('.leaflet-control-zoom').append(ref);
$('.leaflet-control-zoom-out').eq(1).click(function(event){
    event.preventDefault();
    event.stopPropagation();
    event.cancelable = true;
    if(marker1){
        map.removeLayer(marker1);
    }
    if(LEDcircle){
        map.removeLayer(LEDcircle);
    }
    // 删除绘制的多区域范围圆
    if(multiCircle.length !== 0){
        for(i in multiCircle){
            map.removeLayer(multiCircle[i]);
        }
    }
    // 删除绘制的多区域的圆心处的标识
    if(divIconArray.length !== 0){
        for(i in divIconArray){
            map.removeLayer(divIconArray[i]);
        }
    }
    var obj = results[0];
    if(obj.multiAreas != undefined){
        for(i in obj.multiAreas){
            multiAreas(obj.multiAreas[i].lat,obj.multiAreas[i].lng,obj.multiAreas[i].radius*1000,i);
        }
        if(obj.multiAreas[0].radius !== ''){
            var x = obj.multiAreas[0].lat;
            var y = obj.multiAreas[0].lng;
            var ledPlus = obj.multiAreas[0].radius*1000 + 100;
            if(ledPlus < 250){
                map.setView(L.latLng(x, y),18);
            }else if(ledPlus >= 250 && ledPlus < 1000){
                map.setView(L.latLng(x, y),15);
            }else if(ledPlus >= 1000 && ledPlus < 2000){
                map.setView(L.latLng(x, y),14);
            }else if(ledPlus >= 2000 && ledPlus < 2500){
                map.setView(L.latLng(x, y),14);
            }else if(ledPlus >= 2500 && ledPlus < 5000){
                map.setView(L.latLng(x, y),13);
            }else if(ledPlus >= 5000 && ledPlus < 7500){
                map.setView(L.latLng(x, y),12);
            }else if(ledPlus >= 7500 && ledPlus < 10000){
                map.setView(L.latLng(x, y),11);
            }else if(ledPlus > 10000 && ledPlus < 15000){
                map.setView(L.latLng(x, y),11);
            }else if(ledPlus > 15000 && ledPlus < 20000){
                map.setView(L.latLng(x, y),10);
            }else if(ledPlus > 20000 && ledPlus < 25000){
                map.setView(L.latLng(x, y),10);
            }else if(ledPlus > 25000 && ledPlus < 30000){
                map.setView(L.latLng(x, y),10);
            }else if(ledPlus > 30000 && ledPlus < 35000){
                map.setView(L.latLng(x, y),9);
            }else{
                map.setView(L.latLng(x, y),8);
            }
        }else{
            if(divIconArray.length != 0){
                for(i in divIconArray){
                    map.removeLayer(divIconArray[i]);
                }
            }
            map.setView(L.latLng(obj.multiAreas[0].lat,obj.multiAreas[0].lng),9);
        }
    }
    // 无多区域 即为单区域
    if(obj.multiAreas == undefined && obj.radius != undefined){
        setLedCircle(obj.latitude,obj.longitude,obj.radius*1000);
    }
    // 无radius 有经纬度 一般为定位到省级别
    if(obj.radius == undefined && obj.longitude != undefined && obj.latitude != undefined && obj.multiAreas == undefined){
        map.setView(L.latLng(obj.latitude,obj.longitude),9);
    }
    // 无radius 无经纬度 定位到中国 缩放比为3
    if(obj.radius == undefined && obj.longitude == undefined && obj.latitude == undefined && obj.multiAreas == undefined){
        map.setView(L.latLng(39.915168, 116.403875),3);
    }

});
function setLedCircle(x,y,led){
    window.LEDcircleLocation = new window.L.LatLng(x, y),
        LEDcircleOptions = {
            color: '#0abbf8',
            fillColor: '#0abbf8',
            fillOpacity: 0.34
        },
        LEDcircle = new window.L.Circle(window.LEDcircleLocation, led, LEDcircleOptions);
    window.map.addLayer(LEDcircle);
    var ledPlus = led + 100;
    if(ledPlus < 250){
        map.setView(L.latLng(x, y),18);
    }else if(ledPlus >= 250 && ledPlus < 1000){
        map.setView(L.latLng(x, y),15);
    }else if(ledPlus >= 1000 && ledPlus < 2000){
        map.setView(L.latLng(x, y),14);
    }else if(ledPlus >= 2000 && ledPlus < 2500){
        map.setView(L.latLng(x, y),14);
    }else if(ledPlus >= 2500 && ledPlus < 5000){
        map.setView(L.latLng(x, y),13);
    }else if(ledPlus >= 5000 && ledPlus < 7500){
        map.setView(L.latLng(x, y),12);
    }else if(ledPlus >= 7500 && ledPlus < 10000){
        map.setView(L.latLng(x, y),11);
    }else if(ledPlus > 10000 && ledPlus < 15000){
        map.setView(L.latLng(x, y),11);
    }else if(ledPlus > 15000 && ledPlus < 20000){
        map.setView(L.latLng(x, y),10);
    }else if(ledPlus > 20000 && ledPlus < 25000){
        map.setView(L.latLng(x, y),10);
    }else if(ledPlus > 25000 && ledPlus < 30000){
        map.setView(L.latLng(x, y),10);
    }else if(ledPlus > 30000 && ledPlus < 35000){
        map.setView(L.latLng(x, y),9);
    }else{
        map.setView(L.latLng(x, y),8);
    }
}
// 绘制多区域的圆和图标
function multiAreas(x,y,r,i){
    var mark1 = L.divIcon({
        iconUrl: '../statics/images/u32.png',
        html:'<span>' + (i-0+1) + '</span>',
        bgPos:(5,5),
        className:'my-icon',
        iconSize:     [22, 29], // size of the icon
        shadowSize:   [5, 2], // size of the shadow
        iconAnchor:   [11, 20], // point of the icon which will correspond to
								// marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [0, -42] // point from which the popup should open
								// relative to the iconAnchor
    });
    window.circleLocation = new window.L.LatLng(x, y),
        circleOptions = {
            color: '#69B5D3',
            fillColor: '#AED3CA',
            fillOpacity: 0.67,
            strokeWidth:1
        },
        circle = new window.L.Circle(window.circleLocation,r,circleOptions);
    window.map.addLayer(circle);
    multiCircle.push(circle);
    multiMarker = L.marker([x,y],{icon:mark1});
    map.addLayer(multiMarker);
    divIconArray.push(multiMarker);
}
function setIcon(list,i){
    var mark1 = L.divIcon({
        iconUrl: '../statics/images/u31.png',
        html:'<span>' + (i-0+1) + '</span>',
        bgPos:(5,5),
        className:'my-icon1',
        iconSize:     [22, 29], // size of the icon
        iconAnchor:   [11, 20], // point of the icon which will correspond to
								// marker's location
        popupAnchor:  [0, -22] // point from which the popup should open
								// relative to the iconAnchor
    });
    if(list[i].phonenumber){
        poiMarker = L.marker([list[i].latitude - 0, list[i].longitude - 0],{icon:mark1,riseOnHover:true}).bindPopup('<span>' + list[i].fullname + '</span><br/><strong>' + list[i].address + '<br/>' + list[i].phonenumber + '</strong>',{className:'POIpopup'}).openPopup().on('click', function(e){$('.leaflet-marker-pane>img').css({width:52,height:56,marginLeft:'-26px',marginTop:"-48px"});$('.leaflet-marker-icon').attr('src','../statics/images/marker-red-small.png')}).on('mouseover',function(){if($('.leaflet-marker-icon').width() != 52){$('.leaflet-marker-icon').attr('src','../statics/images/marker-blue-small.png')}else{return null;}}).on('mouseout',function(){$('.leaflet-marker-icon').attr('src','../statics/images/marker-red-small.png')});
    }else{
        poiMarker = L.marker([list[i].latitude - 0, list[i].longitude - 0],{icon:mark1,riseOnHover:true}).bindPopup('<span>' + list[i].fullname + '</span><br/><strong>' + list[i].address + '</strong>',{className:'POIpopup'}).openPopup().on('click', function(e){$('.leaflet-marker-pane>img').css({width:52,height:56,marginLeft:'-26px',marginTop:"-48px"});$('.leaflet-marker-icon').attr('src','../statics/images/marker-red-small.png')}).on('mouseover',function(){if($('.leaflet-marker-icon').width() != 52){$('.leaflet-marker-icon').attr('src','../statics/images/marker-blue-small.png')}else{return null;}}).on('mouseout',function(){$('.leaflet-marker-icon').attr('src','../statics/images/marker-red-small.png')});
    }
    map.addLayer(poiMarker);
    poiMostArray.push(poiMarker);
}
// 这个函数用来判断准确度和精确度的星级
function scoreStar(score){
    if(score == 5){
        return '<span class="star"><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/></span>';
    }else if(score == 4){
        return '<span class="star"><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/dark_star.png"/></span>';
    }else if(score == 3){
        return '<span class="star"><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/></span>';
    }else if(score == 2){
        return '<span class="star"><img src="../statics/images/bright_star.png"/><img src="../statics/images/bright_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/></span>';
    }else{
        return '<span class="star"><img src="../statics/images/bright_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/><img src="../statics/images/dark_star.png"/></span>';
    }
}


// 验证IP是否正确的正则
var reg = /((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
var flag = true;
// 控制请求定位结果 和 一级类别
function All(){
    // 如果IP输入格式不对 弹出输入框
    if(reg.test($('.ipAddress').val().trim()) != true){
        $('#warning').css({display:'block'});
    }else{
        $('#warning').css({display:'none'});
    }
    // 选择一项服务之后开始请求
    if(reg.test($('.ipAddress').val().trim()) == true){
        // 初始化一次 删除地图上的圆和icon
        if(flag){
            $('.list').slideUp();
            // 点击定位 让二级类别 三级类别和POI隐藏
            $('#POI_class').hide();
            $('#poiClass_two').hide();
            $('#poiClass_three').hide();
            $('#poiList').hide();
            $('#inner').html('');
            clickUP = 0;
            if(marker1){
                map.removeLayer(marker1);
            }
            if(LEDcircle){
                map.removeLayer(LEDcircle);
            }
            // 删除绘制的多区域范围圆
            if(multiCircle.length != 0){
                for(i in multiCircle){
                    map.removeLayer(multiCircle[i]);
                }
            }
            if(divIconArray.length != 0){
                for(i in divIconArray){
                    map.removeLayer(divIconArray[i]);
                }
            }

            $('.results_show_one').removeClass('add');
            $('.results_show_two').removeClass('add');
            $('.results_show_three').removeClass('add');
            $('.results_show_four').removeClass('add');
            $('.top').removeClass('add2');
            // 请求定位结果
            $.ajax({
                type: "get",
                url: '/ip/locate/web',
                data: {ip:$('.ipAddress').val().trim(), key: '', coord: 'WGS84'},
                datatype: "json",
                beforeSend: function(){$('.results_show_one').addClass('add1');$('.results_show_two').addClass('add1');$('.results_show_three').addClass('add1');$('.results_show_four').addClass('add1');$('.more').hide();$('#results>p').css({'visibility':'hidden'});},
                success: function(data){
                    $('#noneuser').hide();
                    setTimeout(function(){
                        $('.more').show();
                    },1000);
                    var obj = data.data;
                    results = [];
                    results.push(obj);
                    var temp1 = '<span>IP地址:</span><span>' + $('.ipAddress').val() + '</span>';
                    var temp2 = '';
                    var temp3 = '';
                    var temp4 = '';
                    var temp5 = '';
                    var height = 0;
                    // 判断多区域的情况是否存在
                    if(obj.multiAreas.length < 1){
                        // 如果请求成功就让结果滑下
                        $('#results').addClass('slide_down');
                        $('.wrap').eq(2).show();
                        $('.results_show_three').show();
                        $('#results>p').hide();
                        $('#multi_mb').hide();
                        $('#last').hide();
                        $('.down').hide();
                        $('.results_show_two').css({
                            marginBottom:15
                        });
                        $('.results_show_one').addClass('add');
                        $('.results_show_two').addClass('add');
                        $('.results_show_three').addClass('add');
                        $('.top').addClass('add2');
                        // 判断返回的数据是否有这些字段 如果有就展示 如果没就不展示
                        if(obj.statusMessage){
                            temp1 += '<span>状态信息:</span><span>'+ obj.statusMessage +'</span>';
                        }
                        if(obj.statusCode){
                            temp1 += '<span>状态码:</span><span>'+ obj.statusCode +'</span>';
                        }
                        if(obj.accuracy){
                            temp1 += '<span>定位精度:</span><span>'+ obj.accuracy +'</span>';
                        }
                        if(obj.source){
                            temp1 += '<span>定位方式:</span><span>' + obj.source + '</span>';
                        }
                        if(obj.processingTime){
                            temp1 += '<span>用时:</span><span>' + obj.processingTime + '</span>';
                        }
                        $('.results_show_one').html(temp1);

                        if(obj.continent){
                            temp2 += '<span>洲:</span><span>' + obj.continent + '</span>';
                        }
                        if(obj.country){
                            temp2 += '<span>国家:</span><span>' + obj.country +'</span>';
                        }
                        if(obj.province){
                            temp2 += '<span>省/直辖市:</span><span>'+ obj.province +'</span>';
                        }
                        if(obj.city){
                            temp2 += '<span>城市:</span><span>'+ obj.city +'</span>';
                        }
                        if(obj.district){
                            temp2 += '<span>区县:</span><span>' + obj.district+ '</span>';
                        }
                        if(obj.longitude){
                            temp2 += '<span>经度:</span><span>' + obj.longitude + '</span>';
                        }
                        if(obj.latitude){
                            temp2 += '<span>纬度:</span><span>' + obj.latitude + '</span>';
                        }
                        if(obj.zipcode){
                            temp2 += '<span>邮政编码:</span><span>' + obj.zipcode + '</span>';
                        }
                        if(obj.timeZone){
                            temp2 += '<span>时区:</span><span>' + obj.timeZone + '</span>';
                        }
                        $('.results_show_two').html(temp2);

                        if(obj.radius){
                            temp3 += '<span>覆盖半径:</span><span>' + (obj.radius) + 'KM</span>';
                        }
                        if(obj.correctness){
                            temp3 += '<span>准确度:</span>' + scoreStar(obj.correctness);
                        }
                        if(obj.consistency){
                            temp3 += '<span>一致性:</span>' + scoreStar(obj.consistency);
                        }
                        if(obj.scenarios){
                            temp3 += '<span>应用场景:</span><span>' + obj.scenarios + '</span>';
                        }
                        if(obj.user){
                            temp3 += '<span>组织机构:</span><span class="breakUser">' + obj.user + '</span>';
                        }
                        if(obj.owner){
                            temp3 += '<span>运营商:</span><span class="breakOwner">' + obj.owner + '</span>';
                        }
                        $('.results_show_three').html(temp3);
                        $('.breakUser').attr('title', obj.user);
                        $('.breakOwner').attr('title', obj.owner);
                        $('.breakUser').css('width', '30%');
                        $('.breakOwner').css('width', '30%');
                    }else{
                        // 如果请求成功就让结果滑下
                        $('#results').addClass('slide_down');
                        setTimeout(function(){
                            $('#results>p').css({
                                'display':'block',
                                'visibility':'visible'
                            });
                        },1000);
                        $('#multi_mb').show();
                        $('#last').show();
                        $('.results_show_three').hide();
                        $('.wrap').eq(2).hide();
                        $('#poiList').hide();
                        $('.wrap').eq(1).css({
                            marginBottom:0
                        });
                        $('.results_show_two').css({
                            marginBottom:0
                        });
                        $('.results_show_one').addClass('add');
                        $('.results_show_two').addClass('add');
                        $('.down').hide();
                        $('#multiAreas').css({
                            top:0
                        });
                        if(obj.statusMessage){
                            temp1 += '<span>状态信息:</span><span>'+ obj.statusMessage +'</span>';
                        }
                        if(data.code){
                            temp1 += '<span>状态码:</span><span>'+ data.code +'</span>';
                        }
                        if(obj.accuracy){
                            temp1 += '<span>定位精度:</span><span>'+ obj.accuracy +'</span>';
                        }
                        if(obj.source){
                            temp1 += '<span>定位方式:</span><span>' + obj.source + '</span>';
                        }
                        if(obj.processingTime){
                            temp1 += '<span>用时:</span><span>' + obj.processingTime + '</span>';
                        }
                        if(obj.scenarios){
                            temp1 += '<span>应用场景:</span><span>' + obj.scenarios + '</span>';
                        }
                        if(obj.user){
                            temp1 += '<span>组织机构:</span><span class="breakUser">' + obj.user + '</span>';
                        }
                        if(obj.owner){
                            temp1 += '<span>运营商:</span><span class="breakOwner">' + obj.owner + '</span>';
                        }
                        $('.results_show_one').html(temp1);
                        $('.breakUser').attr('title', obj.user);
                        $('.breakOwner').attr('title', obj.owner);
                        $('.breakUser').css('width', '30%');
                        $('.breakOwner').css('width', '30%');
                        if(obj.continent){
                            temp2 += '<span>洲:</span><span>' + obj.continent + '</span>';
                        }
                        if(obj.country){
                            temp2 += '<span>国家:</span><span>' + obj.country +'</span>';
                        }
                        if(obj.province){
                            temp2 += '<span>省/直辖市:</span><span>'+ obj.province +'</span>';
                        }
                        if(obj.zipcode){
                            temp2 += '<span>邮政编码:</span><span>' + obj.zipcode + '</span>';
                        }
                        if(obj.timezone){
                            temp2 += '<span>时区:</span><span>' + obj.timezone + '</span>';
                        }
                        $('.results_show_two').html(temp2);

                        for(i in obj.multiAreas){
                            temp4 += '<div class="wrapFour"><div class="results_show_four">';
                            if(obj.multiAreas[i].prov){
                                temp4 += '<span>省/直辖市:</span><span>'+ obj.multiAreas[i].prov +'</span>';
                                height += 22;
                            }
                            if(obj.multiAreas[i].city){
                                temp4 += '<span>城市:</span><span>' + obj.multiAreas[i].city + '</span>';
                                height += 22;
                            }
                            if(obj.multiAreas[i].district){
                                temp4 += '<span>区县:</span><span>' + obj.multiAreas[i].district + '</span>';
                                height += 22;
                            }
                            if(obj.multiAreas[i].lng){
                                temp4 += '<span>经度:</span><span>' + obj.multiAreas[i].lng + '</span>';
                                height += 22;
                            }
                            if(obj.multiAreas[i].lat){
                                temp4 += '<span>纬度:</span><span>' + obj.multiAreas[i].lat + '</span>';
                                height += 22;
                            }
                            if(obj.multiAreas[i].radius){
                                temp4 += '<span>覆盖半径:</span><span>' + obj.multiAreas[i].radius + 'KM</span>';
                                height += 22;
                            }
                            temp4 += '<div class="num">'+ (i-0+1) +'.</div></div><div class="top"></div></div>';
                            multiAreas(obj.multiAreas[i].lat,obj.multiAreas[i].lng,obj.multiAreas[i].radius*1000,i);
                        }

                        if(obj.multiAreas.length <= 3){
                            if(obj.multiAreas[0].prov == '' || obj.multiAreas[0].city == '' || obj.multiAreas[0].district == '' || obj.multiAreas[0].lng == '' || obj.multiAreas[0].lat == '' || obj.multiAreas[0].radius == ''){
                                $('#multi_mb').css({
                                    height:height + 15 + 'px'
                                });
                            }else{
                                temp5 += '';
                                $('#multi_mb').css({
                                    height:obj.multiAreas.length * 147 + 'px'
                                });
                            }
                        }else{
                            temp5 += '<img class="up" src="../statics/images/slide_down.png"/>';
                            $('#multi_mb').css({
                                height:'441px'
                            });

                        }
                        temp5 += '<div class="wrapFour"><div class="results_show_four"><span>准确度:</span>' + scoreStar(obj.correctness) +　'<span>一致性:</span>' + scoreStar(obj.consistency) + '</div><div class="top"></div>';
                        $("#multiAreas").html(temp4);
                        $('#last').html(temp5);
                        if(obj.multiAreas[0].radius !== ''){
                            var x = obj.multiAreas[0].lat;
                            var y = obj.multiAreas[0].lng;
                            var ledPlus = obj.multiAreas[0].radius*1000 + 100;
                            if(ledPlus < 250){
                                map.setView(L.latLng(x, y),18);
                            }else if(ledPlus >= 250 && ledPlus < 1000){
                                map.setView(L.latLng(x, y),15);
                            }else if(ledPlus >= 1000 && ledPlus < 2000){
                                map.setView(L.latLng(x, y),14);
                            }else if(ledPlus >= 2000 && ledPlus < 2500){
                                map.setView(L.latLng(x, y),14);
                            }else if(ledPlus >= 2500 && ledPlus < 5000){
                                map.setView(L.latLng(x, y),13);
                            }else if(ledPlus >= 5000 && ledPlus < 7500){
                                map.setView(L.latLng(x, y),12);
                            }else if(ledPlus >= 7500 && ledPlus < 10000){
                                map.setView(L.latLng(x, y),11);
                            }else if(ledPlus > 10000 && ledPlus < 15000){
                                map.setView(L.latLng(x, y),11);
                            }else if(ledPlus > 15000 && ledPlus < 20000){
                                map.setView(L.latLng(x, y),10);
                            }else if(ledPlus > 20000 && ledPlus < 25000){
                                map.setView(L.latLng(x, y),10);
                            }else if(ledPlus > 25000 && ledPlus < 30000){
                                map.setView(L.latLng(x, y),10);
                            }else if(ledPlus > 30000 && ledPlus < 35000){
                                map.setView(L.latLng(x, y),9);
                            }else{
                                map.setView(L.latLng(x, y),8);
                            }
                        }else{
                            if(divIconArray.length != 0){
                                for(i in divIconArray){
                                    map.removeLayer(divIconArray[i]);
                                }
                            }
                            map.setView(L.latLng(obj.multiAreas[0].lat,obj.multiAreas[0].lng),9);
                        }
                        $('.results_show_four').addClass('add');
                        $('.top').addClass('add2');
                    }
                    // 无多区域 即为单区域
                    if(obj.multiAreas == undefined && obj.radius != undefined){
                        setLedCircle(obj.latitude,obj.longitude,obj.radius*1000);
                    }
                    // 无radius 有经纬度 一般为定位到省级别
                    if(obj.radius == undefined && obj.longitude != undefined && obj.latitude != undefined && obj.multiAreas == undefined){
                        map.setView(L.latLng(obj.latitude,obj.longitude),9);
                    }
                    // 无radius 无经纬度 定位到中国 缩放比为3
                    if(obj.radius == undefined && obj.longitude == undefined && obj.latitude == undefined && obj.multiAreas == undefined){
                        map.setView(L.latLng(39.915168, 116.403875),3);
                    }
                } 
            });
            flag = false;
            setTimeout(function(){
                flag = true;
                $('#warning_repeat').hide();
            },5000);
        }else{
            $('#warning_repeat').show();
            $('#warning_repeat').css('left', $('.service-list').width() + 340 + 'px');
        }
    }
}


$('.button').on('click', function(){
    All();
});
$(document).keyup(function(event){
    if(event.keyCode == 13){
        All();
    }
});

// 多区域定位结果展示的箭头动画
var clickUP = 0;
$('body').on('click', '.up', function(){
    clickUP++;
    if(results[0].multiAreas.length - 3 == clickUP){
        $('.up').hide();
        $('.down').show();
    }
    $('#multiAreas').animate({
        top: -147*clickUP + 'px'
    },500)
});
$('body').on('click', '.down', function(){
    clickUP--;
    if($('#multiAreas').position().top == -147){
        $('.up').show();
        $('.down').hide();
    }
    $('#multiAreas').animate({
        top: -147*clickUP + 'px'
    },500)
});
$('.delete').on('click', function(){
    $('.ipAddress').val('');
});
