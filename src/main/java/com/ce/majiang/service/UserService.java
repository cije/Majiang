package com.ce.majiang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.User;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.result.exception.CustomizeException;
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
        User byAccountId = userMapper.selectOne(new QueryWrapper<User>().eq("account_id", user.getAccountId()).last("limit 1"));
        if (ObjectUtils.isEmpty(byAccountId)) {
            userMapper.insert(user);
        } else {
            Integer updated = userMapper.updateByAccountId(user);
            if (updated.equals(0)) {
                throw new CustomizeException(ResultStatus.UPDATE_USER_ERROR);
            }
        }
    }

    public User findByToken(String token) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("token", token).last("limit 1"));
    }

}
