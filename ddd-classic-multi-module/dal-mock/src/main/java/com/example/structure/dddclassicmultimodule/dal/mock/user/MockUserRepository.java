package com.example.structure.dddclassicmultimodule.dal.mock.user;

import com.example.structure.dddclassicmultimodule.common.persistence.InMemoryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.user.UserRepository;
import com.example.structure.dddclassicmultimodule.dal.api.user.po.UserPO;
import com.example.structure.dddclassicmultimodule.dal.api.user.qry.UserQry;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * 用户仓储联调替身：与真实实现种子一致，独立实现，不依赖 dal-impl。
 */
@Repository
@ConditionalOnProperty(name = "demo.dal.mode", havingValue = "mock")
public class MockUserRepository extends InMemoryRepository<UserPO, Long> implements UserRepository {

    public MockUserRepository() {
        save(1L, new UserPO(1L, "张三", 1L));
        save(2L, new UserPO(2L, "李四", 2L));
    }

    @Override
    public Optional<UserPO> findById(UserQry qry) {
        return find(qry.getId().getValue());
    }
}
