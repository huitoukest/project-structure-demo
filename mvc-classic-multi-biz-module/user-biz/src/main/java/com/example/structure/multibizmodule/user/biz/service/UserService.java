package com.example.structure.multibizmodule.user.biz.service;

import com.example.structure.multibizmodule.common.constant.ErrorCode;
import com.example.structure.multibizmodule.common.exception.BizException;
import com.example.structure.multibizmodule.user.api.UserApi;
import com.example.structure.multibizmodule.user.api.UserBrief;
import com.example.structure.multibizmodule.user.biz.dao.UserDao;
import com.example.structure.multibizmodule.user.biz.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户域实现：实现 user-api 的对外契约，内部访问 UserDao。
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserApi {

    private final UserDao userDao;

    /**
     * 按主键查询用户，未命中转 404 业务异常。
     *
     * @param id 用户主键
     * @return 用户摘要
     */
    @Override
    public UserBrief getById(Long id) {
        User user = userDao.findById(id);
        if (user == null) {
            throw new BizException(ErrorCode.USER_NOT_FOUND, "用户不存在: " + id);
        }
        return new UserBrief(user.getId(), user.getName(), user.getOrgId());
    }
}
