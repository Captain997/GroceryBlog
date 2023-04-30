package com.epochge.dao;
import com.epochge.entities.SystemSetup;

import java.util.List;

/**
 * 系统设置
 */
public interface SystemSetupDao {
    // 查询全站的系统设置
    List<SystemSetup> getAllSystemSetup();

    // 修改系统设置
    int updateSystemSetup(SystemSetup systemSetup);
}
