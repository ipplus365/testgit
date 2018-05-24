package com.ipplus360.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSUtil {
	
	static final String appkey = "24606872";
	static final String secret = "58804b129dde97e7e84222d94ca8a566";
	static final String url = "http://gw.api.taobao.com/router/rest";


	// 短信发送
	public static boolean sendSms(Map<String,String> map) {
		String appkey = "24606872";
		String secret = "58804b129dde97e7e84222d94ca8a566";
		String url = "http://gw.api.taobao.com/router/rest";
		boolean success = false;
		try {
			TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			
			req.setExtend("");
			req.setSmsType("normal");
			req.setSmsFreeSignName("埃文科技");
			
			
			if(map.get("expireCode") != null && map.get("code") != null){
				req.setSmsParamString("{code:'" + map.get("code") + "',expire:'"+map.get("expireCode")+"'}");
			}else if(map.get("code") != null){
				req.setSmsParamString("{code:'" +  map.get("code")  + "',expire:'30'}");
			}
			
			if(map.get("phoneNum") != null){
				req.setRecNum(map.get("phoneNum"));
			}
			
			if(map.get("expireCode")!=null){
				req.setSmsParamString("{code:'" + map.get("code") + "',expire:'"+map.get("expireCode")+"'}");
			}else{
				req.setSmsParamString("{code:'" + map.get("code") + "',expire:'30'}");
			}
			req.setRecNum(map.get("phoneNum"));
			if(map.get("smsTemplateCode") != null){
				req.setSmsTemplateCode(map.get("smsTemplateCode"));
			}else{
				req.setSmsTemplateCode("SMS_91750002");
			}
			
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			//System.out.println("======"+rsp.getBody());
			
			
			Map<String, Object> maps = changeToMap(rsp.getBody());
			if(maps.get("success").toString().equals("true")){
				return true;
			}
		} catch (ApiException e) {
			return false;
		}
		return false;
	}

	static Map<String, Object> mapd = new HashMap<String, Object>();
			
	public static Map<String, Object> changeToMap(String str) {
		// json格式的字符串
		net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(str);
		Set<String> set = obj.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (obj.get(key) instanceof Map) {
				changeToMap(obj.get(key).toString());
			} else {
				mapd.put(key, obj.get(key).toString());
			}
		}
		return mapd;
	}
	

}
