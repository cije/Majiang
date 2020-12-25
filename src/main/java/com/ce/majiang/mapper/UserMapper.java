package com.ce.majiang.mapper;

import com.ce.majiang.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

}
