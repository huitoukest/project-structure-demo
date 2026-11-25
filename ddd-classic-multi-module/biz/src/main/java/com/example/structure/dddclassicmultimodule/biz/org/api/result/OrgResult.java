package com.example.structure.dddclassicmultimodule.biz.org.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门查询结果。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgResult {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 父部门主键。 */
    private Long parentId;
}
