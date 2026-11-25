# mapper 目录说明

真实项目把 MyBatis Mapper XML 放在 `src/main/resources/mapper/`；本示例使用内存实现（`dal-impl/.../InMemory*Repository`），没有 SQL 映射文件。

切换到 `dal-mock` 时使用 `--demo.dal.mode=mock`，两套实现种子数据一致，用于联调替身演示。
