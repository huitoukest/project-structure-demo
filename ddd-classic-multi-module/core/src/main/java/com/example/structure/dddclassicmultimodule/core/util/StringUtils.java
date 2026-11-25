package com.example.structure.dddclassicmultimodule.core.util;

/**
 * 字符串工具，避免内核模块依赖第三方工具库。
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * 判断字符串为 null 或仅含空白字符。
     *
     * @param value 待判断字符串
     * @return true 表示空白
     */
    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
