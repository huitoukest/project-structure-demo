package com.example.structure.dddclassicmultimodule.adapter.mq.consumer;

import com.example.structure.dddclassicmultimodule.core.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 领域事件消费替身：用 Spring 事件机制模拟 MQ 入站消费。
 */
@Slf4j
@Component
public class DomainEventConsumer {

    /**
     * 消费领域事件。
     *
     * @param event 领域事件
     */
    @EventListener
    public void onDomainEvent(DomainEvent event) {
        log.info("inbound event (MQ stand-in): {}", event);
    }
}
