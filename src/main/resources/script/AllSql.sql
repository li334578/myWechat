create table tb_user
(
    id              bigint primary key comment '分布式id',
    username        varchar(50) comment '用户名',
    password        varchar(70) comment '密码',
    phone           varchar(20) comment '手机号',
    email           varchar(100) comment '邮箱',
    remark          varchar(100) comment '备注',
    login_count     int comment '登录次数',
    last_login_time datetime comment '上次登录时间',
    update_time     datetime comment '更新时间',
    create_time     datetime comment '创建时间'
);