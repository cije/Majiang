package com.ce.majiang.mapper;

import com.ce.majiang.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author c__e
 * @date 2020/12/24 21:03
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where token=#{token} limit 1")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id} limit 1")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{accountId} limit 1")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    Integer updateByAccountId(User user);

    @Insert("insert into user(account_id,name,token,gmt_created,gmt_modified,bio,avatar_url) " +
            "values(#{accountId},#{name},#{token},#{gmtCreated},#{gmtModified},#{bio},#{avatarUrl})")
    Integer insert(User user);
}
