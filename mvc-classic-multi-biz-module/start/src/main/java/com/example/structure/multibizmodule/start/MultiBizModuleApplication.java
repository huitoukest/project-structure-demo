package com.example.structure.multibizmodule.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mvc-classic-multi-biz-module 启动类：位于 start 模块根包，显式扫描全部子模块包。
 */
@SpringBootApplication(scanBasePackages = "com.example.structure.multibizmodule")
public class MultiBizModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiBizModuleApplication.class, args);
    }
}
