package com.example.structure.dddclassicmultimodule.biz.org.domain;

import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 部门领域对象。
 */
@Getter
@RequiredArgsConstructor
public class Org {

    /** 部门标识。 */
    private final OrgId orgId;

    /** 部门名。 */
    private final String name;

    /** 父部门标识，根部门为 null。 */
    private final OrgId parentId;
}
