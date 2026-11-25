package com.example.structure.packagesplit.modules.org.controller;

import com.example.structure.packagesplit.common.api.ApiResponse;
import com.example.structure.packagesplit.modules.org.service.OrgService;
import com.example.structure.packagesplit.modules.org.vo.OrgTreeVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门查询接口。
 */
@RestController
@RequestMapping("/api/orgs")
@RequiredArgsConstructor
public class OrgController {

    private final OrgService orgService;

    /**
     * 部门树，本示例为两个平级根部门。
     */
    @GetMapping("/tree")
    public ApiResponse<List<OrgTreeVO>> tree() {
        List<OrgTreeVO> tree = orgService.tree().stream().map(OrgTreeVO::from).toList();
        return ApiResponse.ok(tree);
    }
}
