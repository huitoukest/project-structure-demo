package com.example.structure.dddclassicmultimodule.adapter.web.dto;

import lombok.Data;

/**
 * 问候接口请求参数：name 缺省或空白时由控制器回退为 World。
 */
@Data
public class HelloQuery {

    /** 问候对象名。 */
    private String name;
}
