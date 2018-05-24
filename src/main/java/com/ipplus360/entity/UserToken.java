package com.ipplus360.entity;

import java.util.Date;

/**
 * Created by 辉太郎 on 2017/3/13.
 */
public class UserToken {
    private long id;
    private String token;
    private long userId;
    /*token生成时间*/
    private Date createdDate;
    /*token更新时间*/
    private Date updateDate;
    /*token生效时间*/
    private Date effectiveDate;
    /*token过期时间*/
    private Date expireDate;
    private Long productId;
    /*token适用产品*/
    private Product product;
    /*token状态*/
    private boolean available;
    /*每个测试包有一个单独的token,每个token有效期一年*/
    private boolean isTest;
    /*是否发送过邮件提醒*/
    private boolean notified;
    /*测试包次数,100000*/
    private Long testCount;
    /*非测试包剩余次数*/
    private Long counts;
    /*场景包次数*/
    private Long sceneCounts;
    private String description;
    /*限制频率 30000次/日 800000次/日... */
    private Integer dailyLimit;
    private Boolean canWebLocate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /* public List<Long> getProductIds() {
        if (productIds == null) {
            productIds = new ArrayList<>();
        }
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public String getProductIdsStr() {
        if (CollectionUtils.isEmpty(productIds)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Long productId : productIds) {
            sb.append(productId);
            sb.append(",");
        }
        return sb.toString();
    }

    public void setProductIdsStr(String productIdsStr) {
        if (StringUtils.isEmpty(productIdsStr)) {
            return;
        }
        String[] productIdsStrs = productIdsStr.split(",");
        for (String productIdStr : productIdsStrs) {
            if (StringUtils.isEmpty(productIdStr)) {
                continue;
            }
            getProductIds().add(Long.valueOf(productIdStr));
        }
    }*/

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public Long getTestCount() {
        return testCount;
    }

    public void setTestCount(Long testCount) {
        this.testCount = testCount;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Integer getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Integer dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Boolean getCanWebLocate() {
        return canWebLocate;
    }

    public void setCanWebLocate(Boolean canWebLocate) {
        this.canWebLocate = canWebLocate;
    }

    public Long getSceneCounts() {
        return sceneCounts;
    }

    public void setSceneCounts(Long sceneCounts) {
        this.sceneCounts = sceneCounts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 正是包次数是否可用
     * @return boolean
     */
    public boolean countValid() {
        return !this.isTest && (null != counts && counts > 0);
    }

    /**
     * 测试包次数是否可用
     * @return boolean
     */
    public boolean testCountValid() {
        return this.isTest && (null != testCount && testCount > 0);
    }

    /**
     * 场景次数是否可用
     * @return boolean
     */
    public boolean sceneCountValid() {
        return hasScene() && (null != sceneCounts && sceneCounts > 0);
    }

    /**
     * token有效期判断
     * @return
     */
    public boolean notExpired() {
        Date now = new Date();
        return this.getExpireDate() == null || now.after(this.getEffectiveDate()) && this.getExpireDate().after(now);
    }

    /**
     * token是否可以进行web定位跳转
     * @return
     */
    public boolean canWebLocate() {
        return productId == 1 &&
                ( (null != testCount && testCount > 0) || (null != counts && counts> 0));
    }
    /**
     * 1. token可用
     * 2. token在有效期内
     * 3. token有可用次数
     * @return boolean
     */
    public boolean valid() {
        return this.isAvailable() && this.notExpired() && (countValid() || testCountValid());
    }

    /**
     * 是否是区县定位token
     * @return boolean
     */
    public boolean hasDistrictLocate() {
        return productId == 5L;
    }

    /**
     * 是否是街道定位token
     * @return boolean
     */
    public boolean hasStreetLocate() {
        return productId == 1L;
    }

    /**
     * 是否是场景token
     * @return boolean
     */
    public boolean hasScene() {
        return productId == 3L;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserToken userToken = (UserToken) o;

        return id == userToken.id;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                ", createdDate=" + createdDate +
                ", updateDate=" + updateDate +
                ", effectiveDate=" + effectiveDate +
                ", expireDate=" + expireDate +
                ", productId=" + productId +
                ", available=" + available +
                ", isTest=" + isTest +
                ", notified=" + notified +
                ", testCount=" + testCount +
                ", counts=" + counts +
                ", sceneCounts=" + sceneCounts +
                ", description='" + description + '\'' +
                ", dailyLimit=" + dailyLimit +
                '}';
    }
}
