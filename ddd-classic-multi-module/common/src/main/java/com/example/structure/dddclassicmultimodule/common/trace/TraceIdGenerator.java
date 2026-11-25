package com.example.structure.dddclassicmultimodule.common.trace;

import java.util.UUID;

/**
 * traceId 生成器：UUID 去掉分隔符。
 */
public final class TraceIdGenerator {

    private TraceIdGenerator() {
    }

    /**
     * 生成新的 traceId。
     *
     * @return 32 位链路标识
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
