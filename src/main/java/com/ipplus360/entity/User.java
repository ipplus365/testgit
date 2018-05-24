package com.ipplus360.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎 on 2017/2/20.
 */
@Alias("user")
public class  User {

    public static final String USERNAME_PATTERN = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{1,19}$";
    public static final String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    public static final String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
    public static final int USERNAME_MIN_LENGTH = 5;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;

    private Long id;

    @Pattern(regexp = USERNAME_PATTERN, message = "用户名不符合规范")
    private String username;

    private String realname;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = EMAIL_PATTERN, message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = MOBILE_PHONE_NUMBER_PATTERN, message = "手机号码格式不正确")
    private String mobile;

    private String password;
    private String passwordSalt;
    private Integer checkCode;
    private Integer checkStatus;
    private Date checkCodeUpdated;
    /*所属公司*/
    private Long organizationId;
    /*帐号创建时间*/
    private Date dateCreated;
    /*帐号修改时间*/
    private Date dateUpdated;
    /*上次登录时间*/
    private Date lastLogin;
    /*上次登录IP*/
    private String lastLoginFrom;
    /*注册IP*/
    private String regFrom;
    /*登录次数*/
    private Long loginCount;
    /*帐号注册方式,前端注册，后台注册*/
    private Integer source;
    /*帐号可用产品*/
    private List<Long> productIds;
    /*测试包购买次数*/
    private Integer testCount;
    /*测试包时间限制，一年必须使用完毕*/
    private Date testDateStarted;
    /*帐号状态*/
    private Integer status;
    /*备注*/
    private String description;
    // 账号激活码
    private String token; 
	// --激活码有效期
    private Date tokenExptime;
    // --激活状态 ，0-未激活，1-已激活
    private Integer tokenStatus;
    //是否为企业注册，false-个人注册，true-企业注册

    private Integer isOrg;

    /* 是否参加过免费区县API活动 */
    private boolean hasFreeDistrictApi;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    public String getRegFrom() {
        return regFrom;
    }

    public void setRegFrom(String regFrom) {
        this.regFrom = regFrom;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    public Date getTestDateStarted() {
        return testDateStarted;
    }

    public void setTestDateStarted(Date testDateStarted) {
        this.testDateStarted = testDateStarted;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public Date getTokenExptime() {
        return tokenExptime;
    }

    public void setTokenExptime(Date tokenExptime) {
        this.tokenExptime = tokenExptime;
    }

    public Integer getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(Integer tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

	public Integer getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(Integer checkCode) {
		this.checkCode = checkCode;
	}

	public Date getCheckCodeUpdated() {
		return checkCodeUpdated;
	}

	public void setCheckCodeUpdated(Date checkCodeUpdated) {
		this.checkCodeUpdated = checkCodeUpdated;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	

	public Integer getIsOrg() {
		return isOrg;
	}

	public void setIsOrg(Integer isOrg) {
		this.isOrg = isOrg;
	}

    public boolean isHasFreeDistrictApi() {
        return hasFreeDistrictApi;
    }

    public void setHasFreeDistrictApi(boolean hasFreeDistrictApi) {
        this.hasFreeDistrictApi = hasFreeDistrictApi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (!email.equals(user.email)) return false;
        if (!dateCreated.equals(user.dateCreated)) return false;
        return status.equals(user.status);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", checkCode=" + checkCode +
                ", checkStatus=" + checkStatus +
                ", checkCodeUpdated=" + checkCodeUpdated +
                ", organizationId=" + organizationId +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", lastLogin=" + lastLogin +
                ", lastLoginFrom='" + lastLoginFrom + '\'' +
                ", regFrom='" + regFrom + '\'' +
                ", loginCount=" + loginCount +
                ", source=" + source +
                ", productIds=" + productIds +
                ", testCount=" + testCount +
                ", testDateStarted=" + testDateStarted +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", token='" + token + '\'' +
                ", tokenExptime=" + tokenExptime +
                ", tokenStatus=" + tokenStatus +
                ", isOrg=" + isOrg +
                '}';
    }
}
