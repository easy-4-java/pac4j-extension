# pac4j-extension-spring

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-21-orange)](https://github.com/easy-4-java/pac4j-extension) [![License](https://img.shields.io/badge/license-Apache%202.0-green)](https://www.apache.org/licenses/LICENSE-2.0.txt)

面向 Spring Boot 的 pac4j OAuth / CAS 客户端属性绑定与 URL 工具

> **当前分支**：`feature/3.0.x`
> **版本**：`3.0.x.20260630-SNAPSHOT`
> **JDK 基线**：21
> **项目状态**：活跃维护（3.0.x 线）。制品通过阿里云 Maven 仓库分发。

## 目录

- [1. 项目概述](#1-项目概述)
- [2. 能力与状态](#2-features--status)
- [3. 运行要求与兼容性](#3-requirements--compatibility)
- [4. 架构与模块](#4-architecture--modules)
- [5. 引入依赖](#5-installation)
- [6. 快速开始](#6-quick-start)
- [7. 配置](#7-configuration)
- [8. 核心用法](#8-core-usage)
- [9. 测试与构建](#9-testing--build)
- [10. 版本线与分支](#10-versioning--branches)
- [11. 贡献与许可证](#11-contributing--license)

## 1. 项目概述

### 1.1 是什么

**pac4j-extension-spring** 为 pac4j 的 OAuth 与 CAS OAuth 客户端提供面向 Spring 的属性层：

- `Pac4jOAuthClientProperties`——共享的 OAuth 客户端属性集（key / secret / callbackUrl、响应类型、scope、超时、自定义参数 / 属性、state）；
- CAS OAuth（`Pac4jOAuthCasClientProperties`）、Facebook、OK.ru 与 Strava 客户端的专用子类；
- `Pac4jClientNames`——规范化的客户端名称常量；
- `Pac4jUrlUtils`——重定向与回调 URL 工具。

本模块是 Spring Boot Starter 自动装配所消费的属性 / 工具层，其本身不是 starter。

### 1.2 不是什么

- 不是完整的自动装配 starter（此处无 `@Configuration` / `@EnableAutoConfiguration` 类）。
- 不是 OAuth 客户端实现——客户端构造仍在 pac4j；本模块只为其提供配置。

### 1.3 典型使用场景

| 场景 | 推荐入口 | 结果 |
|---|---|---|
| 从配置绑定 OAuth 客户端设置 | `Pac4jOAuthClientProperties` | key / secret / 回调 URL / scope / 超时聚合在一个 POJO |
| CAS OAuth 客户端设置 | `Pac4jOAuthCasClientProperties` | CAS OAuth URL + 登出 URL + 流程开关 |
| 供应商专属客户端 | Facebook / OK / Strava 属性类 | 定制客户端属性 |
| 按规范名称引用客户端 | `Pac4jClientNames` 常量 | 避免字符串拼写漂移 |
| 重定向 / 回调 URL | `Pac4jUrlUtils` | `sendRedirect`、`constructRedirectUrl`、`urlEncode` |

<a id="2-features--status"></a>
## 2. 能力与状态

| 能力 | 状态 | 说明 |
|---|:---:|---|
| 共享 OAuth 客户端属性 | 可用 | `Pac4jOAuthClientProperties`：`callbackUrl`、`name`、`desc`、`logoUrl`、`key`、`secret`、`tokenAsHeader`、`responseType`（默认 `code`）、`scope`、`hasGrantType`、超时、`profileVerb`、`customParams`、`profileAttrs`、`withState`、`stateData` |
| CAS OAuth 属性 | 可用 | `Pac4jOAuthCasClientProperties`：`casOAuthUrl`、`casLogoutUrl`、`springSecurityCompliant`、`implicitFlow` |
| 供应商属性类 | 可用 | `Pac4jOAuthFacebookClientProperties`、`Pac4jOAuthOkClientProperties`、`Pac4jOAuthStravaClientProperties` |
| 客户端名称常量 | 可用 | `Pac4jClientNames`：`cas-client`、`form-client`、`direct-basic-auth-client`、`indirect-basic-auth-client` 等 |
| URL 工具 | 可用 | `Pac4jUrlUtils.sendRedirect(...)`、`constructRedirectUrl(...)`、`urlEncode(...)` |

<a id="3-requirements--compatibility"></a>
## 3. 运行要求与兼容性

| 组件 | 版本 | 说明 |
|---|---:|---|
| JDK | 21+ | 3.0.x 线基线 |
| Maven | 3.0+ | 构建要求 |
| pac4j-core / pac4j-oauth | 4.5.7 | 固定版本 |
| javax.servlet-api | 4.0.1 | compile scope（`Pac4jUrlUtils` 重定向） |
| SLF4J | 2.0.18 | 日志门面 |

版本线矩阵：

| 版本线 | 分支 | JDK | 版本模式 | 用途 |
|---|---|---:|---|---|
| 1.0.x | `feature/1.0.x` | 8 | `1.0.x.*` | 存量项目（`javax.servlet`） |
| 2.0.x | `feature/2.0.x` 与 `main` | 17 | `2.0.x.*` | JDK 17 线（`javax.servlet`） |
| 3.0.x | `feature/3.0.x`（当前分支） | 21 | `3.0.x.*` | JDK 21 线（`javax.servlet`） |

<a id="4-architecture--modules"></a>
## 4. 架构与模块

```text
[ Spring Boot 应用 ]
        |
        | starter 自动装配消费 pac4j-extension-spring
        v
+------------------------------------------+
| 属性     Pac4jOAuthClientProperties       |
|          + CAS / Facebook / OK /          |
|            Strava 子类                    |
| 名称     Pac4jClientNames 常量            |
| 工具     Pac4jUrlUtils（重定向 /          |
|          回调 URL 工具）                  |
+------------------------------------------+
        |
        v
[ pac4j 客户端（pac4j-oauth / pac4j-core）]
```

单模块库（打包类型 `jar`）。包结构：

| 包 | 职责 |
|---|---|
| `org.pac4j.spring.boot` | 属性类：`Pac4jOAuthClientProperties`、`Pac4jOAuthCasClientProperties`、`Pac4jOAuthFacebookClientProperties`、`Pac4jOAuthOkClientProperties`、`Pac4jOAuthStravaClientProperties`、`Pac4jClientNames` |
| `org.pac4j.spring.boot.utils` | `Pac4jUrlUtils` |

<a id="5-installation"></a>
## 5. 引入依赖

Maven：

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pac4j-extension-spring</artifactId>
    <version>3.0.x.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle：

```groovy
implementation 'io.github.easy4j:pac4j-extension-spring:3.0.x.20260630-SNAPSHOT'
```

快照版本需要启用对应快照仓库（`pom.xml` 中 `distributionManagement` 指向 Aliyun Maven 仓库）。

<a id="6-quick-start"></a>
## 6. 快速开始

```java
Pac4jOAuthCasClientProperties props = new Pac4jOAuthCasClientProperties();
props.setCallbackUrl("https://localhost:8080/myapp/callback");
props.setKey("cas-oauth-key");
props.setSecret("cas-oauth-secret");
props.setCasOAuthUrl("https://cas.example.com/oauth2.0");
props.setCasLogoutUrl("https://cas.example.com/logout");
props.setImplicitFlow(false);
props.setSpringSecurityCompliant(true);
```

**预期结果**：填充完成的 POJO 可直接被 Starter 自动装配消费以构建 pac4j CAS OAuth 客户端——`callbackUrl` 携带客户端回调地址，`key` / `secret` 为客户端凭据，CAS 专属字段为授权服务器端点。

<a id="7-configuration"></a>
## 7. 配置

本模块自身定义属性面，无运行时配置项；绑定前缀由消费方 Starter 定义。

| 属性组 | 类 | 代表性字段 |
|---|---|---|
| 通用 OAuth 客户端 | `Pac4jOAuthClientProperties` | `callbackUrl`、`name`、`desc`、`logoUrl`、`key`、`secret`、`tokenAsHeader`、`responseType`、`scope`、`hasGrantType`、`connectTimeout`、`readTimeout`、`profileVerb`、`customParams`、`profileAttrs`、`withState`、`stateData` |
| CAS OAuth 客户端 | `Pac4jOAuthCasClientProperties` | + `casOAuthUrl`、`casLogoutUrl`、`springSecurityCompliant`、`implicitFlow` |
| Facebook / OK / Strava | 供应商子类 | 继承通用字段 |

<a id="8-core-usage"></a>
## 8. 核心用法

### 8.1 重定向与回调 URL 工具

```java
// 构造携带客户端名称的 pac4j 重定向 URL
String redirectUrl = Pac4jUrlUtils.constructRedirectUrl(
        "https://localhost:8080/myapp/callback", "client_name", "cas-client");

// 发起 Servlet 重定向
Pac4jUrlUtils.sendRedirect(response, "https://cas.example.com/login");
```

### 8.2 规范客户端名称

```java
// 用常量替代字符串字面量：
String casClient = Pac4jClientNames.CAS_CLIENT;            // "cas-client"
String formClient = Pac4jClientNames.FORM_CLIENT;          // "form-client"
```

<a id="9-testing--build"></a>
## 9. 测试与构建

```bash
mvn clean verify
```

- JaCoCo 在 `verify` 阶段执行 `prepare-agent`、`report` 与 `check`，行覆盖率规则为 **90%**（`haltOnFailure=false`）。
- 模块自带 Maven Wrapper（`mvnw`）。
- 发布打包（`mvn -Prelease deploy`）附带 sources 与 javadoc 构件并执行 GPG 签名，对接 Sonatype Central Publishing；普通 `mvn deploy` 按版本后缀路由到 Aliyun Maven 仓库（见 `distributionManagement`）。

<a id="10-versioning--branches"></a>
## 10. 版本线与分支

| 分支 | 版本模式 | JDK | 维护策略 |
|---|---|---|---|
| `feature/1.0.x`（当前分支） | `1.0.x.*` | 8 | 仅接受兼容性修复与 JDK 8 安全的依赖升级（`javax.servlet`） |
| `feature/2.0.x` | `2.0.x.*` | 17 | JDK 17 线（`javax.servlet`） |
| `feature/3.0.x` | `3.0.x.*` | 21 | JDK 21 线 |

<a id="11-contributing--license"></a>
## 11. 贡献与许可证

欢迎贡献。提交 Pull Request 前请执行 `mvn clean verify`，并说明兼容性、测试与迁移影响。本项目采用 [Apache License 2.0](LICENSE) 许可证。
