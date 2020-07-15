package com.study.api.rpc.api;

/**
 * @Author：xiangguangye
 * @Desc：降级实现DemoService接口
 * @Date：2020/7/15 10:17
 */
public class DemoServiceMock implements DemoService {
    @Override
    public String sayHello(String name) {
        return null;
    }
}
