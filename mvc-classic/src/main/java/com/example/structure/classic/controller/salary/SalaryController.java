package com.example.structure.classic.controller.salary;

import com.example.structure.classic.common.api.ApiResponse;
import com.example.structure.classic.service.SalaryService;
import com.example.structure.classic.vo.UserProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户档案（部门 + 薪资）接口，按用例归属放在 salary 控制器。
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    /**
     * 用户档案：user 仅含 id/name，org 含 id/name，salary 为月薪（元）。
     */
    @GetMapping("/{id}/profile")
    public ApiResponse<UserProfileVO> profile(@PathVariable Long id) {
        return ApiResponse.ok(UserProfileVO.from(salaryService.profile(id)));
    }
}
