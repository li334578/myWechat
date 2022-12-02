package com.example.mywechat.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * `tb_employee`(Employee)表实体类
 *
 * @author liwenbo
 * @since 2022-11-30 10:14:42
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键 雪花算法id
     */
    private Long id;
    /**
     * 员工姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别 1 男 2 女
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 手机号
     */
    private String phone;
}

