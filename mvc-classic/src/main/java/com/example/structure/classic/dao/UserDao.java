package com.example.structure.classic.dao;

import com.example.structure.classic.entity.User;

/**
 * 用户存储访问接口，真实项目中由 MyBatis Mapper 实现。
 */
public interface UserDao {

    /**
     * 按主键查询用户。
     *
     * @param id 用户主键
     * @return 用户行对象，未命中时返回 null
     */
    User findById(Long id);
}
