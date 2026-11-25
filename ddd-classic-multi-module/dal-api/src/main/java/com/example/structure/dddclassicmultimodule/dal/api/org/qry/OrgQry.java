package com.example.structure.dddclassicmultimodule.dal.api.org.qry;

import com.example.structure.dddclassicmultimodule.model.value.OrgId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部门查询条件：parentId 为 null 表示查询全部部门。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgQry {

    /** 父部门标识。 */
    private OrgId parentId;
}
