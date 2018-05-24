package com.ipplus360.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品属性
 * 场景 区县库 规格
 * Created by 辉太郎 on 2017/9/25.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductAttr {

    private Integer id;
    /* 属性名称 */
    private String attrKey;
    /* 属性值 */
    private String attrValue;
    private String attrType;
    private String attr;
    private Long productId;
    private Integer parentId;
    /* 起始时间 */
    @JsonIgnore
    private LocalDateTime startTime;
    private Integer status;
    private BigDecimal price;
    private ProductAttr parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductAttr getParent() {
        return parent;
    }

    public void setParent(ProductAttr parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ProductAttr{" +
                "id=" + id +
                ", attrKey='" + attrKey + '\'' +
                ", attrValue='" + attrValue + '\'' +
                ", attrType='" + attrType + '\'' +
                ", attr='" + attr + '\'' +
                ", productId=" + productId +
                ", parentId=" + parentId +
                ", startTime=" + startTime +
                ", status=" + status +
                ", price=" + price +
                ", parent=" + parent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductAttr attr = (ProductAttr) o;

        if (id != null ? !id.equals(attr.id) : attr.id != null) return false;
        return attrValue != null ? attrValue.equals(attr.attrValue) : attr.attrValue == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
        return result;
    }
}
