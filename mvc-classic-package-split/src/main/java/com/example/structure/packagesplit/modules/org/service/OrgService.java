package com.example.structure.packagesplit.modules.org.service;

import com.example.structure.packagesplit.modules.org.model.OrgTreeModel;
import java.util.List;

/**
 * 部门领域服务。
 */
public interface OrgService {

    /**
     * 查询部门树，service 内组装并校验父子关系。
     *
     * @return 部门树模型
     */
    List<OrgTreeModel> tree();
}
