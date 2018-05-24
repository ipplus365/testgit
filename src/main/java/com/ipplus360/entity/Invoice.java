package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;


/**
 * 发票信息</br>
 * 与订单一对一关联,</br>
 * Created by 辉太郎 on 2017/3/09.</br>
 * @author wangguoyan
 *
 */
@Alias("invoice")
public class Invoice {
	
	private Long id;
	private Long userId;
	private String orderId;
	/*公司名称*/
	private String orgName;
	/*纳税人识别号*/
	private String taxId;
	/*公司注册地址*/
	private String regAddress;
	/*公司注册电话*/
	private String regMobile;
	/*公司注册银行*/
	private String bank;
	/*公司注册银行账号*/
	private String bankAccount;
	/*收票人*/
	private String userContacts;
	/*收票人手机*/
	private String userMobile;
//	/*收票人省份*/
//	private String userProvince;
//	/*收票人城市*/
//	private String userCity;
//	/*收票人地区*/
//	private String userArea;
	/*收票人地址*/
	private String userAddress;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getRegAddress() {
		return regAddress;
	}
	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	public String getRegMobile() {
		return regMobile;
	}
	public void setRegMobile(String regMobile) {
		this.regMobile = regMobile;
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
	public String getUserContacts() {
		return userContacts;
	}
	public void setUserContacts(String userContacts) {
		this.userContacts = userContacts;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
//	public String getUserProvince() {
//		return userProvince;
//	}
//	public void setUserProvince(String userProvince) {
//		this.userProvince = userProvince;
//	}
//	public String getUserCity() {
//		return userCity;
//	}
//	public void setUserCity(String userCity) {
//		this.userCity = userCity;
//	}
//	public String getUserArea() {
//		return userArea;
//	}
//	public void setUserArea(String userArea) {
//		this.userArea = userArea;
//	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	@Override
	public String toString(){
		return "Invoice [id="+ id
				+ ", userId="+ userId
				+ ", orderId="+ orderId
				+ ", orgName="+ orgName
				+ ", taxId="+ taxId
				+ ", regAddress="+ regAddress
				+ ", regMobile="+ regMobile
				+ ", bank="+ bank
				+ ", bankAccount="+ bankAccount
				+ ", userContacts="+ userContacts
				+ ", userMobile="+ userMobile
//				+ ", userProvince="+ userProvince
//				+ ", userCity="+ userCity
//				+ ", userArea="+ userArea
				+ ", userAddress="+ userAddress
				+ "]";
	}
	
}
