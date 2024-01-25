package com.neGleb1.api.model;

public class Image {

    private long imageId;

    private long productId;

    private Boolean isThumbnail;

    private String path;

    private String timestamp;

    public Image() {
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public Boolean getIsThumbnail() {
        return isThumbnail;
    }

    public void setIsThumbnail(Boolean isThumbnail) {
        this.isThumbnail = isThumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", productId=" + productId + ", isThumbnail=" + isThumbnail + ", path="
                + path + ", timestamp=" + timestamp + "]";
    }
}
