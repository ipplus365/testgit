package com.ipplus360.dto;

import com.ipplus360.entity.Invoice;

public class InvoiceOutput {
 
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
	/*收票人地址*/
	private String userAddress;
	
	public InvoiceOutput(){
		
	}
	
	public InvoiceOutput(Invoice invoice){
		this.orgName = invoice.getOrgName();
		this.taxId = invoice.getTaxId();
		this.regAddress = invoice.getRegAddress();
		this.regMobile = invoice.getRegMobile();
		this.bank = invoice.getBank();
		this.bankAccount = invoice.getBankAccount();
		this.userContacts = invoice.getUserContacts();
		this.userMobile = invoice.getUserMobile();
		this.userAddress = invoice.getUserAddress();
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
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
