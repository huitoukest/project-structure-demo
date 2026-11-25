package com.example.structure.multibizmodule.salary.biz.dao;

import com.example.structure.multibizmodule.salary.biz.entity.SalaryRecord;

/**
 * 薪资存储访问接口。
 */
public interface SalaryDao {

    /**
     * 按用户主键查询薪资记录。
     *
     * @param userId 用户主键
     * @return 薪资行对象，未命中返回 null
     */
    SalaryRecord findByUserId(Long userId);
}
