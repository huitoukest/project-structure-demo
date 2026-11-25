package com.example.structure.dddclassicmultimodule.common.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务模板：统一记录耗时，子类只实现业务动作。
 */
public abstract class AbstractJob {

    /** 子类可直接使用的日志对象。 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 执行任务并记录耗时。
     */
    public final void execute() {
        long start = System.currentTimeMillis();
        try {
            doExecute();
        } finally {
            log.info("job {} finished in {} ms", getClass().getSimpleName(), System.currentTimeMillis() - start);
        }
    }

    /**
     * 子类任务逻辑。
     */
    protected abstract void doExecute();
}
