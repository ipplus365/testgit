package com.ipplus360.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎 on 2017/5/9.
 */
@Alias("delivery")
public class Delivery {
    private Long id;
    private String token;
    private String orderSerial;
    private Long counts;
    private String operator;
    private Date deliveryDate;
    private Long userId;
    private User users;

    public Delivery() {
    }

    public Delivery(Long userId,String orderSerial, String operator) {
        this.userId = userId;
        this.orderSerial = orderSerial;
        this.operator = operator;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String toString(){
        return "Delivery:{" +
                "id:"+id+
                ", token:"+token+
                ", orderSerial:"+orderSerial+
                ", counts:" +counts+
                ", operator:" +operator+
                ", deliveryDate:" +deliveryDate+
                ", userId:" +userId+
                "}";
    }

}
