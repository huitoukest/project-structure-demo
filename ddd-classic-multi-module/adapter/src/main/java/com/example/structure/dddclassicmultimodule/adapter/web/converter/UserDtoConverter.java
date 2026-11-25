package com.example.structure.dddclassicmultimodule.adapter.web.converter;

import com.example.structure.dddclassicmultimodule.api.user.dto.UserDTO;
import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;
import org.springframework.stereotype.Component;

/**
 * 用户结果 → 对外 DTO。
 */
@Component
public class UserDtoConverter {

    /**
     * 转换用户结果。
     *
     * @param result 业务结果
     * @return 对外 DTO
     */
    public UserDTO toDto(UserResult result) {
        return new UserDTO(result.getId(), result.getName(), result.getOrgId());
    }
}
