package com.study.api.rpc.service.impl;

import com.study.api.rpc.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author：xiangguangye
 * @Desc：ApiService实现类
 * @Date：2020/7/13 11:05
 */
public class ApiServiceImpl implements ApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public String hello(String name) {
        return "hello," + name + "!";
    }

}
