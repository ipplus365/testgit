package com.ipplus360.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by 辉太郎 on 2017/10/14.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileOrder {
    private Long id;
    private String attrs;
    private Date createTime;
    private Date expireTime;
    private Long userId;
    private Long orderId;
    private Integer downloadCounts;
    private String file;
    private boolean free;
    private String attrIds;
    private String version;
    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getDownloadCounts() {
        return downloadCounts;
    }

    public void setDownloadCounts(Integer downloadCounts) {
        this.downloadCounts = downloadCounts;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getAttrIds() {
        return attrIds;
    }

    public void setAttrIds(String attrIds) {
        this.attrIds = attrIds;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "FileOrder{" +
                "id=" + id +
                ", attrs='" + attrs + '\'' +
                ", createTime=" + createTime +
                ", expireTime=" + expireTime +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", downloadCounts=" + downloadCounts +
                ", file='" + file + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
