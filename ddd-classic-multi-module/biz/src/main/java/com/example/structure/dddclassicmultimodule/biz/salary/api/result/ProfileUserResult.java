package com.example.structure.dddclassicmultimodule.biz.salary.api.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案用户结果：按用例裁剪为 id/name。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserResult {

    /** 用户主键。 */
    private Long id;

    /** 用户名。 */
    private String name;
}
