# project-structure-demo
通过java demo项目来学习和演示分包、分模块的项目结构的演进和适用场景

Maven **模块 / 分包结构**示例集合：用同一套业务（用户、部门、薪资）和同一份 HTTP 契约，分别演示四种常见的 Java 工程组织方式。

> 这是**结构示例**，不是业务脚手架：只读接口、无认证、无数据库、无外部服务，数据全部是内存 mock。

## 1. 四个示例一览

| 目录 | 结构 | 端口 | 一句话说明 | 文档 |
|---|---|---|---|---|
| [`mvc-classic/`](mvc-classic/README.md) | 单 Maven 模块 + 按技术层分包 | 18081 | 最经典的 `controller / service / dao / entity / model / vo` 分层 | [README](mvc-classic/README.md) |
| [`mvc-classic-package-split/`](mvc-classic-package-split/README.md) | 单 Maven 模块 + 按业务域分包 | 18082 | `modules/{user,org,salary}` 各自成套；**没有**跨模块 api 契约包，直连其他模块 DAO/entity 是刻意保留的现状 | [README](mvc-classic-package-split/README.md) |
| [`mvc-classic-multi-biz-module/`](mvc-classic-multi-biz-module/README.md) | **真正的 Maven 多模块**（parent + 8 子模块） | 18083 | `common` + `user-api/user-biz` + `org-api/org-biz` + `salary-api/salary-biz` + `start`，靠 Maven 依赖建立编译期边界 | [README](mvc-classic-multi-biz-module/README.md) |
| [`ddd-classic-multi-module/`](ddd-classic-multi-module/README.md) | **Maven 多模块 + DDD 分层/端口适配器**（parent + 10 子模块） | 18084 | `core/common/model/api/dal-api/dal-impl/dal-mock/integration/biz/adapter`；biz 仅用 spring-context 注解自动装配，adapter 唯一可执行 | [README](ddd-classic-multi-module/README.md) |

> 四个示例都是**单进程**模块化单体，最终各产出一个可执行 jar；**Maven 多模块不是微服务**，**工程结构 ≠ 部署形态**；其中 DDD 示例可作为单个微服务的内部工程结构。


## 2. 技术栈

| 项 | 说明 |
|---|---|
| JDK | 17 |
| Maven | 3.9.x |
| Spring Boot | 3.4.2（`spring-boot-starter-parent`） |
| Lombok | 1.18.36（可执行 jar 中已排除；DDD 的 integration 用 `@CommonsLog`） |
| Web | `spring-boot-starter-web`（内嵌 Tomcat） |
| 测试 | `spring-boot-starter-test` + `maven-surefire-plugin 3.5.4` |
| 存储 | 无数据库；DAO 用 `ConcurrentHashMap` 内存实现，重启重置 |
| 外部依赖 | 无 DB / MQ / Redis / 外部 HTTP |

## 3. 统一业务与契约

四个示例的数据、接口、错误码完全一致（仅端口不同）。

**mock 数据**

| 表 | 数据 |
|---|---|
| 用户 | `1 张三 → 开发部`、`2 李四 → 测试部` |
| 部门 | `1 开发部`、`2 测试部`（均为根部门，parentId=null） |
| 薪资 | `张三 10000`、`李四 8000`（单位：元） |

**接口**

| 请求 | 成功 `data` |
|---|---|
| `GET /api/hello?name=张三` | `"Hello, 张三"`；缺省/空白 → `"Hello, World"` |
| `GET /api/users/1` | `{"id":1,"name":"张三","orgId":1}` |
| `GET /api/users/1/profile` | `{"user":{"id":1,"name":"张三"},"org":{"id":1,"name":"开发部"},"salary":10000}` |
| `GET /api/orgs/tree` | `[{"id":1,"name":"开发部","children":[]},{"id":2,"name":"测试部","children":[]}]` |

统一响应：`ApiResponse{code, message, data}`，成功 `code=0`、`message="ok"`。

错误码：

| code | HTTP | 含义 |
|---|---|---|
| 40001 | 400 | 参数类型错误（如 `/api/users/abc`） |
| 40401 | 404 | 用户不存在（如 `/api/users/999`） |
| 40402 | 404 | 部门不存在（正常种子数据不触发；代码路径可达） |
| 40403 | 404 | 薪资记录不存在（正常种子数据不触发；代码路径可达） |
| 50001 | 500 | 部门树结构非法（正常种子数据不触发；代码路径可达） |
| 50000 | 500 | 内部错误（兜底） |

注意：`profile.user` **只有 `id`、`name`**，不含 `orgId`；`/api/users/{id}` 的 `UserVO` 才含 `orgId`——这是“VO 按用例裁剪”的示例。

## 4. 快速开始

前置：JDK 17、Maven 3.9（IDE 内编辑还需 Lombok 插件 + annotation processing）。

### 4.1 mvc-classic（18081）

```powershell
cd mvc-classic
mvn -q clean verify                  # 8 条 MockMvc 冒烟
mvn spring-boot:run                  # 或 java -jar target/mvc-classic-1.0.0-SNAPSHOT.jar
```

### 4.2 mvc-classic-package-split（18082）

```powershell
cd mvc-classic-package-split
mvn -q clean verify
mvn spring-boot:run                  # 或 java -jar target/mvc-classic-package-split-1.0.0-SNAPSHOT.jar
```

### 4.3 mvc-classic-multi-biz-module（18083，多模块）

```powershell
cd mvc-classic-multi-biz-module
mvn -q clean verify                                        # parent 下构建 8 个子模块
java -jar start/target/start-1.0.0-SNAPSHOT.jar            # 主推：可执行 jar
# 单模块构建示例：mvn -q -pl user-biz -am package
```

