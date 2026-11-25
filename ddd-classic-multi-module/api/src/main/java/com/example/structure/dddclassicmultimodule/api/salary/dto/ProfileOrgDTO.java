package com.example.structure.dddclassicmultimodule.api.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案部门视图：id/name。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileOrgDTO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;
}
