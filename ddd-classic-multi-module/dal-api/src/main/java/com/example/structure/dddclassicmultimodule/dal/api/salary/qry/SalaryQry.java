package com.example.structure.dddclassicmultimodule.dal.api.salary.qry;

import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 薪资查询条件。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryQry {

    /** 用户标识。 */
    private UserId userId;
}
