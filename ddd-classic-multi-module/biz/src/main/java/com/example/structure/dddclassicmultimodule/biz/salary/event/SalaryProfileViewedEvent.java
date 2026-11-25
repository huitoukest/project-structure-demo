package com.example.structure.dddclassicmultimodule.biz.salary.event;

import com.example.structure.dddclassicmultimodule.model.event.BaseDomainEvent;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.Getter;

/**
 * 薪资档案被查看事件。
 */
@Getter
public class SalaryProfileViewedEvent extends BaseDomainEvent {

    /** 用户标识。 */
    private final UserId userId;

    /** 部门标识。 */
    private final OrgId orgId;

    public SalaryProfileViewedEvent(UserId userId, OrgId orgId) {
        this.userId = userId;
        this.orgId = orgId;
    }
}
