package com.example.structure.classic.common.constant;

/**
 * 业务错误码：业务码 + HTTP 状态 + 默认提示。
 */
public enum ErrorCode {

    /** 参数类型错误。 */
    PARAM_TYPE_MISMATCH(40001, 400, "参数类型错误"),
    /** 用户不存在。 */
    USER_NOT_FOUND(40401, 404, "用户不存在"),
    /** 部门不存在。 */
    ORG_NOT_FOUND(40402, 404, "部门不存在"),
    /** 薪资记录不存在。 */
    SALARY_NOT_FOUND(40403, 404, "薪资记录不存在"),
    /** 部门树结构非法。 */
    ORG_TREE_INVALID(50001, 500, "部门树结构非法"),
    /** 内部错误。 */
    INTERNAL_ERROR(50000, 500, "内部错误");

    private final int code;
    private final int httpStatus;
    private final String message;

    ErrorCode(int code, int httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public int httpStatus() {
        return httpStatus;
    }

    public String message() {
        return message;
    }
}
