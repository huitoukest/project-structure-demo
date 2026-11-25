package com.example.structure.packagesplit.modules.salary.dao.mem;

import com.example.structure.packagesplit.modules.salary.dao.SalaryDao;
import com.example.structure.packagesplit.modules.salary.entity.SalaryRecord;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 薪资 DAO 内存实现：以 userId 为业务键，固定种子数据。
 */
@Repository
public class InMemorySalaryDao implements SalaryDao {

    private static final long ZHANG_SAN_SALARY = 10_000L;
    private static final long LI_SI_SALARY = 8_000L;

    private final Map<Long, SalaryRecord> salaries = new ConcurrentHashMap<>();

    public InMemorySalaryDao() {
        salaries.put(1L, new SalaryRecord(1L, ZHANG_SAN_SALARY));
        salaries.put(2L, new SalaryRecord(2L, LI_SI_SALARY));
    }

    /**
     * 按用户主键查询薪资记录，未命中返回 null。
     *
     * @param userId 用户主键
     * @return 薪资行对象
     */
    @Override
    public SalaryRecord findByUserId(Long userId) {
        return salaries.get(userId);
    }
}
