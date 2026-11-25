package com.example.structure.multibizmodule.user.biz.controller;

import com.example.structure.multibizmodule.common.api.ApiResponse;
import com.example.structure.multibizmodule.user.api.UserApi;
import com.example.structure.multibizmodule.user.biz.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户查询接口：依赖 user-api 契约而非具体实现。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApi userApi;

    /**
     * 按主键查询用户。
     *
     * @param id 用户主键
     * @return 统一响应，data 含 orgId
     */
    @GetMapping("/{id}")
    public ApiResponse<UserVO> findById(@PathVariable Long id) {
        return ApiResponse.ok(UserVO.from(userApi.getById(id)));
    }
}
