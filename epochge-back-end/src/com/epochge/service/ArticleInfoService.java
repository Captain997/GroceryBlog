package com.epochge.service;

import com.epochge.dao.ArticleInfoDao;
import com.epochge.dao.impl.ArticleInfoDaoImpl;
import com.epochge.entities.*;

import java.util.List;

/**
 * @author Bpvank
 */
public class ArticleInfoService {
    ArticleInfoDao dao = new ArticleInfoDaoImpl();

    // 添加文章
    public Integer insertArticleInfo(ArticleInfo articleInfo) {
        if (articleInfo == null) {
            return -1;
        }
        return dao.insertArticle(articleInfo);
    }

    // 修改文章
    public int updateArticle(ArticleInfo articleInfo) {
        if (articleInfo == null) {
            return -1;
        }
        return dao.updateArticle(articleInfo);
    }

    // 根据文章id删除文章
    public int deleteArticle(Integer articleId) {
        if (articleId != null) {
            return dao.deleteArticle(articleId);
        }
        return -1;
    }

    // 根据用户id 删除文章
    public int deleteArticleByUserId(Integer userId) {
        if (userId != null) {
            return dao.deleteArticleByUserId(userId);
        }
        return -1;
    }

    // 查询全部文章
    public List<ArticleInfo> getAllArticle() {
        return dao.getAllArticle();
    }

    // 查询通过审核文章总数
    public int getArticleCount() {
        return dao.getArticleCount();
    }

    // 根据用户id查询该用户有多少篇文章
    public int getArticleCountByUserId(Integer userId) {
        if (userId == null) {
            return -1;
        }
        return dao.getArticleCountByUserId(userId);
    }

    // 通过文章id查找文章内容
    public List<ArticleInfo> getAllArticleById(Integer articleId) {
        if (articleId == null) {
            return null;
        }
        return dao.getAllArticleById(articleId);
    }

    // 通过id修改文章浏览量
    public int updateArticleClick(Integer articleId) {
        if (articleId != null) {
            return dao.updateArticleClick(articleId);
        }
        return -1;
    }

    // 根据文章id修改文章审核状态
    public int updateArticlePass(Integer articleId, Integer articlePass) {
        if (articleId != null && articlePass != null) {
            return dao.updateArticlePass(articleId, articlePass);
        }
        return -1;
    }

    // 后台文章管理：分页查询+通过用户权限获取文章信息   管理员获取全部  普通用户获取自己的
    public PageBean<ArticleInfo> findStudentByPage(int _currentPage, int _pageSize, Integer userId) {
        ArticleInfoDao dao = new ArticleInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<ArticleInfo> pb = new PageBean<ArticleInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount(userId);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<ArticleInfo> articleInfoList = dao.findByPage(start, rows, userId);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 根据文章标题进行搜索 并且 进行分页  判断是管理员还是普通用户
    public PageBean<ArticleInfo> findStudentByPage2(int _currentPage, int _pageSize, String articleTitle, Integer userId) {
        ArticleInfoDao dao = new ArticleInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<ArticleInfo> pb = new PageBean<ArticleInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount2(articleTitle, userId);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<ArticleInfo> articleInfoList = dao.findByPage2(start, rows, articleTitle, userId);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 下拉框筛选文章审核状态，查询出相关内容并进行分页
    public PageBean<ArticleInfo> findStudentByPage3(int _currentPage, int _pageSize, Integer articlePass, Integer userId) {
        ArticleInfoDao dao = new ArticleInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<ArticleInfo> pb = new PageBean<ArticleInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount3(articlePass, userId);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<ArticleInfo> articleInfoList = dao.findByPage3(start, rows, articlePass, userId);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 前台文章展示 通过审核、所有人可见的文章    可根据文章标题、分类、用户、摘要、进行搜索 并且 进行分页
    public PageBean<ArticleInfo> findStudentByPage4(int _currentPage, int _pageSize, String keyword) {
        ArticleInfoDao dao = new ArticleInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<ArticleInfo> pb = new PageBean<ArticleInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount4(keyword);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<ArticleInfo> articleInfoList = dao.findByPage4(start, rows, keyword);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 查询搜索出来的数据有多少条
    public int getArticleSearchCount(String keyword) {
        return dao.getArticleSearchCount(keyword);
    }

    ;

    // 前台分类文章展示 通过审核、所有人可见的文章   进行分页
    public PageBean<ArticleInfo> findStudentByPage5(int _currentPage, int _pageSize, Integer classifyId) {
        ArticleInfoDao dao = new ArticleInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<ArticleInfo> pb = new PageBean<ArticleInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount5(classifyId);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<ArticleInfo> articleInfoList = dao.findByPage5(start, rows, classifyId);
        pb.setList(articleInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
