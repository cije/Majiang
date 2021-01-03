package com.ce.majiang.mapper;

/**
 * @author c__e
 * @date 2020/12/25 11:25
 */

import com.ce.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    /**
     * 插入问题
     *
     * @param question 待插入的问题
     */
    @Insert("insert into question(title,description,tag,creator,gmt_created,gmt_modified)" +
            " values(#{title},#{description},#{tag},#{creator},#{gmtCreated},#{gmtModified})")
    void insertQuestion(Question question);

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
     * 计算问题总数
     *
     * @return 问题总数
     */
    @Select("select count(1) from question")
    Integer count();

    /**
     * 根据用户Id计算问题总数
     *
     * @return 用户的问题总数
     */
    @Select("select count(1) from question where creator= #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

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

    @Select("select * from question where id = #{id} limit 1")
    Question findById(@Param("id") Integer id);
}