package com.epochge.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 */
public class CommentInfo {
    private Integer commentId;           // 评论id
    private String content;              // 评论内容
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;            // 评论日期
    private Integer articleId; // 文章编号   这里考虑到实际情况下：因为blog本身可能很大，在前后端传输数据时会影响效率，所以comment没必要再包含文章id
    private Integer userId; // 评论用户编号
    private Integer parentId; // 父评论的id没有则为0
    private List<CommentInfo> child = new ArrayList<>(); // 本评论下的子评论

    // 多表查询到的字段
    private Integer userType; // 用户类型  1：管理员 2：普通用户 默认普通用户
    private String userName; // 用户名
    private String userPass; // 密码
    private String userEmail; // 邮箱
    private Date userRegdate; // 注册时间
    private String userSignature; // 个性签名
    private String userIcon; // 个人头像

    private String articleTitle; // 文章标题

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<CommentInfo> getChild() {
        return child;
    }

    public void setChild(List<CommentInfo> child) {
        this.child = child;
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", parentId=" + parentId +
                ", child=" + child +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegdate=" + userRegdate +
                ", userSignature='" + userSignature + '\'' +
                ", userIcon='" + userIcon + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                '}';
    }
}
