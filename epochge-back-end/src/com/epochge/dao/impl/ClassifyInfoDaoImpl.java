package com.epochge.dao.impl;

import com.epochge.dao.ClassifyInfoDao;
import com.epochge.entities.ClassifyInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 功能说明
 *
 */
public class ClassifyInfoDaoImpl implements ClassifyInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 查询所有分类
    @Override
    public List<ClassifyInfo> getAllClassifyInfo() {
        String sql = "select * from classifyInfo";
        List<ClassifyInfo> classifyInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ClassifyInfo>(ClassifyInfo.class));
        return classifyInfoList;
    }

    // 根据分类id增加文章数量
    @Override
    public int updateArticleNumber(Integer classifyId) {
        String sql = "update classifyInfo set articleNumber = articleNumber+1 where classifyId = ?";
        int i = jdbcTemplate.update(sql, classifyId);
        return i;
    }

    // 根据分类id减少文章数量
    @Override
    public int updateArticleNumber2(Integer classifyId) {
        String sql = "update classifyInfo set articleNumber = articleNumber-1 where classifyId = ?";
        int i = jdbcTemplate.update(sql, classifyId);
        return i;
    }

    // 添加分类
    @Override
    public int insertClassify(ClassifyInfo classifyInfo) {
        String sql = "insert into classifyInfo(classifyName,classifyIntroduce,color1,color2) value (?,?,?,?)";
        int i = jdbcTemplate.update(sql, classifyInfo.getClassifyName(), classifyInfo.getClassifyIntroduce(), classifyInfo.getColor1(), classifyInfo.getColor2());
        return i;
    }

    // 根据文章id修改分类
    @Override
    public int updateClassify(ClassifyInfo classifyInfo) {
        String sql = "update classifyInfo set classifyName = ?,classifyIntroduce=?,color1 = ?,color2 = ? where classifyId = ?";
        int count = jdbcTemplate.update(sql, classifyInfo.getClassifyName(), classifyInfo.getClassifyIntroduce(), classifyInfo.getColor1(), classifyInfo.getColor2(), classifyInfo.getClassifyId());
        return count;
    }

    // 根据文章id删除分类
    @Override
    public int deleteClassify(Integer classifyId) {
        String sql = "delete from classifyInfo where classifyId = ?";
        int i = jdbcTemplate.update(sql, classifyId);
        return i;
    }

    // 根据分类id查询分类信息
    @Override
    public List<ClassifyInfo> getClassifyInfoByClassifyId(Integer classifyId) {
        String sql = "select * from classifyInfo where classifyId=?";
        List<ClassifyInfo> classifyInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ClassifyInfo>(ClassifyInfo.class), classifyId);
        return classifyInfoList;
    }
}
