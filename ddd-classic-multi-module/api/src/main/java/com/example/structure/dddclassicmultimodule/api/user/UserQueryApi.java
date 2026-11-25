package com.example.structure.dddclassicmultimodule.api.user;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.user.dto.UserDTO;

/**
 * 用户查询契约：由适配层的 facade 实现，对外暴露给 HTTP 调用方。
 */
public interface UserQueryApi {

    /**
     * 按主键查询用户（含归属部门）。
     *
     * @param id 用户主键
     * @return 统一响应
     */
    ApiResponse<UserDTO> getById(Long id);
}
