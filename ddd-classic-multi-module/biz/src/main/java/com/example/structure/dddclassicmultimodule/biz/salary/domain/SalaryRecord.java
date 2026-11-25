package com.example.structure.dddclassicmultimodule.biz.salary.domain;

import com.example.structure.dddclassicmultimodule.model.value.Money;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 薪资记录领域对象。
 */
@Getter
@RequiredArgsConstructor
public class SalaryRecord {

    /** 用户标识。 */
    private final UserId userId;

    /** 薪资金额。 */
    private final Money salary;
}
