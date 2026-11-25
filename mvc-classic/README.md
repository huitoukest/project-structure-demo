# mvc-classic

经典 MVC **单 Maven 模块 + 多分包** 结构示例：一个 `src/main/java` 下按 `controller / service / dao / entity / model / vo / common / config` 分包，用内存 mock 数据模拟数据库层。

> 这是**结构示例**，不是业务脚手架：只读接口、无认证、无数据库、无写操作。

## 1. 项目定位

- 演示最传统的 Spring MVC 分层：`Controller → Service → DAO`，一个 Maven 模块、一个 `src` 目录，靠**包名约定**表达分层。
- 业务场景仅用于演示：用户-部门-薪资的只读查询。
- 重点说明各包的功能职责与约束，而不是具体业务实现。

## 2. 技术栈与运行前提

| 项 | 版本/说明 |
|---|---|
| JDK | 17 |
| Maven | 3.9.x |
| Spring Boot | 3.4.2 |
| Web | `spring-boot-starter-web`（内嵌 Tomcat） |
| 测试 | `spring-boot-starter-test` + `maven-surefire-plugin 3.5.4` |
| 数据库 | 无；DAO 用 `ConcurrentHashMap` 内存实现 |
| Lombok | 1.18.36；编译期注解处理，IDE 需插件与 annotation processing |
| 端口 | `18081` |

运行前提：

- **命令行构建/运行**：JDK 17 + Maven 3.9 即可，Lombok 在编译 classpath 完成注解处理。
- **IDE 内编辑**：需安装 Lombok 插件并启用 annotation processing，否则编辑器会标红 getter/构造器。

GAV：`com.example.structure.classic:mvc-classic:1.0.0-SNAPSHOT`，包根 `com.example.structure.classic`。

## 3. 目录结构与包职责

```text
mvc-classic/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/example/structure/classic/   # 根包：启动类与全包扫描
    │   │   ├── common/
    │   │   │   ├── api/                          # 统一响应包装：HTTP 外层信封，隔离 controller 返回格式
    │   │   │   ├── constant/                     # 错误码常量：业务码与 HTTP 状态映射
    │   │   │   └── exception/                    # 业务异常：携带错误码，交由全局异常处理
    │   │   ├── config/                           # 全局基础设施：异常处理等横切能力，不放业务逻辑
    │   │   ├── controller/                       # HTTP 入参绑定/路由：只调用 service，不 import dao，不写业务编排
    │   │   │   ├── common/                       # 按用例/域二级分包：通用入口
    │   │   │   ├── user/                         # 按用例/域二级分包：用户入口
    │   │   │   ├── org/                          # 按用例/域二级分包：组织入口
    │   │   │   └── salary/                       # 按用例/域二级分包：薪资入口
    │   │   ├── service/                          # 用例接口：定义业务能力边界，便于代理/替换实现
    │   │   │   └── impl/                         # 用例实现：校验、编排一个或多个 DAO、组装 model，不直接暴露 entity
    │   │   ├── dao/                              # 存储访问边界：只做数据存取，不写业务规则；真实项目由 Mapper 实现
    │   │   │   └── mem/                          # 内存实现：@Repository 固定种子数据，模拟数据库层
    │   │   ├── entity/                           # 持久化行对象：字段与表结构一致，不做业务裁剪
    │   │   ├── model/                            # service 层聚合模型：跨表/跨 DAO 组合的中间形态，不直接对外
    │   │   └── vo/                               # HTTP 响应对象：按用例裁剪暴露字段，提供 from(...) 映射
    │   └── resources/                            # 应用配置：端口、应用名等运行参数
    └── test/java/com/example/structure/classic/  # 测试包：MockMvc 验证 HTTP 契约
```

## 4. 分层调用方向

```text
Controller 绑定入参
  → Service 接口
  → ServiceImpl 校验/编排一个或多个 DAO
  → DAO 返回 entity（未命中返回 null）
  → ServiceImpl 组装 model
  → VO.from(model) 裁剪响应
  → ApiResponse 统一包装
```

