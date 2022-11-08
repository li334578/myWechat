package com.example.mywechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mywechat.entity.User;

import java.util.Optional;

/**
 * (User)表服务接口
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名以及原始密码查询用户
     *
     * @param username    用户名
     * @param rawPassword 原始密码
     * @return 符合条件的用户数据
     */
    Optional<User> getByUsernameAndRawPassword(String username, String rawPassword);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 符合条件的用户数据
     */
    Optional<User> getByUserName(String username);
}

