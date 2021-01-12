package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author c__e
 * @date 2021/1/7 10:10
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 更新 commentCount
     *
     * @param id 待更新的comment
     * @return 影响的条目
     */
    @Update("update comment set comment_count =comment_count+1 where id = #{id}")
    Integer updateCommentCountById(@Param("id") Long id);

}
