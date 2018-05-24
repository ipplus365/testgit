<%@ page language="Java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="detail-left">
     <div class="conBox detail bg-white" id="conBox"  v-cloak>
         <nav class="nav new-tab">
             <ul>
                 <li v-for="(item, index) in data" id="detailTab1" v-on:click="isShow(index)" v-bind:class="[item.show ? 'on' : '']">
                     <a href="javascript:;">{{item.msg}}</a>
                 </li>
             </ul>
             <div class="tab-right">
                 <span class="price"></span>
                 <a href="javascript:;" class="purchaseBtn" clstag="pageclick|keycount|wxlink_201512091|89">立即购买</a>
             </div>
         </nav>
         <div id="con_detail">
             <div id="con-detailTab-1" style="display:block;">
                 <%--<aside class="side-list">--%>
                     <%--<ul>--%>
                         <%--<li id="index0"  class="cur">--%>
                             <%--<a title="nht" href="javascript:;"><i></i>nht</a>--%>
                         <%--</li>--%>
                     <%--</ul>--%>
                 <%--</aside>--%>
                 <section class="content" id="interface0" style="margin-left: 0px;">
                     ${productDesc.returnType}
                 </section>
                 <div class="mask" id="confirmAppKey" style="display:none">
                     <div class="homePop innerPop conAPP" id="confirmApp">
                         <div class="myappKey appKeyDiv">密钥</div>
                         <div class="appclose"></div>
                         <div class="text appcontent appKeyCon" style="top:30px;left:25px;">
                                    这是您在埃文科技调用服务所需要的密钥，<br>
                                    可以调用平台所有即用服务。
                             <div class="small" style="font-weight: bolder;color:red;"></div>
                            		 您可以在接口测试工具及用户中心查看您的<br>
                             APPKEY，为了您能够安全调用，请不要将<br>
                             	密钥给他人使用。
                         </div>
                     </div>
                 </div>
             </div>
             <div id="con-detailTab-2" style="display:none;">
                 <div class="detail content_error" style="min-height: 500px" >
                     <section class="textContent" id="intro" style="overflow:hidden">
                         <p>${productDesc.description } </p>
                         <br />
                         <table id="checkout" class="order_data checkout-steps" cellpadding="0" cellspacing="0">
                             <tbody>
                                <tr>
                                    <th>覆盖范围</th>
                                    <td>${productDesc.coverage}</td>
                                </tr>
                                <tr>
                                    <th>应用领域</th>
                                    <td>${productDesc.applicationAreas }</td>
                                </tr>
                                <tr>
                                    <th>产品特点</th>
                                    <td>${productDesc.productFeatures }</td>
                                </tr>
                                <tr>
                                     <th>在线并发</th>
                                     <td>${productDesc.concurrency }</td>
                                </tr>
                                <tr>
                                    <th>使用规范</th>
                                    <td>${productDesc.usageRules }</td>
                                </tr>
                                <tr>
                                    <th>注意事项</th>
                                    <td>${productDesc.attention }</td>
                                </tr>
                                <tr>
                                    <th>优惠规则</th>
                                    <td>${productDesc.discountPolicies }</td>
                                </tr>
                             </tbody>
                         </table>
                     </section>
                     <div class="clear"></div>
                 </div>
             </div>
             <div id="con-detailTab-3" style="display:none;">
                 <div class="detail content_error" >
                     <section>
                         <h4 class="f18 gray mb15">系统级错误码参照：</h4>
                         <table class="rectbl noboder">
                             <colgroup>
                                 <col width="300">
                                 <col width="550">
                             </colgroup>
                             <thead>
                                 <tr>
                                     <th class="tleft">错误码</th>
                                     <th class="tleft">说明</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr>
                                     <td class="tleft">200</td>
                                     <td class="tleft">查询成功</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft">403</td>
                                     <td class="tleft">错误的请求key</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft">404</td>
                                     <td class="tleft">IP格式不正确</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft">405</td>
                                     <td class="tleft">参数不正确</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft">406</td>
                                     <td class="tleft">接口调用太频繁</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft">500</td>
                                     <td class="tleft">服务异常</td>
                                 </tr>
                                 <%--<tr>
                                     <td class="tleft"> 10010 </td>
                                     <td class="tleft">接口需要付费，请充值</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10020 </td>
                                     <td class="tleft">系统繁忙，请稍后再试</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10030 </td>
                                     <td class="tleft">调用万象网关失败， 请联系管理员</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10040 </td>
                                     <td class="tleft">超过每天限量，请明天继续</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10050 </td>
                                     <td class="tleft">用户已被禁用</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10060 </td>
                                     <td class="tleft">提供方设置调用权限，请联系提供方</td>
                                 </tr>
                                 <tr>
                                     <td class="tleft"> 10070 </td>
                                     <td class="tleft">该数据只允许企业用户调用</td>
                                 </tr>--%>
                             </tbody>
                         </table>
                     </section>
                 </div>
             </div>
             <div id="con-detailTab-5" style="display:none;">
                 <div class="comment-w">
                     <ul class="qus-box mt25">
                         <li>
                             <textarea class="qus-txt" v-model="commentContent" id="publishComment" placeholder="我有话要说(本条评论字数不得超过150字)" maxlength="149"></textarea>
                         </li>
                         <li>
                             <a href="javascript:;" class="blueBtn" id="submitComment" @click="addComment()">提交</a>
                             <span class="red">{{ warnMsg }}</span>
                         </li>
                     </ul>
                     <ul class="comment-lst script-comment-list">
                      <li v-for="(item, index) in com">
                     	<div class="comment-tit">
                     		{{ item.email }}
                     		<span class="comment-time">
                     			{{  new Date(item.commentDate).toLocaleString()}}
                     		</span>
                     	</div>
                     		<p class="comment-p">{{item.content}}</p>
                     	</li>
                  </ul>
                  <div class="pageControl cursor_hover">
                     	<span @click="firstCommentPage">首页</span>
                     	<span @click="reduceComPage()">上一页</span>
                     	<span @click="addComPage()">下一页</span>
                     	<span @click="lastCommentPage">尾页</span>
                     	<span>第  {{commentCurPage}}  页/共  {{totalCommentPage}}  页</span>
                     </div>
                     <span class="async-page"></span>
                 </div>
             </div>
             <div id="con-detailTab-6" style="display:none;">
                 <div class="order-w">
                     <div class="order-lst script-order-list">
                         <div class="detail content_error">
                     		<section class="textContent  whitebg">
                     			<div>
                     				<table class="rectbl">
                     					<colgroup>
                     						<col width="220"/>
                     						<col width="300"/>
                     						<col width="250"/>
                     						<col width="250"/>
                     					</colgroup>
                     					<thead>
                     						<tr>
                     							<th>用户</th>
                     							<c:choose>
											       	<c:when test="${productInfo.id==5}">
											             <th>下载时间</th>
											       	</c:when>
											      	<c:otherwise>
											           	 <th>购买时间</th>
											       	</c:otherwise>
												</c:choose>
                     							<th>数量</th>
                     							<th>规格</th>
                     						</tr>
                     					</thead>
                     					<tbody>
                     						<tr v-for="(item,index) in order">
                      						<td>{{ item.email }}</td>
                      						<td>{{ new Date(item.date_created).toLocaleString() }}</td>
                      						<td>{{ item.item_num }}</td>
                      						<td>{{ item.amount }}</td>
                      					</tr>
                      				</tbody>
                     				</table>
                     				<div class="pageControl cursor_hover">
			                         	<span @click="firstOrderPage">首页</span>
			                         	<span @click="reduceOrderPage()">上一页</span>
			                         	<span @click="addOrderPage()">下一页</span>
			                         	<span @click="lastOrderPage">尾页</span>
			                         	<span>第  {{orderCurPage}}  页/共  {{totalOrderPage}}  页</span>
			                         </div>
                     			</div>
                     		</section>
                     	</div>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>