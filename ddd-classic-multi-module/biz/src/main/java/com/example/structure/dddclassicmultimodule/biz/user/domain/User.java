package com.example.structure.dddclassicmultimodule.biz.user.domain;

import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 用户领域对象：只表达业务关心的属性。
 */
@Getter
@RequiredArgsConstructor
public class User {

    /** 用户标识。 */
    private final UserId userId;

    /** 用户名。 */
    private final String name;

    /** 归属部门标识。 */
    private final OrgId orgId;
}
