package com.example.structure.dddclassicmultimodule.common.messaging;

import com.example.structure.dddclassicmultimodule.core.mq.Message;
import com.example.structure.dddclassicmultimodule.core.mq.MessageSender;
import com.example.structure.dddclassicmultimodule.core.result.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息发送兜底实现：只打印日志，不连接真实 MQ；由自动装配在缺少其他实现时启用。
 */
@Slf4j
public class LoggingMessageSender implements MessageSender {

    @Override
    public Result<Void> send(Message message) {
        log.info("message send (fallback) topic={}, headers={}", message.getTopic(), message.getHeaders());
        return Result.ok(null);
    }
}
