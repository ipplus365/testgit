package com.ipplus360.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;

/**
 * 收款确认
 * @author wangguoyan
 *
 */
@Alias("payConfirm")
public class PayConfirm {

	private static final long serialVersionUID = 1L;
	private Long id;

	private Long userId;

	private User users;

	private String orderSerial;
	/*支付创建日期*/
	private Date dateCreated;
	/*支付更新日期*/
	private Date dateUpdated;
	/*收款银行*/
	private String bank;
	/*收款账号*/
	private String bankAccount;
	/*收款金额*/
	private BigDecimal price;
	/*支付方式*/
	private String paymentMethod;
	/*收款人*/
	private String payee;
	/*备注*/
	private String description;
	/*TokenId*/
	private List<Long> tokenIds;
	/*TokenIdStr*/
	//private String tokenIdsStr;
	/*非测试包流量*/
	private Long counts;

	public PayConfirm() {
	}

	public PayConfirm(Long userId, String orderSerial, Date dateCreated, BigDecimal price, String paymentMethod, String payee) {
		this.userId = userId;
		this.orderSerial = orderSerial;
		this.dateCreated = dateCreated;
		this.price = price;
		this.paymentMethod = paymentMethod;
		this.payee = payee;
	}

	public List<Long> getTokenIds() {
		if(tokenIds == null){
			tokenIds = new ArrayList<Long>();
		}
		return tokenIds;
	}

	public void setTokenIds(List<Long> tokenIds) {
		this.tokenIds = tokenIds;
	}

	public String getTokenIdsStr() {
		if(CollectionUtils.isEmpty(tokenIds)){
			return  null;
		}
		StringBuilder sb = new StringBuilder();
		for(Long tokenId:tokenIds ){
			sb.append(tokenId);
			sb.append(",");
		}
		return sb.toString();
	}

	public void setTokenIdsStr(String tokenIdsStr) {
		if(StringUtils.isEmpty(tokenIdsStr)){
			return;
		}
		String[] tokenIdsStrs = tokenIdsStr.split(",");
		for (String tokenIdStr:tokenIdsStrs) {
			if(StringUtils.isEmpty(tokenIdStr)){
				continue;
			}
			getTokenIds().add(Long.valueOf(tokenIdStr));
		}
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId(){
		return userId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String toString(){
		return "PayConfirm:{" +
				"id:" +id+
				", userId:" +userId+
				", orderSerial:" +orderSerial+
				", dateCreated:" +dateCreated+
				", dateUpdated:" +dateUpdated+
				", price:" +price+
				", counts:" +counts+
				", paymentMethod:" +paymentMethod+
				", payee:" +payee+
				"}";
	}
}
