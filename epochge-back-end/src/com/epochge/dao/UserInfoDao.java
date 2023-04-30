package com.epochge.dao;

import com.epochge.entities.UserInfo;

import java.util.List;

/**
 * 用户信息管理
 */
public interface UserInfoDao {
    // 注册：根据客户端传的用户名，判断数据库中是否存在，不存在的话返回的List为空
    List<UserInfo> isExistUserName(String userName);

    // 注册：根据客户端传的邮箱，判断数据库中是否存在，不存在的话返回的List为空
    List<UserInfo> isExistUserEmail(String userEmail);

    // 注册：提交数据，将用户填写的信息添加进数据库
    Integer insertRegisterInfo(UserInfo userInfo);

    // 登录：查询用户信息表全部信息，再判断登录信息是否存在
    List<UserInfo> getAllUserInfo(String loginName, String loginPass);

    // 忘记密码：查询输入的用户名或者邮箱是否存在
    List<UserInfo> isExistuserNameOrEmail(String userNameOrEmail);

    // 忘记密码：修改用户密码
    int updateUserPass(String userPass, String userEmail);

    // 文章展示：查询所有用户信息
    List<UserInfo> getAllUser();

    // 根据用户id查询用户信息
    List<UserInfo> getUserByUserId(Integer userId);

    // 查询注册用户总数
    int getUserCount();

    // 根据用户id修改用户基础信息：用户名、签名、头像
    int updateUserBasicsInfo(Integer userId, String userName, String userSignature, String userIcon);

    // 根据用户id修改用户绑定邮箱
    int updateUserEmail(Integer userId, String userEmail);

    // 根据用户id修改用户密码
    int updateUserPass(Integer userId, String userPass);

    // 根据用户id删除用户
    int deleteUser(Integer userId);

    // 分页查询  + 搜索框内容
    int findTotalCount(String searchContent);

    List<UserInfo> findByPage(int start, int rows, String searchContent);

    // 根据用户id修改用户信息
    int updateUser(UserInfo userInfo);
}
