package com.example.structure.multibizmodule.salary.biz.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案中的用户视图：仅 id/name，不暴露 orgId。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserVO {

    /** 用户主键。 */
    private Long id;

    /** 姓名。 */
    private String name;
}
