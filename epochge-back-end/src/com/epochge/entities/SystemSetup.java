package com.epochge.entities;

/**
 * 系统设置
 *
 * @author Bpvank
 */
public class SystemSetup {
    private String stickArticle;// '置顶',
    private String allArticle;// '文章展示',
    private String featuredArticle;// '左侧精选文章',
    private String technologyArticle;// '左侧技术专区文章',
    private String resourceArticle;// '右侧资源专区文章',
    private String advertising1;// '左侧广告图1',
    private String advertisingLink1;// '左侧广告链接1',
    private String advertising2;// '右侧广告图1',
    private String advertisingLink2;// '左侧广告链接1',
    private String advertising3;// '右侧广告图2',
    private String advertisingLink3;// '左侧广告链接1'

    private Boolean effects01; // 特效1
    private Boolean effects02; // 特效2

    public String getStickArticle() {
        return stickArticle;
    }

    public void setStickArticle(String stickArticle) {
        this.stickArticle = stickArticle;
    }

    public String getAllArticle() {
        return allArticle;
    }

    public void setAllArticle(String allArticle) {
        this.allArticle = allArticle;
    }

    public String getFeaturedArticle() {
        return featuredArticle;
    }

    public void setFeaturedArticle(String featuredArticle) {
        this.featuredArticle = featuredArticle;
    }

    public String getTechnologyArticle() {
        return technologyArticle;
    }

    public void setTechnologyArticle(String technologyArticle) {
        this.technologyArticle = technologyArticle;
    }

    public String getResourceArticle() {
        return resourceArticle;
    }

    public void setResourceArticle(String resourceArticle) {
        this.resourceArticle = resourceArticle;
    }

    public String getAdvertising1() {
        return advertising1;
    }

    public void setAdvertising1(String advertising1) {
        this.advertising1 = advertising1;
    }

    public String getAdvertisingLink1() {
        return advertisingLink1;
    }

    public void setAdvertisingLink1(String advertisingLink1) {
        this.advertisingLink1 = advertisingLink1;
    }

    public String getAdvertising2() {
        return advertising2;
    }

    public void setAdvertising2(String advertising2) {
        this.advertising2 = advertising2;
    }

    public String getAdvertisingLink2() {
        return advertisingLink2;
    }

    public void setAdvertisingLink2(String advertisingLink2) {
        this.advertisingLink2 = advertisingLink2;
    }

    public String getAdvertising3() {
        return advertising3;
    }

    public void setAdvertising3(String advertising3) {
        this.advertising3 = advertising3;
    }

    public String getAdvertisingLink3() {
        return advertisingLink3;
    }

    public void setAdvertisingLink3(String advertisingLink3) {
        this.advertisingLink3 = advertisingLink3;
    }

    public Boolean getEffects01() {
        return effects01;
    }

    public void setEffects01(Boolean effects01) {
        this.effects01 = effects01;
    }

    public Boolean getEffects02() {
        return effects02;
    }

    public void setEffects02(Boolean effects02) {
        this.effects02 = effects02;
    }

    @Override
    public String toString() {
        return "SystemSetup{" +
                "stickArticle='" + stickArticle + '\'' +
                ", allArticle='" + allArticle + '\'' +
                ", featuredArticle='" + featuredArticle + '\'' +
                ", technologyArticle='" + technologyArticle + '\'' +
                ", resourceArticle='" + resourceArticle + '\'' +
                ", advertising1='" + advertising1 + '\'' +
                ", advertisingLink1='" + advertisingLink1 + '\'' +
                ", advertising2='" + advertising2 + '\'' +
                ", advertisingLink2='" + advertisingLink2 + '\'' +
                ", advertising3='" + advertising3 + '\'' +
                ", advertisingLink3='" + advertisingLink3 + '\'' +
                ", effects01=" + effects01 +
                ", effects02=" + effects02 +
                '}';
    }
}
