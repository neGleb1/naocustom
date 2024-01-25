package com.neGleb1.api.dto;

import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Sex;

public class ProductItemResponse {

    private long productId;

    private long merchantId;

    private String productName;

    private String merchantName;

    private Sex sex;

    private Integer price;

    private Category category;

    private String color;

    private String size;

    private String brand;

    private String material;

    private String timestamp;

    private String thumbnailPath;

    public ProductItemResponse() {
    }

    public ProductItemResponse(long merchantId, long productId, String productName, String merchantName, Sex sex,
            Integer price, Category category, String color, String size, String brand, String material,
            String timestamp, String thumbnailPath) {
        this.merchantId = merchantId;
        this.productId = productId;
        this.productName = productName;
        this.merchantName = merchantName;
        this.sex = sex;
        this.price = price;
        this.category = category;
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.material = material;
        this.timestamp = timestamp;
        this.thumbnailPath = thumbnailPath;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @Override
    public String toString() {
        return "ProductDto [merchantId=" + merchantId + ", productId=" + productId + ", productName=" + productName
                + ", merchantName=" + merchantName + ", sex=" + sex + ", price=" + price + ", category=" + category
                + ", color=" + color + ", size=" + size + ", brand=" + brand + ", material=" + material + ", timestamp="
                + timestamp + ", thumbnailPath=" + thumbnailPath + "]";
    }
}
