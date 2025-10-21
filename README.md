<h1 align="center">🌞 Seeho Framework</h1>
<p align="center">
A modular backend framework built with Spring Boot & Spring Cloud Alibaba — featuring unified SSO, API Gateway, and domain-driven design.<br/>
基于 Spring Boot 与 Spring Cloud Alibaba 构建的模块化后端框架，支持统一认证中心、API 网关与领域驱动设计架构。
</p>

---

## 🧭 Overview 概述

**Seeho** is a distributed, enterprise-level backend framework that provides a clean modular foundation for building scalable systems.  
**Seeho** 是一套分布式企业级后端框架，提供清晰的模块化基础，用于构建可扩展、高内聚的系统。

It integrates core Spring Cloud components with modern architectural principles like DDD, layered abstraction, and unified authentication.  
它融合了 Spring Cloud 的核心组件与现代架构理念（DDD、分层抽象、统一认证体系）。

---

## 🧩 System Architecture / 系统架构示意图

```bash
                          ┌────────────────────────────┐
                          │ 🌍 Client / Web / App      │
                          └────────────┬──────────────┘
                                       │
                                       ▼
                          ┌────────────────────────────┐
                          │ 🛡 seeho-gateway            │
                          │ API Gateway / 路由与鉴权入口 │
                          └────────────┬──────────────┘
                                       │
                  ┌────────────────────┼────────────────────┐
                  │                                         │
                  ▼                                         ▼
        ┌──────────────────────┐                  ┌──────────────────────┐
        │ 🔐 seeho-sso          │                  │ 🌈 seeho-aurora-api   │
        │ Auth Center / 认证中心 │                  │ BFF 层 / 前端接口层   │
        └──────────┬───────────┘                  └──────────┬───────────┘
                   │                                           │
                   ▼                                           ▼
        ┌──────────────────────┐                  ┌──────────────────────┐
        │ ⚙️ seeho-core-app     │◄────────────────► Feign / Event 调用     │
        │ 应用层 / 业务编排      │                  └──────────────────────┘
        └──────────┬───────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ 🧩 seeho-core-domain      │
        │ 领域层 / 聚合与实体        │
        └──────────┬──────────────┘
                   │
                   ▼
        ┌──────────────────────────┐
        │ 🔧 seeho-infra            │
        │ 基础设施层 / Repo / MQ / Redis │
        └──────────┬──────────────┘
                   │
         ┌─────────┼──────────┬─────────┐
         ▼         ▼          ▼         ▼
     seeho-omen  seeho-craft  seeho-canon  docs
     (异常体系)   (工具库)       (常量/枚举)   (文档)

🎯 Design Goals / 核心设计目标
English	中文
🧩 Modularization — Each component is independent and replaceable	🧩 模块化设计——每个模块可独立开发与替换
🔐 Security-first — Centralized authentication with JWT & OAuth2	🔐 安全优先——统一认证中心，支持 JWT 与 OAuth2
🚀 Scalability — Cloud-native, ready for microservices	🚀 可扩展性——云原生架构，支持微服务
🌐 Cross-domain SSO — Seamless login across sub-systems	🌐 跨域单点登录——多子系统共享登录状态
⚙️ Maintainability — Clear separation of concerns	⚙️ 易维护性——职责清晰、结构分明
🏗️ Project Structure / 项目结构
seeho/
├─ pom.xml                        # Parent POM: dependencies, plugins, version alignment
│                                # 父级 POM：依赖、插件、版本统一管理
│
├─ seeho-bom/                     # (Optional) BOM: version control
│                                # 可选：BOM 模块用于版本管理
│
├─ seeho-starters/                # Public starters 公共组件封装
│  ├─ starter-cache-redis/        # Cache & distributed lock 缓存与分布式锁
│  ├─ starter-web/                # Web abstraction (exception / interceptors)
│  ├─ starter-feign/              # Feign abstraction (Resilience4j integration)
│  └─ starter-logging/            # Unified logging & tracing 日志与链路追踪
│
├─ seeho-gateway/                 # 🛡 API Gateway: routing, CORS, JWT verification
│                                # 🛡 网关：统一入口、跨域配置、路由与鉴权
├─ seeho-sso/                     # 🔐 Auth Center (OAuth2 / JWT / SSO)
│                                # 🔐 统一认证中心：OAuth2 登录、JWT、SSO
│
├─ seeho-aurora-api/              # 🌈 API / BFF layer
│                                # 🌈 API/BFF 层（前端适配层）
├─ seeho-core-app/                # ⚙️ Application layer
│                                # ⚙️ 应用层（用例编排、事务控制）
├─ seeho-core-domain/             # 🧩 Domain layer
│                                # 🧩 领域层（实体、聚合、领域服务）
├─ seeho-infra/                   # 🔧 Infrastructure adapters
│                                # 🔧 基础设施层（Repo、MQ、Redis）
│
├─ seeho-omen/                    # 💥 Global exception & error code
│                                # 💥 全局异常与错误码
├─ seeho-craft/                   # 🛠 Utilities (DTO, encryption, helper)
│                                # 🛠 通用工具类封装
├─ seeho-canon/                   # 📘 Constants & enums
│                                # 📘 常量、枚举、状态机定义
│
└─ docs/                          # 📄 Documentation & architecture diagrams
                                 # 📄 文档与架构设计说明

⚙️ Tech Stack / 技术栈
Layer 层级	Technologies 技术栈
Core Framework 核心框架	Spring Boot 3.x, Spring Cloud Alibaba
Gateway 网关	Spring Cloud Gateway
Service Discovery / Config 服务注册与配置中心	Nacos
Auth & SSO 认证与单点登录	Spring Security OAuth2, JWT
Messaging 消息中间件	RocketMQ
Scheduler 调度系统	XXL-Job
Cache 缓存	Redis, Redisson
Database 数据库	MySQL (MyBatis / JPA)
Transaction 分布式事务	Seata
Tracing & Logs 链路追踪与日志	Sleuth + Zipkin
Build / Deploy 构建与部署	Maven, Docker, Docker Compose
🚀 Highlights / 特性亮点
English	中文
✅ Multi-account login (Gmail / iCloud / WeChat / Phone)	✅ 支持多账号登录（Gmail、iCloud、微信、手机号）
🔄 Cross-domain SSO across subdomains	🔄 跨子域单点登录共享登录状态
🔒 JWT security with refresh mechanism	🔒 JWT 短期令牌 + Refresh Token 安全机制
🧱 Domain-driven modular design	🧱 领域驱动的模块化架构
☁️ Cloud-native ready for microservices	☁️ 云原生架构，微服务可平滑拆分
📘 License / 许可协议
Copyright (c) 2025 Leonpo.
All Rights Reserved.

This repository is provided for learning and demonstration purposes only.  
Unauthorized redistribution, reproduction, or commercial use of the code is strictly prohibited.  

The author retains all rights to the design, structure, and source code contained herein.  
For collaboration or licensing inquiries, please contact: leonpo-dev@yourdomain.com


📖 本仓库仅供学习与展示使用，禁止未经授权的转载、二次开发或商用。
如需合作或授权，请联系作者。

📬 Contact / 联系方式
Field	Info
Author / 作者	Leonpo
Email / 邮箱	guanyuanfei66@icloud.com
