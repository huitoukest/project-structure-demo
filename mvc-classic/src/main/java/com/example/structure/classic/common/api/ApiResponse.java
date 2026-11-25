package com.example.structure.classic.common.api;

import com.example.structure.classic.common.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一 HTTP 响应包装：成功 code=0、message="ok"；失败携带业务错误码。
 *
 * @param <T> 业务数据类型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    /** 成功业务码：固定为 0。 */
    private static final int SUCCESS_CODE = 0;

    /** 成功提示信息。 */
    private static final String SUCCESS_MESSAGE = "ok";

    /** 业务码。 */
    private int code;

    /** 提示信息。 */
    private String message;

    /** 业务数据。 */
    private T data;

    /**
     * 成功响应。
     *
     * @param data 业务数据
     * @param <T>  业务数据类型
     * @return 成功包装
     */
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    /**
     * 失败响应：明细为空时回退到错误码默认提示。
     *
     * @param errorCode 错误码
     * @param detail    明细信息，可为 null
     * @param <T>       业务数据类型
     * @return 失败包装
     */
    public static <T> ApiResponse<T> fail(ErrorCode errorCode, String detail) {
        return new ApiResponse<>(errorCode.code(), resolveMessage(errorCode, detail), null);
    }

    /**
     * 明细为空时回退到错误码默认提示。
     *
     * @param errorCode 错误码
     * @param detail    明细信息，可为 null
     * @return 最终提示信息
     */
    private static String resolveMessage(ErrorCode errorCode, String detail) {
        return detail == null || detail.isBlank() ? errorCode.message() : detail;
    }
}
