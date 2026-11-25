package com.example.structure.packagesplit.modules.user.controller;

import com.example.structure.packagesplit.common.api.ApiResponse;
import com.example.structure.packagesplit.modules.user.service.UserService;
import com.example.structure.packagesplit.modules.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户查询接口。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 按主键查询用户，响应含 orgId。
     */
    @GetMapping("/{id}")
    public ApiResponse<UserVO> findById(@PathVariable Long id) {
        return ApiResponse.ok(UserVO.from(userService.findById(id)));
    }
}
