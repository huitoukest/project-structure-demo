package com.example.structure.dddclassicmultimodule.biz.shared;

/**
 * 业务异常明细文案：集中维护，保证 404/500 明细与契约一致。
 */
public final class BizMessages {

    private BizMessages() {
    }

    /**
     * 用户不存在明细。
     *
     * @param userId 用户主键
     * @return 明细文案
     */
    public static String userNotFound(Long userId) {
        return "用户不存在: " + userId;
    }

    /**
     * 部门不存在明细。
     *
     * @param orgId 部门主键
     * @return 明细文案
     */
    public static String orgNotFound(Long orgId) {
        return "部门不存在: " + orgId;
    }

    /**
     * 薪资记录不存在明细。
     *
     * @param userId 用户主键
     * @return 明细文案
     */
    public static String salaryNotFound(Long userId) {
        return "薪资记录不存在: " + userId;
    }

    /**
     * 部门树结构非法明细。
     *
     * @param parentId 缺失的父部门主键
     * @return 明细文案
     */
    public static String orgTreeInvalid(Long parentId) {
        return "部门树结构非法: 父部门不存在 " + parentId;
    }
}
