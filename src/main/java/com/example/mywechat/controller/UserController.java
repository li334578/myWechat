package com.example.mywechat.controller;


import com.example.mywechat.entity.User;
import com.example.mywechat.service.UserService;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

}

