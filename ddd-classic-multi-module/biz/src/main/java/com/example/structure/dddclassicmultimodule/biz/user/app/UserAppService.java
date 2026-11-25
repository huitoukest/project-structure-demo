package com.example.structure.dddclassicmultimodule.biz.user.app;

import com.example.structure.dddclassicmultimodule.biz.shared.BizMessages;
import com.example.structure.dddclassicmultimodule.biz.user.api.UserQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.user.api.cmd.GetUserCmd;
import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;
import com.example.structure.dddclassicmultimodule.biz.user.app.assembler.UserAssembler;
import com.example.structure.dddclassicmultimodule.biz.user.domain.User;
import com.example.structure.dddclassicmultimodule.biz.user.event.UserQueriedEvent;
import com.example.structure.dddclassicmultimodule.core.constant.ErrorCode;
import com.example.structure.dddclassicmultimodule.core.event.DomainEventPublisher;
import com.example.structure.dddclassicmultimodule.core.exception.BizException;
import com.example.structure.dddclassicmultimodule.dal.api.user.UserRepository;
import com.example.structure.dddclassicmultimodule.dal.api.user.po.UserPO;
import com.example.structure.dddclassicmultimodule.dal.api.user.qry.UserQry;
import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户应用服务：查询仓储、转换领域对象、发布查询事件。
 */
@Service
@RequiredArgsConstructor
public class UserAppService implements UserQueryHandler {

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public UserResult getUser(GetUserCmd cmd) {
        UserPO po = userRepository.findById(new UserQry(UserId.of(cmd.getId())))
            .orElseThrow(() -> new BizException(ErrorCode.USER_NOT_FOUND, BizMessages.userNotFound(cmd.getId())));
        User user = userAssembler.toDomain(po);
        domainEventPublisher.publish(new UserQueriedEvent(user.getUserId()));
        return userAssembler.toResult(user);
    }
}
