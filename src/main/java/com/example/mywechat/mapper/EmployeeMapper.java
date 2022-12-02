package com.example.mywechat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mywechat.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * `tb_employee`(Employee)表数据库访问层
 *
 * @author liwenbo
 * @since 2022-11-30 10:14:42
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}

