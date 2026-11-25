package com.example.structure.dddclassicmultimodule.common.config;

import com.example.structure.dddclassicmultimodule.common.messaging.LoggingMessageSender;
import com.example.structure.dddclassicmultimodule.common.messaging.SpringDomainEventPublisher;
import com.example.structure.dddclassicmultimodule.core.event.DomainEventPublisher;
import com.example.structure.dddclassicmultimodule.core.mq.MessageSender;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

/**
 * 消息与事件自动装配：消息端口、事件发布端口均提供兜底实现，存在外部实现时退让。
 */
@AutoConfiguration
public class MessagingAutoConfiguration {

    /**
     * 兜底消息发送实现：仅当没有其他 MessageSender 时生效。
     *
     * @return 日志实现
     */
    @Bean
    @ConditionalOnMissingBean(MessageSender.class)
    public MessageSender loggingMessageSender() {
        return new LoggingMessageSender();
    }

    /**
     * 领域事件发布实现。
     *
     * @param applicationEventPublisher Spring 事件总线
     * @param messageSender            消息发送端口
     * @return 事件发布实现
     */
    @Bean
    @ConditionalOnMissingBean(DomainEventPublisher.class)
    public DomainEventPublisher springDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher,
                                                           MessageSender messageSender) {
        return new SpringDomainEventPublisher(applicationEventPublisher, messageSender);
    }
}
