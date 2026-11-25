package com.example.structure.packagesplit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mvc-classic-package-split 启动类，位于根包，显式扫描全部子包。
 */
@SpringBootApplication(scanBasePackages = "com.example.structure.packagesplit")
public class PackageSplitApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageSplitApplication.class, args);
    }
}
