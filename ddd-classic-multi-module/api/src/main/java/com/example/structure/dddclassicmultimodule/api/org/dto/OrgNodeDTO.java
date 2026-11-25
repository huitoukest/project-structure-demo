package com.example.structure.dddclassicmultimodule.api.org.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树节点 DTO：递归结构，叶子节点 children 为空列表。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgNodeDTO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 子部门。 */
    private List<OrgNodeDTO> children;
}
