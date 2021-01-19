package com.ce.majiang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ce.majiang.dto.QuestionDTO;
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
    @Select("select id,title,gmt_created,gmt_modified,creator,comment_count,view_count,like_count,tag from question order by gmt_created desc")
    List<Question> list();

    /**
     * 分页查找所有问题
     *
     * @param offset offset
     * @param size   size
     * @return 分页问题数据
     */
    @Select("select id,title,gmt_created,gmt_modified,creator,comment_count,view_count,like_count,tag from question order by gmt_created desc limit #{offset},#{size}")
    List<Question> pageList(@Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 分页搜索查找所有问题
     *
     * @param search 搜索词
     * @param offset offset
     * @param size   size
     * @return 分页问题数据
     */
    @Select("select id,title,gmt_created,gmt_modified,creator,comment_count,view_count,like_count,tag from question where title REGEXP(REPLACE(#{search},' ','|'))  order by gmt_created desc limit #{offset},#{size}")
    List<Question> pageSearchList(@Param("search") String search, @Param("offset") Integer offset, @Param("size") Integer size);


    /**
     * 查找当前用户的问题
     *
     * @param userId 用户Id
     * @param offset offset
     * @param size   size
     * @return 当前用户的问题
     */
    @Select("select id,title,gmt_created,gmt_modified,creator,comment_count,view_count,like_count,tag from question where creator = #{userId} order by gmt_created desc limit #{offset},#{size} ")
    List<Question> pageListByUserId(@Param("userId") Long userId, @Param("offset") Integer offset, @Param("size") Integer size);


    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id}")
    Integer updateQuestion(Question question);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    Integer updateViewCountById(@Param("id") Long id);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    Integer updateCommentCountById(Question question);

    /**
     * 查询相关标签的问题 除过当前问题
     */
    @Select("select * from question where id != #{id} and tag REGEXP(REPLACE(#{tag},',','|')) limit 15")
    List<Question> selectRelatedByTag(@Param("id") Long id, @Param("tag") String tag);

    /**
     * 查找阅读数前15的问题
     */
    @Select("select * from question order by view_count desc limit 15")
    List<Question> selectAllOrderByViewCountDesc();

    /**
     * @param search
     * @return search总数
     */
    @Select("select count(*) from question where title REGEXP(REPLACE(#{search},' ','|'))")
    Integer selectCountBySearch(@Param("search") String search);
}
