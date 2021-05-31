package com.xiang.myrules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author：Yerik Xiang
 * @Date：2021/5/31 11:59
 * @Desc：必须满足@Configuration，但是，他不在主应用程序上下文的@ComponentScan中，
 * 否则将由所有@RibbonClients共享，如果您使用@ComponentScan（或@SpringBootApplication），
 * 则需要采取措施避免包含（例如将其放在一个单独的，不重叠的包中，或者指定要在@ComponentScan）
 * 拷贝RandomRule的代码进行重写
 */
@Configuration
public class MyRule extends AbstractLoadBalancerRule {
//    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE")
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            // 获取活着的服务
            List<Server> upList = lb.getReachableServers();
            // 获取全部服务
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            // 生成区间随机数
            int index = chooseRandomInt(serverCount);
            // 从或者的服务中随机获取一个
            server = upList.get(index);

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // TODO Auto-generated method stub

    }
}
