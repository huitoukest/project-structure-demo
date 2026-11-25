package com.example.structure.dddclassicmultimodule.api.salary;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.salary.dto.SalaryProfileDTO;

/**
 * 薪资档案查询契约：由适配层的 facade 实现。
 */
public interface SalaryQueryApi {

    /**
     * 查询用户档案（用户 + 部门 + 薪资）。
     *
     * @param userId 用户主键
     * @return 统一响应
     */
    ApiResponse<SalaryProfileDTO> getProfile(Long userId);
}
