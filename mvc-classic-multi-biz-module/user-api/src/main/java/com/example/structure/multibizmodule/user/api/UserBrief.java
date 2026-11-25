package com.example.structure.multibizmodule.user.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户摘要（跨模块 DTO）：只暴露 id/name/orgId。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBrief {

    /** 用户主键。 */
    private Long id;

    /** 姓名。 */
    private String name;

    /** 所属部门主键。 */
    private Long orgId;
}
