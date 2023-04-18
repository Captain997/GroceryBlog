package com.epochge.dao;

import com.epochge.entities.CommentInfo;

import java.util.List;

/**
 * 评论
 */
public interface CommentInfoDao {
    // 查询所有评论   多表查询
    List<CommentInfo> getAllComment();

    // 根据文章id查询文章对应的评论
    List<CommentInfo> getCommentByArticleId(Integer articleId);

    // 添加评论
    int insertComment(CommentInfo commentInfo);

    // 根据评论id 删除评论
    int deleteComment(Integer commentId);

    // 根据文章id 删除评论
    int deleteCommentByArticleId(Integer articleId);

    // 根据用户id 删除评论
    int deleteCommentByUserId(Integer userId);

    // 查询评论总数
    int getCommentCount();

    // 后台评论管理：分页查询  + 搜索框内容  管理员获取全部  普通用户获取自己的
    int findTotalCount(Integer userId, String searchContent);

    List<CommentInfo> findByPage(int start, int rows, Integer userId, String searchContent);
}
