package com.study.api.service;

import org.springframework.stereotype.Service;
import com.study.boot.service.TestService;
/**
 * @Author：xiangguangye
 * @Desc：
 * @Date：2020/7/21 16:11
 */
@Service
public class TestServiceImpl implements TestService {


    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
