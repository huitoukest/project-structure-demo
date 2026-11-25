package com.example.structure.classic.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树模型：service 层组装，子节点为空列表表示叶子。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgTreeModel {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 子部门。 */
    private List<OrgTreeModel> children;
}
