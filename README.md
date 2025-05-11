# Cthulhu RPC

Cthulhu RPC 是一个插件化、可扩展的 Java RPC 框架，通过模块化设计实现高度的可定制性和扩展性。

## 项目结构

项目采用模块化设计，各模块功能和实现优先级如下：

### 核心模块（优先实现）

| 模块名称 | 功能描述 | 优先级 |
|---------|---------|-------|
| **rpc-api** | 定义框架的核心接口和数据结构，包括服务提供者和消费者接口、请求响应数据结构等 | P0 |
| **rpc-common** | 提供通用工具类和常量定义，包括字符串处理、集合操作、全局常量等 | P0 |
| **rpc-core** | 实现框架的核心功能，包括扩展点管理、配置管理、上下文管理和异常处理 | P0 |
| **rpc-transport** | 负责网络通信和数据传输，包括序列化、协议编解码和网络传输实现 | P0 |
| **rpc-protocol** | 定义和实现 RPC 协议，包括协议格式规范和编解码实现 | P0 |

### 服务模块（次优先实现）

| 模块名称 | 功能描述 | 优先级 |
|---------|---------|-------|
| **rpc-client** | 实现服务消费者客户端，包括服务代理、调用和连接管理 | P1 |
| **rpc-server** | 实现服务提供者服务端，包括服务暴露、注册和请求处理 | P1 |
| **rpc-registry** | 实现服务注册与发现，支持多种注册中心 | P1 |
| **rpc-processor** | 处理请求和响应的核心处理器，实现请求分发和处理 | P1 |

### 增强模块（后期实现）

| 模块名称 | 功能描述 | 优先级 |
|---------|---------|-------|
| **rpc-governance** | 提供服务治理能力，包括负载均衡、熔断和限流 | P2 |
| **rpc-filter** | 提供请求过滤和拦截能力，实现请求和响应的拦截器链 | P2 |
| **rpc-observability** | 提供可观察性能力，包括指标收集、分布式追踪和日志记录 | P2 |
| **rpc-security** | 提供安全相关功能，包括身份认证、授权和数据加密 | P3 |

### 示例和测试模块

| 模块名称 | 功能描述 | 优先级 |
|---------|---------|-------|
| **examples** | 提供使用示例，展示框架的基本使用方法 | 与对应功能同步 |
| **integration-test** | 提供集成测试，验证各模块的集成功能 | 与对应功能同步 |

## 模块依赖关系

```
rpc-common <-- rpc-api <-- rpc-core <-- rpc-transport
                                     <-- rpc-governance
                                     <-- rpc-observability
                                     <-- rpc-registry
                                     <-- rpc-client
                                     <-- rpc-server
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

## 开发指南

### 环境要求
- JDK 11 或以上
- Maven 3.6 或以上

### 构建项目
```bash
mvn clean install
```

### 运行示例
```bash
cd examples
mvn spring-boot:run
```

## 贡献指南

欢迎贡献代码，请遵循以下步骤：
1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/amazing-feature`)
3. 提交变更 (`git commit -m 'Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 创建 Pull Request

## 许可证

本项目采用 Apache 2.0 许可证 - 详情见 [LICENSE](LICENSE) 文件
