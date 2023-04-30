package com.epochge.dao.impl;

import com.epochge.dao.BackstageMenuInfoDao;
import com.epochge.entities.BackstageMenuInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 功能说明
 *
 */
public class BackstageMenuInfoDaoImpl implements BackstageMenuInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 获取后台菜单全部信息
    @Override
    public List<BackstageMenuInfo> getAllBackstageMenuInfo() {
        String sql = "select * from backstageMenuInfo";
        List<BackstageMenuInfo> backstageMenuInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BackstageMenuInfo>(BackstageMenuInfo.class));
        return backstageMenuInfoList;
    }

    // 通过用户权限获取后台菜单全部信息
    @Override
    public List<BackstageMenuInfo> getBackstageMenuInfo(Integer userType) {
        String sql1 = "";
        if (userType != null) {
            sql1 = "where menuStatus = " + userType;
        }
        String sql = "select * from backstageMenuInfo " + sql1;
        List<BackstageMenuInfo> backstageMenuInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<BackstageMenuInfo>(BackstageMenuInfo.class));
        return backstageMenuInfoList;
    }
}
