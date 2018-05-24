package com.ipplus360.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 订单</br>
 * 一个订单可以包含多个订单项,
 * 与订单项一对多关联</br>
 * Created by 辉太郎 on 2017/2/22.</br>
 */
@Alias("order")
public class Order {
	/* 订单ID */
    private Long id;
    /* 订单号 */
    private String orderSerial;
    /* 订单创建时间 */
    private Date dateCreated;
    /* 订单更新时间*/
    private Date dateUpdated;
    /* 用户ID */
    private Long userId;
    /* 订单项 */
    private List<OrderItem> orderItems;
    /* 总价 */
    private BigDecimal price;
    /* 订单状态 */
    private Integer status;
    /* 订单备注*/
    private String description;
    
    /* 发票信息 */
    private Integer isInvoice;
    private Invoice invoice;
    /*发货状态*/
    //0:未发货,1:已发货
    private Integer shippingStatus;
    /*支付方式*/
    //0:在线支付,1:线下支付
    private Integer paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(Integer shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	@Override
	public String toString(){
		return "Order [id="+ id
				+ ", orderSerial="+ orderSerial
				+ ", dateCreated="+ dateCreated
				+ ", dateUpdated="+ dateUpdated
				+ ", userId="+ userId
				+ ", orderItems="+ orderItems
				+ ", price="+ price
				+ ", status="+ status
				+ ", description="+description
				+ ", isInvoice"+ isInvoice
				+ ", shippingStatus="+ shippingStatus
				+ ", paymentMethod="+ paymentMethod
				+ "]";
	}

}
