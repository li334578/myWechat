package com.example.mywechat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mywechat.dao.UserDao;
import com.example.mywechat.entity.User;
import com.example.mywechat.service.UserService;
import com.example.mywechat.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * (User)表服务实现类
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 根据用户名以及原始密码查询用户
     *
     * @param username    用户名
     * @param rawPassword 原始密码
     * @return 用户数据
     */
    @Override
    public Optional<User> getByUsernameAndRawPassword(String username, String rawPassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        // 密码加密
        queryWrapper.eq("password", SecurityUtil.encryptionPassword(username, rawPassword));
        return Optional.ofNullable(userDao.selectOne(queryWrapper));
    }

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 符合条件的用户数据
     */
    @Override
    public Optional<User> getByUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return Optional.ofNullable(userDao.selectOne(queryWrapper));
    }
}

