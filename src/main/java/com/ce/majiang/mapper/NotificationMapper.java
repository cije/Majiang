package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.model.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author c__e
 * @date 2021/1/13 14:52
 */
@Mapper
@Repository
public interface NotificationMapper extends BaseMapper<Notification> {
    /**
     * 分页查询未读的通知
     */
    @Select("select * from notification where receiver = #{userId} order by gmt_created desc limit #{offset},#{size}")
    List<Notification> pageListByUserId(@Param("userId") Long userId, @Param("offset") Integer offset, @Param("size") Integer size);
}
