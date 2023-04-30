package com.epochge.dao.impl;

import com.epochge.dao.ArticleInfoDao;
import com.epochge.entities.ArticleInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 */
public class ArticleInfoDaoImpl implements ArticleInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    // 添加文章
    public int insertArticle(ArticleInfo articleInfo) {
        String sql = "insert into articleInfo(userId,userName,articleTitle,articleClassifyId,articleClassifyName,articleDase,articleImgLitimg,articleContent,articleState,articlePass,commentState) value (?,?,?,?,?,?,?,?,?,?,?)";
        int i = jdbcTemplate.update(sql,
                articleInfo.getUserId(),
                articleInfo.getUserName(),
                articleInfo.getArticleTitle(),
                articleInfo.getArticleClassifyId(),
                articleInfo.getArticleClassifyName(),
                articleInfo.getArticleDase(),
                articleInfo.getArticleImgLitimg(),
                articleInfo.getArticleContent(),
                articleInfo.getArticleState(),
                articleInfo.getArticlePass(),
                articleInfo.getCommentState());
        return i;
    }

    // 修改文章
    @Override
    public int updateArticle(ArticleInfo articleInfo) {
        String sql = "update articleInfo set articleTitle = ?,articleClassifyId = ?,articleClassifyName = ?,articleDase = ?,articleImgLitimg = ?,articleContent = ?,articleState = ?,articlePass=?,commentState = ?  where articleId = ?";
        int count = jdbcTemplate.update(sql,
                articleInfo.getArticleTitle(),
                articleInfo.getArticleClassifyId(),
                articleInfo.getArticleClassifyName(),
                articleInfo.getArticleDase(),
                articleInfo.getArticleImgLitimg(),
                articleInfo.getArticleContent(),
                articleInfo.getArticleState(),
                articleInfo.getArticlePass(),
                articleInfo.getCommentState(),
                articleInfo.getArticleId());
        return count;
    }

    // 根据文章id删除文章
    @Override
    public int deleteArticle(Integer articleId) {
        String sql = "delete from articleInfo where articleId = ?";
        int i = jdbcTemplate.update(sql, articleId);
        return i;
    }

    // 根据用户id 删除文章
    @Override
    public int deleteArticleByUserId(Integer userId) {
        String sql = "delete from articleInfo where userId = ?";
        int i = jdbcTemplate.update(sql, userId);
        return i;
    }

    // 查询全部文章  按时间排序
    @Override
    public List<ArticleInfo> getAllArticle() {
        String sql = "select * from articleInfo where articlePass = 2 && articleState = 1 order by publishTime desc";
        List<ArticleInfo> articleInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class));
        return articleInfoList;
    }

    // 查询通过审核文章总数
    @Override
    public int getArticleCount() {
        String sql = "select count(*) from  articleInfo where articlePass = 2";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    // 根据用户id查询该用户有多少篇文章
    @Override
    public int getArticleCountByUserId(Integer userId) {
        String sql = "select count(*) from articleInfo where userId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count;
    }

    // 通过文章id查找文章内容
    @Override
    public List<ArticleInfo> getAllArticleById(Integer articleId) {
        String sql1 = "";
        if (articleId != null) {
            sql1 = "where articleId = " + articleId;
        }
        String sql = "select * from articleInfo " + sql1;
        List<ArticleInfo> backstageMenuInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class));
        return backstageMenuInfoList;
    }

    // 通过id修改文章点击量
    @Override
    public int updateArticleClick(Integer articleId) {
        String sql = "update articleInfo set click = click+1 where articleId =?";
        int i = jdbcTemplate.update(sql, articleId);
        return i;
    }

    // 根据文章id修改文章审核状态
    @Override
    public int updateArticlePass(Integer articleId, Integer articlePass) {
        String sql = "update articleInfo set articlePass = ? where articleId = ?";
        int i = jdbcTemplate.update(sql, articlePass, articleId);
        return i;
    }

    // 后台文章管理：分页查询+通过用户权限获取文章信息   管理员获取全部  普通用户获取自己的
    @Override
    public int findTotalCount(Integer userId) {
        String sql = "select count(1) from articleInfo where userId like concat('%',IFNULL(?,''),'%')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count;
    }

    @Override
    public List<ArticleInfo> findByPage(int start, int rows, Integer userId) {
        String sql = "select * from articleInfo where userId like concat('%',IFNULL(?,''),'%') order by publishTime desc limit ?,?";
        List<ArticleInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class), userId, start, rows);
        return list;
    }


    // 根据文章标题、分类、用户、进行搜索 并且 进行分页  判断是管理员还是普通用户
    @Override
    public int findTotalCount2(String articleTitle, Integer userId) {
        String sql = "select count(1) from articleInfo where (articleTitle like concat('%',ifnull(?,''),'%') " +
                "|| articleClassifyName like concat('%',ifnull(?,''),'%') " +
                "|| userName like concat('%',ifnull(?,''),'%')) " +
                "&& userId like concat('%',IFNULL(?,''),'%')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, articleTitle, articleTitle, articleTitle, userId);
        return count;
    }

    @Override
    public List<ArticleInfo> findByPage2(int start, int rows, String articleTitle, Integer userId) {
        String sql = "select * from articleInfo where (articleTitle like concat('%',ifnull(?,''),'%') " +
                "|| articleClassifyName like concat('%',ifnull(?,''),'%') " +
                "|| userName like concat('%',ifnull(?,''),'%')) " +
                "&& userId like concat('%',ifnull(?,''),'%') order by publishTime desc limit ?,?";
        List<ArticleInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class), articleTitle, articleTitle, articleTitle, userId, start, rows);
        return list;
    }

    // 下拉框筛选文章审核状态，查询出相关内容并进行分页
    @Override
    public int findTotalCount3(Integer articlePass, Integer userId) {
        String sql = "select count(1) from articleInfo where articlePass like concat('%',ifnull(?,''),'%') and userId like concat('%',IFNULL(?,''),'%')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, articlePass, userId);
        return count;
    }

    @Override
    public List<ArticleInfo> findByPage3(int start, int rows, Integer articlePass, Integer userId) {
        String sql = "select * from articleInfo where articlePass like concat('%',ifnull(?,''),'%') and userId like concat('%',ifnull(?,''),'%') order by publishTime desc limit ?,?";
        List<ArticleInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class), articlePass, userId, start, rows);
        return list;
    }

    // 前台文章展示 通过审核、所有人可见的文章    可根据文章标题、分类、用户、摘要、进行搜索 并且 进行分页
    @Override
    public int findTotalCount4(String keyword) {
        String sql = "select count(1) from articleInfo where (articleTitle like concat('%',ifnull(?,''),'%') " +
                "|| articleClassifyName like concat('%',ifnull(?,''),'%') " +
                "|| userName like concat('%',ifnull(?,''),'%') " +
                "|| articleDase like concat('%',ifnull(?,''),'%') )" +
                "&& articleState = 1 && articlePass = 2";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, keyword, keyword, keyword, keyword);
        return count;
    }

    @Override
    public List<ArticleInfo> findByPage4(int start, int rows, String keyword) {
        String sql = "select * from articleInfo where (articleTitle like concat('%',ifnull(?,''),'%') " +
                "|| articleClassifyName like concat('%',ifnull(?,''),'%') " +
                "|| userName like concat('%',ifnull(?,''),'%') " +
                "|| articleDase like concat('%',ifnull(?,''),'%') )" +
                "&& articleState = 1 && articlePass = 2 order by publishTime desc limit ?,?";
        List<ArticleInfo> list = new ArrayList<>();
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class), keyword, keyword, keyword, keyword, start, rows);
        return list;
    }

    // 查询搜索出来的数据有多少条
    @Override
    public int getArticleSearchCount(String keyword) {
        String sql = "select count(*) from  articleInfo where (articleTitle like concat('%',ifnull(?,''),'%') " +
                "|| articleClassifyName like concat('%',ifnull(?,''),'%') " +
                "|| userName like concat('%',ifnull(?,''),'%') " +
                "|| articleDase like concat('%',ifnull(?,''),'%') )" +
                "&& articleState = 1 && articlePass = 2";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, keyword, keyword, keyword, keyword);
        return count;
    }

    // 前台分类文章展示 通过审核、所有人可见的文章   进行分页
    @Override
    public int findTotalCount5(Integer classifyId) {
        String sql = "select count(1) from articleInfo where articleClassifyId=? && articleState = 1 && articlePass = 2";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, classifyId);
        return count;
    }

    @Override
    public List<ArticleInfo> findByPage5(int start, int rows, Integer classifyId) {
        String sql = "select * from articleInfo where articleClassifyId=? && articleState = 1 && articlePass = 2 order by publishTime desc limit ?,?";
        List<ArticleInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleInfo>(ArticleInfo.class), classifyId, start, rows);
        return list;
    }
}
