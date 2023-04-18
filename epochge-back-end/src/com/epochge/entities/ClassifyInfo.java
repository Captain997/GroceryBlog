package com.epochge.entities;

/**
 * 文章分类表
 * @author Bpvank
 */
public class ClassifyInfo {
    private Integer classifyId; // 分类编号
    private String classifyName; // 分类名称
    private Integer articleNumber; // 文章数量
    private String color1;// 渐变色1,
    private String color2;// 渐变色2
    private String classifyIntroduce; // 分类介绍

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }


    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getClassifyIntroduce() {
        return classifyIntroduce;
    }

    public void setClassifyIntroduce(String classifyIntroduce) {
        this.classifyIntroduce = classifyIntroduce;
    }

    @Override
    public String toString() {
        return "ClassifyInfo{" +
                "classifyId=" + classifyId +
                ", classifyName='" + classifyName + '\'' +
                ", articleNumber=" + articleNumber +
                ", color1='" + color1 + '\'' +
                ", color2='" + color2 + '\'' +
                ", classifyIntroduce='" + classifyIntroduce + '\'' +
                '}';
    }
}
