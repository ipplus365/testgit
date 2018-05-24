package com.ipplus360.domain;

import java.util.Date;

/**
 * sso客户端SessionUser
 * Created by 辉太郎 on 2017/9/2.
 */
public class SessionUser {

    private Long id;
    private String email;
    private String username;
    private String ssoToken;
    private String mobile;
    private Date dateCreated;
    private Date lastLogin;
    private String lastLoginFrom;
    private Integer status;
    /*测试包购买次数*/
    private Integer testCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", ssoToken='" + ssoToken + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastLogin=" + lastLogin +
                ", lastLoginFrom='" + lastLoginFrom + '\'' +
                ", status=" + status +
                '}';
    }
}
