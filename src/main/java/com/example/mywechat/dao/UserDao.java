package com.example.mywechat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywechat.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author liwenbo
 * @since 2022-11-07 22:14:28
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

