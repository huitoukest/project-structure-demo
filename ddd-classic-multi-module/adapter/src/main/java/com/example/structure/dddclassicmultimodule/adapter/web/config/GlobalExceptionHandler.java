package com.example.structure.dddclassicmultimodule.adapter.web.config;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.core.constant.ErrorCode;
import com.example.structure.dddclassicmultimodule.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理：业务异常按错误码映射 HTTP 状态，路径参数类型错误返回 400，其余 500 兜底。
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常：HTTP 状态由错误码决定，响应体仍为统一结构。
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ApiResponse<Void>> handleBizException(BizException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.httpStatus())
            .body(ApiResponse.fail(errorCode, ex.getMessage()));
    }

    /**
     * 路径/查询参数类型错误，例如 /api/users/abc。
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorCode errorCode = ErrorCode.PARAM_TYPE_MISMATCH;
        String detail = errorCode.message() + ": " + ex.getName() + "=" + ex.getValue();
        return ResponseEntity.status(errorCode.httpStatus())
            .body(ApiResponse.fail(errorCode, detail));
    }

    /**
     * 兜底异常，记录日志后返回 500/50000。
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        log.error("未处理异常: {}", ex.getMessage(), ex);
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        return ResponseEntity.status(errorCode.httpStatus())
            .body(ApiResponse.fail(errorCode, null));
    }
}
