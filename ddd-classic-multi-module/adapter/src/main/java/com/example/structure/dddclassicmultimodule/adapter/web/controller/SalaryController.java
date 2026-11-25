package com.example.structure.dddclassicmultimodule.adapter.web.controller;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.salary.SalaryQueryApi;
import com.example.structure.dddclassicmultimodule.api.salary.dto.SalaryProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 薪资档案接口：只依赖对外契约。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryQueryApi salaryQueryApi;

    /**
     * 查询用户档案。
     *
     * @param id 用户主键
     * @return 统一响应
     */
    @GetMapping("/{id}/profile")
    public ApiResponse<SalaryProfileDTO> getProfile(@PathVariable Long id) {
        return salaryQueryApi.getProfile(id);
    }
}
