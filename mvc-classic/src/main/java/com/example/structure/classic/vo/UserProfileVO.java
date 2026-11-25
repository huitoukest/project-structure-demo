package com.example.structure.classic.vo;

import com.example.structure.classic.model.UserProfileModel;
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
    private OrgVO org;

    /** 月薪（元）。 */
    private long salary;

    /**
     * model → VO 映射。
     *
     * @param model 档案聚合模型
     * @return 档案响应对象
     */
    public static UserProfileVO from(UserProfileModel model) {
        return new UserProfileVO(
            ProfileUserVO.from(model.getUser()),
            OrgVO.from(model.getOrg()),
            model.getSalary());
    }
}
