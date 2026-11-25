package com.example.structure.dddclassicmultimodule.common.web;

import com.example.structure.dddclassicmultimodule.common.trace.TraceIdGenerator;
import com.example.structure.dddclassicmultimodule.core.log.TraceContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 链路追踪过滤器：读取或生成 X-Trace-Id，写入 TraceContext/MDC/响应头，请求结束清理。
 */
public class TraceIdFilter extends OncePerRequestFilter {

    /** 链路追踪请求头。 */
    private static final String TRACE_HEADER = "X-Trace-Id";

    /** MDC 键。 */
    private static final String MDC_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String traceId = request.getHeader(TRACE_HEADER);
        if (traceId == null || traceId.isBlank()) {
            traceId = TraceIdGenerator.generate();
        }
        TraceContext.set(traceId);
        MDC.put(MDC_KEY, traceId);
        response.setHeader(TRACE_HEADER, traceId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            TraceContext.clear();
            MDC.remove(MDC_KEY);
        }
    }
}
