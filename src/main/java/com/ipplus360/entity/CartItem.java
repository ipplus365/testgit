package com.ipplus360.entity;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车项</br>
 * Created by 辉太郎 on 2017/2/23.
 */
@Alias("cartItem")
public class CartItem {
	/* 购物项ID */
    private Long id;
    /* 购物项数量 */
    private Integer itemNum;
    /* 购物车ID*/
    private Long cartId;
    /* 产品信息 */
    private Product product;
    /* 价格包*/
    private PricePackage pricePackage;
    /* 该购物项折扣之和价格总和*/
    private BigDecimal price;
    /* 购物项折扣信息*/
    private String discount;
    /* 该购物项折扣之前价格总和*/
    private BigDecimal originalPrice;
    /* 区县库下载购物车项 */
    private List<ProductAttr> attrs;
    /**
     * 订单类型
     * 1.IPSceneAPI
     * 2.IPSceneDownload
     * 3.IPDistrictAPI
     * 4.IPDistrictDownload
     */
    private String itemType;
    private String attrIds;
    public CartItem(){
    	
    }
    
    public CartItem(Long cartId,Long productId,Long pricePackageId){
    	this.cartId = cartId;
    	this.product = new Product(productId);
    	this.pricePackage = new PricePackage(pricePackageId);
    	
    }
    
    public CartItem(Long cartId,Long productId,Long pricePackageId,Integer itemNum,String itemType){
    	this.cartId = cartId;
    	this.product = new Product(productId);
    	this.pricePackage = new PricePackage(pricePackageId);
    	this.itemNum = itemNum;
    	this.itemType = itemType;
    }

    public CartItem(Long id, Product product, String attrIds) {
        this.cartId = id;
        this.product = product;
        this.attrIds = attrIds;
    }

    public CartItem(Long id,Long productId,Long pricePackageId, PricePackage pricePackage, Integer itemNum) {
    	this.cartId = id;
    	this.product = new Product(productId);
    	this.pricePackage = new PricePackage(pricePackageId);
    	this.pricePackage = pricePackage;
    	this.itemNum = itemNum;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PricePackage getPricePackage() {
        return pricePackage;
    }

    public void setPricePackage(PricePackage pricePackage) {
        this.pricePackage = pricePackage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

    public List<ProductAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<ProductAttr> attrs) {
        this.attrs = attrs;
    }

    
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAttrIds() {
        return attrIds;
    }

    public void setAttrIds(String attrIds) {
        this.attrIds = attrIds;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", itemNum=" + itemNum +
                ", cartId=" + cartId +
                ", product=" + product +
                ", pricePackage=" + pricePackage +
                ", price=" + price +
                ", discount='" + discount + '\'' +
                ", originalPrice=" + originalPrice +
                ", itemType=" + itemType +
                ", attrs=" + attrs +
                '}';
    }
}