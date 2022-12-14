package com.example.mywechat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mywechat.dto.PageBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@TableName("tb_user")
@EqualsAndHashCode(callSuper = false)
@Data
public class User extends PageBean<User> implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 分布式id
     */
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;

}

