package com.example.structure.multibizmodule.salary.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户档案（跨模块 DTO）：用户 id/name、部门 id/name、月薪。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryProfile {

    /** 用户主键。 */
    private Long userId;

    /** 用户姓名。 */
    private String userName;

    /** 部门主键。 */
    private Long orgId;

    /** 部门名。 */
    private String orgName;

    /** 月薪（元）。 */
    private long salary;
}
