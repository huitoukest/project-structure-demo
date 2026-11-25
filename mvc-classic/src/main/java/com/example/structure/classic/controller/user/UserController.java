package com.example.structure.classic.controller.user;

import com.example.structure.classic.common.api.ApiResponse;
import com.example.structure.classic.service.UserService;
import com.example.structure.classic.vo.UserVO;
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
