package com.example.mywechat.controller;


import com.example.mywechat.config.MyBatisPlusConfig;
import com.example.mywechat.entity.User;
import com.example.mywechat.pojo.Employee;
import com.example.mywechat.service.EmployeeService;
import com.example.mywechat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * `tb_employee`(Employee)表控制层
 *
 * @author liwenbo
 * @since 2022-11-30 10:14:41
 */
@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    @Resource
    private UserService userService;

    @GetMapping("/all")
    public String getEmployee() {
        List<Employee> all = new ArrayList<>();
        IntStream.range(0, 10).forEach(item -> {
            Map<String, String> map = new HashMap<>();
            map.put("employee", "tb_employee" + item);
            MyBatisPlusConfig.myTableNameMap.set(map);
            all.addAll(employeeService.list());
            MyBatisPlusConfig.myTableNameMap.remove();
        });
        List<User> list = userService.list();
        log.info("总数据条数为{}", all.size());
        return "success";
    }
}

