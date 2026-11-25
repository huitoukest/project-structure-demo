package com.example.structure.dddclassicmultimodule.biz.org.domain;

import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 部门树节点领域对象。
 */
@Getter
@RequiredArgsConstructor
public class OrgNode {

    /** 部门标识。 */
    private final OrgId orgId;

    /** 部门名。 */
    private final String name;

    /** 子节点。 */
    private final List<OrgNode> children;
}
