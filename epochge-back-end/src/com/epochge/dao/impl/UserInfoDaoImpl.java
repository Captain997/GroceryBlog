package com.epochge.dao.impl;

import com.epochge.dao.UserInfoDao;
import com.epochge.entities.UserInfo;
import com.epochge.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bpvank
 */
public class UserInfoDaoImpl implements UserInfoDao {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    // 注册：根据客户端传的用户名，判断数据库中是否存在，不存在的话返回的List为空
    @Override
    public List<UserInfo> isExistUserName(String userName) {
        String sql1 = "";
        if (userName != null) {
            sql1 = "where userName = '" + userName + "'";
        }
        String sql = "select * from  userInfo " + sql1;
        List<UserInfo> userInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return userInfoList;
    }

    // 注册：根据客户端传的邮箱，判断数据库中是否存在，不存在的话返回的List为空
    @Override
    public List<UserInfo> isExistUserEmail(String userEmail) {
        String sql1 = "";
        if (userEmail != null) {
            sql1 = "where userEmail = '" + userEmail + "'";
        }
        String sql = "select * from userInfo " + sql1;
        List<UserInfo> userInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return userInfoList;
    }

    // 注册：提交数据，将用户填写的信息添加进数据库
    @Override
    public Integer insertRegisterInfo(UserInfo userInfo) {
        String sql = "insert into userInfo(userName,userPass,userEmail) values(?,?,?)";
        int count = jdbcTemplate.update(sql, userInfo.getUserName(), userInfo.getUserPass(), userInfo.getUserEmail());
        return count;
    }

    // 登录：查询用户信息表全部信息，再判断登录信息是否存在
    @Override
    public List<UserInfo> getAllUserInfo(String loginName, String loginPass) {
       String sql1 = "";
        if (loginName != null && loginPass != null) {
            sql1 = "where (userName = '" + loginName + "' || userEmail = '" + loginName + "') && userPass ='" + loginPass + "'";
        }
        String sql = "select * from userInfo " + sql1;
        List<UserInfo> userInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return userInfoList;
    }

    // 忘记密码：查询输入的用户名或者邮箱是否存在
    @Override
    public List<UserInfo> isExistuserNameOrEmail(String userNameOrEmail) {
        String sql1 = "";
        if (userNameOrEmail != null) {
            sql1 = "where userName = '" + userNameOrEmail + "' || userEmail = '" + userNameOrEmail + "'";
        }
        String sql = "select * from userInfo " + sql1;
        List<UserInfo> userInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return userInfoList;
    }

    // 忘记密码：修改用户密码
    @Override
    public int updateUserPass(String userPass, String userEmail) {
        String sql = "update userInfo set userPass = ? where userEmail = ?";
        int count = jdbcTemplate.update(sql, userPass, userEmail);
        return count;
    }

    // 文章展示：查询所有用户信息
    @Override
    public List<UserInfo> getAllUser() {
        String sql = "select * from userInfo";
        List<UserInfo> userInfoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return userInfoList;
    }

    // 根据用户id查询用户信息
    @Override
    public List<UserInfo> getUserByUserId(Integer userId) {
        String sql1 = "";
        if (userId != null) {
            sql1 = "where userId = " + userId;
        }
        String sql = "select * from userInfo " + sql1;
        List<UserInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
        return list;
    }

    // 查询注册用户总数
    @Override
    public int getUserCount() {
        String sql = "select count(*) from  userInfo";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    // 根据用户id修改用户基础信息：用户名、签名、头像
    @Override
    public int updateUserBasicsInfo(Integer userId, String userName, String userSignature, String userIcon) {
        String sql = "update userInfo set userName = ?,userSignature = ?,userIcon = ?  where userId =?";
        int i = jdbcTemplate.update(sql, userName, userSignature, userIcon, userId);
        return i;
    }

    // 根据用户id修改用户绑定邮箱
    @Override
    public int updateUserEmail(Integer userId, String userEmail) {
        String sql = "update userInfo set userEmail = ? where userId =?";
        int i = jdbcTemplate.update(sql,userEmail, userId);
        return i;
    }

    // 根据用户id修改用户密码
    @Override
    public int updateUserPass(Integer userId, String userPass) {
        String sql = "update userInfo set userPass = ? where userId =?";
        int i = jdbcTemplate.update(sql,userPass, userId);
        return i;
    }

    // 根据用户id删除用户
    @Override
    public int deleteUser(Integer userId) {
        String sql = "delete from userInfo where userId = ?";
        int i = jdbcTemplate.update(sql, userId);
        return i;
    }

    /**
     * 统计总页数  搜索
     *
     * @return
     */
    @Override
    public int findTotalCount(String searchContent) {
        String sql = "select count(1) from userInfo where userName like concat('%',IFNULL(?,''),'%')";
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
    public List<UserInfo> findByPage(int start, int rows, String searchContent) {
        String sql = "select * from userInfo where userName like concat('%',IFNULL(?,''),'%') order by userId limit ?,?";
        List<UserInfo> list = new ArrayList<>();
        // BeanPropertyRowMapper  转换为bean对象
        list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), searchContent, start, rows);
        return list;
    }

    // 根据用户id修改用户信息
    @Override
    public int updateUser(UserInfo userInfo) {
        String sql = "update userInfo set userName = ?,userPass = ?,userEmail=?,userSignature=?,userType=?  where userId = ?";
        int count = jdbcTemplate.update(sql,userInfo.getUserName(),userInfo.getUserPass(),userInfo.getUserEmail(),userInfo.getUserSignature(),userInfo.getUserType(),userInfo.getUserId());
        return count;
    }
}
