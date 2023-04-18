package com.epochge.dao;

import com.epochge.entities.ClassifyInfo;

import java.util.List;

/**
 * 文章分类表
 */
public interface ClassifyInfoDao {
    // 查询所有分类
    List<ClassifyInfo> getAllClassifyInfo();

    // 根据分类id增加文章数量
    int updateArticleNumber(Integer classifyId);

    // 根据分类id减少文章数量
    int updateArticleNumber2(Integer classifyId);

    // 添加分类
    int insertClassify(ClassifyInfo classifyInfo);

    // 根据文章id修改分类
    int updateClassify(ClassifyInfo classifyInfo);

    // 根据分类id删除分类
    int deleteClassify(Integer classifyId);

    // 根据分类id查询分类信息
    List<ClassifyInfo> getClassifyInfoByClassifyId(Integer classifyId);
}
