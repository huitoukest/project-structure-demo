package com.example.structure.multibizmodule.salary.api;

/**
 * 薪资域对外契约：其他模块只能通过本接口访问薪资/档案能力。
 */
public interface SalaryApi {

    /**
     * 查询用户档案（用户 + 部门 + 薪资）。
     *
     * @param userId 用户主键
     * @return 档案信息
     */
    SalaryProfile getProfile(Long userId);
}
