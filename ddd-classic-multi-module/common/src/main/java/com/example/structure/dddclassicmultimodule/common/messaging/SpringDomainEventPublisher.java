package com.example.structure.dddclassicmultimodule.common.messaging;

import com.example.structure.dddclassicmultimodule.core.event.DomainEvent;
import com.example.structure.dddclassicmultimodule.core.event.DomainEventPublisher;
import com.example.structure.dddclassicmultimodule.core.mq.Message;
import com.example.structure.dddclassicmultimodule.core.mq.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 领域事件发布实现：进程内事件交给 Spring 事件总线，同时通过消息端口投递一份（MQ 替身）。
 */
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final MessageSender messageSender;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
        messageSender.send(new Message("domain-event", event.toString(), MessageHeaderUtils.withTraceId(null)));
    }
}
