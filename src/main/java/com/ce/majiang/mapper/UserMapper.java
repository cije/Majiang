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

    @Insert("insert into user(account_id,name,token,bio,avatar_url,gmt_create,gmt_modified)" +
            " values(#{accountId},#{name},#{token},#{bio},#{avatarUrl},#{gmtCreated},#{gmtModified})")
    public void insert(User user);


    @Select("select * from user where token=#{token} limit 1")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id} limit 1")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{accountId} limit 1")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},bio=#{bio},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    void updateByAccountId(User user);
}
