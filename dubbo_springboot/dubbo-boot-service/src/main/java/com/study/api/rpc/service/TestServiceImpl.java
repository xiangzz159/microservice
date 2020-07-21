package com.study.api.rpc.service;

import com.study.boot.servicemock.TestServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author：xiangguangye
 * @Desc：
 * @Date：2020/7/21 16:11
 */
public class TestServiceImpl extends TestServiceMock {

    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
