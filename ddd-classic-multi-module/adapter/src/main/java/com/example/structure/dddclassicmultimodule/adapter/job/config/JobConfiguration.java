package com.example.structure.dddclassicmultimodule.adapter.job.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务装配：默认关闭，demo.job.enabled=true 时开启调度。
 */
@Configuration(proxyBeanMethods = false)
@EnableScheduling
@ConditionalOnProperty(name = "demo.job.enabled", havingValue = "true")
public class JobConfiguration {
}
