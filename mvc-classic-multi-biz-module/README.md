# mvc-classic-multi-biz-module

**真正的 Maven 多模块**结构示例：1 个 parent（`packaging=pom`）+ 8 个子模块，按 `common` + 每个业务域的 `-api` / `-biz` + `start` 组织。重点说明多模块下各模块/包的通用功能职责与依赖约束。

> **Maven 模块 ≠ 微服务**：这里仍然只有 1 个可运行 jar（`start`），模块只是构建期与依赖期的边界。

## 1. 定位

- 模块划分：`common`、`user-api`/`user-biz`、`org-api`/`org-biz`、`salary-api`/`salary-biz`、`start`。
- `api` = 对外契约（接口 + DTO）；`biz` = 业务实现（controller/service/dao/entity/vo），实现本域 `api`；`start` = 装配、配置、启动、冒烟测试。
- 跨域访问**只能经过对方 `-api`**，由 Maven 依赖图保证没有反向依赖。
- 业务场景仅用于演示：用户-部门-薪资的只读查询。

## 2. 模块/包结构与通用职责

```text
mvc-classic-multi-biz-module/
├── pom.xml                         # parent：packaging=pom，聚合 8 个子模块，统一依赖与版本
├── README.md
├── common/                         # 通用基础模块：不依赖 Spring Web，供 biz/start 复用
│   └── src/main/java/.../common/
│       ├── api/                    # 统一响应包装：HTTP 外层信封，隔离 controller 返回格式
│       ├── constant/               # 错误码常量：业务码与 HTTP 状态映射
│       └── exception/              # 业务异常：携带错误码，交由全局异常处理
├── user-api/                       # 用户域契约模块：只放接口与 DTO，不依赖 Spring Web/common
│   └── src/main/java/.../user/api/ # 对外契约与 DTO；其他模块只能经此跨域，不得暴露实现
├── user-biz/                       # 用户域实现模块：实现 user-api，不依赖其他 biz
│   └── src/main/java/.../user/biz/
│       ├── controller/             # HTTP 入参绑定/路由：只调用 service，不 import dao
│       ├── service/                # 用例实现：校验、编排 DAO、组装 VO；实现 user-api 契约
│       ├── dao/                    # 存储访问边界：只做数据存取，不写业务规则
│       ├── entity/                 # 持久化行对象：字段与表结构一致，不做业务裁剪
│       └── vo/                     # HTTP 响应对象：按用例裁剪字段，提供 from(...) 映射
├── org-api/                        # 部门域契约模块：只放接口与 DTO，不依赖 Spring Web/common
│   └── src/main/java/.../org/api/
├── org-biz/                        # 部门域实现模块：实现 org-api，不依赖其他 biz
│   └── src/main/java/.../org/biz/  # controller/service/dao/entity/vo
├── salary-api/                     # 薪资域契约模块：只放接口与 DTO，不依赖 Spring Web/common
│   └── src/main/java/.../salary/api/
├── salary-biz/                     # 薪资域实现模块：实现 salary-api；跨域只依赖 user-api/org-api
│   └── src/main/java/.../salary/biz/ # controller/service/dao/entity/vo
└── start/                          # 唯一启动模块：装配、配置、启动、测试；不写业务逻辑
    ├── src/main/java/.../start/
    │   ├── Application.java        # 启动类：唯一 @SpringBootApplication
    │   ├── config/                 # Web 装配相关：全局异常处理等横切能力
    │   └── controller/             # 系统连通性入口；不放业务编排
    ├── src/main/resources/         # 应用配置：端口、应用名等运行参数
    └── src/test/java/.../          # 测试包：MockMvc 验证 HTTP 契约
```

依赖图（无环，reactor 自动排序）：

```text
common   ◀── user-biz / org-biz / salary-biz / start
user-api ◀── user-biz ◀──┐
org-api  ◀── org-biz  ◀──┼── start
salary-api ◀ salary-biz ◀┘
salary-biz ──▶ user-api / org-api   （只依赖 api，不依赖 user-biz/org-biz）
```

## 3. api / biz / start 的通用职责与约束

- **api**：只能放接口与 DTO，**不依赖 Spring Web、不依赖 common**；其他模块编译期只能看到这一层。跨域取值必须走对方 `-api`，若想直接 import 对方 `-biz` 的 DAO/entity，必须先加 Maven 依赖，这一步会被评审/依赖检查发现。
- **biz**：本域实现，包含 controller/service/dao/entity/vo；以 `-api` 接口作契约，biz 只放实现类，避免同名接口与 impl 重复。controller 只调用 service，不 import dao；dao 只做存取，不写业务规则。
- **start**：装配 + 配置 + 启动 + 测试；除系统连通性入口外不写业务逻辑；全局异常处理器也在 start，因为它是 Web 装配的一部分。
- 术语提醒：开源项目里的 `xxx-biz` 通常指「业务实现模块」（内部含 controller/service/dal），不是 DDD 意义上的 `biz` **层**。本项目同样如此。

## 4. 构建 / 启动（在 parent 目录执行，命令从仓库根开始）

