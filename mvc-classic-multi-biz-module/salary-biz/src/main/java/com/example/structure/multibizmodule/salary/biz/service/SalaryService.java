package com.example.structure.multibizmodule.salary.biz.service;

import com.example.structure.multibizmodule.common.constant.ErrorCode;
import com.example.structure.multibizmodule.common.exception.BizException;
import com.example.structure.multibizmodule.org.api.OrgApi;
import com.example.structure.multibizmodule.org.api.OrgBrief;
import com.example.structure.multibizmodule.salary.api.SalaryApi;
import com.example.structure.multibizmodule.salary.api.SalaryProfile;
import com.example.structure.multibizmodule.salary.biz.dao.SalaryDao;
import com.example.structure.multibizmodule.salary.biz.entity.SalaryRecord;
import com.example.structure.multibizmodule.user.api.UserApi;
import com.example.structure.multibizmodule.user.api.UserBrief;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 薪资域实现：通过 user-api/org-api 契约跨域取值，不依赖其他 biz 模块实现。
 */
@Service
@RequiredArgsConstructor
public class SalaryService implements SalaryApi {

    private final UserApi userApi;
    private final OrgApi orgApi;
    private final SalaryDao salaryDao;

    /**
     * 组装用户档案，任一步未命中转对应 404 业务异常。
     *
     * @param userId 用户主键
     * @return 档案信息
     */
    @Override
    public SalaryProfile getProfile(Long userId) {
        UserBrief user = userApi.getById(userId);
        Long orgId = user.getOrgId();
        if (orgId == null) {
            throw new BizException(ErrorCode.ORG_NOT_FOUND, "部门不存在: " + orgId);
        }
        OrgBrief org = orgApi.getById(orgId);
        SalaryRecord salary = salaryDao.findByUserId(userId);
        if (salary == null) {
            throw new BizException(ErrorCode.SALARY_NOT_FOUND, "薪资记录不存在: " + userId);
        }
        return new SalaryProfile(userId, user.getName(), orgId, org.getName(), salary.getAmount());
    }
}
