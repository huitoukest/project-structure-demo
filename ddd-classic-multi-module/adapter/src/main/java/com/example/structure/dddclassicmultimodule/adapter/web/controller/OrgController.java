package com.example.structure.dddclassicmultimodule.adapter.web.controller;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.org.OrgQueryApi;
import com.example.structure.dddclassicmultimodule.api.org.dto.OrgNodeDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门接口：只依赖对外契约。
 */
@RestController
@RequestMapping("/api/orgs")
@RequiredArgsConstructor
public class OrgController {

    private final OrgQueryApi orgQueryApi;

    /**
     * 查询部门树。
     *
     * @return 统一响应
     */
    @GetMapping("/tree")
    public ApiResponse<List<OrgNodeDTO>> tree() {
        return orgQueryApi.tree();
    }
}
