package com.example.structure.dddclassicmultimodule.dal.api.org;

import com.example.structure.dddclassicmultimodule.dal.api.org.po.OrgPO;
import com.example.structure.dddclassicmultimodule.dal.api.org.qry.OrgQry;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.List;
import java.util.Optional;

/**
 * 部门仓储接口：查询结果按主键升序。
 */
public interface OrgRepository {

    /**
     * 按部门标识查询。
     *
     * @param id 部门标识
     * @return 部门持久化对象
     */
    Optional<OrgPO> findById(OrgId id);

    /**
     * 按条件查询部门列表。
     *
     * @param qry 查询条件，parentId 为 null 表示全部
     * @return 部门持久化对象列表
     */
    List<OrgPO> findAll(OrgQry qry);
}
