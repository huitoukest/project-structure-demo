package com.example.structure.dddclassicmultimodule.adapter.web.facade;

import com.example.structure.dddclassicmultimodule.adapter.web.converter.SalaryDtoConverter;
import com.example.structure.dddclassicmultimodule.api.core.ApiResponse;
import com.example.structure.dddclassicmultimodule.api.salary.SalaryQueryApi;
import com.example.structure.dddclassicmultimodule.api.salary.dto.SalaryProfileDTO;
import com.example.structure.dddclassicmultimodule.biz.salary.api.SalaryQueryHandler;
import com.example.structure.dddclassicmultimodule.biz.salary.api.cmd.GetSalaryProfileCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 薪资档案契约实现：把 biz 档案结果转换为对外 DTO。
 */
@Component
@RequiredArgsConstructor
public class SalaryQueryFacade implements SalaryQueryApi {

    private final SalaryQueryHandler salaryQueryHandler;

    private final SalaryDtoConverter salaryDtoConverter;

    @Override
    public ApiResponse<SalaryProfileDTO> getProfile(Long userId) {
        return ApiResponse.ok(salaryDtoConverter.toDto(salaryQueryHandler.getProfile(new GetSalaryProfileCmd(userId))));
    }
}
