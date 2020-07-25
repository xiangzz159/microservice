package com.study.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = { "classpath:consumer.xml" })
public class ConsumerApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplicationMain.class, args);
    }
}
