$(function() {
    // 如果不支持placeholder，用jQuery来完成
    if(!isSupportPlaceholder()) {
        // 遍历所有input对象, 除了密码框
        $('input').not("input[type='password']").each(
            function() {
                var self = $(this);
                var val = self.attr("placeholder");
                input(self, val);
            }
        );

        /**
         *  对password框的特殊处理
         * 1.创建一个text框 
         * 2.获取焦点和失去焦点的时候切换
         */
        $('input[type="password"]').each(
            function() {
                var pwdField    = $(this);
                var pwdVal      = pwdField.attr('placeholder');
                var pwdId       = pwdField.attr('id');
                // 重命名该input的id为原id后跟1
                pwdField.after('<input id="' + pwdId +'1" type="text" value='+pwdVal+' autocomplete="off" />');
                var pwdPlaceholder = $('#' + pwdId + '1');
                pwdPlaceholder.show();
                pwdField.hide();

                pwdPlaceholder.focus(function(){
                    pwdPlaceholder.hide();
                    pwdField.show();
                    pwdField.focus();
                });

                pwdField.blur(function(){
                    if(pwdField.val() == '') {
                        pwdPlaceholder.show();
                        pwdField.hide();
                    }
                });
            }
        );
    }
});

// 判断浏览器是否支持placeholder属性
function isSupportPlaceholder() {
    var input = document.createElement('input');
    return 'placeholder' in input;
}

// jQuery替换placeholder的处理
function input(obj, val) {
    var $input = obj;
    var val = val;
    $input.attr({value:val});
    $input.focus(function() {
        if ($input.val() == val) {
            $(this).attr({value:""});
        }
    }).blur(function() {
        if ($input.val() == "") {
            $(this).attr({value:val});
        }
    });
}
if(navigator.userAgent.indexOf("MSIE 8.0")>0 && ! window.innerWidth){
　　var $textarea = $('textarea');//获取元素集合
　　$.each($textarea,function(i,el){//遍历
　　　　if($(el).text() == ''){
　　　　　　$(el).siblings('.ieTextarea').removeClass('ieTextHide');
　　　　}else{
　　　　　　$(el).siblings('.ieTextarea').addClass('ieTextHide');
　　　　};
　　});
　　$('.ieTextarea').off('focus').focus(function(){//span聚焦的时候
　　　　$(this).addClass('ieTextHide');
　　　　$(this).siblings('textarea').focus();//模仿textarea聚焦
　　});
　　$('textarea').off('blur').blur(function(){ 
　　　　if($(this).text() == ""){ 
　　　　　　$(this).siblings('.ieTextarea').removeClass('ieTextHide');//失焦的时候让模拟span显示
　　　　}
　　});
　　$('textarea').off('focus').focus(function(){
　　　　$(this).siblings('.ieTextarea').addClass('ieTextHide');
　　});
}