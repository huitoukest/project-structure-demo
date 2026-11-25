package com.example.structure.dddclassicmultimodule.biz.salary.api;

import com.example.structure.dddclassicmultimodule.biz.salary.api.cmd.GetSalaryProfileCmd;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.SalaryProfileResult;

/**
 * 薪资上下文入口。
 */
public interface SalaryQueryHandler {

    SalaryProfileResult getProfile(GetSalaryProfileCmd cmd);
}
