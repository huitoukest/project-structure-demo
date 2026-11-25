package com.example.structure.packagesplit.modules.user.vo;

import com.example.structure.packagesplit.modules.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户接口响应对象：含 orgId，与 profile 的 ProfileUserVO 区分。
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
     * entity → VO 映射。
     *
     * @param user 用户行对象
     * @return 用户响应对象
     */
    public static UserVO from(User user) {
        return new UserVO(user.getId(), user.getName(), user.getOrgId());
    }
}