```powershell
cd mvc-classic-multi-biz-module

# 全量构建 + 测试（start 模块 8 条 MockMvc）
mvn -q clean verify

# 启动（主推：可执行 fat jar）
java -jar start/target/start-1.0.0-SNAPSHOT.jar

# 只构建某个业务模块及其上游（示例：user-biz）
mvn -q -pl user-biz -am package

# 备选：只打包 start 及其依赖，跳过测试
mvn -q -pl start -am package -DskipTests
```

> 多模块 reactor 下不建议把 `mvn spring-boot:run` 当主命令（对 library 模块执行会失败）；用 jar 启动最直观。

## 5. 契约 / 数据 / Lombok

| 接口 | 成功 data |
|---|---|
| `GET /api/hello?name=张三` | `"Hello, 张三"`；缺省/空白 → `"Hello, World"` |
| `GET /api/users/1` | `{"id":1,"name":"张三","orgId":1}` |
| `GET /api/users/1/profile` | `{"user":{"id":1,"name":"张三"},"org":{"id":1,"name":"开发部"},"salary":10000}` |
| `GET /api/orgs/tree` | `[{"id":1,"name":"开发部","children":[]},{"id":2,"name":"测试部","children":[]}]` |

错误码：`40001`(400) 参数类型错误、`40401`(404) 用户不存在、`40402`(404) 部门不存在、`40403`(404) 薪资记录不存在、`50001`(500) 部门树结构非法、`50000`(500) 内部错误；404/400 的 body 仍是 `ApiResponse`。

mock 数据（内存 `ConcurrentHashMap`，重启重置）：

| 表 | 数据 |
|---|---|
| `org` | `1 开发部 parentId=null`、`2 测试部 parentId=null` |
| `user` | `1 张三 orgId=1`、`2 李四 orgId=2` |
| `salary_record` | `1 → 10000`、`2 → 8000`（元） |

Lombok（编译期注解处理，parent 统一 optional）：

- 命令行 `mvn` 构建只需 Lombok 在编译 classpath，无需额外安装；
- IDE 编辑需要安装 Lombok 插件并启用 annotation processing；
- `start` 的 `spring-boot-maven-plugin` 配了 `<excludes>`，可执行 jar 中不含 Lombok。

curl 示例：

```powershell
curl.exe "http://localhost:18083/api/hello?name=%E5%BC%A0%E4%B8%89"
curl.exe "http://localhost:18083/api/users/1"
curl.exe "http://localhost:18083/api/users/1/profile"
curl.exe "http://localhost:18083/api/users/2/profile"
curl.exe "http://localhost:18083/api/orgs/tree"
curl.exe -i "http://localhost:18083/api/users/999"   # 404 {"code":40401,...}
curl.exe -i "http://localhost:18083/api/users/abc"   # 400 {"code":40001,...}
```

## 6. 优缺点与适用场景

**优点**

- 编译期边界：`salary-biz` 没有依赖 `user-biz` 就无法 import 其 DAO/entity，跨域只能走 `-api`；依赖图由 Maven 强制。
- 独立构建：`mvn -pl user-biz -am package` 只构建一个域及其上游，模块可独立测试与复用。
- 契约先行：`-api` 只有接口 + DTO，便于未来替换实现（本地实现 → RPC）。

**缺点**

- 样板成本：9 个 pom、依赖管理、`start` 装配；小项目会觉得重。
- 变更牵连：改 `-api` 的 DTO 会触发所有下游模块重编译。
- 仍然单进程：模块边界不等于部署边界，跨模块调用仍是本地方法调用，没有网络隔离/容错。

**适用场景**：单应用、多团队并行、业务域稳定且需要依赖约束；需要独立部署/扩缩容时再演进到微服务。

## 7. 边界自查命令

```powershell
cd mvc-classic-multi-biz-module

# 1) 9 个 pom（parent + 8 子模块）
Get-ChildItem . -Recurse -Filter pom.xml

# 2) salary-biz 不依赖其他 biz（应无输出）
Select-String -Path salary-biz/pom.xml -Pattern "user-biz|org-biz"

# 3) api 模块不依赖 common / web（应无输出）
Select-String -Path user-api/pom.xml,org-api/pom.xml,salary-api/pom.xml -Pattern "spring-boot-starter-web|<artifactId>common</artifactId>"

# 4) record 应为 0；@Data 应为 13
Get-ChildItem . -Recurse -Include *.java | Select-String "^\s*(public\s+)?record\s"
Get-ChildItem . -Recurse -Include *.java | Select-String -SimpleMatch "@Data"

# 5) fat jar 不含 Lombok（应无输出）
jar tf start/target/start-1.0.0-SNAPSHOT.jar | Select-String lombok

# 6) 仅 start 有 @SpringBootApplication（应仅 1 个文件命中）
Get-ChildItem . -Recurse -Include *.java | Select-String -SimpleMatch "@SpringBootApplication"
```

停止服务后确认端口释放：

```powershell
Get-NetTCPConnection -LocalPort 18083 -State Listen -ErrorAction SilentlyContinue
```

## 8. 范围说明

- 未实现微服务/注册中心/网关/RPC；未接数据库/MyBatis/MQ/Redis/外部 HTTP；只读接口，无认证/分页/写操作。
- 本项目只说明 Maven 多模块下 `api` / `biz` / `start` 的通用功能职责与依赖约束，不展开具体业务实现。