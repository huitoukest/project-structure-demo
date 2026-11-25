package com.example.structure.multibizmodule.org.api;

import java.util.List;

/**
 * 部门域对外契约：其他模块只能通过本接口访问部门能力。
 */
public interface OrgApi {

    /**
     * 按主键查询部门摘要。
     *
     * @param id 部门主键
     * @return 部门摘要
     */
    OrgBrief getById(Long id);

    /**
     * 查询部门树。
     *
     * @return 部门树节点列表
     */
    List<OrgNode> tree();
}
