package com.example.structure.dddclassicmultimodule.biz.user.app.assembler;

import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;
import com.example.structure.dddclassicmultimodule.biz.user.domain.User;
import com.example.structure.dddclassicmultimodule.dal.api.user.po.UserPO;
import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import org.springframework.stereotype.Component;

/**
 * 用户装配器：持久化对象 ↔ 领域对象 ↔ 查询结果。
 */
@Component
public class UserAssembler {

    /**
     * 持久化对象转领域对象。
     *
     * @param po 用户持久化对象
     * @return 用户领域对象
     */
    public User toDomain(UserPO po) {
        return new User(UserId.of(po.getId()), po.getName(),
            po.getOrgId() == null ? null : OrgId.of(po.getOrgId()));
    }

    /**
     * 领域对象转查询结果。
     *
     * @param user 用户领域对象
     * @return 用户查询结果
     */
    public UserResult toResult(User user) {
        return new UserResult(user.getUserId().getValue(), user.getName(),
            user.getOrgId() == null ? null : user.getOrgId().getValue());
    }
}
