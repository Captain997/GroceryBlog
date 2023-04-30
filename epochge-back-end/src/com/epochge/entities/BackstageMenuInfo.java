package com.epochge.entities;

import java.util.List;

/**
 * '后台菜单表'
 */
public class BackstageMenuInfo {
    private Integer id; // 菜单id
    private String name; // 菜单名称
    private String chineseName; //菜单中文名称
    private String title; // 菜单标题
    private String path; // 菜单路径
    private String icon; // 菜单图标
    private Integer parentMenuId; // 父菜单id
    private Integer menuStatus; // 菜单是否可见
    private List<BackstageMenuInfo> childMenu;  // 子菜单


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public List<BackstageMenuInfo> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<BackstageMenuInfo> childMenu) {
        this.childMenu = childMenu;
    }

    @Override
    public String toString() {
        return "BackstageMenuInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", parentMenuId=" + parentMenuId +
                ", menuStatus=" + menuStatus +
                ", childMenu=" + childMenu +
                '}';
    }
}
