package com.example.serviceregistry.customer;

import com.example.serviceregistry.provider.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Customer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("customer.xml");
        context.start();
        System.out.println("consumer start");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("consumer");
        System.out.println(demoService.sayHello("World"));

    }
}
