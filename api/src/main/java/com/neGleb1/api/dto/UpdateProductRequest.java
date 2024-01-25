package com.neGleb1.api.dto;

import java.util.Arrays;

import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Sex;

public class UpdateProductRequest {

    private long productId;

    private long merchantId;

    private String productName;

    private Sex sex;

    private Integer price;

    private Category category;

    private String color;

    private String size;

    private String brand;

    private String material;

    private String description;

    private String oldThumbnail;

    private String[] oldImages;

    public UpdateProductRequest() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOldThumbnail() {
        return oldThumbnail;
    }

    public void setOldThumbnail(String oldThumbnail) {
        this.oldThumbnail = oldThumbnail;
    }

    public String[] getOldImages() {
        return oldImages;
    }

    public void setOldImages(String[] oldImages) {
        this.oldImages = oldImages;
    }

    @Override
    public String toString() {
        return "UpdateProductRequest [productId=" + productId + ", merchantId=" + merchantId + ", productName="
                + productName + ", sex=" + sex + ", price=" + price + ", category=" + category + ", color=" + color
                + ", size=" + size + ", brand=" + brand + ", material=" + material + ", description=" + description
                + ", oldThumbnail=" + oldThumbnail + ", oldImages=" + Arrays.toString(oldImages) + "]";
    }
    
}
