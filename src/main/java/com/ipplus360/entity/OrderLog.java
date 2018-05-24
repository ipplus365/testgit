package com.ipplus360.entity;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Created by 辉太郎 on 2017/4/26.
 */
@Alias("orderLog")
public class OrderLog {

    private Long id;
    private Date operateDate;
    private String operator;
    private String type;
    private String orderSerial;

    public OrderLog(){}

    /**
     * 订单操作记录
     * @param operateDate 操作时间
     * @param operator 操作人员：用户（前台），系统（前台），管理员（后台）
     * @param type 操作类型：创建，修改，确认，付款，发货，完成，取消，其他
     * @param orderSerial 订单序列号
     */
    public OrderLog(Date operateDate, String operator, String type, String orderSerial){
        this.operateDate = operateDate;
        this.operator = operator;
        this.type = type;
        this.orderSerial = orderSerial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(String orderSerial) {
        this.orderSerial = orderSerial;
    }

    public String toString(){
        return "OrderLog:[" +
                "id:" + id +
                ", operateDate:" + operateDate +
                ", operator:" + operator +
                ", type:" + type +
                ", orderSerial:" + orderSerial +
                "]";
    }
}
