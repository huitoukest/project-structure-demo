package com.example.structure.dddclassicmultimodule.biz.org.api.result;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树节点结果：递归结构。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgNodeResult {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 子部门。 */
    private List<OrgNodeResult> children;
}
