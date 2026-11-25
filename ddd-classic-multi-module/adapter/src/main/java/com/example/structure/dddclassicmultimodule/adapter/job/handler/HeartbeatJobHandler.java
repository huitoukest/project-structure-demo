package com.example.structure.dddclassicmultimodule.adapter.job.handler;

import com.example.structure.dddclassicmultimodule.common.scheduling.AbstractJob;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 心跳任务示例：默认关闭，demo.job.enabled=true 时随 JobConfiguration 一起启用。
 */
@Component
@ConditionalOnProperty(name = "demo.job.enabled", havingValue = "true")
public class HeartbeatJobHandler extends AbstractJob {

    /**
     * 定时触发模板方法。
     */
    @Scheduled(fixedDelay = 30_000L)
    public void heartbeat() {
        execute();
    }

    @Override
    protected void doExecute() {
        log.info("heartbeat: ddd-classic-multi-module is alive");
    }
}
