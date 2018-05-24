package com.ipplus360.entity;

public class WeatherMessage {

	private String txt;// 天气状态
	private String city;// 城市
	private String qlty;// 天气质量
	private String tmp;// 天气温度
	private String status;// 获取状态


	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQlty() {
		return qlty;
	}

	public void setQlty(String qlty) {
		this.qlty = qlty;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
