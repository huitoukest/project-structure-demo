package com.example.structure.dddclassicmultimodule.adapter.web.controller;

import com.example.structure.dddclassicmultimodule.adapter.web.dto.HelloQuery;
import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用接口：连通性示例。
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /** name 缺省或空白时的默认问候对象。 */
    private static final String DEFAULT_NAME = "World";

    /**
     * 问候接口，name 缺省或空白时返回 World。
     *
     * @param query 请求参数
     * @return 统一响应
     */
    @GetMapping("/hello")
    public ApiResponse<String> hello(@ModelAttribute HelloQuery query) {
        String name = query.getName();
        String target = name == null || name.isBlank() ? DEFAULT_NAME : name;
        return ApiResponse.ok("Hello, " + target);
    }
}
