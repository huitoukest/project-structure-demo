package com.example.structure.multibizmodule.org.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树节点（跨模块 DTO）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgNode {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 子部门。 */
    private List<OrgNode> children;
}
