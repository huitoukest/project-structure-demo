package com.example.structure.multibizmodule.org.biz.controller;

import com.example.structure.multibizmodule.common.api.ApiResponse;
import com.example.structure.multibizmodule.org.api.OrgApi;
import com.example.structure.multibizmodule.org.biz.vo.OrgTreeVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门查询接口：依赖 org-api 契约而非具体实现。
 */
@RestController
@RequestMapping("/api/orgs")
@RequiredArgsConstructor
public class OrgController {

    private final OrgApi orgApi;

    /**
     * 部门树，本示例为两个平级根部门。
     *
     * @return 统一响应，data 为部门树
     */
    @GetMapping("/tree")
    public ApiResponse<List<OrgTreeVO>> tree() {
        List<OrgTreeVO> tree = orgApi.tree().stream().map(OrgTreeVO::from).toList();
        return ApiResponse.ok(tree);
    }
}
