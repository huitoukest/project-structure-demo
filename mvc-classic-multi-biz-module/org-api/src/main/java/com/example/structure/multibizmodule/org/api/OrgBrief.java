package com.example.structure.multibizmodule.org.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门摘要（跨模块 DTO）：只暴露 id/name。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgBrief {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;
}
