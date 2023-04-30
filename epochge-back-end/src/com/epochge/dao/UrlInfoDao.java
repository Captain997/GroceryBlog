package com.epochge.dao;

import com.epochge.entities.UrlInfo;

import java.util.List;

/**
 * 添加链接
 */
public interface UrlInfoDao {
    // 添加链接
    int insertUrl(UrlInfo urlInfo);

    // 分页查询  + 搜索框内容
    int findTotalCount(String searchContent);

    List<UrlInfo> findByPage(int start, int rows, String searchContent);

    // 分页查询  + 审核筛选下拉框内容
    int findTotalCount2(String auditContent);

    List<UrlInfo> findByPage2(int start, int rows, String auditContent);

    // 根据链接编号修改审核状态
    int updateUrl(Integer urlId, Integer urlPass);

    // 根据链接编号进行删除
    int deleteUrl(Integer urlId);

    // 根据链接名称查找信息
    List<UrlInfo> getUrlInfoByUrlName(String urlName);

    // 根据链接类型查找审核通过的链接信息
    List<UrlInfo> getUrlInfoByUrlTyep(Integer urlType);
}