异常链路：`BizException` → `GlobalExceptionHandler` → 按 `ErrorCode.httpStatus` 输出，body 仍是 `ApiResponse`。

## 5. 优缺点与适用场景

**优点**

- 结构直观：包名即分层，新人一眼看懂调用方向；无需理解 Maven 模块与依赖方向。
- 起步成本最低：一个 `pom.xml`、一次构建；IDE 索引与重构最顺畅。
- 分层职责仍在：`entity/model/vo` 分离、service 接口 + impl 保留，结构约束清晰。

**缺点**

- **包级约定没有编译期边界**：`controller` 可以随手 import `dao`，架构约束只能靠评审/工具（ArchUnit、checkstyle）保证。
- **service 直连多个 DAO 易腐化**：当出现多表编排蔓延时，service 层会膨胀，DAO 也容易被塞进业务规则。
- 依赖关系不可裁剪：无法只发布/只测试某个业务域，构建时间随代码量线性增长。

**适用场景**：小体量单体、教学/入门示例、业务域边界尚不清晰的原型。

## 6. 运行 / 测试 / curl（从仓库根执行）

```powershell
cd mvc-classic

# 构建 + 启动测试（MockMvc，8 个用例，不占端口）
mvn -q clean verify

# 启动服务（默认 18081；端口被占用时可覆盖）
mvn spring-boot:run
# 或：mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=18082
```

另开终端（Windows 控制台是 GBK，curl 中文参数用 percent-encoded）：

```powershell
curl.exe "http://localhost:18081/api/hello?name=%E5%BC%A0%E4%B8%89"
# {"code":0,"message":"ok","data":"Hello, 张三"}

curl.exe "http://localhost:18081/api/users/1"
# {"code":0,"message":"ok","data":{"id":1,"name":"张三","orgId":1}}

curl.exe "http://localhost:18081/api/users/1/profile"
# {"code":0,"message":"ok","data":{"user":{"id":1,"name":"张三"},"org":{"id":1,"name":"开发部"},"salary":10000}}

curl.exe "http://localhost:18081/api/orgs/tree"
# {"code":0,"message":"ok","data":[{"id":1,"name":"开发部","children":[]},{"id":2,"name":"测试部","children":[]}]}

# 错误场景（HTTP 状态码需用 -i 或 -w 查看）
curl.exe -i "http://localhost:18081/api/users/999"   # 404 {"code":40401,"message":"用户不存在: 999","data":null}
curl.exe -i "http://localhost:18081/api/users/abc"   # 400 {"code":40001,...}
```

## 7. mock 数据与重置

| 表 | 数据 |
|---|---|
| `org` | `1 开发部 parentId=null`、`2 测试部 parentId=null` |
| `user` | `1 张三 orgId=1`、`2 李四 orgId=2` |
| `salary_record` | `1 → 10000`、`2 → 8000`（单位：元） |

数据写在 `dao/mem/InMemory*Dao` 构造器中，进程启动时写入 `ConcurrentHashMap`；**重启即重置**，无持久化。

错误码：`40001`(400) 参数类型错误、`40401`(404) 用户不存在、`40402`(404) 部门不存在、`40403`(404) 薪资记录不存在、`50001`(500) 部门树结构非法、`50000`(500) 内部错误。

## 8. 停止服务

- 前台运行 `mvn spring-boot:run` 时按 `Ctrl+C`；
- 停止后确认端口已释放（应无输出）：

```powershell
netstat -ano | findstr ":18081" | findstr "LISTENING"
# 或
Get-NetTCPConnection -LocalPort 18081 -State Listen -ErrorAction SilentlyContinue
```

> 端口冲突时用 `-Dspring-boot.run.arguments=--server.port=其他端口` 覆盖，不要杀未知进程。