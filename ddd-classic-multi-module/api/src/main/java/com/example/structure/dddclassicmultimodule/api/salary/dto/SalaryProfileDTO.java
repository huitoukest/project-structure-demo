package com.example.structure.dddclassicmultimodule.api.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户档案 DTO：用户 + 部门 + 薪资。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryProfileDTO {

    /** 档案用户视图（仅 id/name）。 */
    private ProfileUserDTO user;

    /** 档案部门视图。 */
    private ProfileOrgDTO org;

    /** 薪资（元）。 */
    private long salary;
}
