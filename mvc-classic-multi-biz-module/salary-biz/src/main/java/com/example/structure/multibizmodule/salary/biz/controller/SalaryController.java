package com.example.structure.multibizmodule.salary.biz.controller;

import com.example.structure.multibizmodule.common.api.ApiResponse;
import com.example.structure.multibizmodule.salary.api.SalaryApi;
import com.example.structure.multibizmodule.salary.biz.vo.UserProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户档案接口：依赖 salary-api 契约而非具体实现。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryApi salaryApi;

    /**
     * 用户档案：user 仅含 id/name，org 含 id/name，salary 为月薪（元）。
     *
     * @param id 用户主键
     * @return 统一响应
     */
    @GetMapping("/{id}/profile")
    public ApiResponse<UserProfileVO> profile(@PathVariable Long id) {
        return ApiResponse.ok(UserProfileVO.from(salaryApi.getProfile(id)));
    }
}
