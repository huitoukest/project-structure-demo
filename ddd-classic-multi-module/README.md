# ddd-classic-multi-module

DDD 分层 + 端口适配器的 **Maven 多模块**结构示例：parent + 10 子模块，用 `core / common / model / api / dal-api / dal-impl / dal-mock / integration / biz / adapter` 表达业务内核与基础设施的边界。重点说明各模块/包的通用功能职责与依赖约束。

> **工程结构 ≠ 部署形态**：单进程的模块化单体，最终只产出一个可执行 jar；Maven 多模块 ≠ 微服务，本示例不是微服务拆分。

## 1. 定位

- **DDD 分层 + 端口适配器（六边形）**：业务内核 `biz` 只依赖 `core`（端口/异常/结果）、`model`（共享语义对象）、`dal-api`（仓储端口）与 `spring-context`（应用服务自动装配），不依赖任何实现模块。
- **模块化单体**：`adapter` 是唯一可执行模块，`common` 提供框架能力，`dal-impl`/`dal-mock`/`integration` 是实现细节，可按需替换。
- 技术栈：JDK 17、Spring Boot 3.4.2、Maven 3.9、Lombok 1.18.36、surefire 3.5.4、UTF-8。
- 包根：`com.example.structure.dddclassicmultimodule`；parent GAV：`com.example.structure.dddclassicmultimodule:ddd-classic-multi-module:1.0.0-SNAPSHOT`。

## 2. 模块/包结构与通用职责

```text
ddd-classic-multi-module/
├── pom.xml                          # parent：packaging=pom，聚合 10 个子模块，统一依赖与版本
├── core/                            # 内核抽象：无 Spring、无业务、无持久化实现
│   └── src/main/java/.../core/      # result / exception / util / trace / event / mq
├── common/                          # 框架能力实现 + 自动装配：不依赖 biz，不放业务规则
│   └── src/main/java/.../common/    # trace / persistence / messaging / scheduling / config
├── model/                           # 共享语义对象：准入制，不放业务实体
│   └── src/main/java/.../model/     # 值对象 / BaseDomainEvent / constants / properties
├── api/                             # 对外契约：DTO + 查询接口，不依赖 Spring Web
│   └── src/main/java/.../api/       # response / dto / query
├── dal-api/                         # 数据访问端口：PO / Qry / Repository 接口，不依赖 Spring/MyBatis
│   └── src/main/java/.../dal/api/   # po / qry / repository
├── dal-impl/                        # 真实仓储实现（示例为内存）：只实现 dal-api，不写业务
│   └── src/main/java/.../dal/impl/  # InMemory*Repository（@Repository + 类级条件 + @Primary）
├── dal-mock/                        # 联调替身仓储实现：与 dal-impl 二选一
│   └── src/main/java/.../dal/mock/  # Mock*Repository（@Repository + 类级条件）
├── integration/                     # 外部系统防腐层：实现 core 出站端口，不依赖 common/biz/dal
│   └── src/main/java/.../integration/
├── biz/                             # 业务内核：应用服务 @Service 自动装配、无 Web、无 ORM
│   └── src/main/java/.../biz/
│       ├── user/                    # 用户上下文
│       │   ├── api/                 # 用例入口：*QueryHandler + cmd/result（不对外）
│       │   ├── app/                 # 用例实现：编排、assembler
│       │   ├── domain/              # 领域模型
│       │   └── event/               # 领域事件
│       ├── org/                     # 部门上下文：api/app/domain/event
│       ├── salary/                  # 薪资上下文：api/app/domain/event
│       └── shared/                  # BizMessages
└── adapter/                         # 唯一可执行模块：Web + 配置，不写业务判断
    ├── src/main/java/.../adapter/
    │   ├── DddClassicApplication.java  # 启动类：唯一 @SpringBootApplication，扫描 adapter/dal/integration/biz
    │   ├── web/
    │   │   ├── controller/            # HTTP 入参绑定/路由：不注入 Repository，不写业务判断
    │   │   ├── facade/                # 实现 api 模块接口：连接 Web 与 biz *QueryHandler
    │   │   ├── dto/                   # Web 层 DTO
    │   │   ├── converter/             # DTO ↔ 业务对象映射
    │   │   └── config/                # GlobalExceptionHandler（需 api.ApiResponse，故在 adapter）
    │   ├── mq/                        # MQ 消费替身
    │   └── job/                       # 定时任务入口
    └── src/main/resources/            # application.yml
```

