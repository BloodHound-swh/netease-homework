package com.netease.homework.entity;

import java.util.Date;

public class User {
    // 用户ID
    private Integer userId;

    // 用户名
    private String userName;

    // 用户密码
    private String passWord;

    // 用户身份：1-顾客，2-卖家，3-管理员
    private Integer userMode;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserMode() {
        return userMode;
    }

    public void setUserMode(Integer isSeller) {
        this.userMode = isSeller;
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
}
