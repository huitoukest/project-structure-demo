package com.example.structure.dddclassicmultimodule.api.org;

import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.org.dto.OrgNodeDTO;
import java.util.List;

/**
 * 部门查询契约：由适配层的 facade 实现。
 */
public interface OrgQueryApi {

    /**
     * 查询部门树。
     *
     * @return 统一响应，data 为根节点列表
     */
    ApiResponse<List<OrgNodeDTO>> tree();
}
