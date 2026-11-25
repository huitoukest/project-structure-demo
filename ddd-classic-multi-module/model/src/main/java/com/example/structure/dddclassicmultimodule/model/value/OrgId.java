package com.example.structure.dddclassicmultimodule.model.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 部门标识值对象：不可变，null 表示无归属部门。
 */
@Getter
@EqualsAndHashCode
public final class OrgId {

    private final Long value;

    private OrgId(Long value) {
        this.value = value;
    }

    /**
     * 由部门主键构造值对象。
     *
     * @param value 部门主键，可为 null
     * @return 部门标识
     */
    public static OrgId of(Long value) {
        return new OrgId(value);
    }
}
