package com.example.structure.packagesplit.modules.salary.service;

import com.example.structure.packagesplit.modules.salary.model.UserProfileModel;

/**
 * 用户档案服务：本示例仍由 service 直接编排多个 DAO（跨模块 DAO 依赖刻意保留）。
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
