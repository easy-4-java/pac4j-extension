# pac4j-extension

pac4j 多模块扩展工程，按照 `xxljob-extension` 的结构组织并由同一个 Reactor 统一发布。

| 模块 | Maven 坐标 | 用途 |
| --- | --- | --- |
| `pac4j-extension-core` | `io.github.easy4j:pac4j-extension-core` | 认证、授权、凭证与用户 Profile 扩展 |
| `pac4j-extension-spring` | `io.github.easy4j:pac4j-extension-spring` | 面向 Spring 的 OAuth 客户端属性与 Servlet URL 工具 |

版本线：

- `feature/1.0.x`：JDK 8，`1.0.x.20260630-SNAPSHOT`
- `feature/2.0.x` 与 `main`：JDK 17，`2.0.x.20260630-SNAPSHOT`
- `feature/3.0.x`：JDK 21，`3.0.x.20260630-SNAPSHOT`

使用 `mvn clean verify` 验证完整 Reactor，使用 `mvn deploy` 统一发布。
