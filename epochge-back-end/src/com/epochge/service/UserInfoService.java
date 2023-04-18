package com.epochge.service;

import com.epochge.dao.UserInfoDao;
import com.epochge.dao.impl.UserInfoDaoImpl;
import com.epochge.entities.PageBean;
import com.epochge.entities.UserInfo;

import java.util.List;

public class UserInfoService {
    UserInfoDao dao = new UserInfoDaoImpl();

    // 注册：根据客户端传的用户名，判断数据库中是否存在，不存在的话返回的List为空
    public List<UserInfo> isExistUserName(String userName){
        if (userName.equals("")){
            return null;
        }
        return dao.isExistUserName(userName);
    }
    // 注册：根据客户端传的邮箱，判断数据库中是否存在，不存在的话返回的List为空
    public List<UserInfo> isExistUserEmail(String userEmail) {
        if (userEmail.equals("")){
            return null;
        }
        return dao.isExistUserEmail(userEmail);
    }
    // 注册：提交数据，将用户填写的信息添加进数据库
    public Integer insertRegisterInfo(UserInfo userInfo){
        if (userInfo == null){
            return -1;
        }
        return dao.insertRegisterInfo(userInfo);
    }

    // 登录：查询用户信息表全部信息，再判断登录信息是否存在
    public List<UserInfo> getAllUserInfo(String loginName,String loginPass){
        if (loginName == null && loginPass == null){
            return null;
        }
        return dao.getAllUserInfo(loginName,loginPass);
    }

    // 忘记密码：查询输入的用户名或者邮箱是否存在
    public List<UserInfo> isExistuserNameOrEmail(String userNameOrEmail) {
        if (userNameOrEmail == null){
            return null;
        }
        return dao.isExistuserNameOrEmail(userNameOrEmail);
    }

    // 忘记密码：修改用户密码
    public int updateUserPass(String userPass, String userEmail) {
        if (userPass == null || userEmail == null){
            return -1;
        }
        return dao.updateUserPass(userPass,userEmail);
    }
    // 文章展示：查询所有用户信息
    public List<UserInfo> getAllUser() {
        return dao.getAllUser();
    }

    // 根据用户id查询用户信息
    public List<UserInfo> getUserByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return dao.getUserByUserId(userId);
    }

    // 查询注册用户总数
    public int getUserCount() {
        return dao.getUserCount();
    }

    // 根据用户id修改用户基础信息：用户名、签名、头像
    public int updateUserBasicsInfo(Integer userId, String userName, String userSignature, String userIcon) {
        return dao.updateUserBasicsInfo(userId,userName,userSignature,userIcon);
    }

    // 根据用户id修改用户绑定邮箱
    public int updateUserEmail(Integer userId, String userEmail) {
        if(userId != null && userEmail != null){
            return dao.updateUserEmail(userId,userEmail);
        }
        return -1;
    }

    // 根据用户id修改用户密码
    public int updateUserPass(Integer userId, String userPass) {
        if(userId != null && userPass != null){
            return dao.updateUserPass(userId,userPass);
        }
        return -1;
    }

    // 根据用户id删除用户
    public int deleteUser(Integer userId) {
        if (userId != null) {
            return dao.deleteUser(userId);
        }
        return -1;
    }

    // 分页查询 搜索
    public PageBean<UserInfo> findStudentByPage(int _currentPage, int _pageSize, String searchContent) {
        UserInfoDao dao = new UserInfoDaoImpl();
        int currentPage = _currentPage;//当前页码
        int rows = _pageSize;//每页显示行数

        // 创建空的pageBean
        PageBean<UserInfo> pb = new PageBean<UserInfo>();
        // 2、设置参数
        pb.setPageSize(rows);//每条显示行数
        pb.setCurrentPage(currentPage);   // 当前页码

        //3：调用dao查询总记录数
        int total = dao.findTotalCount(searchContent);
        pb.setTotal(total);

        // 4:调用dao查询的list集合
        // 计算开始记录的索引  从索引几开始 = (当前页码-1)*5
        int start = (currentPage - 1) * rows;

        List<UserInfo> userInfoList = dao.findByPage(start, rows, searchContent);
        pb.setList(userInfoList);


        // 5：计算总页码  总记录数 % 每页显示的条数 ==0 ？总记录数/每页的条数 ： 总记录数/每页的条数+1
        int totalPage = (total % rows) == 0 ? total / rows : total / rows + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    // 根据用户id修改用户信息
    public int updateUser(UserInfo userInfo) {
        if (userInfo == null) {
            return -1;
        }
        return dao.updateUser(userInfo);
    }
}
