package com.example.structure.classic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mvc-classic 启动类，位于根包，显式扫描全部子包。
 */
@SpringBootApplication(scanBasePackages = "com.example.structure.classic")
public class MvcClassicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcClassicApplication.class, args);
    }
}
