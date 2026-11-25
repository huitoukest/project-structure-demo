package com.example.structure.classic.vo;

import com.example.structure.classic.model.OrgTreeModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门树响应对象：递归映射 model 子节点。
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
     * model → VO 映射（递归）。
     *
     * @param model 部门树模型
     * @return 部门树响应对象
     */
    public static OrgTreeVO from(OrgTreeModel model) {
        List<OrgTreeVO> children = model.getChildren() == null
            ? List.of()
            : model.getChildren().stream().map(OrgTreeVO::from).toList();
        return new OrgTreeVO(model.getId(), model.getName(), children);
    }
}
