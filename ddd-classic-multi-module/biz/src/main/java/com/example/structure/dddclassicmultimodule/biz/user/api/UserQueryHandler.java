package com.example.structure.dddclassicmultimodule.biz.user.api;

import com.example.structure.dddclassicmultimodule.biz.user.api.cmd.GetUserCmd;
import com.example.structure.dddclassicmultimodule.biz.user.api.result.UserResult;

/**
 * 用户上下文入口。
 */
public interface UserQueryHandler {

    UserResult getUser(GetUserCmd cmd);
}
