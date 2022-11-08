package com.example.mywechat.controller.user.vo;

import com.example.mywechat.entity.User;
import lombok.Data;

/**
 * @Date 8/11/2022 0008 上午 11:39
 * @Description TODO
 * @Version 1.0.0
 * @Author liwenbo
 */
@Data
public class LoginResultBean {

    /**
     * 用户信息
     */
    private User user;

    /**
     * token值
     */
    private String satoken;
}
