package com.example.structure.multibizmodule.user.biz.dao.mem;

import com.example.structure.multibizmodule.user.biz.dao.UserDao;
import com.example.structure.multibizmodule.user.biz.entity.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * 用户 DAO 内存实现：固定种子数据，重启重置。
 */
@Repository
public class InMemoryUserDao implements UserDao {

    private final Map<Long, User> users = new ConcurrentHashMap<>();

    public InMemoryUserDao() {
        users.put(1L, new User(1L, "张三", 1L));
        users.put(2L, new User(2L, "李四", 2L));
    }

    /**
     * 按主键查询用户，未命中返回 null。
     *
     * @param id 用户主键
     * @return 用户行对象
     */
    @Override
    public User findById(Long id) {
        return users.get(id);
    }
}
