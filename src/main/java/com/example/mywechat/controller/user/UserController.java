package com.example.mywechat.controller.user;


import cn.hutool.core.util.StrUtil;
import com.example.mywechat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;

/**
 * (User)表控制层
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    private Environment environment;


    private static final ThreadLocal<String> local = new ThreadLocal<>();

    @GetMapping("/getStr")
    public String getStr() {
        String st = local.get();
        if (StrUtil.isBlank(st)) {
            local.set("aa" + ThreadLocalRandom.current().nextInt(10));
        }
        log.info("{}的local值是{}", Thread.currentThread().getName(), st);
        return "server:" + environment.getProperty("local.server.port");
    }
}

