package com.example.mywechat.controller.user;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ReUtil;
import com.example.mywechat.controller.user.vo.LoginResultBean;
import com.example.mywechat.dto.ResultBean;
import com.example.mywechat.entity.User;
import com.example.mywechat.service.UserService;
import com.example.mywechat.util.SecurityUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @Date 8/11/2022 0008 上午 11:22
 * @Description 认证相关接口
 * @Version 1.0.0
 * @Author liwenbo
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    private static final String regexEmail = ".+@.+\\.com";

    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public ResultBean<LoginResultBean> login(@RequestParam("username") String username,
                                             @RequestParam("rawPassword") String rawPassword) {
        LoginResultBean loginResultBean = new LoginResultBean();
        Optional<User> userOptional = userService.getByUsernameAndRawPassword(username, rawPassword);
        userOptional.ifPresent(user -> {
            // 将密码置空
            user.setPassword(null);
            StpUtil.login(user.getId());
            StpUtil.getSession().set("user", user);
            loginResultBean.setUser(user);
            loginResultBean.setSatoken(StpUtil.getTokenValue());
        });
        // 将user和token信息返回
        return userOptional.isPresent() ? ResultBean.success(loginResultBean) : ResultBean.error("用户名不存在或密码不正确");
    }

    @PostMapping("/register")
    public ResultBean<String> register(@RequestBody User user) {
        if (Objects.isNull(user.getUsername())
                || Objects.isNull(user.getPassword())
                || Objects.isNull(user.getPhone())
                || Objects.isNull(user.getEmail())) {
            return ResultBean.error("必填数据不能为null");
        }
        // 用户名密码不能一致
        if (Objects.equals(user.getUsername(), user.getPassword())) {
            return ResultBean.error("用户名密码不能一致");
        }
        // 邮箱不能为空且符合正则 xxx@xx.com
        if (!ReUtil.isMatch(regexEmail, user.getEmail())) {
            return ResultBean.error("邮箱格式必须为 xxx@xx.com");
        }
        // 用户名不可以重复
        Optional<User> byUserName = userService.getByUserName(user.getUsername());
        if (byUserName.isPresent()) {
            return ResultBean.error("该用户名已存在");
        }
        user.setPassword(SecurityUtil.encryptionPassword(user.getUsername(), user.getPassword()));
        userService.save(user);
        return ResultBean.success();
    }
}
