package com.example.mywechat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mywechat.dao.UserDao;
import com.example.mywechat.entity.User;
import com.example.mywechat.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

