package com.example.structure.multibizmodule.salary.biz.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案中的部门视图：仅 id/name。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileOrgVO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;
}
