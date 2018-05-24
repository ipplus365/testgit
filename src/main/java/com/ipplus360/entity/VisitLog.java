package com.ipplus360.entity;

import java.util.Date;

/**
 * 用户访问日志--首页
 * Created by 辉太郎 on 2017/7/4.
 */
public class VisitLog {

    private Long id;
    /*访问者IP*/
    private String ip;
    /*访问者头信息*/
    private String userAgent;
    /*访问者来源*/
    private String source;
    /*访问时间*/
    private Date visitTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", source='" + source + '\'' +
                ", visitTime=" + visitTime +
                '}';
    }
}
