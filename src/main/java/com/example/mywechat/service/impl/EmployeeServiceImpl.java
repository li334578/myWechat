package com.example.mywechat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mywechat.mapper.EmployeeMapper;
import com.example.mywechat.pojo.Employee;
import com.example.mywechat.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * `tb_employee`(Employee)表服务实现类
 *
 * @author liwenbo
 * @since 2022-11-30 10:14:42
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}

