package com.example.structure.dddclassicmultimodule.core.event;

/**
 * 领域事件发布端口：业务内核只依赖该抽象，具体实现由基础设施提供。
 */
public interface DomainEventPublisher {

    /**
     * 发布领域事件。
     *
     * @param event 领域事件
     */
    void publish(DomainEvent event);
}
