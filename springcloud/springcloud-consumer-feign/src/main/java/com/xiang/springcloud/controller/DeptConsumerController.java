package com.xiang.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.xiang.springcloud.pojo.Dept;
import com.xiang.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    @Autowired
    private DeptClientService deptClientService = null;

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return this.deptClientService.queryById(id);
    }

    @PostMapping("/consumer/dept/add")
    public boolean addDept(Dept dept) {
        return this.deptClientService.addDept(dept);
    }
    @GetMapping("/consumer/dept/list")
    public List<Dept> queryAll() {
        return this.deptClientService.queryAll();
    }

}
