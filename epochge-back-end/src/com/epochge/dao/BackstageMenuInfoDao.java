package com.epochge.dao;

import com.epochge.entities.BackstageMenuInfo;

import java.util.List;

/**
 * 功能说明
 *
 * @author Bpvank
 * @date 2022-07-20 10:21:12
 */
public interface BackstageMenuInfoDao {
    // 获取后台菜单全部信息
    List<BackstageMenuInfo> getAllBackstageMenuInfo();

    // 通过用户权限获取后台菜单全部信息
    List<BackstageMenuInfo> getBackstageMenuInfo(Integer userType);
}
