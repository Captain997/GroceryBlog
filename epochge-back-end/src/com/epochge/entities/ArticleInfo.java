package com.epochge.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
public class ArticleInfo {
    /**
     * 文章自身属性
     **/
    private Integer articleId; // 文章编号
    private Integer userId; // 编写用户编号
    private String userName; // 编写用户名称
    private String articleTitle; // 文章标题
    private Integer articleClassifyId; // 文章分类id
    private String articleClassifyName; // 文章分类名称
    private String articleDase; // 文章描述
    private String articleImgLitimg; // 文章缩略图
    private String articleContent; // 文章内容
    // @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishTime; // 发表时间
    private Integer articlePass; // 审核状态
    private Integer articleState; // 文章状态 所有人 仅自己
    private Integer commentState;// 评论状态 开启 关闭
    private Integer click; // 阅读次数
    private Integer review; // 评论次数

    /**
     * 实体关系
     **/
    private List<CommentInfo> comments = new ArrayList<>();    // 博客评论（一对多）

    // 构造器 ...
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleClassifyId() {
        return articleClassifyId;
    }

    public void setArticleClassifyId(Integer articleClassifyId) {
        this.articleClassifyId = articleClassifyId;
    }

    public String getArticleClassifyName() {
        return articleClassifyName;
    }

    public void setArticleClassifyName(String articleClassifyName) {
        this.articleClassifyName = articleClassifyName;
    }

    public String getArticleDase() {
        return articleDase;
    }

    public void setArticleDase(String articleDase) {
        this.articleDase = articleDase;
    }

    public String getArticleImgLitimg() {
        return articleImgLitimg;
    }

    public void setArticleImgLitimg(String articleImgLitimg) {
        this.articleImgLitimg = articleImgLitimg;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getArticlePass() {
        return articlePass;
    }

    public void setArticlePass(Integer articlePass) {
        this.articlePass = articlePass;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public List<CommentInfo> getComments() {
        return comments;
    }

    public void setComments(List<CommentInfo> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleClassifyId=" + articleClassifyId +
                ", articleClassifyName='" + articleClassifyName + '\'' +
                ", articleDase='" + articleDase + '\'' +
                ", articleImgLitimg='" + articleImgLitimg + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", publishTime=" + publishTime +
                ", articlePass=" + articlePass +
                ", articleState=" + articleState +
                ", commentState=" + commentState +
                ", click=" + click +
                ", review=" + review +
                ", comments=" + comments +
                '}';
    }
}
