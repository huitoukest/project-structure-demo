package com.example.structure.dddclassicmultimodule.biz.org.api.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门按主键查询命令。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrgCmd {

    /** 部门主键。 */
    private Long id;
}
