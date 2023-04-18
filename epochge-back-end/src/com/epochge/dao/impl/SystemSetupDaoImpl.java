package com.epochge.dao.impl;

import com.epochge.dao.SystemSetupDao;
import com.epochge.entities.SystemSetup;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 系统设置
 */
public class SystemSetupDaoImpl implements SystemSetupDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 查询全站的系统设置
    @Override
    public List<SystemSetup> getAllSystemSetup() {
        String sql = "select * from systemSetup";
        List<SystemSetup> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<SystemSetup>(SystemSetup.class));
        return list;
    }

    // 修改系统设置
    @Override
    public int updateSystemSetup(SystemSetup systemSetup) {
        String sql = "update systemSetup set stickArticle=?,allArticle=?,featuredArticle=?,technologyArticle=?,resourceArticle=?,advertising1=?,advertisingLink1=?,advertising2=?,advertisingLink2=?,advertising3=?,advertisingLink3=?,effects01=?,effects02=?";
        int count = jdbcTemplate.update(sql,
                systemSetup.getStickArticle(),
                systemSetup.getAllArticle(),
                systemSetup.getFeaturedArticle(),
                systemSetup.getTechnologyArticle(),
                systemSetup.getResourceArticle(),
                systemSetup.getAdvertising1(),
                systemSetup.getAdvertisingLink1(),
                systemSetup.getAdvertising2(),
                systemSetup.getAdvertisingLink2(),
                systemSetup.getAdvertising3(),
                systemSetup.getAdvertisingLink3(),
                systemSetup.getEffects01(),
                systemSetup.getEffects02());

        return count;
    }
}
