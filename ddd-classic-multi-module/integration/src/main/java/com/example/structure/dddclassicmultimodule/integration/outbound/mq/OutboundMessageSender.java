package com.example.structure.dddclassicmultimodule.integration.outbound.mq;

import com.example.structure.dddclassicmultimodule.core.log.TraceContext;
import com.example.structure.dddclassicmultimodule.core.mq.Message;
import com.example.structure.dddclassicmultimodule.core.mq.MessageSender;
import com.example.structure.dddclassicmultimodule.core.result.Result;
import com.example.structure.dddclassicmultimodule.core.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

/**
 * 出站消息防腐层：把内核消息端口适配到“外部 MQ”，示例只打印日志。
 *
 * 使用 spring-jcl（随 spring-context 传递）提供的日志门面，避免给本模块引入 slf4j。
 */
@CommonsLog
@Component
public class OutboundMessageSender implements MessageSender {

    /** 链路追踪消息头。 */
    private static final String TRACE_HEADER = "traceId";

    @Override
    public Result<Void> send(Message message) {
        Map<String, String> headers = new HashMap<>();
        if (message.getHeaders() != null) {
            headers.putAll(message.getHeaders());
        }
        String traceId = TraceContext.get();
        if (!StringUtils.isBlank(traceId)) {
            headers.put(TRACE_HEADER, traceId);
        }
        log.info("outbound -> external MQ topic:" + message.getTopic() + ", headers:" + headers);
        return Result.ok(null);
    }
}
