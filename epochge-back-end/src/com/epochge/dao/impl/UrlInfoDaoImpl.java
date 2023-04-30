package com.epochge.dao.impl;

import com.epochge.dao.UrlInfoDao;
import com.epochge.entities.UrlInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UrlInfoDaoImpl implements UrlInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 添加链接
    @Override
    public int insertUrl(UrlInfo urlInfo) {
        String sql = "insert into urlInfo(urlName,urlAddres,urlReferral,urlLitimg,urlType,webmasterEmail) value (?,?,?,?,?,?) ";
        int i = jdbcTemplate.update(sql, urlInfo.getUrlName(), urlInfo.getUrlAddres(), urlInfo.getUrlReferral(), urlInfo.getUrlLitimg(), urlInfo.getUrlType(), urlInfo.getWebmasterEmail());
        return i;
    }

    @Override
    // 根据链接编号修改链接审核状态
    public int updateUrl(Integer urlId, Integer urlPass) {
        String sql = "update urlInfo set urlPass = ? where urlId =?";
        int i = jdbcTemplate.update(sql, urlPass, urlId);
        return i;
    }

    @Override
    // 根据链接编号删除链接
    public int deleteUrl(Integer urlId) {
        String sql = "delete from urlInfo where urlId = ?";
        int i = jdbcTemplate.update(sql, urlId);
        return i;
    }

    /**
     * 统计总页数  搜索
     *
     * @return
     */
    @Override
    public int findTotalCount(String searchContent) {
        String sql = "select count(1) from urlInfo where urlName like concat('%',IFNULL(?,''),'%')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, searchContent);
        return count;
    }

    /**
     * 统计每页显示行数 搜索
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<UrlInfo> findByPage(int start, int rows, String searchContent) {
        String sql = "select * from urlInfo where urlName like concat('%',IFNULL(?,''),'%') order by urlId limit ?,?";
        List<UrlInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlInfo>(UrlInfo.class), searchContent, start, rows);
        return list;
    }

    /**
     * 统计总页数 下拉框
     *
     * @return
     */
    @Override
    public int findTotalCount2(String auditContent) {
        String sql = "select count(1) from urlInfo where urlPass like concat('%',IFNULL(?,''),'%')";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, auditContent);
        return count;
    }

    /**
     * 统计每页显示行数 下拉框
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<UrlInfo> findByPage2(int start, int rows, String auditContent) {
        String sql = "select * from urlInfo where urlPass like concat('%',IFNULL(?,''),'%') order by urlId limit ?,?";
        List<UrlInfo> list = new ArrayList<>();
        //BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlInfo>(UrlInfo.class), auditContent, start, rows);
        return list;
    }


    // 根据链接名称查找信息
    @Override
    public List<UrlInfo> getUrlInfoByUrlName(String urlName) {
        String sql1 = "";
        if (urlName != null) {
            sql1 = "where urlName = " + urlName;
        }
        String sql = "select * from urlInfo " + sql1;
        List<UrlInfo> backstageMenuInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlInfo>(UrlInfo.class));
        return backstageMenuInfoList;
    }

    // 根据链接类型查找审核通过的链接信息
    @Override
    public List<UrlInfo> getUrlInfoByUrlTyep(Integer urlType) {
        String sql1 = "";
        if (urlType != null) {
            sql1 = "where urlType = " + urlType + " && urlPass = 2";
        }
        String sql = "select * from urlInfo " + sql1;
        List<UrlInfo> backstageMenuInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlInfo>(UrlInfo.class));
        return backstageMenuInfoList;
    }

}
