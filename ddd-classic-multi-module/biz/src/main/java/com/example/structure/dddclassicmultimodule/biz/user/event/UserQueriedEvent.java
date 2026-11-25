package com.example.structure.dddclassicmultimodule.biz.user.event;

import com.example.structure.dddclassicmultimodule.model.event.BaseDomainEvent;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.Getter;

/**
 * 用户被查询事件：读模型也发事件，用于演示事件发布链路。
 */
@Getter
public class UserQueriedEvent extends BaseDomainEvent {

    /** 用户标识。 */
    private final UserId userId;

    public UserQueriedEvent(UserId userId) {
        this.userId = userId;
    }
}
