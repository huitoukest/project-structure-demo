package com.example.structure.dddclassicmultimodule.adapter.web.facade;

import com.example.structure.dddclassicmultimodule.adapter.web.converter.UserDtoConverter;
import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.user.UserQueryApi;
import com.example.structure.dddclassicmultimodule.api.user.dto.UserDTO;
import com.example.structure.dddclassicmultimodule.biz.user.api.UserQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.user.api.cmd.GetUserCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户契约实现：把 biz 结果转换为对外 DTO，并包装统一响应。
 */
@Component
@RequiredArgsConstructor
public class UserQueryFacade implements UserQueryApi {

    private final UserQueryHandler userQueryHandler;

    private final UserDtoConverter userDtoConverter;

    @Override
    public ApiResponse<UserDTO> getById(Long id) {
        return ApiResponse.ok(userDtoConverter.toDto(userQueryHandler.getUser(new GetUserCmd(id))));
    }
}
