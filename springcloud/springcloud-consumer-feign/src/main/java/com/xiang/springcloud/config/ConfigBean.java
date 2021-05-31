package com.xiang.springcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySource("classpath:application.yml")
public class ConfigBean {
    @Value("${spring.security.user.name}")
    private String username;
    @Value("${spring.security.user.password}")
    private String password;


    // 配置负载均衡实现RestTemplate
    // IRule
    // RoundRobinRule:轮询（默认）
    // RandomRule:随机
    // AvaliablityFilteringRule:会先过滤调访问故障的服务，对剩下的服务进行轮询
    // RetryRule:会先按照轮询获取服务，如果服务获取失败，则会在制定的时间内进行，重试
    @Bean
    @LoadBalanced   // Ribbon:配置负载均衡实现RestTemplate
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
        return restTemplate;
    }


}
