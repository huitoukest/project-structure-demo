package com.example.structure.multibizmodule.org.biz.vo;

import com.example.structure.multibizmodule.org.api.OrgNode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树响应对象：递归映射 api 的 OrgNode。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgTreeVO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 子部门。 */
    private List<OrgTreeVO> children;

    /**
     * api DTO → VO 映射（递归）。
     *
     * @param node 部门树节点
     * @return 部门树响应对象
     */
    public static OrgTreeVO from(OrgNode node) {
        List<OrgTreeVO> children = node.getChildren() == null
            ? List.of()
            : node.getChildren().stream().map(OrgTreeVO::from).toList();
        return new OrgTreeVO(node.getId(), node.getName(), children);
    }
}
