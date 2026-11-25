package com.example.structure.dddclassicmultimodule.api.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案用户视图：按用例裁剪为 id/name，不含 orgId（部门已在 org 字段给出）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserDTO {

    /** 用户主键。 */
    private Long id;

    /** 用户名。 */
    private String name;
}
