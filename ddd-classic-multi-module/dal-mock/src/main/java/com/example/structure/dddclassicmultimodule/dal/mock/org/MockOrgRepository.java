package com.example.structure.dddclassicmultimodule.dal.mock.org;

import com.example.structure.dddclassicmultimodule.common.persistence.InMemoryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.org.OrgRepository;
import com.example.structure.dddclassicmultimodule.dal.api.org.po.OrgPO;
import com.example.structure.dddclassicmultimodule.dal.api.org.qry.OrgQry;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * 部门仓储联调替身：findAll 按主键升序，支持按父部门过滤。
 */
@Repository
@ConditionalOnProperty(name = "demo.dal.mode", havingValue = "mock")
public class MockOrgRepository extends InMemoryRepository<OrgPO, Long> implements OrgRepository {

    public MockOrgRepository() {
        save(1L, new OrgPO(1L, "开发部", null));
        save(2L, new OrgPO(2L, "测试部", null));
    }

    @Override
    public Optional<OrgPO> findById(OrgId id) {
        return find(id.getValue());
    }

    @Override
    public List<OrgPO> findAll(OrgQry qry) {
        List<OrgPO> orgs = findAll().stream()
            .sorted(Comparator.comparing(OrgPO::getId))
            .toList();
        if (qry == null || qry.getParentId() == null) {
            return orgs;
        }
        Long parentId = qry.getParentId().getValue();
        return orgs.stream()
            .filter(org -> parentId.equals(org.getParentId()))
            .toList();
    }
}
