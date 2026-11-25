package com.example.structure.packagesplit.modules.salary.service.impl;

import com.example.structure.packagesplit.common.constant.ErrorCode;
import com.example.structure.packagesplit.common.exception.BizException;
import com.example.structure.packagesplit.modules.org.dao.OrgDao;
import com.example.structure.packagesplit.modules.org.entity.Org;
import com.example.structure.packagesplit.modules.salary.dao.SalaryDao;
import com.example.structure.packagesplit.modules.salary.entity.SalaryRecord;
import com.example.structure.packagesplit.modules.salary.model.UserProfileModel;
import com.example.structure.packagesplit.modules.salary.service.SalaryService;
import com.example.structure.packagesplit.modules.user.dao.UserDao;
import com.example.structure.packagesplit.modules.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户档案服务实现：依次校验用户、部门、薪资记录后组装聚合模型。
 */
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final UserDao userDao;
    private final OrgDao orgDao;
    private final SalaryDao salaryDao;

    /**
     * 组装用户档案，任一步未命中转对应 404 业务异常。
     *
     * @param userId 用户主键
     * @return 档案聚合模型
     */
    @Override
    public UserProfileModel profile(Long userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new BizException(ErrorCode.USER_NOT_FOUND, "用户不存在: " + userId);
        }
        Long orgId = user.getOrgId();
        if (orgId == null) {
            throw new BizException(ErrorCode.ORG_NOT_FOUND, "部门不存在: " + orgId);
        }
        Org org = orgDao.findById(orgId);
        if (org == null) {
            throw new BizException(ErrorCode.ORG_NOT_FOUND, "部门不存在: " + orgId);
        }
        SalaryRecord salary = salaryDao.findByUserId(userId);
        if (salary == null) {
            throw new BizException(ErrorCode.SALARY_NOT_FOUND, "薪资记录不存在: " + userId);
        }
        return new UserProfileModel(user, org, salary.getAmount());
    }
}
