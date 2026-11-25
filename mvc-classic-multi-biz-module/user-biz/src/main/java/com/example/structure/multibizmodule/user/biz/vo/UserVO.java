package com.example.structure.multibizmodule.user.biz.vo;

import com.example.structure.multibizmodule.user.api.UserBrief;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户接口响应对象：含 orgId。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    /** 用户主键。 */
    private Long id;

    /** 姓名。 */
    private String name;

    /** 所属部门主键。 */
    private Long orgId;

    /**
     * api DTO → VO 映射。
     *
     * @param brief 用户摘要
     * @return 用户响应对象
     */
    public static UserVO from(UserBrief brief) {
        return new UserVO(brief.getId(), brief.getName(), brief.getOrgId());
    }
}
