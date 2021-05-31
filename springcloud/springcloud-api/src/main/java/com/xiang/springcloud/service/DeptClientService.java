package com.xiang.springcloud.service;

import com.xiang.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author：Yerik Xiang
 * @Date：2021/5/31 14:22
 * @Desc：
 */

@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id);
    @GetMapping("/dept/list")
    public List<Dept> queryAll();
    @PostMapping("/dept/add")
    public boolean addDept(Dept dept);
}
