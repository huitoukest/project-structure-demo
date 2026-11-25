package com.example.structure.dddclassicmultimodule.dal.api.salary.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 薪资持久化对象：以用户主键为业务键。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryPO {

    /** 用户主键。 */
    private Long userId;

    /** 薪资（元）。 */
    private Long salary;
}
