package com.example.structure.dddclassicmultimodule.dal.api.org.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门持久化对象：parentId 为 null 表示根部门。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgPO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 父部门主键，根部门为 null。 */
    private Long parentId;
}
