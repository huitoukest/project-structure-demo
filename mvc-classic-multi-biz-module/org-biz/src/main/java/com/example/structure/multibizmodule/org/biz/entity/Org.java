package com.example.structure.multibizmodule.org.biz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门表行对象；parentId 为空表示根部门。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Org {

    /** 主键。 */
    private Long id;

    /** 部门名。 */
    private String name;

    /** 父部门主键，根部门为 null。 */
    private Long parentId;
}
