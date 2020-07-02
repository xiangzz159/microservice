package com.example.serviceregistry.provider.serviceimpl;

import com.example.serviceregistry.provider.service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
