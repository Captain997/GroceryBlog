package com.epochge.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用户信息管理表
 * @author Bpvank
 */
public class UserInfo {
    private Integer userId; // 用户编号
    private Integer userType; // 用户类型  1：管理员 2：普通用户 默认普通用户
    private String userName; // 用户名
    private String userPass; // 密码
    private String userEmail; // 邮箱
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userRegdate; // 注册时间
    private String userSignature; // 个性签名
    private String userIcon; // 个人头像

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(Date userRegdate) {
        this.userRegdate = userRegdate;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPass + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegdate=" + userRegdate +
                ", userSignature='" + userSignature + '\'' +
                ", userIcon='" + userIcon + '\'' +
                '}';
    }
}
