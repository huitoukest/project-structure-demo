package com.example.structure.dddclassicmultimodule.adapter.web.controller;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.user.UserQueryApi;
import com.example.structure.dddclassicmultimodule.api.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口：只依赖对外契约，不直接接触业务实现或数据访问实现。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserQueryApi userQueryApi;

    /**
     * 按主键查询用户。
     *
     * @param id 用户主键
     * @return 统一响应
     */
    @GetMapping("/{id}")
    public ApiResponse<UserDTO> getById(@PathVariable Long id) {
        return userQueryApi.getById(id);
    }
}
