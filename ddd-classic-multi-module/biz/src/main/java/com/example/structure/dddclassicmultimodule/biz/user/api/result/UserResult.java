package com.example.structure.dddclassicmultimodule.biz.user.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询结果。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResult {

    /** 用户主键。 */
    private Long id;

    /** 用户名。 */
    private String name;

    /** 归属部门主键。 */
    private Long orgId;
}
