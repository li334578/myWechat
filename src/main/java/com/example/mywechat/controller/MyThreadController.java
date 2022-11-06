package com.example.mywechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName MyThreadController
 * @Description TODO
 * @Author Wenbo Li
 * @Date 6/11/2022 下午 1:08
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/thread")
public class MyThreadController {


    @GetMapping("/getThreadInfo")
    public String getThreadInfo() {
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : Thread.getAllStackTraces().entrySet()) {
            Thread key = threadEntry.getKey();
            StackTraceElement[] value = threadEntry.getValue();
            if (Objects.equals(key, Thread.currentThread())) {
                continue;
            }
            log.info("线程:{}", key.getName());
            for (StackTraceElement stackTraceElement : value) {
                log.info("\t{}\n", stackTraceElement);
            }
        }
        return "Success";

    }
}
