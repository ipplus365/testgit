package com.ipplus360.dto;

import com.ipplus360.entity.PricePackage;

/**
 * Created by 辉太郎 on 2017/3/15.
 */
public class PricePackageOutput {

    private Long id;
    private String total;
    private String unit;
    private Long amount;
    private String amountStr;
    private String price;
    private Long productId;
    private String accuracy;
    private Integer accuracyId;
    private String tips;

    public PricePackageOutput() {
    }

    public PricePackageOutput(PricePackage pricePackage) {
        this.id = pricePackage.getId();
        this.total = pricePackage.getTotalPrice();
        this.unit = pricePackage.getUnit();
        this.amount = pricePackage.getAmount();
        this.price = pricePackage.getPrice();
        this.productId = pricePackage.getProductId();
        this.accuracy = pricePackage.getAccuracy().getAccuracy();
        this.tips = pricePackage.getTips();
        this.amountStr = pricePackage.getAmountStr();
        this.accuracyId = pricePackage.getAccuracy().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAmountStr() {
        return amountStr;
    }

    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getAccuracyId() {
        return accuracyId;
    }

    public void setAccuracyId(Integer accuracyId) {
        this.accuracyId = accuracyId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
