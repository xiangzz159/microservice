# microservice
Demo for Java micro service



# 微服务介绍
## 微服务架构4个核心问题？ 网络不可靠（本质原因）
1. 服务很多，客户端该怎么访问
2. 这么多服务，服务之间如何通讯
3. 这么多服务，服务如何治理
4. 服务挂了怎么办

万变不离其宗： 
1. API（路由问题）
2. HTTP、RPC（通信问题）
3. 注册和发现（高可用问题）
4. 熔断机制（服务降级问题）

## 解决方案：
1. Spring Cloud Netflix 一站式解决方案！
    - api网关： zuul组件
    - Feign -- HttpClient ---- Http通讯通讯方式，同步，阻塞
    - 服务注册发现：Eureka
    - 熔断机制： Hystrix
    
2. Apache Dubbo Zookeeper 半自动，需要整合其他的！
    - API：没有，找第三方组件，或者自己实现
    - 通讯协议：Dubbo
    - 服务注册发现：zookeeper
    - 熔断机制：没有，可以整合Hystrix
    
3. Spring Cloud Alibaba 新的一站式解决方案！ 更简单

4. 新概念：服务网格~ Service Mesh：istio

## SpringBoot和SpringCloud关系
- SpringBoot专注于快速方便的开发单个个体微服务，SpringCloud关助全局的服务治理框架
- SpringCloud是关注全局的微服务协调整理治理框架，他将SpringBoot开发的一个个单体微服务整合并管理起来，为各个服务之间提供：配置管理，服务发现，端路由器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等集成服务
- SpringBoot可以离开SpringCloud独立使用，开发项目，但是SpringCloud离不开SpringBoot，属于依赖关系


# 微服务优缺点  
## 优点：
- 单一职责原则
- 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或者业务需求
- 开发简单，开发效率提高，一个服务可能就是专一的只干一件事
- 微服务能够被小团队独立开发，这个小团队可以是2~5人的开发人员组成
- 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的
- 微服务能使用不同的语言开发
- 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins，Hudson，bamboo等
- 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关助自己的工作成果。无需通过合作才能体现价值
- 微服务允许你利用融合最新技术
- 微服务只是业务逻辑的代码，不会和HTML，CSS或其他界面混合
- 每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一的数据库

## 缺点：
- 开发人员要处理分布式系统的复杂性
- 多服务运维难度，随着服务的增加，运维的压力也在增加
- 系统部署依赖
- 服务间通信成本
- 数据一致性
- 系统集成测试
- 性能监控

# 微服务技术栈
|微服务条目|落地技术|
| :----: | :----: |
|服务开发|SpringBoot,Spring,SpringMVC|
|服务注册与管理|Archaius(Netfix),Diamond(阿里)|
|服务注册与发现|Euraka,Consul,Zookeeper|
|服务调用|Rest,RPC,gRPC|
|服务熔断器|Hystrix,Envoy等|
|负载均衡|Ribbon,Nginx等|
|服务接口调用（客户端调用服务的简化工具）|Feign等|
|消息队列|Kafka,RabbitMQ,ActiveMQ等|
|服务配置中心管理|SpringCloudConfig,Chef等|
|服务路由（API网关）|Zuul等|
|服务监控|Zabbix,Nagios,Metrics,Specatator等|
|全链路追踪|Zipkin,Brave,Dapper等|
|服务部署|Docker,OpenStock,Kubernetes等|
|数据流操作开发包|SpringCloud Stream(封装与Redis,Rabbit,Kafka等发送接受消息)|
|事件消息总线|SpringCloud Bus|

## SpringCloud与Dubbo的区别

|服务框架|SpringCloud|Dubbo|
| :----: | :----: | :----: |
|功能定位|完整的微服务框架（生态）|服务框架|
|支持Rest|是，Ribbon支持多种可拔插的序列化支持|否|
|支持RPC|否|是|
|支持多语言|是（Rest形式）|否|
|负载均可|是（服务端Zuul+客户端Ribbon）|是（客户端）|
|配置服务|Nexfix，SpringCloud Config Server|否|
|服务调用链监控|是（Zuul）|否|
|高可用|是（服务端Hystrix+客户端Ribbon）|是（客户端）|
|社区活跃度|高|2017年后重新开始维护，之间中断5年|
|学习难度|中等|低|
|文档丰富程度|高|高|
|其他|SpringCloud Bus为我们的应用程序带来了更多的管理端点|实践的公司比较多|


# Docker安装各种服务
### Dcoker安装mysql
创建mysql挂载文件夹 为了安全性，我们应该将数据和配置放到宿主机中，首先执行下面的命令创建data目录和config目录<br>

```
    mkdir docker-mysql
    cd docker-mysql
    mkdir data
    mkdir config
    cd config
    vi my.cnf
   ****************
    [mysqld]
    user=mysql
    character-set-server=utf8
    default_authentication_plugin=mysql_native_password
    [client]
    default-character-set=utf8
    [mysql]
    default-character-set=utf8
```

创建mysql容器

```
docker run -d -p 4306:3306 -h mysql --name mysql --restart always --privileged=true -v=/home/admin/docker-mysql/config/my.cnf:/etc/my.cnf -v=/home/admin/docker-mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD="b4tsNS*rySoT" -e MYSQL_ROOT_HOST=% mysql:5.6
# -p 4306:3306 端口映射
# –restart always: 开机启动
# –privileged=true: 提升容器内权限
# -v=/root/docker-mysql/config/my.cnf:/etc/my.cnf: 映射配置文件
# -v=/root/docker-mysql/data:/var/lib/mysql: 映射数据目录
```

### Dcoker安装redis
```shell
docker pull redis
docker run -d -h redis --name redis --restart always --privileged=true redis
```
### Dcoker安装zookeeper
```shell
# 获取zookeeper镜像
docker pull zookeeper
# 启动zookeeper
docker run -d -p 2181:2181 -h zookeeper --name zookeeper1 --restart always --privileged=true zookeeper
# 进入zookeeper内部
docker exec -it zookeeper1 ./bin/zkCli.sh
```

# SpringCloud组件
## Euerka
Euerka包含两个组件：Euerka Server和Euerka Client

Euerka Server提供服务注册服务，各个节点启动后，会在EuerkaServer中进行注册，这样Euerka Server中的服务注册表中将会列出所有可用服务节点的信息，服务系欸但的信息可以在界面中直观的看到

Euerka Client是一个Java客户端，用于简化EuerkaServer的交互，客户同时也具备一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EuerkaServer发送心跳（默认周期为30s）。如果Euerka Server在多个心跳周期内没有接收到某个节点的心跳，EuerkaServer将会从服务注册表中把这个服务节点移除掉

## 三大角色
- Euerka Server：提供服务的注册发现
- Euerka Provider：将自身服务注册到Eureka中，从而使消费方能够找到
- Service Consumer：服务消费方从Eureka中获取注册服务列表，从而找到消费服务



