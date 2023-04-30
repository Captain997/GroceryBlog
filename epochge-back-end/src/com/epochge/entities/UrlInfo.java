package com.epochge.entities;

/**
 * 链接管理表
 */
public class UrlInfo {
    private Integer urlId; // 链接编号
    private String urlName; // 网站名称
    private String urlAddres; // 网站地址
    private String urlReferral; // 网站介绍
    private String urlLitimg;// 网站缩略图
    private String webmasterEmail; // 站长邮箱
    private Integer urlPass; // 后台审核是否通过 1待审核 2审核通过 3审核失败
    private Integer urlType; // 链接类型  1：学习网站 2：友情链接

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlAddres() {
        return urlAddres;
    }

    public void setUrlAddres(String urlAddres) {
        this.urlAddres = urlAddres;
    }

    public String getUrlReferral() {
        return urlReferral;
    }

    public void setUrlReferral(String urlReferral) {
        this.urlReferral = urlReferral;
    }

    public String getUrlLitimg() {
        return urlLitimg;
    }

    public void setUrlLitimg(String urlLitimg) {
        this.urlLitimg = urlLitimg;
    }

    public String getWebmasterEmail() {
        return webmasterEmail;
    }

    public void setWebmasterEmail(String webmasterEmail) {
        this.webmasterEmail = webmasterEmail;
    }

    public Integer getUrlPass() {
        return urlPass;
    }

    public void setUrlPass(Integer urlPass) {
        this.urlPass = urlPass;
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    @Override
    public String toString() {
        return "UrlInfo{" +
                "urlId=" + urlId +
                ", urlName='" + urlName + '\'' +
                ", urlAddres='" + urlAddres + '\'' +
                ", urlReferral='" + urlReferral + '\'' +
                ", urlLitimg='" + urlLitimg + '\'' +
                ", webmasterEmail='" + webmasterEmail + '\'' +
                ", urlPass=" + urlPass +
                ", urlType=" + urlType +
                '}';
    }
}
