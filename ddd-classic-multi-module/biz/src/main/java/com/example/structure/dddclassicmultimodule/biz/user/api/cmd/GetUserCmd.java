package com.example.structure.dddclassicmultimodule.biz.user.api.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询命令。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserCmd {

    /** 用户主键。 */
    private Long id;
}
