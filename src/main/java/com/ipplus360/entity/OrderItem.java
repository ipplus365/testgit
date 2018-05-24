package com.ipplus360.entity;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

/**
 * 订单项</br>
 * 一个订单项只能属于一个订单, 与订单关系是多对一</br>
 * Created by 辉太郎 on 2017/2/22.
 */
@Alias("orderItem")
public class OrderItem {
	/* 订单项ID */
	private Long id;
	/* 订单ID */
	private Long orderId;
	/* 订单项数量 */
	private Integer itemNum;
	/* 产品信息 */
	private Product product;
	/* 价格包 */
	private PricePackage pricePackage;
	private Long pricePackageId;

	/* 数量 */
	private String amountStr;
	private Long amount;
	/**
	 * 订单类型 1.IPSceneAPI 2.IPSceneDownload 3.IPDistrictAPI 4.IPDistrictDownload
	 */
	private String itemType;

	/**
	 * 区县库, 场景库规格 WGS84,BD09 更新频率 数据格式...
	 */
	private String attrs;
	private String attrIds;

	/* 实际总价 */
	private BigDecimal price;
	/* 原始总价 */
	private BigDecimal originalPrice;
	/* 折扣信息 */
	private String discount;

	public OrderItem() {

	}

	public OrderItem(Product product) {
		this.product = product;
		this.pricePackage = new PricePackage();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PricePackage getPricePackage() {
		return pricePackage;
	}

	public void setPricePackage(PricePackage pricePackage) {
		this.pricePackage = pricePackage;
	}

	public Long getPricePackageId() {
		return pricePackageId;
	}

	public void setPricePackageId(Long pricePackageId) {
		this.pricePackageId = pricePackageId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	public String getAttrIds() {
		return attrIds;
	}

	public void setAttrIds(String attrIds) {
		this.attrIds = attrIds;
	}

	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", itemNum=" + itemNum + ", product=" + product
				+ ", pricePackage=" + pricePackage + ", amountStr=" + amountStr + ", amount=" + amount + ", price="
				+ price + ", originalPrice=" + originalPrice + ", discount=" + discount + ", itemType=" + itemType
				+ ", attrs=" + attrs + "]";
	}
}
