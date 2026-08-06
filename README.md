# pac4j-extension

[English](./README.md) | [简体中文](./README.zh-CN.md)

Spring Boot property-binding and URL utilities for pac4j OAuth / CAS clients
[简体中文](./README.zh-CN.md)

> **Current branch**: `feature/1.0.x`
> **Version**: `1.0.x.20260630-SNAPSHOT`
> **JDK baseline**: 8
> **Project status**: maintenance (1.0.x line). Not yet published to Maven Central; artifacts are distributed via the Aliyun Maven repository and GitHub Releases.

## Table of Contents

- [1. Project Overview](#1-project-overview)
- [2. Features & Status](#2-features--status)
- [3. Requirements & Compatibility](#3-requirements--compatibility)
- [4. Architecture & Modules](#4-architecture--modules)
- [5. Installation](#5-installation)
- [6. Quick Start](#6-quick-start)
- [7. Configuration](#7-configuration)
- [8. Core Usage](#8-core-usage)
- [9. Testing & Build](#9-testing--build)
- [10. Versioning & Branches](#10-versioning--branches)
- [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

### 1.1 What it is

**pac4j-extension** provides the Spring Boot-facing property layer for pac4j (4.5.7) OAuth and CAS OAuth clients:

- `Pac4jOAuthClientProperties` — the shared OAuth client property set (key/secret/callbackUrl, response type, scopes, timeouts, custom params/attributes, state);
- Specialized subclasses for CAS OAuth (`Pac4jOAuthCasClientProperties`), Facebook, OK.ru and Strava clients;
- `Pac4jClientNames` — canonical client-name constants;
- `Pac4jUrlUtils` — redirect and callback URL helpers.

This module is the property/utility layer a Spring Boot starter auto-configuration consumes; it is not itself a starter.

### 1.2 What it is not

- Not a full auto-configuration starter (no `@Configuration` / `@EnableAutoConfiguration` classes here).
- Not an OAuth client implementation — client construction stays in pac4j; this module feeds it configuration.

### 1.3 Typical scenarios

| Scenario | Recommended entry | Result |
|---|---|---|
| Bind OAuth client settings from configuration | `Pac4jOAuthClientProperties` | Key/secret/callback URL/scope/timeouts in one POJO |
| CAS OAuth client settings | `Pac4jOAuthCasClientProperties` | CAS OAuth URL + logout URL + flow switches |
| Provider-specific clients | Facebook / OK / Strava property classes | Tailored client properties |
| Referencing clients by canonical names | `Pac4jClientNames` constants | No string-typo drift |
| Redirects / callback URLs | `Pac4jUrlUtils` | `sendRedirect`, `constructRedirectUrl`, `urlEncode` |

<a id="2-features--status"></a>
## 2. Features & Status

| Capability | Status | Notes |
|---|:---:|---|
| Shared OAuth client properties | Available | `Pac4jOAuthClientProperties`: `callbackUrl`, `name`, `desc`, `logoUrl`, `key`, `secret`, `tokenAsHeader`, `responseType` (default `code`), `scope`, `hasGrantType`, timeouts, `profileVerb`, `customParams`, `profileAttrs`, `withState`, `stateData` |
| CAS OAuth properties | Available | `Pac4jOAuthCasClientProperties`: `casOAuthUrl`, `casLogoutUrl`, `springSecurityCompliant`, `implicitFlow` |
| Provider property classes | Available | `Pac4jOAuthFacebookClientProperties`, `Pac4jOAuthOkClientProperties`, `Pac4jOAuthStravaClientProperties` |
| Client name constants | Available | `Pac4jClientNames`: `cas-client`, `form-client`, `direct-basic-auth-client`, `indirect-basic-auth-client`, ... |
| URL utilities | Available | `Pac4jUrlUtils.sendRedirect(...)`, `constructRedirectUrl(...)`, `urlEncode(...)` |

<a id="3-requirements--compatibility"></a>
## 3. Requirements & Compatibility

| Component | Version | Notes |
|---|---:|---|
| JDK | 8+ | 1.0.x line baseline |
| Maven | 3.0+ | Build requirement |
| pac4j-core / pac4j-oauth | 4.5.7 | Pinned |
| javax.servlet-api | 4.0.1 | Compile scope (`Pac4jUrlUtils` redirects) |
| SLF4J | 2.0.18 | Logging facade |

Version-line matrix:

| Version line | Branch | JDK | Version pattern | Purpose |
|---|---|---:|---|---|
| 1.0.x | `feature/1.0.x` (this branch) | 8 | `1.0.x.*` | Legacy projects, Boot 2.x starter line (`javax.servlet`) |
| 2.0.x | `feature/2.0.x` | 17 | `2.0.x.*` | Boot 3.x line (`jakarta.servlet`) |
| 3.0.x | `feature/3.0.x` | 21 | `3.0.x.*` | New projects |

<a id="4-architecture--modules"></a>
## 4. Architecture & Modules

```text
[ Spring Boot Application ]
        |
        | starter auto-configuration consumes pac4j-extension
        v
+------------------------------------------+
| Properties  Pac4jOAuthClientProperties    |
|             + CAS / Facebook / OK /       |
|               Strava subclasses           |
| Names       Pac4jClientNames constants    |
| Utils       Pac4jUrlUtils (redirect /     |
|             callback URL helpers)         |
+------------------------------------------+
        |
        v
[ pac4j clients (pac4j-oauth / pac4j-core) ]
```

Single-module library (packaging `jar`). Package layout:

| Package | Responsibility |
|---|---|
| `org.pac4j.spring.boot` | Property classes: `Pac4jOAuthClientProperties`, `Pac4jOAuthCasClientProperties`, `Pac4jOAuthFacebookClientProperties`, `Pac4jOAuthOkClientProperties`, `Pac4jOAuthStravaClientProperties`, `Pac4jClientNames` |
| `org.pac4j.spring.boot.utils` | `Pac4jUrlUtils` |

<a id="5-installation"></a>
## 5. Installation

Maven:

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pac4j-extension</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
implementation 'io.github.easy4j:pac4j-extension:1.0.x.20260630-SNAPSHOT'
```

Snapshot builds require an enabled snapshot repository (Aliyun Maven snapshot repository per `distributionManagement` in `pom.xml`).

<a id="6-quick-start"></a>
## 6. Quick Start

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

**Expected result**: the populated POJO is ready to be consumed by a starter's auto-configuration to build a pac4j CAS OAuth client — `callbackUrl` carries the client callback location, `key`/`secret` the client credentials, and the CAS-specific fields the authorization server endpoints.

<a id="7-configuration"></a>
## 7. Configuration

This module defines the property surface itself; there are no runtime settings of its own. The binding prefix is defined by the consuming starter.

| Property group | Class | Representative fields |
|---|---|---|
| Generic OAuth client | `Pac4jOAuthClientProperties` | `callbackUrl`, `name`, `desc`, `logoUrl`, `key`, `secret`, `tokenAsHeader`, `responseType`, `scope`, `hasGrantType`, `connectTimeout`, `readTimeout`, `profileVerb`, `customParams`, `profileAttrs`, `withState`, `stateData` |
| CAS OAuth client | `Pac4jOAuthCasClientProperties` | + `casOAuthUrl`, `casLogoutUrl`, `springSecurityCompliant`, `implicitFlow` |
| Facebook / OK / Strava | provider subclasses | Inherited generic fields |

<a id="8-core-usage"></a>
## 8. Core Usage

### 8.1 Redirect and callback URL helpers

```java
// Build a pac4j redirect URL carrying the client name
String redirectUrl = Pac4jUrlUtils.constructRedirectUrl(
        "https://localhost:8080/myapp/callback", "client_name", "cas-client");

// Issue a servlet redirect
Pac4jUrlUtils.sendRedirect(response, "https://cas.example.com/login");
```

### 8.2 Canonical client names

```java
// Constants instead of string literals:
String casClient = Pac4jClientNames.CAS_CLIENT;            // "cas-client"
String formClient = Pac4jClientNames.FORM_CLIENT;          // "form-client"
```

<a id="9-testing--build"></a>
## 9. Testing & Build

```bash
mvn clean verify
```

- JaCoCo runs `prepare-agent`, `report` and `check` on the `verify` phase with a **90% line-coverage** rule (`haltOnFailure=false`).
- The module ships a Maven wrapper (`mvnw`).
- Release packaging (`mvn -Prelease deploy`) attaches sources and javadoc jars, GPG-signs artifacts and is wired for Sonatype Central Publishing; plain `mvn deploy` routes SNAPSHOT/release artifacts to the Aliyun Maven repository per `distributionManagement`.

<a id="10-versioning--branches"></a>
## 10. Versioning & Branches

| Branch | Version pattern | JDK | Maintenance policy |
|---|---|---|---|
| `feature/1.0.x` (this branch) | `1.0.x.*` | 8 | Compatibility fixes and JDK-8-safe dependency upgrades only (`javax.servlet`) |
| `feature/2.0.x` | `2.0.x.*` | 17 | JDK 17 line (`jakarta.servlet`) |
| `feature/3.0.x` | `3.0.x.*` | 21 | JDK 21 line |

<a id="11-contributing--license"></a>
## 11. Contributing & License

Contributions are welcome. Run `mvn clean verify` before opening a pull request and describe compatibility, testing and migration impact. This project is licensed under the [Apache License 2.0](LICENSE).
