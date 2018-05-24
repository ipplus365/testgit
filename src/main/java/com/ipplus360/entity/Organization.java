package com.ipplus360.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公司
 * Created by 辉太郎 on 2017/2/21.
 */
@Alias("organization")
public class Organization {
    private Long id;
    /*用户ID*/
    private Long userId;
    /*公司名称*/
    private String orgName;
    /*所属行业*/
    private String industry;
    /*企业法人*/
    private String legalPerson;
    /*法人身份证号*/
    private String legalPersonId;
    /*公司业务联系人*/
    private String businessContacts;
    /*公司业务联系人手机*/
    private String mobile;
    /*公司业务联系人邮箱*/
    private String email;
    /*营业执照号/统一社会信用代码*/
    private String licenseNumber;
    /*营业执照所在地*/
    private String licenseAddress;
    /*营业执照有效期/营业期限*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date licenseStartDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date licenseEndDate;
    /*营业执照电子版*/
    private String licenseDir;
    /*公司对公账号*/
    private String bankAccount;
    /*银行开户名*/
    private String bankUserName;
    /*对公账号开户行*/
    private String bank;

    private String token;

    private Integer available;

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

	public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonId() {
		return legalPersonId;
	}

	public void setLegalPersonId(String legalPersonId) {
		this.legalPersonId = legalPersonId;
	}

	public String getBusinessContacts() {
        return businessContacts;
    }

    public void setBusinessContacts(String businessContacts) {
        this.businessContacts = businessContacts;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseAddress() {
		return licenseAddress;
	}

	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress;
	}

	public Date getLicenseStartDate() {
		return licenseStartDate;
	}
	
	public void setLicenseStartDate(Date licenseStartDate) {
		this.licenseStartDate = licenseStartDate;
	}

	public Date getLicenseEndDate() {
		return licenseEndDate;
	}
	public void setLicenseEndDate(Date licenseEndDate) {
		this.licenseEndDate = licenseEndDate;
	}

	public String getLicenseDir() {
		return licenseDir;
	}

	public void setLicenseDir(String licenseDir) {
		this.licenseDir = licenseDir;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public Integer getAvailable() {
        return available;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
    
    public String toString(){
    	return "Organization [id="+ id
    			+ ", userId="+ userId
    			+ ", orgName="+ orgName
    			+ ", industry="+ industry
    			+ ", legalPerson="+ legalPerson
    			+ ", legalPersonId="+ legalPersonId
    			+ ", businessContacts="+ businessContacts
    			+ ", mobile="+ mobile
    			+ ", email="+ email
    			+ ", licenseNumber="+ licenseNumber
    			+ ", licenseAddress="+ licenseAddress
    			+ ", licenseStartDate="+ licenseStartDate
    			+ ", licenseEndDate="+ licenseEndDate
    			+ ", licenseDir="+ licenseDir
    			+ ", bankAccount="+ bankAccount
    			+ ", bankUserName"+ bankUserName
    			+ ", bank="+ bank
                + ", token="+ token
    			+ ", available="+ available
    			+ "]";
    }
}
