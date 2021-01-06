package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 查找所有问题
     *
     * @return 所有问题集合
     */
    @Select("select * from question")
    List<Question> list();

    /**
     * 分页查找所有问题
     *
     * @param offset offset
     * @param size   size
     * @return 分页问题数据
     */
    @Select("select * from question limit #{offset},#{size}")
    List<Question> pageList(@Param("offset") Integer offset, @Param("size") Integer size);


    /**
     * 查找当前用户的问题
     *
     * @param userId 用户Id
     * @param offset offset
     * @param size   size
     * @return 当前用户的问题
     */
    @Select("select * from question where creator = #{userId} limit #{offset},#{size} ")
    List<Question> pageListByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);


    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id}")
    Integer updateQuestion(Question question);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    Integer updateViewCountById(@Param("id") Integer id);
}
