package com.epochge.service;

import com.epochge.dao.CommentInfoDao;
import com.epochge.dao.impl.CommentInfoDaoImpl;
import com.epochge.entities.*;

import java.util.List;

/**
 * 功能说明
 *
 */
public class CommentInfoService {
    CommentInfoDao dao = new CommentInfoDaoImpl();

    // 查询所有评论
    public List<CommentInfo> getAllComment() {
        return dao.getAllComment();
    }

    // 根据文章id查询文章对应的评论
    public List<CommentInfo> getCommentByArticleId(Integer articleId) {
        if (articleId == null) {
            return null;
        }
        return dao.getCommentByArticleId(articleId);
    }

    // 添加评论
    public Integer insertComment(CommentInfo commentInfo) {
        if (commentInfo == null) {
            return -1;
        }
        return dao.insertComment(commentInfo);
    }

    // 根据评论id 删除评论
    public int deleteComment(Integer commentId) {
        if (commentId != null) {
            return dao.deleteComment(commentId);
        }
        return -1;
    }

    // 根据文章id 删除评论
    public int deleteCommentByArticleId(Integer articleId) {
        if (articleId != null) {
            return dao.deleteCommentByArticleId(articleId);
        }
        return -1;
    }

    // 根据用户id 删除评论
    public int deleteCommentByUserId(Integer userId) {
        if (userId != null) {
            return dao.deleteCommentByUserId(userId);
        }
        return -1;
    }

    // 查询评论总数
    public int getCommentCount() {
        return dao.getCommentCount();
    }

    // 后台评论管理：分页查询  + 搜索框内容  管理员获取全部  普通用户获取自己的
    public PageBean<CommentInfo> findStudentByPage(int _currentPage, int _pageSize, Integer userId, String searchContent) {
        CommentInfoDao dao = new CommentInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<CommentInfo> pb = new PageBean<CommentInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount(userId,searchContent);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<CommentInfo> commentInfoList = dao.findByPage(start, rows, userId,searchContent);
        pb.setList(commentInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
