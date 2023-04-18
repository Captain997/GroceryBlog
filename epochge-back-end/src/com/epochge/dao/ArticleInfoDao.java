package com.epochge.dao;

import com.epochge.entities.ArticleInfo;

import java.util.List;

/**
 * 文章信息表
 */
public interface ArticleInfoDao {
    // 添加文章
    int insertArticle(ArticleInfo articleInfo);

    // 修改文章
    int updateArticle(ArticleInfo articleInfo);

    // 根据文章id删除文章
    int deleteArticle(Integer articleId);

    // 根据用户id 删除文章
    int deleteArticleByUserId(Integer userId);

    // 查询所有通过审核并且所有人可见的文章
    List<ArticleInfo> getAllArticle();

    // 查询通过审核文章总数
    int getArticleCount();

    // 根据用户id查询该用户有多少篇文章
    int getArticleCountByUserId(Integer userId);

    // 根据文章id查找文章内容
    List<ArticleInfo> getAllArticleById(Integer articleId);

    // 根据文章id修改文章点击量
    int updateArticleClick(Integer articleId);

    // 根据文章id修改文章审核状态
    int updateArticlePass(Integer articleId, Integer articlePass);

    // 后台文章管理：分页查询+通过用户权限获取文章信息   管理员获取全部  普通用户获取自己的
    int findTotalCount(Integer userId);

    List<ArticleInfo> findByPage(int start, int rows, Integer userId);

    // 根据文章标题进行搜索 并且 进行分页  判断是管理员还是普通用户
    int findTotalCount2(String articleTitle, Integer userId);

    List<ArticleInfo> findByPage2(int start, int rows, String articleTitle, Integer userId);

    // 下拉框筛选文章审核状态，查询出相关内容并进行分页
    int findTotalCount3(Integer articlePass, Integer userId);

    List<ArticleInfo> findByPage3(int start, int rows, Integer articlePass, Integer userId);

    // 前台文章展示 通过审核、所有人可见的文章    可根据文章标题、分类、用户、摘要、进行搜索 并且 进行分页
    int findTotalCount4(String keyword);

    List<ArticleInfo> findByPage4(int start, int rows, String keyword);

    // 搜索出来多少条信息
    int getArticleSearchCount(String keyword);

    // 前台分类文章展示 通过审核、所有人可见的文章   进行分页
    int findTotalCount5(Integer classifyId);

    List<ArticleInfo> findByPage5(int start, int rows, Integer classifyId);
}
