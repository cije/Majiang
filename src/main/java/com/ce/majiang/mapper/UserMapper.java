package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author c__e
 * @date 2020/12/24 21:03
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where account_id=#{accountId} limit 1")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    Integer updateByAccountId(User user);

}
