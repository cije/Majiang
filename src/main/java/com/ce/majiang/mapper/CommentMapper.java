package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author c__e
 * @date 2021/1/7 10:10
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
