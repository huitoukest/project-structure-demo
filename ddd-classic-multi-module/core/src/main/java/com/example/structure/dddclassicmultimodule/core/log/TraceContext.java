package com.example.structure.dddclassicmultimodule.core.log;

/**
 * 链路追踪上下文：以 ThreadLocal 保存当前请求的 traceId，由基础设施层写入与清理。
 */
public final class TraceContext {

    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    private TraceContext() {
    }

    /**
     * 写入当前线程 traceId。
     *
     * @param traceId 链路标识
     */
    public static void set(String traceId) {
        TRACE_ID.set(traceId);
    }

    /**
     * 读取当前线程 traceId。
     *
     * @return 链路标识，未设置时为 null
     */
    public static String get() {
        return TRACE_ID.get();
    }

    /**
     * 清理当前线程 traceId，防止线程池复用串号。
     */
    public static void clear() {
        TRACE_ID.remove();
    }
}
