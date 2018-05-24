(function(){
    var api_url = "";
    var userId = undefined;
    $(document).ready(function(){

        if($("#canWebLocate").val() == "true"){
            $("#goto_locate").removeClass("inactive");
            // 右上角跳到定位
            $("#goto_locate").click(function(){
                var ip = $("#redirect_locateIP").val();
                if (ip==""){
                    ip = $("#current_ip").text();
                }else{
                    ;
                }
                if ($("#hasScene").val() == "true" && $("#checkbox_scene").prop("checked")){
                    window.open("/ip/locatepage"+"?ip="+ip+"&coord=BD09&hasScene=true");
                }else{
                    window.open("/ip/locatepage"+"?ip="+ip+"&coord=BD09&hasScene=false");
                }
                
            });
        }else{
            $("#goto_locate").click(function(){
                $(".isable_buy").fadeIn(800).delay(1000).fadeOut(800);
            });
        }

        if( $("#hasScene").val() == "true"){
            $("#checkbox_scene").click(function(){
                ;
            });
        }else{
            $("#checkbox_scene").click(function(e){
                e.preventDefault();
                // alert("您目前没有购买应用场景，请购买后再使用。");
                layer.open({
                  type: 1,
                  offset: 'rb',
                  content: '<div style="padding: 20px 100px;">'+ '您目前没有购买应用场景，请购买后再使用。' +'</div>',
                  btn: '立即购买',
                  btnAlign: 'c',
                  shade: 0,
                  yes: function(){
                    window.location.href = "/product/buy";
                  }
                });
            });
        }

        // 版本处理
        $.ajax({
            type : "get",
            url : api_url+"/version/current",
            dataType : "json",
            success : function(obj){
                var mydata = obj.data;
                if (mydata == "user_not_login"){
                    alert("由于您长时间未登录，为了保护您的账号安全，请重新登录。");
                    window.location.href = "/user/toLogin";
                }else{
                    $("#data_version")[0].innerText = "当前数据版本 : " + mydata.publicVersion.replace("ip_","V").replace(/_/g,".");
                    $("#update_time")[0].innerText = "更新日期 : " + new Date(mydata.startTime).format("yyyy年mm月dd日");
                }
            },
            error:function(){
                console.log("Error");
            }
        });

        // 用户信息
        $.ajax({
                type : "get",
                url : api_url+"/info/user",
                dataType : "json",
                success : function(obj){
                    // console.log(obj);
                    if (obj.success == true){
                        var user_data = obj.data.user;
                        // 用户名处理
                        $(".login_account_msg").text( user_data.email );
                        ///////////////////////
                        $(".login_account span").text( user_data.email );

                        // 时间处理
                        var created_datetime = new Date(user_data.dateCreated);
                        $(".created_date")[0].innerText = created_datetime.format("yyyy-mm-dd");
                        $(".created_time")[0].innerText = created_datetime.format("HH:MM:ss");

                        var lastLogin_datetime = new Date(user_data.lastLogin);
                        $(".last_login_date")[0].innerText = lastLogin_datetime.format("yyyy-mm-dd");
                        $(".last_login_time")[0].innerText = lastLogin_datetime.format("HH:MM:ss");
                        // 上次登录IP处理
                        $(".last_login_ip")[0].innerText = user_data.lastLoginFrom;
                        // 上次登录地处理
                        $(".last_login_address")[0].innerText = obj.data.city==undefined ? "未知" : obj.data.city;
                        userId = obj.data.id;
                    }else{
                       console.log("Error");
                    }

                },
                error : function(){
                    console.log("Error");
                }
            });

        $('.login_account').click(function(){
             $('.login_account .list').slideToggle();
        });
        $('.login_account .list').click(function(){
             //退出功能添加
          $.ajax({
            type:"get",
            url:api_url + "/user/logout",
            dataType:'json',
            async:true,
            success:function(res){
              if(res.success == true){
                window.location.href = "/product/buy";
              }else{
                alert(res.msg);
              }
            }
          });
        });

        // tokens
        $.ajax({
                type : "get",
                url : api_url+"/info/tokens",
                dataType : "json",
                success : function(obj){
                    // console.log(obj);
                    if (obj.success == true){
                        var mydata = obj.data;
                        var product_map = {
                            1:"IP问问",
                            3:"应用场景",
                            5:"IP区县"
                        };
                        temple_products_str = "<p>{{product_name}}</p>";
                        mydata.forEach(function(mydata_item,index){
                            temple_all_str = '<tr>'+
                                  '<td>{{index}}</td>'+
                                  '<td><span class="token_link" token_index={{index}}>{{token_sub}}</span></td>'+
                                  '<td>{{expireDate}}</td>'+
                                  '<td>{{products_list}}</td>'+
                                  '<td {{class="allow"}}>{{available}}</td>'+
                                  '<td>{{daily_limits}}</td>'+
                                  '<td>{{counts}}</td>'+
                                  '<td>{{test}}</td>'+
                                  '<td>{{canWebLocate}}<span class="reset" reset_token={{reset_token}}>重置</span><span class="{{canWebLocateLink}}" token_id={{id}}>定位</span></td>'+
                              '</tr>';
                            temple_all_str = temple_all_str.replace(/{{index}}/g, index+1);
                            temple_all_str = temple_all_str.replace("{{token_sub}}", mydata_item.token.substr(0,10)+"***");
                            temple_all_str = temple_all_str.replace("{{reset_token}}", mydata_item.token);
                            if (mydata_item.canWebLocate){
                                temple_all_str = temple_all_str.replace("{{canWebLocate}}", '');
                                temple_all_str = temple_all_str.replace("{{canWebLocateLink}}", 'locate');
                            }else{
                                temple_all_str = temple_all_str.replace("{{canWebLocate}}", '<p class="isable_cannotlocate"><i></i>当前产品不能使用WEB定位</p>');
                                temple_all_str = temple_all_str.replace("{{canWebLocateLink}}", 'nolocate');
                            }
                            temple_all_str = temple_all_str.replace("{{id}}",mydata_item.id);
                            var timestep = mydata_item.expireDate;
                            if (timestep == undefined){
                                temple_all_str = temple_all_str.replace("{{expireDate}}","永久使用");
                            }else{
                                temple_all_str = temple_all_str.replace("{{expireDate}}",new Date(timestep).format("yyyy-mm-dd HH:MM:ss"));
                            }
                            var product_ID = mydata_item.productId;
                            var products_list = temple_products_str.replace("{{product_name}}", product_map[product_ID]);
                            temple_all_str = temple_all_str.replace("{{products_list}}", products_list);
                            temple_all_str = temple_all_str.replace("{{available}}",mydata_item.available==true ? "可用" : "不可用");
                            temple_all_str = temple_all_str.replace("{{daily_limits}}",mydata_item.dailyLimit!=0 ? mydata_item.dailyLimit : "/");
                            temple_all_str = temple_all_str.replace('{{class="allow"}}',mydata_item.available==true ? 'class="allow"' : '');
                            if (mydata_item.test==true){
                                if (product_ID == 5){
                                    temple_all_str = temple_all_str.replace("{{counts}}",mydata_item.counts);
                                }else{
                                    temple_all_str = temple_all_str.replace("{{counts}}",mydata_item.testCount);
                                }
                            }else{
                                if (product_ID == 3){
                                    temple_all_str = temple_all_str.replace("{{counts}}",mydata_item.sceneCounts);
                                }else{
                                    temple_all_str = temple_all_str.replace("{{counts}}",mydata_item.counts);
                                }
                            }
                            temple_all_str = temple_all_str.replace("{{test}}",mydata_item.test==true ? "是" : "否");
                            $("#tb_tokens").append(temple_all_str);
                        });

                        // 重置token功能
                        $("#main .main_mid #message .bot table td>span.reset").each(function(){
                            $(this).click(function(){
                                $.ajax({
                                    type : "get",
                                    url : api_url+"/token/resetToken",
                                    dataType : "json",
                                    data : {
                                        userId: userId,
                                        token : $(this).attr("reset_token")
                                    },
                                    success : function(obj){

                                        console.log(obj.msg);
                                        window.location.reload();
                                    },
                                    error : function(){
                                        console.log("Error");
                                    }
                                })
                            });
                        });

                        // token跳到定位
                        $("#main .main_mid #message .bot table td>span.locate").each(function(){
                            $(this).click(function(){
                                var id = $(this).attr("token_id");
                                window.open("/ip/locatepage"+"?tokenId="+id+"&coord=BD09&hasScene=false");
                            });
                        });
                        //  token nolocate拦截
                        $("#main .main_mid #message .bot table td>span.nolocate").each(function(){
                            $(this).click(function(){
                                $(this).prevAll().filter(".isable_cannotlocate").fadeIn(800).delay(1000).fadeOut(800);
                            });
                        });

                        // 变量为后面重置token服务
                        $("#main .main_mid #message .bot table td>span.token_link").each(function(){
                            $(this).click(function(){
                                var index = $(this).attr("token_index");
                                var mydata_item = mydata[index - 1];
                                $("#token_index")[0].innerText = index;
                                $("#token_token")[0].innerText = mydata_item.token;
                                $("#token_created_date")[0].innerText = new Date(mydata_item.createdDate).format("yyyy-mm-dd HH:MM:ss");
                                $("#token_updated_date")[0].innerText = mydata_item.updateDate ? new Date(mydata_item.updateDate).format("yyyy-mm-dd HH:MM:ss") : "";
                                $("#token_active_date")[0].innerText = new Date(mydata_item.effectiveDate).format("yyyy-mm-dd HH:MM:ss");
                                var timestep = mydata_item.expireDate;
                                if (timestep == undefined){
                                    $("#token_expire_date")[0].innerText = "永久使用";
                                }else{
                                    $("#token_expire_date")[0].innerText = new Date(timestep).format("yyyy-mm-dd HH:MM:ss");
                                }
                                var product_ID = mydata_item.productId;
                                products_list =  product_map[product_ID];

                                $("#token_suit_products")[0].innerText = products_list;
                                $("#token_available")[0].innerText = mydata_item.available==true ? "可用" : "不可用";
                                $("#daily_limits")[0].innerText = mydata_item.dailyLimit!=0 ? mydata_item.dailyLimit : "/";

                                if (mydata_item.test==true){
                                    if (product_ID == 5){
                                        $("#token_remain_times")[0].innerText = mydata_item.counts;
                                    }else{
                                        $("#token_remain_times")[0].innerText = mydata_item.testCount;
                                    }
                                }else{
                                    if (product_ID == 3){
                                        $("#token_remain_times")[0].innerText = mydata_item.sceneCounts;
                                    }else{
                                        $("#token_remain_times")[0].innerText = mydata_item.counts;
                                    }
                                }

                                $("#token_is_test")[0].innerText = mydata_item.test==true ? "是" : "否";

                                if (mydata_item.canWebLocate){
                                    $("#token_locate").removeClass("nolocate").addClass("locate");
                                }else{
                                    $("#token_locate").removeClass("locate").addClass("nolocate");
                                }
                                $("#token_reset").attr("bind-data", mydata_item.token);
                                $("#token_locate").attr("bind-data", mydata_item.id);
                                $('#bot_token').hide();
                                $('.token').show();
                            });
                            });

                        // 重置token功能
                        $("#token_reset").click(function(){
                            $.ajax({
                                type : "get",
                                url : api_url+"/token/resetToken",
                                dataType : "json",
                                data : {
                                    userId: userId,
                                    token : $("#token_reset").attr("bind-data")
                                },
                                success : function(obj){
                                    if (obj.data == "user_not_login"){
                                        alert("为了您的安全，请重新登录");
                                        window.location.href = "/user/toLogin";
                                    }else{
                                        console.log(obj.msg);
                                        window.location.reload();
                                    }

                                },
                                error : function(){
                                    console.log("Error");
                                }
                            });
                        });

                        // 跳到定位
                        $("#token_locate").click(function(){
                                var id = $("#token_locate").attr("bind-data");
                                if($(this).hasClass("nolocate")){
                                    $("#token_detail_popup").fadeIn(800).delay(1000).fadeOut(800);
                                }else{
                                    window.open("/ip/locatepage"+"?id="+id+"&coord=BD09");
                                }
                                
                        });

                        $("#token_back").click(function(){
                                $('#bot_token').show();
                                $('.token').hide();
                        });
                    }else{
                        console.log("You have no tokens");
                    }
                },
                error:function(){
                    console.log("Error");
                }
            });

        // 订单
        $.ajax({
            type : "get",
            url : api_url+"/info/orders",
            dataType : "json",
            success : function(obj){
                // console.log(obj);
                if(obj.success == true){
                    var mydata = obj.data;
                    // console.log(mydata);
                    var paymentMethod_map = {
                        0: "在线支付",
                        1: "线下支付"
                    };

                    var status_map = {
                        0: "未提交",
                        1: "待付款",
                        2: "已付款",
                        3: "已取消",
                        4: "已完成"
                    };
                    mydata.forEach(function(mydata_item, index){
                        var temple_str = '<tr>'+
                            '<td><p class="order_link" order_index="{{index}}">{{orderSerial}}</p></td>'+
                            '<td>{{dateCreated}}</td>'+
                            '<td>{{dateUpdated}}</td>'+
                            '<td>共{{orderLength}}件</td>'+
                            '<td><p>{{orderDetails}}</p></td>'+
                            '<td>{{price}}</td>'+
                            '<td>{{paymentMethod}}</td>'+
                            '<td>{{status}}</td>'+
                            '<td><a target="_blank" class="operation_link" href="/alipay/online?orderSerial={{orderSerial}}">{{operation}}</a></td>'+
                            '<td>{{isInvoice}}</td>'+
                        '</tr>';
                        var status_num = mydata_item.status;
                        temple_str = temple_str.replace("{{index}}", index);
                        temple_str = temple_str.replace("{{operation}}", status_num==0||status_num==1 ? "立即付款":"");
                        temple_str = temple_str.replace(/{{orderSerial}}/g, mydata_item.orderSerial);
                        temple_str = temple_str.replace("{{dateCreated}}", new Date(mydata_item.dateCreated).format("yyyy-mm-dd HH:MM:ss"));
                        temple_str = temple_str.replace("{{dateUpdated}}", new Date(mydata_item.dateUpdated).format("yyyy-mm-dd HH:MM:ss"));
                        var orderItems = mydata_item.orderItems
                        temple_str = temple_str.replace("{{orderLength}}", orderItems.length);
                        var item_array = [];
                        for (var i=0;i<orderItems.length;i++){
                            if (i>=3){
                                item_array.push("&ensp;&ensp;&ensp;&ensp;&bull;&bull;&bull;");
                                break;
                            }
                            var orderItemType = orderItems[i].itemType;
                            if(!orderItemType){
                                item_array.push("IP问问-高精准");
                            }else if(orderItemType == "IPDistrictDownload"){
                                item_array.push("IP区县库-下载");
                            }else if(orderItemType == "IPSceneAPI"){
                                item_array.push("IP场景-API");
                            }else if(orderItemType == "IPDistrictAPI"){
                                item_array.push("IP区县库-API");
                            }else{
                                item_array.push("未知");
                            }
                        }
                        temple_str = temple_str.replace("{{orderDetails}}", item_array.join("<br />"));
                        temple_str = temple_str.replace("{{price}}", mydata_item.price+"元");
                        temple_str = temple_str.replace("{{paymentMethod}}", paymentMethod_map[mydata_item.paymentMethod]);
                        temple_str = temple_str.replace("{{status}}", status_map[status_num] ? status_map[status_num] : "其它");
                        temple_str = temple_str.replace("{{isInvoice}}", mydata_item.isInvoice==1 ? "是" : "否");
                        $("#tb_dingdan").append(temple_str);
                    });

                    $("#dingdan .order_link").each(function(){
                        $(this).click(function(){
                            $("#tb_order_details").empty();
                            var order_index = $(this).attr("order_index");
                            var mydata_item = mydata[order_index];
                            $("#orderdetail_id").text(mydata_item.orderSerial);    
                            $("#commit_time").text(new Date(mydata_item.dateCreated).format("yyyy年mm月dd日 hh时MM分"));  
                            $("#total_fee").text(mydata_item.price+"元");
                            var status_num = mydata_item.status;
                            $("#order_status").text(status_map[status_num] ? status_map[status_num] : "其它");
                            $("#order_cancel").text(status_num ==1 ? "取消订单" : "");
                            $("#order_cancel").attr("order_cancel_id", mydata_item.orderSerial);
                            $("#order_to_pay").text(status_num==0||status_num==1 ? "立即付款":"");
                            $("#order_to_pay").attr("href", "/alipay/online?orderSerial="+mydata_item.orderSerial);
                            $("#total_fee_copy").text(mydata_item.price+"元");
                            mydata_item.orderItems.forEach(function(orderItem, index){
                                var order_detail_temple =   '<tr>'+
                                                            '    <td class="first_column">{{index}}</td>'+
                                                            '    <td>{{productName_amountStr}}</td>'+
                                                            '    <td>{{itemNum}}</td>   '+
                                                            '    <td>{{discount}}</td>  '+
                                                            '    <td>{{paymentMethod}}</td>  '+
                                                            '    <td>{{price}}</td>   '+
                                                            '</tr>'
                                order_detail_temple =  order_detail_temple.replace('{{index}}', index+1);
                                order_detail_temple =  order_detail_temple.replace('{{productName_amountStr}}', orderItem.amountStr ? orderItem.product.productName + "（" + orderItem.amountStr+"）" : orderItem.product.productName);
                                order_detail_temple =  order_detail_temple.replace('{{itemNum}}', orderItem.itemNum);
                                order_detail_temple =  order_detail_temple.replace('{{discount}}', orderItem.discount+"折");
                                order_detail_temple =  order_detail_temple.replace('{{paymentMethod}}', paymentMethod_map[mydata_item.paymentMethod]);
                                order_detail_temple =  order_detail_temple.replace('{{price}}', orderItem.price+"元");
                                $("#tb_order_details").append(order_detail_temple);
                            });
                        $("#dingdan").hide();
                        $("#orderdetails").show();
                        });
                    });
                    $("#order_cancel").click(function(){
                        var order_cancel_id = $(this).attr("order_cancel_id");
                         $.ajax({
                                type : "get",
                                url : api_url+"/order/cancel",
                                dataType : "json",
                                data : {
                                        orderId : order_cancel_id
                                    },
                                success : function(obj){
                                    console.log(obj);
                                    if(obj.success == true){
                                        alert("成功取消订单");
                                        window.location.href = "/info?content=dingdan";
                                    }else{
                                        alert(obj.msg);
                                    }
                            }
                        });
                    });
                    $("#order_detail_back").click(function(){
                        $("#orderdetails").hide();
                        $("#dingdan").show();
                    });
                }else{
                    console.log("You have no orders.");
                }
            },
            error:function(){
                console.log("Error");
            }
        });

        // 修改密码
        $("#password_commit").click(function(){
            var old_password = $("#old_password").val();
            var new_password = $("#new_password").val();
            var confirm_passsord = $("#confirm_passsord").val();
            // 长度过滤、空格过滤、
            if (old_password.length<6 || old_password.length>20){
                alert("旧密码长度小于6或大于20，请重新输入！");
            }else if(new_password.length<6 || new_password.length>20){
                alert("新密码长度小于6或大于20，请重新输入！");
            }else if(confirm_passsord.length<6 || confirm_passsord.length>20){
                alert("重复密码长度小于6或大于20，请重新输入！");
            }else if (old_password.indexOf(" ") >= 0){
                alert("旧密码有空格，请重新输入！");
            }else if (new_password.indexOf(" ") >= 0){
                alert("新密码有空格，请重新输入！");
            }else if (confirm_passsord.indexOf(" ") >= 0){
                alert("重复密码有空格，请重新输入！");
            }else if (confirm_passsord != new_password){
                alert("重复密码不相同，请重新输入！");
            }else{
                $.ajax({
                    type: "post",
                    url: api_url+"/user/changepwd",
                    data: {
                        password : old_password,
                        newPwd : new_password,
                        confirmPwd : confirm_passsord
                    },
                    datatype:'json',
                    success:function(res){
                        if(res.success){
                            alert(res.msg);
                            window.location.href = "/user/toLogin";
                        }else if (res.data == "user_not_login"){
                            alert("为了您的安全，请重新登录");
                            window.location.href = "/user/toLogin";
                        }else{
                            alert(res.msg);
                            console.log("Error");
                       }
                    },
                    error:function(){
                        console.log("Error");
                    }
                });
            }
        });

        // 已购数据
        $.ajax({
            type : "get",
            url : api_url+"/info/fileOrders",
            dataType : "json",
            success : function(obj){
                if(obj.success == true){
                    var mydata = obj.data;
                    mydata.forEach(function(mydata_item, index){
                        var myattrs_array = JSON.parse(mydata_item.attrs);
                        var temple_str = '<tr>'+
                            '<td>{{array_index}}</td>'+
                            '<td>{{db_level}}</td>'+
                            '<td>{{version_type}}</td>'+
                            '<td>{{coord_type}}</td>'+
                            '<td>{{update_frequence}}</td>'+
                            '<td>{{data_structure}}</td>'+
                            '<td>{{area}}</td>'+
                            '<td>{{update_type}}</td>'+
                            '<td>{{createTime}}</td>'+
                            '<td>{{expireTime}}</td>'+
                            '<td>{{version_number}}</td>'+
                            '<td class="blue download_areaDB" download_id={{id}}>下载</td>'+
                        '</tr>';
                        temple_str = temple_str.replace("{{id}}", mydata_item.id);
                        temple_str = temple_str.replace("{{version_number}}", mydata_item.version ? mydata_item.version.replace("ip_","V").replace(/_/g,"."):"");
                        temple_str = temple_str.replace("{{area}}", "多区域");
                        temple_str = temple_str.replace("{{update_type}}", "全量更新");
                        temple_str = temple_str.replace("{{array_index}}", index+1);
                        temple_str = temple_str.replace("{{db_level}}","区县库");
                        temple_str = temple_str.replace("{{data_structure}}","MySQL");
                        myattrs_array.forEach(function(inner_obj, index){
                            if (inner_obj["attrKey"] == "version"){
                                temple_str = temple_str.replace("{{version_type}}", inner_obj["attrValue"]);
                            }else if (inner_obj["attrKey"] == "coord"){
                                temple_str = temple_str.replace("{{coord_type}}", inner_obj["attrValue"]);
                            }else if (inner_obj["attrKey"] == "updaterate"){
                                temple_str = temple_str.replace("{{update_frequence}}", inner_obj["attrValue"]);
                            }else{
                                temple_str = temple_str.replace("{{update_frequence}}", "/");
                            }
                        });                        
                        temple_str = temple_str.replace("{{createTime}}", new Date(mydata_item.createTime).format("yyyy年mm月dd日 HH:MM:ss"));
                        temple_str = temple_str.replace("{{expireTime}}", mydata_item.expireTime ? new Date(mydata_item.expireTime).format("yyyy年mm月dd日 HH:MM:ss"):"");
                        $("#tb_buy_list").append(temple_str);
                    });

                    $(".download_areaDB").click(function(){
                        // console.log(this);
                        window.open(api_url + "/download/file?fileId=" + $(this).attr("download_id"));
                    });
                }else{
                    console.log("You have no bought data.");
                }
            },
            error:function(){
                console.log("Error");
            }
        });

        // 密码保护
        // 点击获取验证码
        if ($("#hasMobile").val() == "true"){
            /////////////////////////
            $("#bind_mobile").remove();
            $("#menu_bind_mobile").remove();
        }else{
            $("#change_mobile").remove();
            $("#menu_change_mobile").remove();
        }

        $('.getMobile').click(function(){
            var mobileReg = /^1[0-9]{10}$/;
            //检测手机号码
            if($('.per_mobile').val().trim() == ''){
                $('.per_mobile').parent().addClass('regError');
                alert('请您输入手机号码');
                per_mobile = false;
            }else if(mobileReg.test($('.per_mobile').val().trim()) !== true || $('.per_mobile').val().trim().length < 11){
                $('.per_mobile').parent().addClass('regError');
                alert('请您输入正确的手机号码');
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
                }, 1000);
                $.ajax({
                    type:"get",
                    url: api_url + "user/sendSms",
                    dataType:'json',
                    async:true,
                    data:{phoneNum:$('.per_mobile').val()},
                    success:function(res){
                        if (res.data == "user_not_login"){
                            alert("为了您的安全，请重新登录");
                            window.location.href = "/user/toLogin";
                        }else if(res.success == true){
                            $('.per_mobileCode_error').html('<i class="success"></i>获取验证码成功');
                            $('.per_mobileCode_error').addClass('successCode');
                            $('.per_mobileCode_error').removeClass('regError');
                            per_mobileCode = true;
                        }else{
                            alert(res.msg);
                            per_mobileCode = true;
                        }
                    }
                });
            }
        });
        
        // 点击绑定手机的确定按钮
        $('#bind_commit').click(function(){
            $.ajax({
                type:"get",
                url:api_url + "user/checkMobileCode",
                dataType:'json',
                data:{phoneNum:$('#phone_number').val(), code:$('#verification').val()},
                success:function(res){
                    if (res.data == "user_not_login"){
                            alert("为了您的安全，请重新登录");
                            window.location.href = "/user/toLogin";
                    }else if(res.success == true){
                        per_mobileCode = true;
                        if ($("#hasMobile").val() == "true"){
                            /////////////////////////
                            $.ajax({
                                type:"get",
                                url:api_url + "user/updateMobile",
                                dataType:'json',
                                data:{
                                    oldPhoneNum : $('#phone_number_original').val(),
                                    newPhoneNum : $('#phone_number').val(),
                                    code : $('#verification').val(),
                                    captcha : $('#verification_captcha').val()
                                },
                                success:function(res){
                                    if (res.data == "user_not_login"){
                                            alert("为了您的安全，请重新登录");
                                            window.location.href = "/user/toLogin";
                                    }else if(res.success){
                                        alert(res.msg);
                                        $("#phone_number_original").val("");
                                        $("#phone_number").val("");
                                        $("#verification").val("");
                                        $("#verification_captcha").val("");
                                    }else{
                                        alert(res.msg);
                                    }
                                }
                            });
                        }else{
                            $.ajax({
                                type:"get",
                                url:api_url + "user/submit",
                                dataType:'json',
                                data:{
                                    phoneNum : $('#phone_number').val(),
                                    code : $('#verification').val(),
                                    captcha : $('#verification_captcha').val()
                                },
                                success:function(res){
                                    console.log(res);
                                    if (res.data == "user_not_login"){
                                            alert("为了您的安全，请重新登录");
                                            window.location.href = "/user/toLogin";
                                    }else if(res.success){
                                        alert(res.msg);
                                        window.location.reload();
                                    }else{
                                        alert(res.msg);
                                    }
                                }
                            });
                        }

                    }else{
                        alert(res.msg);
                        per_mobileCode = false;
                    }
                }
            });
        });

    });
}());