依赖图（无环，reactor 自动排序）：

```text
adapter ──compile──> api / biz / dal-api / integration / common / core / model
   ├──runtime──> dal-impl（默认 demo.dal.mode=impl）
   └──runtime──> dal-mock（--demo.dal.mode=mock）

biz ──> dal-api ──> model ──> core
  ├──> core
  └──> spring-context
integration ──> core + spring-context
dal-impl / dal-mock ──> dal-api + common ──> core
common ──> core
api ──> core
```

## 3. 模块职责与约束

| 模块 | 职责 | 禁止 |
|---|---|---|
| `core` | `Result`、`BizException`、`ErrorCode`、`StringUtils`、`TraceContext`、`DomainEvent`/`DomainEventPublisher`、`Message`/`MessageSender` | Spring / 业务 / 持久化实现 |
| `common` | trace（`TraceIdFilter`）、persistence（`InMemoryRepository` 基类）、messaging（消息头/日志发送/事件发布）、scheduling（`AbstractJob`）、config（两个 `@AutoConfiguration`） | 被 `biz` 依赖；业务规则 |
| `model` | 值对象（`Currency`/`UserId`/`OrgId`/`Money`）、`BaseDomainEvent`、`ModelConstants` | Web/ORM/传输注解；业务实体 |
| `api` | 对外契约：`ApiResponse`、DTO、三个 `*QueryApi` 接口（由 adapter facade 实现） | Spring Web / Feign / 实现类 |
| `dal-api` | `UserPO`/`OrgPO`/`SalaryPO`、`*Qry`、`*Repository` 接口 | Spring / MyBatis / 实现 |
| `dal-impl` | 内存 `InMemory*Repository`（`@Repository`，`demo.dal.mode=impl`，bean 名 `inMemory*`，`@Primary`） | 被 `biz` 依赖；业务规则 |
| `dal-mock` | 联调替身 `Mock*Repository`（`@Repository`，`demo.dal.mode=mock`，bean 名 `mock*`） | 被 `biz` 依赖；业务规则 |
| `integration` | `OutboundMessageSender` 实现 `core.mq.MessageSender`（外部 MQ ACL）`@Component` | 依赖 `common`/`biz`/`dal-*`；Web/MVC |
| `biz` | 三个上下文的 `api`（*QueryHandler + cmd/result）、`app`（`@Service` 用例实现 + assembler）、`domain`、`event`、`shared/BizMessages` | Web/ORM；依赖 `common`/`dal-impl`/`dal-mock`/`integration`/`adapter`/`api` |
| `adapter` | `DddClassicApplication`、web（controller/facade/dto/converter/config）、mq 消费替身、job、`application.yml` | 业务判断；controller/facade 直接注入 Repository |

关键约束（由模块依赖图强制，不靠评审）：

- `biz → core` 出站端口，`integration` 实现端口：`biz` 不直接依赖 `integration`，避免经 `integration` 传递依赖 `common`。
- `GlobalExceptionHandler` 在 `adapter/web/config`：需要 `api.ApiResponse`，放 `common` 会引入 `common → api`。
- `api` 模块（对外契约）≠ `biz/{ctx}/api`（*QueryHandler + cmd/result）：两层通过 adapter 的 facade/converter 连接，facade 注入 biz `*QueryHandler` 接口，`biz` 不实现 `api` 模块接口。
- `dal-impl` 的 converter 放 `biz/app/assembler`：DAL 不依赖 domain。
- `core`/`model` 用包名约定守住"无 Spring/ORM 注解"；`common` 不做业务；`adapter` 不做业务判断。

