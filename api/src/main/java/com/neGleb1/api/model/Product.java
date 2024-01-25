package com.neGleb1.api.model;

public class Product {

    private long productId;

    private long merchantId;

    private String productName;

    private Sex sex;

    private Integer price;

    private Boolean isAvailable;

    private Category category;

    private String timestamp;

    public Product(){
    }

    public Product(long productId, long merchantId, String productName, Sex sex, Integer price, Boolean isAvailable,
            Category category, String timestamp) {
        this.productId = productId;
        this.merchantId = merchantId;
        this.productName = productName;
        this.sex = sex;
        this.price = price;
        this.isAvailable = isAvailable;
        this.category = category;
        this.timestamp = timestamp;
    }

    public String getProductName() {
        return productName;
    }

    public Sex getSex() {
        return sex;
    }

    public Integer getPrice() {
        return price;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getProductId() {
        return productId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", merchantId=" + merchantId + ", productName=" + productName
                + ", sex=" + sex + ", price=" + price + ", isAvailable=" + isAvailable + ", category=" + category
                + ", timestamp=" + timestamp + "]";
    }
}
