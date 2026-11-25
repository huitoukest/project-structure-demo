package com.example.structure.packagesplit.modules.user.service.impl;

import com.example.structure.packagesplit.common.constant.ErrorCode;
import com.example.structure.packagesplit.common.exception.BizException;
import com.example.structure.packagesplit.modules.user.dao.UserDao;
import com.example.structure.packagesplit.modules.user.entity.User;
import com.example.structure.packagesplit.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现。
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * 按主键查询用户，未命中转 404 业务异常。
     *
     * @param id 用户主键
     * @return 用户行对象
     */
    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new BizException(ErrorCode.USER_NOT_FOUND, "用户不存在: " + id);
        }
        return user;
    }
}
