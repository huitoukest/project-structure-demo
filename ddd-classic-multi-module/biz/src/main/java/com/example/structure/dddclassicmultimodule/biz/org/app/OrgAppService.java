package com.example.structure.dddclassicmultimodule.biz.org.app;

import com.example.structure.dddclassicmultimodule.biz.org.api.OrgQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgCmd;
import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgTreeCmd;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgNodeResult;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgResult;
import com.example.structure.dddclassicmultimodule.biz.org.app.assembler.OrgAssembler;
import com.example.structure.dddclassicmultimodule.biz.org.domain.Org;
import com.example.structure.dddclassicmultimodule.biz.org.domain.OrgNode;
import com.example.structure.dddclassicmultimodule.biz.org.domain.OrgTreeBuilder;
import com.example.structure.dddclassicmultimodule.biz.shared.BizMessages;
import com.example.structure.dddclassicmultimodule.core.constant.ErrorCode;
import com.example.structure.dddclassicmultimodule.core.exception.BizException;
import com.example.structure.dddclassicmultimodule.dal.api.org.OrgRepository;
import com.example.structure.dddclassicmultimodule.dal.api.org.po.OrgPO;
import com.example.structure.dddclassicmultimodule.dal.api.org.qry.OrgQry;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门应用服务：查询部门、组装部门树。
 */
@Service
@RequiredArgsConstructor
public class OrgAppService implements OrgQueryHandler {

    private final OrgRepository orgRepository;

    private final OrgTreeBuilder orgTreeBuilder;

    private final OrgAssembler orgAssembler;

    @Override
    public OrgResult getById(GetOrgCmd cmd) {
        OrgPO po = orgRepository.findById(OrgId.of(cmd.getId()))
            .orElseThrow(() -> new BizException(ErrorCode.ORG_NOT_FOUND, BizMessages.orgNotFound(cmd.getId())));
        return orgAssembler.toResult(orgAssembler.toDomain(po));
    }

    @Override
    public List<OrgNodeResult> getTree(GetOrgTreeCmd cmd) {
        List<Org> orgs = orgRepository.findAll(new OrgQry(null)).stream()
            .map(orgAssembler::toDomain)
            .toList();
        return orgTreeBuilder.build(orgs).stream()
            .map(orgAssembler::toNodeResult)
            .toList();
    }
}
