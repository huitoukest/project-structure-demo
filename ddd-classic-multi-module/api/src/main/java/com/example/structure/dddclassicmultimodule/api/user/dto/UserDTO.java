package com.example.structure.dddclassicmultimodule.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户契约 DTO：{@code GET /api/users/{id}} 的响应数据，含归属部门主键。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /** 用户主键。 */
    private Long id;

    /** 用户名。 */
    private String name;

    /** 归属部门主键。 */
    private Long orgId;
}
