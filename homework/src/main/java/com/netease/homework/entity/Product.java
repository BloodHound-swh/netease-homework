package com.netease.homework.entity;

import java.util.Date;

public class Product {
    // 商品ID
    private Integer productId;

    // 商品名称
    private String productName;

    // 商品介绍
    private String productDesc;

    // 商品图片地址
    private String imgAddr;

    // 商品价格
    private String productPrice;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 商品状态：0-下架，1-上架
    private Integer enableStatus;

    // 购买状态：0-未售，1-已售
    private Integer sellStatus;

    // 卖家ID
    private Integer sellerId;

    private Integer buyerId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyllerId) {
        this.buyerId = buyllerId;
    }
}
