package com.example.mywechat;

import com.example.mywechat.handler.DemoGlueJobHandler;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWechatApplication.class, args);
        XxlJobSpringExecutor.registJobHandler("wbHandler", new DemoGlueJobHandler());
    }

}
