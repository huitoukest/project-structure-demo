package com.example.structure.dddclassicmultimodule.dal.api.user.qry;

import com.example.structure.dddclassicmultimodule.model.value.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询条件。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQry {

    /** 用户标识。 */
    private UserId id;
}
