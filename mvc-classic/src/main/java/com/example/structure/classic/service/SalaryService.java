package com.example.structure.classic.service;

import com.example.structure.classic.model.UserProfileModel;

/**
 * 用户档案服务：classic 中由 service 直接编排多个 DAO。
 */
public interface SalaryService {

    /**
     * 组装用户档案（用户 + 部门 + 薪资）。
     *
     * @param userId 用户主键
     * @return 档案聚合模型
     */
    UserProfileModel profile(Long userId);
}
