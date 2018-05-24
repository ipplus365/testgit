<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	response.setHeader("Expires", "0");
	response.setHeader("Pragma","no-cache"); 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link rel="stylesheet" href="/statics/css/reset.css">
    <link rel="stylesheet" href="/statics/css/common.css">
    <link rel="shortcut icon" href="/statics/images/favicon.ico"/>
    <style>
        .user-content-r{
            float: left;
        }
        .wrap{
            width: 1805px;
        }
    </style>
</head>
<body class="bg-white">
    <!--顶部用户信息栏-->
    <c:import url="../layout/header.jsp"/>
    <!--content-->
    <div class="user-content">
        <div class="wrap cl">
            <div class="user-content-l">
                <h3 class="title-v1">用户中心</h3>
                <ul>
                    <li><a href="/person/user" class="icon-info"><i class="sp-icon"></i>基本信息</a></li>
                    <li><a href="/person/order" class="icon-order"><i class="sp-icon"></i>我的订单</a></li>
                    <li><a href="/person/comment"><i class="inav2"></i>我的评论</a></li>
                    <li class="current"><a href="" class="icon-company"><i class="sp-icon"></i>企业认证</a></li>
                    <li><a href="/person/changPwd" class="icon-info"><i class="sp-icon"></i>修改密码</a></li>
                </ul>
            </div>
            <div id="detail">
            <c:choose>
            <c:when test="${company==null}">
			<form id="userAuth" action="/company/submit" method="post" autocomplete="off" style="display: block;" enctype="multipart/form-data">
                    <div class="user-content-r" >
                        <h3 class="title-v1" style="text-align: center;font-weight:bold;">
                            实名认证
                        </h3>
                        <div class="certification">
                            <div class="certification-item">
                                <h4>企业基本信息</h4>
                                <dl>
                                    <dt><i class="required">*</i>公司名称：</dt>
                                    <dd>
                                        <input type="text" class="normal-input"  name="orgName" value="${company.orgName }" placeholder="需与营业执照上的名称一致，认证通过，企业名称不可修改。" >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>所属行业：</dt>
                                    <dd>
                                    	<input type="hidden" id="industry" name="industry" value="${company.industry }">
                                        <select class="normal-input"  id="industryCode" required onchange="setSelectValue(this.options[this.selectedIndex].text)">
                                            <option value="">请选择</option>
                                            <option value="1" tag="">互联网行业
                                                (IT、电子、互联网等)
                                            </option>
                                            <option value="2" tag="">金融行业
                                                (银行、保险、基金、证券等)
                                            </option>
                                            <option value="3" tag="">房地产/建筑业
                                                (建筑、装潢、物业等)
                                            </option>
                                            <option value="4" tag="">商业服务
                                                (广告、中介、外包、质检等)
                                            </option>
                                            <option value="5" tag="">贸易零售
                                                (进出口、租赁等)
                                            </option>
                                            <option value="6" tag="">通讯行业
                                                (电信服务、通讯设备等)
                                            </option>
                                            <option value="7" tag="">服务业
                                                (酒店、餐饮、旅游、度假等)
                                            </option>
                                            <option value="8" tag="">文化传媒
                                                (媒体、出版、娱乐等)
                                            </option>
                                            <option value="9" tag="">生产加工制造
                                                (印刷、医药、包装等)
                                            </option>
                                            <option value="10" tag="">物流行业
                                                (交通、运输、物流仓储等)
                                            </option>
                                            <option value="11" tag="">能源环保
                                                (矿产、石油、电气、水利等)
                                            </option>
                                            <option value="12" tag="">政府机构
                                            </option>
                                            <option value="13" tag="">农林牧渔
                                            </option>
                                            <option value="14" tag="">个人使用
                                            </option>
                                            <option value="15" tag="">其他
                                            </option>
                                        </select>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>营业执照所在地/住所：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" name="licenseAddress" id="entAddress" value="${company.licenseAddress }">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>法人：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="legalName" name="legalPerson" value="${company.legalPerson}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>法人身份证号：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="legalNumber" name="legalPersonId" value="${company.legalPersonId}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactName" name="businessContacts" value="${company.businessContacts}" >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人手机：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactPhone" name="mobile" value="${company.mobile}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>公司业务联系人邮箱：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="contactEmail" name="email" value="${company.email}">
                                    </dd>
                                </dl>
                            </div>
                            <div class="certification-item">
                                <h4>企业资质信息</h4>
                                <dl>
                                    <dt><i class="required">*</i>统一社会信用代码：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="licenseNumber" name="licenseNumber" value="${company.licenseNumber}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required">*</i>营业执照有效期/营业期限：</dt>
                                    <dd>
                                        自&nbsp;<input class="normal-input w153" type="text" id="validityStart"
                                                      name="licenseStartDate"
                                                      value="<fmt:formatDate value="${company.licenseStartDate}" pattern="yyyy-MM-dd"/>"
                                                      readonly="readonly"                                                onfocus="WdatePicker();" > <i class="dateIcon"></i>
                                        至&nbsp;<input type="text" class="normal-input w153" id="validityEnd" name="licenseEndDate"
                                                      value="<fmt:formatDate value="${company.licenseEndDate}" pattern="yyyy-MM-dd"/>"
                                                      readonly="readonly"                                                onfocus="WdatePicker();" > <i class="dateIcon"></i>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt class="pt40"><i class="required">*</i>营业执照电子版：</dt>
                                    <dd class="update-img">
                                        <img src="/statics/images/update_default.png" width="160" height="120"
                                             id="licensePicDisplay">
                                        <input type="file" name="file" id="licensePicUrl">
                                        <i class="required">*</i>图片大小不能超过2M
                                    </dd>
                                </dl>
                            </div>
                            <div class="certification-item">
                                <h4>企业对公账号</h4>
                                <dl>
                                    <dt><i class="required"></i>公司对公账号：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="bankAccount" name="bankAccount"
                                               value="${company.bankAccount}"
                                                >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required"></i>银行开户名：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="accountName" name="bankUserName"
                                               value="${company.bankUserName}" maxlength=30
                                                >
                                    </dd>
                                </dl>
                                <dl>
                                    <dt><i class="required"></i>对公账号开户行：</dt>
                                    <dd>
                                        <input type="text" class="normal-input" id="bankName" name="bank"
                                               value="${company.bank}"
                                                >
                                    </dd>
                                </dl>
                            </div>
                            <input type="hidden" id="userType" name="userType" value="0"/>
                            <div class="btnDiv">
                                <span style="color: #ff0000" id="returnInfo"></span>
                                <dl>
                                    <dd><input type="checkbox" id="authCheckbox"> 我已阅读并同意<a href="/company/protocol"
                                                                                            target="_blank">《实名认证协议》</a>
                                    </dd>
                                </dl>
                                <input class="grayBtn" type="submit" id="submitInfo" disabled></input>

                                <a href="/person/user " class="grayBtn ml60">取消</a>
                            </div>
                        </div>
                    </div>
                </form>
              	</c:when>
              	<c:otherwise>
              	<div class="user-content-r" >
                    <c:if test="${company != null && company.available == 0 }">
                	    <h3 class="title-v1" style="text-align: center;font-weight:bold;">企业认证中...</h3>
                    </c:if>
                    <c:if test="${company.available == 1}">
                        <h3 class="title-v1" style="text-align: center;font-weight:bold;">已认证</h3>
                    </c:if>
                	<div class="certification">
	                    <div class="certification-item">
	                        <h4>企业基本信息</h4>
	                        <dl>
	                            <dt><i class="required">*</i>公司名称：</dt>
	                            <dd>
	                                ${company.orgName }
	                            </dd>
	                        </dl>
	                        <dl>
	                            <dt><i class="required">*</i>所属行业：</dt>
	                            <dd>
	                                ${company.industry }
	                            </dd>
	                        </dl>
	                        <dl>
	                            <dt><i class="required">*</i>营业执照所在地/住所：</dt>
	                            <dd>
	                                ${company.licenseAddress }
	                            </dd>
	                        </dl>
	                        <dl>
	                            <dt><i class="required">*</i>法人：</dt>
	                            <dd>
	                                ${company.legalPerson}
	                            </dd>
	                        </dl>
	                        <c:if test="${company.legalPersonId != null && company.legalPersonId !='' }">
	                        <dl>
	                            <dt><i class="required"></i>法人身份证号：</dt>
	                            <dd>
	                               ${company.legalPersonId}
	                            </dd>
	                        </dl>
	                        </c:if>
	                        <c:if test="${company.businessContacts != null && company.businessContacts != ''}">
	                        <dl>
	                            <dt><i class="required"></i>公司业务联系人：</dt>
	                            <dd>
	                                ${company.businessContacts}
	                            </dd>
	                        </dl>
	                        </c:if>
	                        <c:if test="${company.mobile != null && company.mobile !='' }">
	                        <dl>
	                            <dt><i class="required"></i>公司业务联系人手机：</dt>
	                            <dd>
	                                ${company.mobile}
	                            </dd>
	                        </dl>
	                        </c:if>
	                        <c:if test="${company.email != null && company.email != '' }">
	                        <dl>
	                            <dt><i class="required"></i>公司业务联系人邮箱：</dt>
	                            <dd>
	                                ${company.email}
	                            </dd>
	                        </dl>
	                        </c:if>
	                    </div>
	                    <div class="certification-item">
	                        <h4>企业资质信息</h4>
	                        <dl>
	                            <dt><i class="required">*</i>统一社会信用代码：</dt>
	                            <dd>
	                               ${company.licenseNumber}
	                            </dd>
	                        </dl>
	                        <dl>
	                            <dt><i class="required">*</i>营业执照有效期/营业期限：</dt>
	                            <dd>自&nbsp;
	                            <fmt:formatDate value="${company.licenseStartDate}" pattern="yyyy年MM月dd日"/>
	                            	&nbsp;至&nbsp;
	                            <fmt:formatDate value="${company.licenseEndDate}" pattern="yyyy年MM月dd日"/>
	                 				&nbsp;
	                            </dd>
	                        </dl>
	                        <!-- <dl>
	                            <dt class="pt40"><i class="required">*</i>营业执照电子版：</dt>
	                            <dd class="update-img">
	                                <img src="/statics/images/update_default.png" width="160" height="120"
	                                     id="licensePicDisplay">
	                                <input type="file" name="file"
	                                        >
	                                <input type="hidden" name="licensePic" id="licensePic">
	                                <i class="required">*</i>图片大小不能超过1M
	                            </dd>
	                        </dl> -->
	                    </div>
	                    
	                        <c:choose>
	                        	<c:when test="${company.bankAccount != null && company.bankAccount !='' }">
		                        	<div class="certification-item">
		                        	<h4>企业对公账号</h4>
	                        	</c:when>
	                        	<c:when test="${company.bankUserName != null && company.bankUserName !=''}">
	                        		<div class="certification-item">
	                        		<h4>企业对公账号</h4>
                        		</c:when>
	                        	<c:when test="${company.bank != null && company.bank !='' }">
	                        		<div class="certification-item">
	                        		<h4>企业对公账号</h4>
                        		</c:when>
                        		<c:otherwise>
                        			<div class="certification-spe">
                        		</c:otherwise>
	                        </c:choose>
	                        <c:if test="${company.bankAccount != null && company.bankAccount !='' }">
	                        <dl>
	                            <dt><i class="required"></i>公司对公账号：</dt>
	                            <dd>
	                                ${company.bankAccount}
	                            </dd>
	                        </dl>
	                        </c:if>
	                        <c:if test="${company.bankUserName != null && company.bankUserName !=''}">
	                        <dl>
	                            <dt><i class="required"></i>银行开户名：</dt>
	                            <dd>
	                                ${company.bankUserName}
	                            </dd>
	                        </dl>
	                        </c:if>
	                        <c:if test="${company.bank != null && company.bank !='' }">
	                        <dl>
	                            <dt><i class="required"></i>对公账号开户行：</dt>
	                            <dd>
	                                ${company.bank}
	                            </dd>
	                        </dl>
	                        </c:if>
	                    </div>
	                   <!--  <input type="hidden" id="userType" name="userType" value="0"/> -->
	                    <!-- <div class="btnDiv">
	                        <span style="color: #ff0000" id="returnInfo"></span>
	                        <dl>
	                            <dd><input type="checkbox" id="authCheckbox"> 我已阅读并同意<a href="/help/center/100"
	                                                                                    target="_blank">《实名认证协议》</a>
	                            </dd>
	                        </dl>
	                        <input   class="grayBtn" type="submit" id="submitInfo"></input>
	
	                        <a href="/user/baseInfo " class="grayBtn ml60">取消</a>
	                    </div> -->
	                </div>
              	</div>
              	</c:otherwise>
            </c:choose>
            </div>
        </div>
    </div>
</body>
</html>
<script src="/statics/js/jquery-1.7.2.min.js"></script>
<script src="/statics/js/company.js"></script>
<script src="/statics/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){ 
	var msg = '${msg}';
	if(msg !=''){
		alert(msg);
		msg='';
	}
});
function setSelectValue(a){
	document.getElementById("industry").value=a;
}

$("#licensePicUrl").change(function(){
    var objUrl = getObjectURL(this.files[0]);
    if (objUrl) {
        $("#licensePicDisplay").attr("src", objUrl) ;
    }
}) ;
//建立一個可存取到該file的url
function getObjectURL(file){
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
</script>

