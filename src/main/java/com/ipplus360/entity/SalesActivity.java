package com.ipplus360.entity;

import java.util.Date;

/**
 * 促销活动
 * Created by 辉太郎 on 2017/10/11.
 */
public class SalesActivity {
    private Integer id;
    private Long userId;
    private Date createDate;
    private String name;
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SalesActivity{" +
                "id=" + id +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", name='" + name + '\'' +
                '}';
    }
}
