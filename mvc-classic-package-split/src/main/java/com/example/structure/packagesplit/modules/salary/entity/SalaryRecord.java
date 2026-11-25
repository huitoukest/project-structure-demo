package com.example.structure.packagesplit.modules.salary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 薪资记录行对象，金额单位为元。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRecord {

    /** 用户主键。 */
    private Long userId;

    /** 月薪（元）。 */
    private long amount;
}
