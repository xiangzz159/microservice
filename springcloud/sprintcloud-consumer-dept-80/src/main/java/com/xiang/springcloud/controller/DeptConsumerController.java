package com.xiang.springcloud.controller;

import com.xiang.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    // 理解：消费者不应该有service层
    // RestTemplate... 供我们直接调用就可以，注册到spring中
    @Autowired
    private RestTemplate restTemplate;
    private static final String REST_URL_PREFIX = "http://localhost:8001";

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @PostMapping("/consumer/dept/add")
    public boolean addDept(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }
    @GetMapping("/consumer/dept/list")
    public List<Dept> queryAll() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

}
