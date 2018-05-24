package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;

@Alias("productdesc")
public class ProductDesc {
	
	private Long id;
	/*产品ID*/
	private Long productId;
	/*详细描述*/
	private String description;
	/*覆盖范围*/
	private String coverage;
	/*应用领域*/
	private String applicationAreas;
	/*产品特点*/
	private String productFeatures;
	/*在线并发*/
	private String concurrency;
	/*使用规范*/
	private String usageRules;
	/*注意事项*/
	private String attention;
	/*优惠规则*/
	private String discountPolicies;
	//api详情
	private String returnType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public String getApplicationAreas() {
		return applicationAreas;
	}
	public void setApplicationAreas(String applicationAreas) {
		this.applicationAreas = applicationAreas;
	}
	public String getProductFeatures() {
		return productFeatures;
	}
	public void setProductFeatures(String productFeatures) {
		this.productFeatures = productFeatures;
	}
	public String getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(String concurrency) {
		this.concurrency = concurrency;
	}
	public String getUsageRules() {
		return usageRules;
	}
	public void setUsageRules(String usageRules) {
		this.usageRules = usageRules;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public String getDiscountPolicies() {
		return discountPolicies;
	}
	public void setDiscountPolicies(String discountPolicies) {
		this.discountPolicies = discountPolicies;
	}
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
}
