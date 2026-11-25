package com.example.structure.dddclassicmultimodule.core.result;

import lombok.Getter;

/**
 * 内核通用结果：用于出站消息等内部返回值，不替代对外 HTTP 契约 {@code ApiResponse}。
 *
 * @param <T> 数据类型
 */
@Getter
public class Result<T> {

    /** 是否成功。 */
    private final boolean success;

    /** 提示信息。 */
    private final String message;

    /** 数据。 */
    private final T data;

    private Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功结果。
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 成功结果
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(true, "ok", data);
    }

    /**
     * 失败结果。
     *
     * @param message 失败原因
     * @param <T>     数据类型
     * @return 失败结果
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }
}
