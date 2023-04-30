package com.epochge.service;

import com.epochge.dao.SystemSetupDao;
import com.epochge.dao.impl.SystemSetupDaoImpl;
import com.epochge.entities.SystemSetup;

import java.util.List;

/**
 * 功能说明
 *
 */
public class SystemSetupService {
    SystemSetupDao dao = new SystemSetupDaoImpl();

    // 查询全站的系统设置
    public List<SystemSetup> getAllSystemSetup() {
        return dao.getAllSystemSetup();
    }

    // 修改系统设置
    public int updateSystemSetup(SystemSetup systemSetup) {
        if (systemSetup == null){
            return -1;
        }
        return dao.updateSystemSetup(systemSetup);
    }
}
