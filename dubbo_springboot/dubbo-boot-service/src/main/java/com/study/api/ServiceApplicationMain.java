package com.study.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


import java.io.IOException;

/**
 * @Author：xiangguangye
 * @Desc：
 * @Date：2020/7/22 16:51
 */


@SpringBootApplication
@ImportResource(value = { "classpath:dubbo-provider.xml" })
public class ServiceApplicationMain {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceApplicationMain.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ServiceApplicationMain.class, args);
    }
}
