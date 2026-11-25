package com.example.structure.dddclassicmultimodule.dal.api.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户持久化对象：与用户表行一一对应。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    /** 用户主键。 */
    private Long id;

    /** 用户名。 */
    private String name;

    /** 归属部门主键。 */
    private Long orgId;
}
