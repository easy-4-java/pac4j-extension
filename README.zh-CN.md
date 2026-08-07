# pac4j-extension

pac4j 多模块扩展工程。仓库统一构建和发布两个模块，同时保留已有 Maven 消费坐标。

| 模块 | Maven 坐标 | 用途 |
| --- | --- | --- |
| `pac4j-extension` | `io.github.easy4j:pac4j-extension` | OAuth 客户端属性与 Servlet URL 工具 |
| `pac4j-biz` | `io.github.easy4j:pac4j-biz` | 认证、授权、凭证与用户 Profile 扩展 |

版本线：

- `feature/1.0.x`：JDK 8，`1.0.x.20260630-SNAPSHOT`
- `feature/2.0.x` 与 `main`：JDK 17，`2.0.x.20260630-SNAPSHOT`
- `feature/3.0.x`：JDK 21，`3.0.x.20260630-SNAPSHOT`

使用 `mvn clean verify` 验证完整 Reactor，使用 `mvn deploy` 统一发布。
