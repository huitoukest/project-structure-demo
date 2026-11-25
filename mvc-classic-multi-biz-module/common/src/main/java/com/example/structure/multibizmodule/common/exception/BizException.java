package com.example.structure.multibizmodule.common.exception;

import com.example.structure.multibizmodule.common.constant.ErrorCode;
import lombok.Getter;

/**
 * 业务异常，携带错误码与明细信息，由 start 模块的全局异常处理器转换为统一响应。
 */
@Getter
public class BizException extends RuntimeException {

    /** 业务错误码。 */
    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode, String detail) {
        super(detail);
        this.errorCode = errorCode;
    }
}
