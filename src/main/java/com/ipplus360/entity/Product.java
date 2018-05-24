package com.ipplus360.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created by 辉太郎 on 2017/2/21.
 */
@Alias("product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private Long id;
    /*商品唯一标识*/
    private String skuId;
    /*商品名称*/
    private String productName;
    /*商品图标路径*/
    private String iconUrl;
    /*商品大图标路径*/
    private String iconLarge;
    /*商品url路径*/
    private String resourceIds = null;
    /*商品单价*/
    private String price;
    /*商品价格套餐*/
    private List<PricePackage> pricePackage;
    private String pageviews;
    private String evaluate;
    private Integer available = 0;
    private String description;

    public Product(){}

    public Product(Long id){
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconLarge() {
        return iconLarge;
    }

    public void setIconLarge(String iconLarge) {
        this.iconLarge = iconLarge;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<PricePackage> getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(List<PricePackage> pricePackage) {
        this.pricePackage = pricePackage;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString(){
        return  "product [id="+ id +
                ", skuId="+ skuId +
                ", productName="+ productName +
                ", iconUrl="+ iconUrl +
                ", iconLarge="+ iconLarge +
                ", resourceIds="+ resourceIds +
                ", pricePackage="+ pricePackage +
                ", price="+ price +
                ", available="+ available +
                ", description="+ description +
                ", pageviews="+ pageviews +
                ", evaluate="+ evaluate +
                "]";
    }

}