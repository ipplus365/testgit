package com.ipplus360.enums;

public enum RegType {

	PERSON(0, "个人注册"), 
	ORGNAIZATIONS(1, "企业注册");

	private Integer status;
	private String message;

	RegType(Integer status, String message) {
	        this.status = status;
	        this.message = message;
	    }

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
