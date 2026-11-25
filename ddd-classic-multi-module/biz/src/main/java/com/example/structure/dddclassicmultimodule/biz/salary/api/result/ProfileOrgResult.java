package com.example.structure.dddclassicmultimodule.biz.salary.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案部门结果。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileOrgResult {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;
}
