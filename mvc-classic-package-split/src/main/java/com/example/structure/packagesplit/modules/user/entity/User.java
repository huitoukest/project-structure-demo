package com.example.structure.packagesplit.modules.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表行对象：与持久化结构一一对应。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** 主键。 */
    private Long id;

    /** 姓名。 */
    private String name;

    /** 所属部门主键。 */
    private Long orgId;
}
