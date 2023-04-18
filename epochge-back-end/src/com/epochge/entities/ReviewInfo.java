package com.epochge.entities;

import java.util.Date;

/**
 * 评论管理表
 * @author Bpvank
 */
public class ReviewInfo {
    private Integer reviewId; // 评论编号
    private Integer articleId; // 文章代号
    private Integer userId; // 评论用户编号
    private String reviewContent; // 评论内容
    private Date reviewDate; // 评论日期

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "ReviewInfo{" +
                "reviewId=" + reviewId +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
