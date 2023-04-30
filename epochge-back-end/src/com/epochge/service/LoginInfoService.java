package com.epochge.service;

import com.epochge.dao.LoginInfoDao;
import com.epochge.dao.impl.LoginInfoDaoImpl;

/**
 * 功能说明
 *
 */
public class LoginInfoService {
    LoginInfoDao dao = new LoginInfoDaoImpl();

    // 添加登录信息
    public Integer insertLoginInfo(Integer userId){
        if (userId == null){
            return -1;
        }
        return dao.insertLoginInfo(userId);
    }
}
