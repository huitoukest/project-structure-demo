package com.example.structure.packagesplit.modules.salary.vo;

import com.example.structure.packagesplit.modules.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 档案中的用户视图：仅 id/name，不暴露 orgId（与 UserVO 的差异是刻意的用例裁剪）。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUserVO {

    /** 用户主键。 */
    private Long id;

    /** 姓名。 */
    private String name;

    /**
     * entity → VO 映射。
     *
     * @param user 用户行对象
     * @return 档案用户视图
     */
    public static ProfileUserVO from(User user) {
        return new ProfileUserVO(user.getId(), user.getName());
    }
}
