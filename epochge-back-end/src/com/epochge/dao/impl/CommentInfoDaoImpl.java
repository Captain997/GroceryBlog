package com.epochge.dao.impl;

import com.epochge.dao.CommentInfoDao;
import com.epochge.entities.CommentInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 */
public class CommentInfoDaoImpl implements CommentInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 查询所有评论
    @Override
    public List<CommentInfo> getAllComment() {
        // 多表查询
        String sql = "select * from commentInfo,userInfo where commentInfo.userId=userInfo.userId";
        List<CommentInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentInfo>(CommentInfo.class));
        return list;
    }

    // 根据文章id查询文章对应的评论
    @Override
    public List<CommentInfo> getCommentByArticleId(Integer articleId) {
        String sql1 = "";
        if (articleId != null) {
            sql1 = "where commentInfo.userId=userInfo.userId && articleId = " + articleId;
        }
        String sql = "select * from commentInfo,userInfo " + sql1;
        List<CommentInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentInfo>(CommentInfo.class));
        return list;
    }

    // 添加评论
    @Override
    public int insertComment(CommentInfo commentInfo) {
        String sql = "insert into commentInfo(articleId,userId,content,parentId)value(?,?,?,?)";
        int i = jdbcTemplate.update(sql, commentInfo.getArticleId(), commentInfo.getUserId(), commentInfo.getContent(), commentInfo.getParentId());
        return i;
    }

    // 根据评论id 删除评论   删除父评论  其他评论跟着删除
    @Override
    public int deleteComment(Integer commentId) {
        String sql = "delete from commentInfo where commentId = ? || parentId = ?";
        int i = jdbcTemplate.update(sql, commentId, commentId);
        return i;
    }

    // 根据文章id 删除评论
    @Override
    public int deleteCommentByArticleId(Integer articleId) {
        String sql = "delete from commentInfo where articleId = ?";
        int i = jdbcTemplate.update(sql, articleId);
        return i;
    }

    // 根据用户id 删除评论
    @Override
    public int deleteCommentByUserId(Integer userId) {
        String sql = "delete from commentInfo where userId = ?";
        int i = jdbcTemplate.update(sql, userId);
        return i;
    }

    // 查询评论总数
    @Override
    public int getCommentCount() {
        String sql = "select count(*) from  commentInfo";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    // 后台评论管理：分页查询  + 搜索框内容  管理员获取全部  普通用户获取自己的
    @Override
    public int findTotalCount(Integer userId, String searchContent) {
        String sql = "select count(1) from commentInfo as c,articleInfo as a where c.articleId=a.articleId && a.userId like concat('%',IFNULL(?,''),'%') && (c.content like concat('%',IFNULL(?,''),'%') || a.articleTitle like concat('%',IFNULL(?,''),'%'))";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, searchContent, searchContent);
        return count;
    }

    @Override
    public List<CommentInfo> findByPage(int start, int rows, Integer userId, String searchContent) {
        String sql = "select c.*,u.userName,a.articleTitle from commentInfo as c,userInfo as u,articleInfo as a where c.userId=u.userId && c.articleId=a.articleId && a.userId like concat('%',IFNULL(?,''),'%') && (c.content like concat('%',IFNULL(?,''),'%')  || a.articleTitle like concat('%',IFNULL(?,''),'%')) order by c.commentDate desc limit ?,?";
        List<CommentInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentInfo>(CommentInfo.class), userId, searchContent, searchContent, start, rows);
        return list;
    }
}
