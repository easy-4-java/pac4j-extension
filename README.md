# pac4j-extension

Multi-module extensions for pac4j, organized like `xxljob-extension` and released from one reactor.

| Module | Maven artifact | Purpose |
| --- | --- | --- |
| `pac4j-extension-core` | `io.github.easy4j:pac4j-extension-core` | Authentication, authorization, credentials and profile extensions |
| `pac4j-extension-spring` | `io.github.easy4j:pac4j-extension-spring` | Spring-facing OAuth client properties and servlet URL utilities |

Release lines:

- `feature/1.0.x`: JDK 8, `1.0.x.20260630-SNAPSHOT`
- `feature/2.0.x` and `main`: JDK 17, `2.0.x.20260630-SNAPSHOT`
- `feature/3.0.x`: JDK 21, `3.0.x.20260630-SNAPSHOT`

Build the complete reactor with `mvn clean verify` and deploy it with `mvn deploy`.

See [README.zh-CN.md](README.zh-CN.md) for Chinese documentation.
