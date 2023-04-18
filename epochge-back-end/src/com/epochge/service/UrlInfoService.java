package com.epochge.service;

import com.epochge.dao.UrlInfoDao;
import com.epochge.dao.impl.UrlInfoDaoImpl;
import com.epochge.entities.PageBean;
import com.epochge.entities.UrlInfo;

import java.util.List;

public class UrlInfoService {
    UrlInfoDao dao = new UrlInfoDaoImpl();

    // 添加链接
    public int insertUrl(UrlInfo urlInfo) {
        if (urlInfo != null) {
            return dao.insertUrl(urlInfo);
        }
        return -1;
    }

    // 根据链接编号修改审核状态
    public int updateUrl(Integer urlId, Integer urlPass) {
        if (urlId != null && urlPass != null) {
            return dao.updateUrl(urlId, urlPass);
        }
        return -1;
    }

    // 根据链接编号进行删除
    public int deleteUrl(Integer urlId) {
        if (urlId != null) {
            return dao.deleteUrl(urlId);
        }
        return -1;
    }

    // 根据链接名称查找链接全部信息
    public List<UrlInfo> getUrlInfoByUrlName(String urlName) {
        if (urlName == null) {
            return null;
        }
        return dao.getUrlInfoByUrlName(urlName);
    }

    // 根据链接类型查找审核通过的链接信息
    public List<UrlInfo> getUrlInfoByUrlTyep(Integer urlType) {
        if (urlType == null) {
            return null;
        }
        return dao.getUrlInfoByUrlTyep(urlType);
    }


    // 分页查询 搜索
    public PageBean<UrlInfo> findStudentByPage(int _currentPage, int _pageSize, String searchContent) {
        UrlInfoDao dao = new UrlInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<UrlInfo> pb = new PageBean<UrlInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount(searchContent);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<UrlInfo> urlInfoList = dao.findByPage(start, rows, searchContent);
        pb.setList(urlInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 分页查询 审核筛选
    public PageBean<UrlInfo> findStudentByPage2(int _currentPage, int _pageSize, String auditContent) {
        UrlInfoDao dao = new UrlInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<UrlInfo> pb = new PageBean<UrlInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount2(auditContent);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<UrlInfo> urlInfoList = dao.findByPage2(start, rows, auditContent);
        pb.setList(urlInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
