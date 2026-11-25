package com.example.structure.dddclassicmultimodule.dal.impl.user;

import com.example.structure.dddclassicmultimodule.common.persistence.InMemoryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.user.UserRepository;
import com.example.structure.dddclassicmultimodule.dal.api.user.po.UserPO;
import com.example.structure.dddclassicmultimodule.dal.api.user.qry.UserQry;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * 用户仓储内存实现：模拟 Mapper，固定种子数据，重启重置。
 */
@Repository
@Primary
@ConditionalOnProperty(name = "demo.dal.mode", havingValue = "impl", matchIfMissing = true)
public class InMemoryUserRepository extends InMemoryRepository<UserPO, Long> implements UserRepository {

    public InMemoryUserRepository() {
        save(1L, new UserPO(1L, "张三", 1L));
        save(2L, new UserPO(2L, "李四", 2L));
    }

    @Override
    public Optional<UserPO> findById(UserQry qry) {
        return find(qry.getId().getValue());
    }
}
