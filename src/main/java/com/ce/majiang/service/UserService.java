package com.ce.majiang.service;

import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @author c__e
 * @date 2020/12/25 9:22
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(User user) {
        User byAccountId = userMapper.findByAccountId(user.getAccountId());
        if (ObjectUtils.isEmpty(byAccountId)) {
            userMapper.insert(user);
        } else {
            userMapper.updateByAccountId(user);
        }
    }

    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

}
