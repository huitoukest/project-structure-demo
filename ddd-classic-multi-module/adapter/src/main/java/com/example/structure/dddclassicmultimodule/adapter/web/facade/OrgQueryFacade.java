package com.example.structure.dddclassicmultimodule.adapter.web.facade;

import com.example.structure.dddclassicmultimodule.adapter.web.converter.OrgDtoConverter;
import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.org.OrgQueryApi;
import com.example.structure.dddclassicmultimodule.api.org.dto.OrgNodeDTO;
import com.example.structure.dddclassicmultimodule.biz.org.api.OrgQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgTreeCmd;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门契约实现：把 biz 树结果转换为对外 DTO。
 */
@Component
@RequiredArgsConstructor
public class OrgQueryFacade implements OrgQueryApi {

    private final OrgQueryHandler orgQueryHandler;

    private final OrgDtoConverter orgDtoConverter;

    @Override
    public ApiResponse<List<OrgNodeDTO>> tree() {
        return ApiResponse.ok(orgDtoConverter.toDtoList(orgQueryHandler.getTree(new GetOrgTreeCmd())));
    }
}
