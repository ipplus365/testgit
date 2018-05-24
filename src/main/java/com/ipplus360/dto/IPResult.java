package com.ipplus360.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * 定位返回DTO 收费信息charge为true
 * Created by 辉太郎 on 2017/2/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IPResult<T> {
	private int code;
	private String ip;
	private boolean charge;
	/* 坐标系 WGS84 BD09 */
	private String coordsys;
	private T data;
	private String msg;

	public IPResult() {
	}

	public IPResult(int code, boolean charge, String ip, T data, String msg) {
		this.code = code;
		this.charge = charge;
		this.ip = ip;
		this.msg = msg;
		this.data = data;
	}

	public IPResult(int code, boolean charge, String ip, T data, String msg, String coordsys) {
		this.code = code;
		this.charge = charge;
		this.ip = ip;
		this.msg = msg;
		this.data = data;
		this.coordsys = coordsys;
	}

	public IPResult(int code, boolean charge, String ip, String msg) {
		this.code = code;
		this.charge = charge;
		this.ip = ip;
		this.msg = msg;
	}

	public IPResult(T data, int code) {
		this.code = code;
		this.data = data;
	}

	public IPResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public IPResult(boolean charge, String msg) {
		this.charge = charge;
		this.msg = msg;
	}

	public IPResult(int code, String msg, Map<String, Object> params) {
		this.code = code;
		this.msg = msg;
		this.charge = (boolean) params.get("charge");
		// TODO
	}

	public IPResult(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCoordsys() {
		return coordsys;
	}

	public void setCoordsys(String coordsys) {
		this.coordsys = coordsys;
	}

	@Override
	public String toString() {
		return "IPResult{" + "code=" + code + ", ip='" + ip + '\'' + ", charge=" + charge + ", data=" + data + ", msg='"
				+ msg + '\'' + '}';
	}
}
