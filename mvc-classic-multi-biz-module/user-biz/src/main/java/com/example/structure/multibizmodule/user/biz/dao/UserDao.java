package com.example.structure.multibizmodule.user.biz.dao;

import com.example.structure.multibizmodule.user.biz.entity.User;

/**
 * 用户存储访问接口。
 */
public interface UserDao {

    /**
     * 按主键查询用户。
     *
     * @param id 用户主键
     * @return 用户行对象，未命中返回 null
     */
    User findById(Long id);
}
