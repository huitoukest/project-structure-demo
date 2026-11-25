package com.example.structure.dddclassicmultimodule.core.mq;

import com.example.structure.dddclassicmultimodule.core.result.Result;

/**
 * 出站消息发送端口：业务内核只依赖该抽象，真实项目由 MQ 客户端实现。
 */
public interface MessageSender {

    /**
     * 发送消息。
     *
     * @param message 出站消息
     * @return 发送结果
     */
    Result<Void> send(Message message);
}
