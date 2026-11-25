package com.example.structure.dddclassicmultimodule.adapter.web.converter;

import com.example.structure.dddclassicmultimodule.api.salary.dto.ProfileOrgDTO;
import com.example.structure.dddclassicmultimodule.api.salary.dto.ProfileUserDTO;
import com.example.structure.dddclassicmultimodule.api.salary.dto.SalaryProfileDTO;
import com.example.structure.dddclassicmultimodule.biz.salary.api.result.SalaryProfileResult;
import org.springframework.stereotype.Component;

/**
 * 薪资档案结果 → 对外 DTO。
 */
@Component
public class SalaryDtoConverter {

    /**
     * 转换档案结果。
     *
     * @param result 业务结果
     * @return 对外 DTO
     */
    public SalaryProfileDTO toDto(SalaryProfileResult result) {
        ProfileUserDTO user = new ProfileUserDTO(result.getUser().getId(), result.getUser().getName());
        ProfileOrgDTO org = new ProfileOrgDTO(result.getOrg().getId(), result.getOrg().getName());
        return new SalaryProfileDTO(user, org, result.getSalary());
    }
}
