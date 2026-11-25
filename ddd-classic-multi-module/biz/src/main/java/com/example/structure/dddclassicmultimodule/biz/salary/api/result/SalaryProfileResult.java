package com.example.structure.dddclassicmultimodule.biz.salary.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 薪资档案结果：用户 + 部门 + 薪资。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryProfileResult {

    /** 档案用户。 */
    private ProfileUserResult user;

    /** 档案部门。 */
    private ProfileOrgResult org;

    /** 薪资（元）。 */
    private long salary;
}
