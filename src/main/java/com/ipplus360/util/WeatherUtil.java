package com.ipplus360.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.ipplus360.entity.WeatherMessage;


public class WeatherUtil {
	
	public static WeatherMessage getWeatherByCity(String city) {
		WeatherMessage weatherMessage = new WeatherMessage();
		//获得天气温度和状态
		String path1 = "https://free-api.heweather.com/v5/now?city=" + city + "&key=55ff7faa93b74f3fa80f9043fad8e32c";
		//获得天气质量
		String path2 = "https://free-api.heweather.com/v5/aqi?city="+city+"&key=55ff7faa93b74f3fa80f9043fad8e32c";
		String str1 = getStrByPath(path1);
		String str2 = getStrByPath(path2);
		Map<String, Object> map = changeToMap(str1);
		Map<String, Object> maps = changeToMap(str2);
	
		if( map.get("city") != null){
			weatherMessage.setCity( String.valueOf(map.get("city")));
		}
		if( map.get("txt") != null){
			weatherMessage.setTxt(String.valueOf(map.get("txt")));
		}
		if( map.get("tmp") != null){
			weatherMessage.setTmp(String.valueOf(map.get("tmp")));
		}
		if( map.get("status") != null){
			weatherMessage.setStatus(String.valueOf(map.get("status")));
		}
		if( maps.get("qlty") != null){
			weatherMessage.setQlty(String.valueOf(maps.get("qlty")));
		}
		return weatherMessage;
	}
	
	static Map<String, Object> map = new HashMap<String, Object>();

	public static Map<String, Object> changeToMap(String str) {
		if(!str.equals("")){
			// json格式的字符串
			net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(str);
			Set<String> set = obj.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = "";
				if(key.equals("HeWeather5")){
					value = obj.get(key).toString().substring(1, obj.get(key).toString().length()-1);
					changeToMap(value);
				}else{
					value =  obj.get(key).toString();
				}
				
				if (obj.get(key) instanceof Map) {
					changeToMap(value);
				} else {
					map.put(key, obj.get(key).toString());
					
				}
			}
		}		
		return map;
	}
	
	public static String getStrByPath(String path){
		String str = "";
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {// 循环读取流
				sb.append(line);
			}
			br.close();// 关闭流
			conn.disconnect();// 断开连接
			str = sb.toString();
		} catch (Exception e) {
	       str = "";
		}
		return str;
	}
	
	public static void main(String[] args) {
	  String path = "https://free-api.heweather.com/v5/weather?city=郑州&key=55ff7faa93b74f3fa80f9043fad8e32c";
	  String str = getStrByPath(path);
	  JSONObject w =JSON.parseObject(str);
	}
}
