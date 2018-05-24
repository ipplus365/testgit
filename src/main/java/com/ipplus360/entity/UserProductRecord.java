package com.ipplus360.entity;

import java.util.Date;

/**
 * 用户产品使用记录，余量统计
 * Created by 辉太郎 on 2017/2/23.
 */
public class UserProductRecord {
    private Long id;
    private Long userId;
    private Long ProductId;
    /*剩余次数*/
    private Long counts;
    /*创建时间 用户统计*/
    private Date dateCreated;

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

    public Long getProductId() {
        return ProductId;
    }

    public void setProductId(Long productId) {
        ProductId = productId;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
