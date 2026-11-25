package com.example.structure.multibizmodule.salary.biz.vo;

import com.example.structure.multibizmodule.salary.api.SalaryProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户档案响应对象：user 仅 id/name，org 仅 id/name，salary 为月薪（元）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileVO {

    /** 档案用户。 */
    private ProfileUserVO user;

    /** 所属部门。 */
    private ProfileOrgVO org;

    /** 月薪（元）。 */
    private long salary;

    /**
     * api DTO → VO 映射。
     *
     * @param profile 档案信息
     * @return 档案响应对象
     */
    public static UserProfileVO from(SalaryProfile profile) {
        return new UserProfileVO(
            new ProfileUserVO(profile.getUserId(), profile.getUserName()),
            new ProfileOrgVO(profile.getOrgId(), profile.getOrgName()),
            profile.getSalary());
    }
}
