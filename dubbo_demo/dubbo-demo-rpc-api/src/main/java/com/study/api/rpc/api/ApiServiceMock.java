package com.study.api.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author：xiangguangye
 * @Desc：
 * @Date：2020/7/13 10:12
 */
public class ApiServiceMock implements ApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceMock.class);

    @Override
    public String hello(String name) {
        return null;
    }
}
