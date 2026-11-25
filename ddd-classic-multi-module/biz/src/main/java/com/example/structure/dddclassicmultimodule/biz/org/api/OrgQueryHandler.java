package com.example.structure.dddclassicmultimodule.biz.org.api;

import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgCmd;
import com.example.structure.dddclassicmultimodule.biz.org.api.cmd.GetOrgTreeCmd;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgNodeResult;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgResult;
import java.util.List;

/**
 * 部门上下文入口。
 */
public interface OrgQueryHandler {

    OrgResult getById(GetOrgCmd cmd);

    List<OrgNodeResult> getTree(GetOrgTreeCmd cmd);
}
