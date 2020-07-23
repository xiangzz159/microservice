package com.study.api.service.impl;

import com.study.api.rpc.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author：xiangguangye
 * @Desc：ApiService实现类
 * @Date：2020/7/13 11:05
 */
public class DemoServiceImpl implements DemoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }
}
