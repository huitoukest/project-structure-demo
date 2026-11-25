package com.example.structure.dddclassicmultimodule.biz.salary.app;

import com.example.structure.dddclassicmultimodule.biz.org.api.OrgQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgCmd;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgResult;
import com.example.structure.dddclassicmultimodule.biz.salary.api.SalaryQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.salary.api.cmd.GetSalaryProfileCmd;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.SalaryProfileResult;
import com.example.structure.dddclassicmultimodule.biz.salary.app.assembler.SalaryAssembler;
import com.example.structure.dddclassicmultimodule.biz.salary.domain.SalaryRecord;
import com.example.structure.dddclassicmultimodule.biz.salary.event.SalaryProfileViewedEvent;
import com.example.structure.dddclassicmultimodule.biz.shared.BizMessages;
import com.example.structure.dddclassicmultimodule.biz.user.api.UserQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.user.api.cmd.GetUserCmd;
import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;
import com.example.structure.dddclassicmultimodule.core.constant.ErrorCode;
import com.example.structure.dddclassicmultimodule.core.event.DomainEventPublisher;
import com.example.structure.dddclassicmultimodule.core.exception.BizException;
import com.example.structure.dddclassicmultimodule.dal.api.salary.SalaryRepository;
import com.example.structure.dddclassicmultimodule.dal.api.salary.po.SalaryPO;
import com.example.structure.dddclassicmultimodule.dal.api.salary.qry.SalaryQry;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 薪资应用服务：编排用户、部门、薪资三个仓储，按 40401 → 40402 → 40403 顺序校验。
 */
@Service
@RequiredArgsConstructor
public class SalaryAppService implements SalaryQueryHandler {

    private final SalaryRepository salaryRepository;

    private final UserQueryHandler userQueryHandler;

    private final OrgQueryHandler orgQueryHandler;

    private final SalaryAssembler salaryAssembler;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public SalaryProfileResult getProfile(GetSalaryProfileCmd cmd) {
        UserResult user = userQueryHandler.getUser(new GetUserCmd(cmd.getUserId()));
        if (user.getOrgId() == null) {
            throw new BizException(ErrorCode.ORG_NOT_FOUND, BizMessages.orgNotFound(null));
        }
        OrgResult org = orgQueryHandler.getById(new GetOrgCmd(user.getOrgId()));
        SalaryPO salary = salaryRepository.findByUserId(new SalaryQry(UserId.of(cmd.getUserId())))
            .orElseThrow(() -> new BizException(ErrorCode.SALARY_NOT_FOUND, BizMessages.salaryNotFound(cmd.getUserId())));
        SalaryRecord record = salaryAssembler.toDomain(salary);
        domainEventPublisher.publish(new SalaryProfileViewedEvent(record.getUserId(), OrgId.of(org.getId())));
        return salaryAssembler.toResult(user, org, record);
    }
}
