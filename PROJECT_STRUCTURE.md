# Cthulhu RPC 框架项目结构

## 项目概述
Cthulhu RPC 是一个插件化、可扩展的 Java RPC 框架，通过模块化设计实现高度的可定制性和扩展性。

## 优化后的模块结构与实现优先级

### 核心模块（P0 - 最高优先级）
- **rpc-api**：框架的 API 定义，包含核心接口和数据结构
  - `service`：服务接口定义
  - `model`：数据模型定义
  - `exception`：异常定义
  - `annotation`：注解定义
  - `callback`：回调接口定义
  
- **rpc-common**：通用工具类和常量定义
  - `constants`：常量定义
  - `utils`：工具类
  - `enums`：枚举类型
  - `threadpool`：线程池工具
  - `timer`：定时器工具
  
- **rpc-core**：框架核心功能实现
  - `extension`：扩展点管理（原 rpc-extension 模块）
  - `config`：配置管理（原 rpc-config 模块）
  - `context`：上下文管理
  - `exception`：异常处理
  - `proxy`：代理实现
  - `invoker`：调用器实现

### 通信模块（P0 - 最高优先级）
- **rpc-transport**：网络通信相关功能
  - `serialize`：序列化功能（原 rpc-serialize 模块）
    - `json`：JSON 序列化实现
    - `protobuf`：Protobuf 序列化实现
    - `hessian`：Hessian 序列化实现
  - `protocol`：协议实现
    - `codec`：编解码器
    - `header`：协议头定义
  - `netty`：基于 Netty 的传输实现
    - `client`：客户端实现
    - `server`：服务端实现
    - `handler`：处理器实现
  - `http`：基于 HTTP 的传输实现

- **rpc-protocol**：协议定义和实现
  - `cthulhu`：Cthulhu 自定义协议
  - `http`：HTTP 协议支持
  - `grpc`：gRPC 协议支持
  - `codec`：协议编解码

### 服务模块（P1 - 次高优先级）
- **rpc-client**：客户端实现
  - `proxy`：服务代理
  - `connection`：连接管理
  - `failover`：失败重试
  - `async`：异步调用支持
  
- **rpc-server**：服务端实现
  - `handler`：请求处理器
  - `exporter`：服务暴露
  - `bootstrap`：服务启动
  - `shutdown`：优雅关闭
  
- **rpc-registry**：服务注册与发现
  - `zookeeper`：ZooKeeper 注册中心实现
  - `nacos`：Nacos 注册中心实现
  - `etcd`：etcd 注册中心实现
  - `discovery`：服务发现
  - `watcher`：服务变更监听

- **rpc-processor**：请求处理器
  - `request`：请求处理
  - `response`：响应处理
  - `dispatch`：请求分发
  - `executor`：请求执行

### 服务治理模块（P2 - 中等优先级）
- **rpc-governance**：服务治理相关功能
  - `loadbalance`：负载均衡（原 rpc-loadbalancer 模块）
    - `random`：随机算法
    - `roundrobin`：轮询算法
    - `consistenthash`：一致性哈希算法
    - `leastactive`：最少活跃度算法
  - `circuitbreaker`：熔断器
    - `counter`：计数器实现
    - `slidingwindow`：滑动窗口实现
  - `ratelimit`：限流器
    - `token`：令牌桶实现
    - `leakybucket`：漏桶实现

- **rpc-filter**：过滤器链
  - `pre`：前置过滤器
  - `post`：后置过滤器
  - `error`：错误处理过滤器
  - `timeout`：超时处理过滤器
  - `auth`：认证过滤器

### 可观察性模块（P2 - 中等优先级）
- **rpc-observability**：可观察性相关功能
  - `metrics`：指标收集
    - `micrometer`：Micrometer 实现
    - `prometheus`：Prometheus 集成
  - `tracing`：分布式追踪
    - `opentracing`：OpenTracing 实现
    - `jaeger`：Jaeger 集成
  - `logging`：日志记录
    - `access`：访问日志
    - `error`：错误日志

### 安全模块（P3 - 低优先级）
- **rpc-security**：安全相关功能
  - `auth`：认证授权
  - `encryption`：数据加密
  - `ssl`：SSL/TLS 支持
  - `acl`：访问控制

### 示例和测试
- **examples**：示例代码（与对应功能同步实现）
  - `quickstart`：快速入门示例
  - `advanced`：高级特性示例
  - `spring`：Spring 集成示例
  
- **integration-test**：集成测试（与对应功能同步实现）
  - `basic`：基本功能测试
  - `performance`：性能测试
  - `reliability`：可靠性测试

## 模块依赖关系
```
rpc-common <-- rpc-api <-- rpc-core <-- rpc-transport
                                     <-- rpc-protocol
                                     <-- rpc-registry
                                     <-- rpc-client
                                     <-- rpc-server
                                     <-- rpc-processor
                                     <-- rpc-governance
                                     <-- rpc-filter
                                     <-- rpc-observability
                                     <-- rpc-security
```

## 实现路线图

### 第一阶段：核心功能（P0）
1. 实现 rpc-common 中的通用工具和常量
2. 实现 rpc-api 中的核心接口定义
3. 实现 rpc-core 中的扩展点管理和配置管理
4. 实现 rpc-transport 中的基本网络传输和序列化
5. 实现 rpc-protocol 中的基本协议编解码

### 第二阶段：基本服务功能（P1）
1. 实现 rpc-server 中的服务暴露和请求处理
2. 实现 rpc-client 中的服务代理和调用
3. 实现 rpc-registry 中的基本服务注册与发现
4. 实现 rpc-processor 中的请求处理器

### 第三阶段：服务治理和可观察性（P2）
1. 实现 rpc-governance 中的负载均衡、熔断和限流
2. 实现 rpc-filter 中的拦截器链
3. 实现 rpc-observability 中的指标收集和分布式追踪

### 第四阶段：安全和高级特性（P3）
1. 实现 rpc-security 中的身份认证和授权
2. 完善各模块的高级特性和优化

## 优化说明
1. **减少模块数量**：将功能相近的模块整合在一起，减少不必要的模块划分
2. **明确模块职责**：每个模块有明确的职责边界，避免职责重叠
3. **简化依赖关系**：优化模块间的依赖关系，减少循环依赖
4. **提高可维护性**：相关功能集中在一起，便于维护和扩展
5. **实现优先级**：按照功能重要性和依赖关系确定实现优先级，确保核心功能先行实现
