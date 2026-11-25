package com.example.structure.dddclassicmultimodule.dal.impl.salary;

import com.example.structure.dddclassicmultimodule.common.persistence.InMemoryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.salary.SalaryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.salary.po.SalaryPO;
import com.example.structure.dddclassicmultimodule.dal.api.salary.qry.SalaryQry;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * 薪资仓储内存实现：以用户主键为业务键。
 */
@Repository
@Primary
@ConditionalOnProperty(name = "demo.dal.mode", havingValue = "impl", matchIfMissing = true)
public class InMemorySalaryRepository extends InMemoryRepository<SalaryPO, Long> implements SalaryRepository {

    private static final long ZHANG_SAN_SALARY = 10_000L;
    private static final long LI_SI_SALARY = 8_000L;

    public InMemorySalaryRepository() {
        save(1L, new SalaryPO(1L, ZHANG_SAN_SALARY));
        save(2L, new SalaryPO(2L, LI_SI_SALARY));
    }

    @Override
    public Optional<SalaryPO> findByUserId(SalaryQry qry) {
        return find(qry.getUserId().getValue());
    }
}
