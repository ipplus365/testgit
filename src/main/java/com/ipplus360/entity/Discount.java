package com.ipplus360.entity;

/**
 * Created by 辉太郎
 * 折扣
 * 创建时间2017年3月8日   
 */

public class Discount {
	//折扣id
	private Long id;
	/* 定位精度*/
	private Integer accuracyId;
    //价格套餐id
	private Long pricePackageId;
    //套餐折扣数量要求
	private Integer packageAmount;
    //套餐折扣
	private String discount;
	/* 套餐包含查询次数 */
	private Long amount;
	/* 套餐单价格 */
	private String  price;
	/* 套餐总价*/
	private String  totalPrice;

	/* 计价方式 */
	private String unit;
	
	public Discount(){
		
	}
	
	public Discount(Integer accuracyId,Long pricePackageId,Integer packageAmount){
		this.accuracyId = accuracyId;
		this.pricePackageId = pricePackageId;
		this.packageAmount = packageAmount;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getAccuracyId() {
		return accuracyId;
	}

	public void setAccuracyId(Integer accuracyId) {
		this.accuracyId = accuracyId;
	}


	public Long getPricePackageId() {
		return pricePackageId;
	}

	public void setPricePackageId(Long pricePackageId) {
		this.pricePackageId = pricePackageId;
	}

	public Integer getPackageAmount() {
		return packageAmount;
	}

	public void setPackageAmount(Integer packageAmount) {
		this.packageAmount = packageAmount;
	}

	public String getDiscount() {
		return discount;
	}
	
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String  getPrice() {
		return price;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setPrice(String  price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString(){
		return "Discount [id="+ id
				+ ", accuracyId="+ accuracyId
				+ ", pricePackageId="+ pricePackageId
				+ ", packageAmount="+ packageAmount
				+ ", discount="+ discount
				+ ", amount="+ amount
				+ ", price="+ price
				+ ", totalPrice="+ totalPrice
				+ ", unit="+ unit
				+ "]";
	}
}