> 多模块 reactor 下不建议把 `mvn spring-boot:run` 当主命令（对 library 模块执行会失败）。

### 4.4 ddd-classic-multi-module（18084，DDD 多模块）

```powershell
cd ddd-classic-multi-module
mvn -q clean verify                                        # parent 下构建 10 个子模块
java -jar adapter/target/adapter-1.0.0-SNAPSHOT.jar        # 主推：可执行 jar（adapter 唯一可执行）
# 联调替身仓储：java -jar adapter/target/adapter-1.0.0-SNAPSHOT.jar --demo.dal.mode=mock
# 单模块构建示例：mvn -q -pl biz -am package
```

### 4.5 冒烟请求（以 18081 为例，换端口即对应另外三个项目）

```powershell
curl.exe "http://localhost:18081/api/hello?name=%E5%BC%A0%E4%B8%89"
curl.exe "http://localhost:18081/api/users/1"
curl.exe "http://localhost:18081/api/users/1/profile"
curl.exe "http://localhost:18081/api/users/2/profile"
curl.exe "http://localhost:18081/api/orgs/tree"
curl.exe -i "http://localhost:18081/api/users/999"   # 404 {"code":40401,...}
curl.exe -i "http://localhost:18081/api/users/abc"   # 400 {"code":40001,...}
```

### 4.6 停止服务

前台进程 `Ctrl+C`；停止后确认端口释放（应无输出）：

```powershell
Get-NetTCPConnection -LocalPort 18081,18082,18083,18084 -State Listen -ErrorAction SilentlyContinue
```

端口被占用时可用 `--server.port=其他端口` 覆盖，不要杀未知进程。

## 5. 四种结构对比

| 维度 | 按层分包（classic） | 按域分包（package-split） | Maven 多模块（multi-biz-module） | DDD 多模块（ddd-classic-multi-module） |
|---|---|---|---|---|
| 形态 | 1 pom、按功能分包 | 1 pom、按域分包 | 1 parent + 8 子模块 | 1 parent + 10 子模块 |
| 分包主线 | `controller/service/dao/...` | `modules/<域>/<层>` | 每域拆 `-api` / `-biz` | `core/common/model/api/dal-*/integration/biz/adapter` |
| 编译期边界 | 无 | 无 | 有，独立 artifact 强制依赖图 | 有，`biz` 不可见实现 |
| 跨模块调用 | 直接 import 其他域 DAO/entity | 同左，刻意展示“分包≠解耦” | 只能依赖对方 `-api` | `core` 端口/事件 + `api` 契约 |
| 构建 | 最快，1 个 pom | 快，1 个 pom | reactor 排序，pom 维护成本最高 | reactor 排序，构建链最长 |
| 部署 | 单进程 1 jar | 单进程 1 jar | 单进程 1 jar | 单进程 1 jar，由 `adapter` 启动 |
| 适用规模 | 小项目、入门 | 中小单体、业务域已清晰 | 大团队单应用、需要依赖约束 | 复杂业务、外部依赖多、预期拆服务 |
| 主要缺点 | 包名无边界，service 易腐化 | 包名仍无边界 | 样板多，改 `-api` 牵连下游 | 模块装配样板最多，过度设计风险 |

四者最终均部署为**单进程 1 个 jar**。

## 6. 结构演进（为什么是这四个示例）

- **E1「包内 biz」已评估后砍掉**：只把 `service` 改名为 `biz`、代码原样不动属于伪分层，依赖关系没有变化，没有教学价值。
- **E3 就是本仓库第三个示例**：Maven 的 `api` / `biz` / `start` 结构，用 `-api` 模块的接口作为跨域契约，`biz` 只放实现（没有 classic 中的 `service` 接口 + `impl` 两层）。
- **E4 就是本仓库第四个示例**：DDD 分层 + 端口适配器，`biz` 仅用 spring-context 注解自动装配，仍是单进程模块化单体，不是微服务；该结构可作为单个微服务的内部工程结构。
- **Maven 模块 ≠ 微服务**：四个示例最终都只产出一个可运行 jar，模块边界只是构建期/依赖期约束。
- 参考风格：`yudao`、`pig`、`jeecg` 等项目的 `xxx-api` + `xxx-biz` + `start` 划分；COLA / DDD 已在第四例落地，但 COLA 框架本身未引入，微服务（独立部署、RPC 契约、注册中心/网关）属于后续阅读方向，本仓库未实现。

## 7. 目录与文档

```
project-structure-demo/
├── mvc-classic/                    # 示例一：单模块按层分包（18081）
├── mvc-classic-package-split/      # 示例二：单模块按域分包（18082）
├── mvc-classic-multi-biz-module/   # 示例三：Maven 多模块 api/biz/start（18083）
├── ddd-classic-multi-module/       # 示例四：DDD 分层 + 端口适配器（18084）
├── .gitignore
└── README.md                       # 本文件
```

每个子目录有自己的 `README.md`（结构树、职责、调用链、对比、运行/测试/curl、mock 数据、停止服务）。

不入库的目录：

- `.backup/`：历史回退快照，仅供本地回滚；
- `.code/`：任务档案与评审记录（需求/设计/评审），不属于示例代码；
- `target/`：Maven 构建产物，运行 `mvn clean` 即可清理。

## 8. 约束与边界

- 只读接口；无写接口、分页、认证、CORS、前端、Docker。
- 不接真实数据库 / MyBatis / MQ / Redis / 外部 HTTP；数据为内存 mock，**重启即重置**。
- 不做微服务/注册中心/网关/RPC；不把 Maven 模块当服务。
- 金额单位为 `long` 元；端口 18081/18082/18083/18084 分别对应四个示例。
