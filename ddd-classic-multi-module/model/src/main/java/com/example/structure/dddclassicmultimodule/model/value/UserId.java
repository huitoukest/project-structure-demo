package com.example.structure.dddclassicmultimodule.model.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 用户标识值对象：不可变，避免 Long 裸值在跨上下文传递时语义丢失。
 */
@Getter
@EqualsAndHashCode
public final class UserId {

    private final Long value;

    private UserId(Long value) {
        this.value = value;
    }

    /**
     * 由用户主键构造值对象。
     *
     * @param value 用户主键
     * @return 用户标识
     */
    public static UserId of(Long value) {
        return new UserId(value);
    }
}
