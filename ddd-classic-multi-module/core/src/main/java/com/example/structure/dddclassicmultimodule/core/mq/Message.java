package com.example.structure.dddclassicmultimodule.core.mq;

import java.util.Map;
import lombok.Getter;

/**
 * 出站消息：主题、载荷与头信息。
 */
@Getter
public class Message {

    /** 消息主题。 */
    private final String topic;

    /** 消息载荷。 */
    private final String payload;

    /** 消息头。 */
    private final Map<String, String> headers;

    public Message(String topic, String payload, Map<String, String> headers) {
        this.topic = topic;
        this.payload = payload;
        this.headers = headers;
    }
}
