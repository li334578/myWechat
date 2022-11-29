package com.example.mywechat.config;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Date 29/11/2022 0029 下午 2:40
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@Configuration
@Slf4j
public class XxlJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    @XxlJob("wbHandlerBean")
    public void demoJobHandler() {
        log.info("hello world console");
        XxlJobHelper.log("hello world 2");
        String param = XxlJobHelper.getJobParam();
        log.info("我是获取到的参数{}", param);
        int i = ThreadLocalRandom.current().nextInt(10);
        if (i > 5) {
            XxlJobHelper.handleSuccess("成功了真好:" + i);
        } else {
            XxlJobHelper.handleFail("失败了真不好:" + i);
        }

    }
}
