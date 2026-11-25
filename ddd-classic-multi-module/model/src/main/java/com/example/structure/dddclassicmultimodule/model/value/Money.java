package com.example.structure.dddclassicmultimodule.model.value;

import com.example.structure.dddclassicmultimodule.model.enums.Currency;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 金额值对象：整数金额 + 币种，示例只使用人民币。
 */
@Getter
@EqualsAndHashCode
public final class Money {

    private final long amount;
    private final Currency currency;

    private Money(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * 构造人民币金额。
     *
     * @param amount 金额（元）
     * @return 金额值对象
     */
    public static Money ofCny(long amount) {
        return new Money(amount, Currency.CNY);
    }
}
