package com.example.structure.dddclassicmultimodule.biz.org.app.assembler;

import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgNodeResult;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgResult;
import com.example.structure.dddclassicmultimodule.biz.org.domain.Org;
import com.example.structure.dddclassicmultimodule.biz.org.domain.OrgNode;
import com.example.structure.dddclassicmultimodule.dal.api.org.po.OrgPO;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 部门装配器：持久化对象 → 领域对象 → 查询结果。
 */
@Component
public class OrgAssembler {

    /**
     * 持久化对象转领域对象。
     *
     * @param po 部门持久化对象
     * @return 部门领域对象
     */
    public Org toDomain(OrgPO po) {
        return new Org(OrgId.of(po.getId()), po.getName(),
            po.getParentId() == null ? null : OrgId.of(po.getParentId()));
    }

    /**
     * 领域对象转查询结果。
     *
     * @param org 部门领域对象
     * @return 部门结果
     */
    public OrgResult toResult(Org org) {
        return new OrgResult(org.getOrgId().getValue(), org.getName(),
            org.getParentId() == null ? null : org.getParentId().getValue());
    }

    /**
     * 树节点转查询结果（递归）。
     *
     * @param node 树节点
     * @return 树节点结果
     */
    public OrgNodeResult toNodeResult(OrgNode node) {
        List<OrgNodeResult> children = node.getChildren().stream()
            .map(this::toNodeResult)
            .toList();
        return new OrgNodeResult(node.getOrgId().getValue(), node.getName(), children);
    }
}
