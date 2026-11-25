package com.example.structure.packagesplit.modules.user.service;

import com.example.structure.packagesplit.modules.user.entity.User;

/**
 * 用户领域服务。
 */
public interface UserService {

    /**
     * 按主键查询用户，不存在时抛业务异常。
     *
     * @param id 用户主键
     * @return 用户行对象
     */
    User findById(Long id);
}
