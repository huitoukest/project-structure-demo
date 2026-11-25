package com.example.structure.dddclassicmultimodule.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ddd-classic-multi-module 启动类：唯一可执行入口，显式扫描 adapter / dal / integration / biz 包。
 *
 * common 的自动装配通过 META-INF/spring 导入；biz 应用服务由 @Service 自动装配。
 */
@SpringBootApplication(scanBasePackages = {
    "com.example.structure.dddclassicmultimodule.adapter",
    "com.example.structure.dddclassicmultimodule.dal",
    "com.example.structure.dddclassicmultimodule.integration",
    "com.example.structure.dddclassicmultimodule.biz"
})
public class DddClassicApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddClassicApplication.class, args);
    }
}
