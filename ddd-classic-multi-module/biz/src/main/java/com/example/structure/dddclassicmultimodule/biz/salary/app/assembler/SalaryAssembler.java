package com.example.structure.dddclassicmultimodule.biz.salary.app.assembler;

import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgResult;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.ProfileOrgResult;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.ProfileUserResult;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.SalaryProfileResult;
import com.example.structure.dddclassicmultimodule.biz.salary.domain.SalaryRecord;
import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;
import com.example.structure.dddclassicmultimodule.dal.api.salary.po.SalaryPO;
import com.example.structure.dddclassicmultimodule.model.value.Money;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import org.springframework.stereotype.Component;

/**
 * 薪资装配器：持久化对象 → 领域对象 → 档案结果。
 */
@Component
public class SalaryAssembler {

    /**
     * 持久化对象转领域对象。
     *
     * @param po 薪资持久化对象
     * @return 薪资记录
     */
    public SalaryRecord toDomain(SalaryPO po) {
        return new SalaryRecord(UserId.of(po.getUserId()), Money.ofCny(po.getSalary()));
    }

    /**
     * 组装档案结果。
     *
     * @param user   用户结果
     * @param org    部门结果
     * @param record 薪资记录
     * @return 档案结果
     */
    public SalaryProfileResult toResult(UserResult user, OrgResult org, SalaryRecord record) {
        ProfileUserResult profileUser = new ProfileUserResult(user.getId(), user.getName());
        ProfileOrgResult profileOrg = new ProfileOrgResult(org.getId(), org.getName());
        return new SalaryProfileResult(profileUser, profileOrg, record.getSalary().getAmount());
    }
}
