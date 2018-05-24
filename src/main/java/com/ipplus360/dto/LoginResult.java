package com.ipplus360.dto;

/**
 * Created by 辉太郎
 *
 * 创建时间2017年3月4日   
 */

public class LoginResult<T> {
	
	private boolean isLoginOk;
	private T data;
    private String msg;
    
    public LoginResult(){
    }
    
    public LoginResult(boolean isLoginOk,String msg){
    	this.isLoginOk = isLoginOk;
    	this.msg = msg;
    }
    
    public boolean isLoginOk() {
		return isLoginOk;
	}
    
	public void setIsLogin(boolean isLoginOk) {
		this.isLoginOk = isLoginOk;
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
    
}
