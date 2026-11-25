package com.example.structure.multibizmodule.start.controller;

import com.example.structure.multibizmodule.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统级连通性接口，不属于任何业务域。
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloController {

    /** name 缺省或空白时的默认问候对象。 */
    private static final String DEFAULT_NAME = "World";

    /**
     * 问候接口，name 缺省或空白时返回 World。
     *
     * @param name 问候对象
     * @return 统一响应
     */
    @GetMapping("/hello")
    public ApiResponse<String> hello(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        String target = name == null || name.isBlank() ? DEFAULT_NAME : name;
        return ApiResponse.ok("Hello, " + target);
    }
}
