package com.example.mywechat.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        // 使用单机模式 设置地址 密码 和所用数据库
        config.useSingleServer().setAddress("redis://localhost:6379")
                .setPassword("root").setDatabase(1);
        return (Redisson) Redisson.create(config);
    }
}
