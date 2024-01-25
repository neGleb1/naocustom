package com.neGleb1.api.model;

public class ProductAttributes {

    private long attributesId;

    private long productId;

    private String description;

    private String color;

    private String size;

    private String brand;

    private String material;

    public ProductAttributes(){
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getAttributesId() {
        return attributesId;
    }

    public void setAttributesId(long attributesId) {
        this.attributesId = attributesId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductAttributes [attributesId=" + attributesId + ", productId=" + productId + ", description="
                + description + ", color=" + color + ", size=" + size + ", brand=" + brand + ", material=" + material
                + "]";
    }
}
