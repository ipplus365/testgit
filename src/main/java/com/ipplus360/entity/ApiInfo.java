package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;

@Alias("apiInfo")
public class ApiInfo {

	private int id;
	private int product_id;
	private String returnType;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\":")
				.append(id);
		sb.append(",\"product_id\":")
				.append(product_id);
		sb.append(",\"returnType\":\"")
				.append(returnType).append('\"');
		sb.append('}');
		return sb.toString();
	}
}
