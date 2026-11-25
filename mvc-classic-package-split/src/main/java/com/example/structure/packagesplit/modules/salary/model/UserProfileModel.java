package com.example.structure.packagesplit.modules.salary.model;

import com.example.structure.packagesplit.modules.org.entity.Org;
import com.example.structure.packagesplit.modules.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户档案聚合模型：service 层把多个 DAO 的结果组装为业务模型。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileModel {

    /** 用户行对象。 */
    private User user;

    /** 所属部门行对象。 */
    private Org org;

    /** 月薪（元）。 */
    private long salary;
}
