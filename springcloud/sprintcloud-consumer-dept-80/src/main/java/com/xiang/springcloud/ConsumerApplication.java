package com.xiang.springcloud;

import com.xiang.myrules.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

// exclude= {DataSourceAutoConfiguration.class} 不引入数据库连接
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
// 在为服务启动的时候就能去加载自定义的ribbon类
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT", configuration = MyRule.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
