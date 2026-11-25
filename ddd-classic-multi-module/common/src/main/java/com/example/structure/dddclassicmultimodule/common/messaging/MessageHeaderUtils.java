package com.example.structure.dddclassicmultimodule.common.messaging;

import com.example.structure.dddclassicmultimodule.core.log.TraceContext;
import com.example.structure.dddclassicmultimodule.core.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息头工具：复制消息头并注入当前 traceId。
 */
public final class MessageHeaderUtils {

    /** 链路追踪消息头。 */
    private static final String TRACE_HEADER = "traceId";

    private MessageHeaderUtils() {
    }

    /**
     * 复制消息头并写入当前 traceId。
     *
     * @param source 原消息头，可为 null
     * @return 新的消息头
     */
    public static Map<String, String> withTraceId(Map<String, String> source) {
        Map<String, String> headers = new HashMap<>();
        if (source != null) {
            headers.putAll(source);
        }
        String traceId = TraceContext.get();
        if (!StringUtils.isBlank(traceId)) {
            headers.put(TRACE_HEADER, traceId);
        }
        return headers;
    }
}