DAL 隔离方式：

- `dal-impl` 与 `dal-mock` 是独立模块、独立包、独立 bean 名；主实现标 `@Primary`，mock 不标。
- 类级 `@ConditionalOnProperty(name="demo.dal.mode", havingValue="impl", matchIfMissing=true)` / `havingValue="mock"` 二选一，不使用 `@Profile`；`application.yml` 默认 `impl`。
- `mode` 非法时两个条件都不匹配，缺少 Repository bean，启动失败。

## 4. 请求链路

`GET /api/users/1/profile` 的通用链路：

1. `adapter/web/controller` 绑定入参 → `adapter/web/facade` 实现 `api` 模块接口；
2. facade 转成 `biz.salary.api` 的 cmd 并调用 `biz.salary.api.SalaryQueryHandler`；
3. `app` 经 `dal-api` 端口读取 user/org/salary 数据（`dal-impl` 或 `dal-mock` 提供实现）；
4. `app` 组装 `biz.salary.api` 的 result → converter 转成 `api` 模块 DTO；
5. `ApiResponse.ok(dto)` 包装为统一响应。

异常链路：`BizException` → `adapter/web/config/GlobalExceptionHandler` → 按 `ErrorCode.httpStatus` 输出，body 仍是 `ApiResponse`。

## 5. 优缺点与适用场景

**优点**

- 依赖方向可编译期验证：`biz` 看不见实现，端口/ACL 隔离变化。
- 业务内核可测试、可替换：`biz` 仅用 spring-context 注解自动装配，实现细节隔离在端口后。
- 边界清晰：模块化单体结构可作为单个微服务的内部工程结构，未来拆服务时边界已就绪。

**缺点**

- 模块与样板最多：11 个 pom、95 个 Java 文件，跨模块调试成本高。
- 构建链最长；改 `core`/`api` 会牵连下游。
- 对小项目/简单 CRUD 过度设计。

**适用**：复杂业务、外部依赖多、团队较大、预期未来拆服务的单体。
**不适用**：简单 CRUD、原型、1-3 人小团队、边界尚不清晰的早期项目。

## 6. 构建 / 启动 / 停止

```powershell
cd ddd-classic-multi-module
mvn -q clean verify                                  # 8 条 MockMvc 冒烟
java -jar adapter/target/adapter-1.0.0-SNAPSHOT.jar  # 启动 18084

# 单模块构建
mvn -q -pl biz -am package
mvn -q -pl adapter -am package -DskipTests

# 切换到联调替身仓储
java -jar adapter/target/adapter-1.0.0-SNAPSHOT.jar --demo.dal.mode=mock
```

> 多模块 reactor 下不把 `mvn spring-boot:run` 作为主命令（对 library 模块执行会失败）。

冒烟请求：

```powershell
curl.exe "http://localhost:18084/api/hello?name=%E5%BC%A0%E4%B8%89"
curl.exe "http://localhost:18084/api/users/1"
curl.exe "http://localhost:18084/api/users/1/profile"
curl.exe "http://localhost:18084/api/users/2/profile"
curl.exe "http://localhost:18084/api/orgs/tree"
curl.exe -i "http://localhost:18084/api/users/999"   # 404 {"code":40401,"message":"用户不存在: 999","data":null}
curl.exe -i "http://localhost:18084/api/users/abc"   # 400 {"code":40001,...}
```

停止服务：前台进程 `Ctrl+C`；确认端口释放（应无输出）：

```powershell
Get-NetTCPConnection -LocalPort 18084 -State Listen -ErrorAction SilentlyContinue
```

