package com.example.structure.dddclassicmultimodule.dal.api.salary;

import com.example.structure.dddclassicmultimodule.dal.api.salary.po.SalaryPO;
import com.example.structure.dddclassicmultimodule.dal.api.salary.qry.SalaryQry;
import java.util.Optional;

/**
 * 薪资仓储接口。
 */
public interface SalaryRepository {

    /**
     * 按用户查询薪资记录。
     *
     * @param qry 查询条件
     * @return 薪资持久化对象
     */
    Optional<SalaryPO> findByUserId(SalaryQry qry);
}
