package com.ipplus360.entity;

import java.util.Date;

/**
 * 用户操作日志
 * Created by 辉太郎 on 2017/3/15.
 */
public class UserLog {
    private Long id;
    private String source;
    private String test;
    private String logId;
    private Long userId;
    private String email;
    private String userIp;
    private String token;
    private String ipId;
    private String locateIp;
    private String productId;
    private String version;
    private Date logDate;
    private Integer cost;
    private String coordsys;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIpId() {
        return ipId;
    }

    public void setIpId(String ipId) {
        this.ipId = ipId;
    }

    public String getLocateIp() {
        return locateIp;
    }

    public void setLocateIp(String locateIp) {
        this.locateIp = locateIp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCoordsys() {
        return coordsys;
    }

    public void setCoordsys(String coordsys) {
        this.coordsys = coordsys;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", test='" + test + '\'' +
                ", logId='" + logId + '\'' +
                ", userId=" + userId +
                ", email='" + email + '\'' +
                ", userIp='" + userIp + '\'' +
                ", token='" + token + '\'' +
                ", ipId='" + ipId + '\'' +
                ", locateIp='" + locateIp + '\'' +
                ", productId='" + productId + '\'' +
                ", version='" + version + '\'' +
                ", logDate=" + logDate +
                ", cost=" + cost +
                ", coordsys='" + coordsys + '\'' +
                '}';
    }
}
