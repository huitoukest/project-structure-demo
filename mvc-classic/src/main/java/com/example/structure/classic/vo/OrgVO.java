package com.example.structure.classic.vo;

import com.example.structure.classic.entity.Org;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门响应对象：仅 id/name；profile.org 复用本对象。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgVO {

    /** 部门主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /**
     * entity → VO 映射。
     *
     * @param org 部门行对象
     * @return 部门响应对象
     */
    public static OrgVO from(Org org) {
        return new OrgVO(org.getId(), org.getName());
    }
}
