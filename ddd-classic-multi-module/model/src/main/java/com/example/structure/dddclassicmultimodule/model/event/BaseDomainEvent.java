package com.example.structure.dddclassicmultimodule.model.event;

import com.example.structure.dddclassicmultimodule.core.event.DomainEvent;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

/**
 * 领域事件共享基类：提供事件标识与发生时间；具体业务事件放在业务上下文模块。
 */
@Getter
public abstract class BaseDomainEvent implements DomainEvent {

    private final String eventId = UUID.randomUUID().toString();

    private final Instant occurredAt = Instant.now();
}
