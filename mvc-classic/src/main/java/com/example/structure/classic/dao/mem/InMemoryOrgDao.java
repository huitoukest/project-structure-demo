package com.example.structure.classic.dao.mem;

import com.example.structure.classic.dao.OrgDao;
import com.example.structure.classic.entity.Org;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 部门 DAO 内存实现：固定种子数据，findAll 按主键升序返回不可变列表。
 */
@Repository
public class InMemoryOrgDao implements OrgDao {

    private final Map<Long, Org> orgs = new ConcurrentHashMap<>();

    public InMemoryOrgDao() {
        orgs.put(1L, new Org(1L, "开发部", null));
        orgs.put(2L, new Org(2L, "测试部", null));
    }

    /**
     * 按主键查询部门，未命中返回 null。
     *
     * @param id 部门主键
     * @return 部门行对象
     */
    @Override
    public Org findById(Long id) {
        return orgs.get(id);
    }

    /**
     * 查询全部部门，按主键升序。
     *
     * @return 部门行对象列表
     */
    @Override
    public List<Org> findAll() {
        return orgs.values().stream()
            .sorted(Comparator.comparing(Org::getId))
            .toList();
    }
}
