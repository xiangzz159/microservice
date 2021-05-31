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
## Euerka(AP原则)
Euerka包含两个组件：Euerka Server和Euerka Client

Euerka Server提供服务注册服务，各个节点启动后，会在EuerkaServer中进行注册，这样Euerka Server中的服务注册表中将会列出所有可用服务节点的信息，服务系欸但的信息可以在界面中直观的看到

Euerka Client是一个Java客户端，用于简化EuerkaServer的交互，客户同时也具备一个内置的，使用轮询负载算法的负载均衡器。在应用启动后，将会向EuerkaServer发送心跳（默认周期为30s）。如果Euerka Server在多个心跳周期内没有接收到某个节点的心跳，EuerkaServer将会从服务注册表中把这个服务节点移除掉

### 三大角色
- Euerka Server：提供服务的注册发现
- Euerka Provider：将自身服务注册到Eureka中，从而使消费方能够找到
- Service Consumer：服务消费方从Eureka中获取注册服务列表，从而找到消费服务

### 自我保护机制
某时刻某个服务不可用了，eureka不会立刻清理，依旧会对该微服务的信息进行保存。

默认情况下，如果EurekaServer在一定时间内没有接收到某个服务实例的心跳，EurekaServer将会注销该实例（默认90s）。但是当网络分区故障发生时，微服务与Eureka之间无法正常通信，以上行为可能变得非常危险--因为微服务本身其实是健康的

此时本不应该注销这个服务，Eureka通过自我保护机制来解决这个问题--当EurekaServer节点在短时间内丢失过多客户端时，那么这个节点就会进入自我保护模式。一旦进入该模式，EurekaServer就会保护服务注册表中的信息，不再删除服务注册表中的数据（不会删除任何微服务）。当王国故障恢复后，该EurekaServer节点会自动退出自我保护模式

它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例

在SpringCloud中，可以使用eureka.server.enable-self-preservation = false禁用自我保护模式

### Zookeeper和Eureka对比
#### CAP原则
- C(Consistency) 强一致性
- A(Avaliablity) 可用性
- P(Partition Tolerance) 分区容错性

一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性者三个需求

根据CAP原理，将NoSQL数据库分成了满足CA、CP、AP原则三大类
- CA：单点集群，通常拓展性较差
- CP：通常性能不是特别高
- AP：通常可能对一致性要求低

#### Zookeeper保证的是CP
当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接受服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。但是zk会出现这样一种情况，当master节点因为网络故障与其他节点失去连接时，剩余节点会重新进行leader选举。当选举时间过长时，整个选举期间整个zk集群是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的情况下，因为网络问题使得zk集群失去master节点是较大概率会发生的事情，虽然服务最终能够回复，但是漫长的选举时间导致的注册长期不可用是不能容忍的。

#### Eureka保证的是AP
Eureka各个节点都是平等的，鸡哥节点挂掉不会影响正常节点的工作，剩余节点依然可以提供注册和查询。而Eureka的客户端在向某个Erureka注册时，如果发生连接失败，则会自动切换到其他节点，只要有一台Eureka还在，就能保证注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有一种自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况
1. Eureka不再从注册列表中移除因为长时间没收到心跳而应该过期的服务
2. Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上
3. 当网络稳定时，当前实例新的注册信息会被同步到其他节点中

因此，Eureka可以恨到的应对因网络故障导致部分节点失去的情况，而不会像zk那样使整个注册服务瘫痪

## Ribbon(客户端负载均衡)
提供客户端的软件负载均衡算法，将NetFlix的中间层服务连接在一起。

负载均衡（LB load Balance）分类
- 集中式LB：在服务的消费方和提供方之间使用独立的LB设施，如Nginx，由该设施负责把访问请求通过某种策略转发至服务的提供方
- 进程是LB：
   1. 将LB逻辑集成到消费方，消费方从服务注册中心获知有那些地址可用，然后自己在从这些地址中选出一个合适的服务器
   2. Ribbon就属于进程内LB，他只是一个类库，集成与消费方进程，消费方通过他来获取服务提供方的地址
   
## Feign负载均衡
feign是声明式的web service客户端，类似于controller调用service，Spring Cloud集成了Ribbon和Eureka，可在使用Feign时提供负载均衡的http客户端

feign主要是社区，大家都习惯面向接口变成。这个是很多开发人员的闺房。调用微服务访问两种方法
1. 微服务名字（ribbon）
2. 接口和注解（feign）
   
在使用Ribbon + RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法，但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。
在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置他（类似于以前Dao接口上标注Mapper注解，现在是一个微服务接口上标注一个Feign注解即可）即可完成对服务提供方的接口半丁，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量

## Hystrix
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整个服务失败，避免级联故障，以提高分布式系统的弹性。

"断路器"本身是一种开关装置，但某个服务但愿发生故障之后，通过断路器的故障监控（类似熔断保险丝），想调用方返回一个服务预期的，可处理的备选响应（Fallback），而不是长时间的等待或者跑出方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间、不必要的占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

- 服务降级
- 服务熔断
- 服务限流
- 接近事实的监控

### 服务雪崩
复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某个时候将不可避免的失败！

多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的"扇出"，如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，今儿引起系统奔溃，所谓的"服务雪崩"

对于高流量的应用来说，歹意的后段依赖可能会导致所有服务器上的所有资源都在几秒内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发射管更多的级联故障，这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

### 服务熔断（服务端）
当某个服务不可用或长时间无响应时，会进行服务的降级，今儿熔断该节点微服务的调用，快速返回错误信息。Hystrix会监控微服务间调用的状况，当失败的调用到一定的阈值，缺省是5s内20次调用失败就会启动熔断机制，熔断机制的注解是@HystrixCommand

### 服务降级（客户端）

## Zuul（路由网关）
Zuul包含对请求的路由和过滤

其中路由功能将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础，而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础。Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的信息，即以后的访问微服务都是通过Zuul跳转后获得

> Zuul服务最终还是会注册到Eureka中
> 提供：代理 + 路由 + 过滤 三大功能

## config（分布式配置）
微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。SpringCloud提供了ConfigServer来解决这个问题，我们每一个微服务自己带着一个application.yml，那上百的配置文件修改起来是很困难的。



 


