package com.example.mywechat.handler;

import com.xxl.job.core.handler.IJobHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Date 29/11/2022 0029 下午 2:42
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@Slf4j
public class DemoGlueJobHandler extends IJobHandler {
    @Override
    public void execute() throws Exception {
        log.info("XXL-JOB HELLO WORLD ");
    }
}
