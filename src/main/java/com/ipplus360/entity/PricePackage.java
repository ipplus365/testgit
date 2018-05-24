package com.ipplus360.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 价格套餐包
 * Created by 辉太郎 on 2017/2/22.
 */
@Alias("pricepackage")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricePackage {
    private Long id;
    /*套餐所属产品*/
    private Long productId;
    /*套餐类型*/
    private int type;
    /*套餐所含查询次数*/
    private Long amount;
    /*套餐所含查询次数大写*/
    private String amountStr;
    /*套餐费用*/
    private String price;
    /*套餐折扣*/
    private String discount;
    /*有效期开始时间*/
    private Date startDate;
    /*有效期截止时间*/
    private Date endDate;
    private String description;
    /*价格包总价*/
    private String totalPrice;
    /*是否可用*/
    private boolean available;


//    private Product product;
//    /*商品名称*/
//    private String productName;
//
//    /*商品图标路径*/
//    private String iconUrl;

    /*套餐类型*/
    private Accuracy accuracy;

    /*价格单位*/
    private String unit;
    /*购买次数限制*/
    private Integer counts;
    //账号提示
    private String tips;

    public PricePackage(){}

    public PricePackage(Long id){
        this.id = id;
    }

    public PricePackage(Long id, Long productId, Integer accuracyId){
        this.id = id;
        this.productId = productId;
        this.accuracy = new Accuracy(accuracyId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String  getPrice() {
        return price;
    }

    public void setPrice(String  price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//	public String getProductName() {
//		return productName;
//	}
//
//	public String getIconUrl() {
//		return iconUrl;
//	}
//
//	public void setIconUrl(String iconUrl) {
//		this.iconUrl = iconUrl;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }



//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString(){
        return  "PricePackage [id="+ id +
                ", productId="+ productId +
                ", type="+ type +
                ", amount="+ amount +
                ", amountStr="+ amountStr +
                ", accuracy="+ accuracy +
                ", price="+ price +
                ", totalPrice="+ totalPrice +
                ", unit="+ unit +
                ", discount="+ discount +
                ", startDate="+ startDate +
                ", endDate="+ endDate +
                ", description="+ description +
                ", counts="+ counts +
                ", tips=" + tips +
                ", available="+ available +
                "]";
    }

}