package com.example.structure.dddclassicmultimodule.common.persistence;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存仓储基类：以内存 Map 模拟持久化，重启重置。
 *
 * @param <T>  持久化对象类型
 * @param <ID> 主键类型
 */
public abstract class InMemoryRepository<T, ID> {

    private final Map<ID, T> store = new ConcurrentHashMap<>(16);

    /**
     * 写入或覆盖一行。
     *
     * @param id    主键
     * @param value 持久化对象
     */
    public void save(ID id, T value) {
        store.put(id, value);
    }

    /**
     * 按主键查询。
     *
     * @param id 主键
     * @return 持久化对象
     */
    public Optional<T> find(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * 查询全部行（顺序不保证）。
     *
     * @return 持久化对象列表
     */
    public List<T> findAll() {
        return List.copyOf(store.values());
    }
}
