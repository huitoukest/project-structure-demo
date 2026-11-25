package com.example.structure.classic.dao;

import com.example.structure.classic.entity.Org;
import java.util.List;

/**
 * 部门存储访问接口。
 */
public interface OrgDao {

    /**
     * 按主键查询部门。
     *
     * @param id 部门主键
     * @return 部门行对象，未命中时返回 null
     */
    Org findById(Long id);

    /**
     * 查询全部部门，按主键升序返回不可变列表。
     *
     * @return 部门行对象列表
     */
    List<Org> findAll();
}
