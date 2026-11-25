package com.example.structure.dddclassicmultimodule.biz.salary.api.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 薪资档案查询命令。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSalaryProfileCmd {

    /** 用户主键。 */
    private Long userId;
}
