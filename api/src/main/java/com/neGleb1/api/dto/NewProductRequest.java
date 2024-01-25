package com.neGleb1.api.dto;

import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Sex;

public class NewProductRequest {

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

    public NewProductRequest() {
    }

    public NewProductRequest(long merchantId, String productName, Sex sex, Integer price, Category category,
            String color, String size, String brand, String material, String description) {
        this.merchantId = merchantId;
        this.productName = productName;
        this.sex = sex;
        this.price = price;
        this.category = category;
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.material = material;
        this.description = description;
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

    @Override
    public String toString() {
        return "NewProductRequest [merchantId=" + merchantId + ", productName=" + productName + ", sex=" + sex
                + ", price=" + price + ", category=" + category + ", color=" + color + ", size=" + size + ", brand="
                + brand + ", material=" + material + ", description=" + description + "]";
    }
    
}