## 7. 契约 / mock / 错误码 / Lombok

- 4 个只读 GET，响应 `ApiResponse{code, message, data}`，成功 `code=0`、`message="ok"`；`profile.user` 只有 `id/name`（无 `orgId`）。
- mock 数据：用户 `1 张三 → 开发部`、`2 李四 → 测试部`；部门 `1 开发部`、`2 测试部`（均为根）；薪资 `10000 / 8000`；重启重置。
- 错误码：`40001`(400) 参数类型错误、`40401`(404) 用户不存在、`40402`(404) 部门不存在、`40403`(404) 薪资记录不存在、`50001`(500) 部门树结构非法、`50000`(500) 内部错误。
- Lombok：数据类用 `@Data`（DTO/PO/Qry/cmd/result 共 23 个）；`core.Result`/`core.Message`、`model` 值对象/`BaseDomainEvent`、`biz` 的 domain/event 用 `@Getter`/`@EqualsAndHashCode`；`BizException` 仅对 `errorCode` 用 `@Getter`。
- 无 `record`；`integration` 用 `@CommonsLog`（spring-jcl），不引入 slf4j；fat jar 中排除 Lombok。

## 8. 边界自查命令

```powershell
# 11 个 pom（parent + 10 子模块）
Get-ChildItem . -Recurse -Filter pom.xml | Measure-Object

# 唯一可执行：各 1 处
Get-ChildItem . -Recurse -Include *.java | Select-String "@SpringBootApplication"
Get-ChildItem . -Recurse -Include *.java | Select-String "static void main"
Select-String -Path */pom.xml,pom.xml -Pattern "spring-boot-maven-plugin"

# record 0；@Data 23；fat jar 无 lombok
Get-ChildItem . -Recurse -Include *.java | Select-String -Pattern "^\s*(public|private|protected)?\s*record\s"
Get-ChildItem . -Recurse -Include *.java | Select-String -Pattern "^\s*@Data\b"
jar tf adapter/target/adapter-1.0.0-SNAPSHOT.jar | Select-String lombok

# biz 不依赖 common/dal/integration/adapter/api；仅允许 spring-context、禁止 spring-boot-starter
Select-String -Path biz/pom.xml -Pattern "<artifactId>common</artifactId>|<artifactId>dal-impl</artifactId>|<artifactId>dal-mock</artifactId>|<artifactId>integration</artifactId>|<artifactId>adapter</artifactId>|<artifactId>api</artifactId>|spring-boot-starter|mybatis|persistence|feign"
Select-String -Path biz/pom.xml -Pattern "spring-context"

# dal-api 无 Spring/MyBatis；api 无 web/feign；integration 不依赖 common/biz/dal
Select-String -Path dal-api/pom.xml -Pattern "spring|mybatis|persistence"
Select-String -Path api/pom.xml -Pattern "spring|feign|web"
Select-String -Path integration/pom.xml -Pattern "<artifactId>common</artifactId>|<artifactId>biz</artifactId>|<artifactId>dal-impl</artifactId>|<artifactId>dal-mock</artifactId>|web|mvc|mybatis"

# web 层不注入 Repository（应无输出）
Get-ChildItem adapter/src/main/java -Recurse -Include *.java | Where-Object { $_.FullName -match "\\web\\" } | Select-String "Repository"
```

> `core`/`model` 用包名约定守住"无 Spring/ORM 注解"；`common` 不做业务，`adapter` 不做业务判断。

## 9. 范围说明

- 不实现真正的微服务拆分/注册中心/网关/RPC/容器化；不接 DB/MyBatis/MQ/Redis/外部 HTTP；不实现写接口/分页/认证。
- 未实现 `bootstrap.yml`、Spring Cloud；`dal-impl`/`dal-mock` 均为内存实现。
- 本项目只说明 DDD 分层 + 端口适配器下各模块/包的通用功能职责与依赖约束，不展开具体业务实现。