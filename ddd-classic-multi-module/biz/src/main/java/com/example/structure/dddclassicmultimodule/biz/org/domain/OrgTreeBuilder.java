package com.example.structure.dddclassicmultimodule.biz.org.domain;

import com.example.structure.dddclassicmultimodule.biz.shared.BizMessages;
import com.example.structure.dddclassicmultimodule.core.constant.ErrorCode;
import com.example.structure.dddclassicmultimodule.core.exception.BizException;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * 部门树构建器：校验 parentId 存在后按父子关系递归建树。
 *
 * 只读种子数据无环，因此不额外做环检测；真实写入场景需要补环校验。
 */
@Component
public class OrgTreeBuilder {

    /**
     * 构建部门树。
     *
     * @param orgs 全部部门
     * @return 根节点列表
     */
    public List<OrgNode> build(List<Org> orgs) {
        validateParentExists(orgs);
        Map<Long, List<Org>> childrenByParent = orgs.stream()
            .filter(org -> org.getParentId() != null)
            .collect(Collectors.groupingBy(org -> org.getParentId().getValue()));
        return orgs.stream()
            .filter(org -> org.getParentId() == null)
            .map(org -> toNode(org, childrenByParent))
            .toList();
    }

    /**
     * 校验每个非根节点的 parentId 都存在于同表。
     *
     * @param orgs 全部部门
     */
    private void validateParentExists(List<Org> orgs) {
        Set<Long> ids = orgs.stream()
            .map(org -> org.getOrgId().getValue())
            .collect(Collectors.toSet());
        for (Org org : orgs) {
            OrgId parentId = org.getParentId();
            if (parentId != null && !ids.contains(parentId.getValue())) {
                throw new BizException(ErrorCode.ORG_TREE_INVALID, BizMessages.orgTreeInvalid(parentId.getValue()));
            }
        }
    }

    /**
     * 递归构建单个节点。
     *
     * @param org              当前部门
     * @param childrenByParent 父主键到子部门列表的索引
     * @return 树节点
     */
    private OrgNode toNode(Org org, Map<Long, List<Org>> childrenByParent) {
        List<OrgNode> children = childrenByParent.getOrDefault(org.getOrgId().getValue(), List.of()).stream()
            .map(child -> toNode(child, childrenByParent))
            .toList();
        return new OrgNode(org.getOrgId(), org.getName(), children);
    }
}
