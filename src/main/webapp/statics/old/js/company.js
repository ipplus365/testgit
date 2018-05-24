$('#entName').blur(function () {
    if($('#entName').val() == ""){
        $('#entName').focus();
        appendWrongInfo($('#entName'), "公司名称为空");
        return false;
    }else{
        removeWrongTag( $('#entName'));
    }
});
$('#industryCode').change(function () {
    if($('#industryCode').val() == ""){
        $('#industryCode').focus();
        appendWrongInfo($('#industryCode'),"所属行业必选");
        return false;
    }else{
        removeWrongTag( $('#industryCode'));
    }
});
$('#entAddress').blur(function () {
    if($('#entAddress').val() == ""){
        $('#entAddress').focus();
        appendWrongInfo($('#entAddress'), "所在地不能为空");
        return false;
    }else{
        removeWrongTag( $('#entAddress'));
    }
});
$('#legalName').blur(function(){
    if($('#legalName').val() == ""){
        $('#legalName').focus();
        appendWrongInfo($('#legalName'), "法人不能为空");
        return false;
    }else{
        removeWrongTag( $('#legalName'));
    }
});
$('#licenseNumber').blur(function () {
    if($('#licenseNumber').val() == ""){
        $('#licenseNumber').focus();
        appendWrongInfo($('#licenseNumber'), "请输入营业执照");
        return false;
    }else{
        removeWrongTag($('#licenseNumber'));
    }
});

function validate(){
   if($('#entName').val() == ""){
       $('#entName').focus();
       appendWrongInfo($('#entName'), "公司名称为空");
       return false;
   }
    if(!checkInfo($('#entName'), "公司名称")) return false;
    if($('#industryCode').val() == ""){
        $('#industryCode').focus();
        appendWrongInfo($('#industryCode'),"所属行业必选");
        return false;
    }
    if(!checkIndustry($('#industryCode'))) return false;
    if($('#entAddress').val() == ""){
        $('#entAddress').focus();
        appendWrongInfo($('#entAddress'), "营业执照所在地不能为空");
        return false;
    }
    if(!checkInfo($('#entAddress'), "营业执照所在地")) return false;
    if($('#legalName').val() == ""){
        $('#legalName').focus();
        appendWrongInfo($('#legalName'), "法人姓名不能为空");
        return false;
    }
    if(!checkInfo($('#legalName'), "法人姓名")) return false;
    // if(!checkLegalNumber()) return false;
    // if($('#contactName').val() == ""){
    //     $('#contactName').focus();
    //     appendWrongInfo($('#contactName'), "公司业务联系人不能为空");
    //     return false;
    // }
    // if(!checkInfo($('#contactName'), "公司业务联系人")) return false;
    // if(!checkMobile()) return false;
    // if(!checkEmail()) return false;
    if(!checkLicenseNumber()) return false;
    if($('#validityStart').val() == ""){
        alert("开始时间不能为空");
        $('#validityStart').focus();
        return false;
    }
    if($('#validityEnd').val() == ""){
        alert("截止时间不能为空");
        $('#validityEnd').focus();
        return false;
    }
    var beginDate=$("#validityStart").val();
    var endDate=$("#validityEnd").val();
    var d1 = new Date(beginDate.replace(/\-/g, "\/"));
    var d2 = new Date(endDate.replace(/\-/g, "\/"));
    if(beginDate!=""&&endDate!=""&&d1 >=d2)
    {
        alert("开始时间不能大于结束时间！");
        $("#validityStart").focus();
        return false;
    }
    // if($('#licensePic').val() == ""){
    //     alert("未上传营业执照");
    //     $('#licensePic').focus();
    //     return false;
    // }
    // if(!checkBankAccount()) return false;
    // if($('#accountName').val() == ""){
    //     $('#accountName').focus();
    //     appendWrongInfo($('#accountName'), "银行开户名不能为空");
    //     return false;
    // }
    // if(!checkInfo($('#accountName'), "银行开户名")) return false;
    // if($('#bankName').val() == ""){
    //     $('#bankName').focus();
    //     appendWrongInfo($('#bankName'), "银行开户行不能为空");
    //     return false;
    // }
    // removeWrongTag($('#bankName'));
    return true;
}

// function checkBankAccount(){
//     var reg=/^[0-9]{1,30}$/;
//     if($('#bankAccount').val() == "" || !reg.test($('#bankAccount').val())){
//         $('#bankAccount').focus();
//         appendWrongInfo($('#bankAccount'), "请输入正确的银行账号");
//         return false;
//     }
//     removeWrongTag($('#bankAccount'));
//     return true;
// }

function checkLicenseNumber(){
    var reg=/^([a-zA-Z0-9])*$/;
    var length = $('#licenseNumber').val().length;
    if(length == 15 || length == 18){
        if(!reg.test($('#licenseNumber').val())){
            $('#licenseNumber').focus();
            appendWrongInfo($('#licenseNumber'), "请输入正确的营业执照号");
            return false;
        }
    }else{
        $('#licenseNumber').focus();
        appendWrongInfo($('#licenseNumber'), "请输入正确的营业执照号");
        return false;
    }
    removeWrongTag($('#licenseNumber'));
    return true;
}

// function checkLegalNumber(){
//     if($('#legalNumber').val() == "")
//         return true;
//     var reg=/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
//     if(!reg.test($('#legalNumber').val())){
//         $('#legalNumber').focus();
//         appendWrongInfo($('#legalNumber'), "请输入正确的身份证号");
//         return false;
//     }
//     removeWrongTag($('#legalNumber'));
//     return true;
// }

// function checkMobile(){
//     var reg = /^1[3-8]+\d{9}$/;
//     if(!reg.test($('#contactPhone').val())){
//         $('#contactPhone').focus();
//         appendWrongInfo($('#contactPhone'), "请输入正确的手机号");
//         return false;
//     }
//     removeWrongTag($('#contactPhone'));
//     return true;
// }

// function checkEmail(){
//     var reg = /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
//     if(!reg.test($('#contactEmail').val())){
//         $('#contactEmail').focus();
//         appendWrongInfo($('#contactEmail'), "请输入正确的邮箱地址");
//         return false;
//     }
//     removeWrongTag($('#contactEmail'));
//     return true;
// }

function checkInfo(obj, name){
    var reg = /^[0-9a-zA-Z一\u4e00-\u9fa5\(\)\-（）&]{1,50}$/;
    if(!reg.test(obj.val())){
        obj.focus();
        appendWrongInfo(obj, "请输入正确的"+name);
        return false;
    }
    removeWrongTag(obj);
    return true;
}
function checkIndustry(obj){
    if(obj.val()==""){
        obj.focus();
        appendWrongInfo(obj, "所属行业必选");
        return false;
    }
    removeWrongTag(obj);
    return true;

}
function appendWrongInfo(obj, msg){
    obj.parent().children("span").remove(".fail");
    obj.after("<span class=\"fail\"><i></i>"+msg+"</span>");
}

function removeWrongTag(obj){
    obj.parent().children("span").remove(".fail");
}

$("#authCheckbox").live("change", function(){
    if($("#authCheckbox").prop("checked")){
        $("#submitInfo").attr("class","blueBtn");
        $("#submitInfo").removeAttr("disabled");
    }else{
        $("#submitInfo").attr("class","grayBtn");
        $("#submitInfo").attr('disabled',"true");
        $("#submitInfo").removeAttr("onclick");
    }
});

