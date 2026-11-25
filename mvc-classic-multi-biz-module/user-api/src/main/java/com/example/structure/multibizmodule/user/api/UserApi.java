package com.example.structure.multibizmodule.user.api;

/**
 * 用户域对外契约：其他模块只能通过本接口访问用户能力。
 */
public interface UserApi {

    /**
     * 按主键查询用户摘要。
     *
     * @param id 用户主键
     * @return 用户摘要
     */
    UserBrief getById(Long id);
}
