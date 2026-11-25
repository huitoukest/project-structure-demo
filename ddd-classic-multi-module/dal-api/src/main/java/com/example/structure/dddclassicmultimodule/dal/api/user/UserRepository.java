package com.example.structure.dddclassicmultimodule.dal.api.user;

import com.example.structure.dddclassicmultimodule.dal.api.user.po.UserPO;
import com.example.structure.dddclassicmultimodule.dal.api.user.qry.UserQry;
import java.util.Optional;

/**
 * 用户仓储接口：真实项目由 MyBatis Mapper 实现，本示例由内存实现替换。
 */
public interface UserRepository {

    /**
     * 按条件查询用户。
     *
     * @param qry 查询条件
     * @return 用户持久化对象
     */
    Optional<UserPO> findById(UserQry qry);
}
